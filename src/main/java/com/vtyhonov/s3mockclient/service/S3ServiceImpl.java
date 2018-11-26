package com.vtyhonov.s3mockclient.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.vtyhonov.s3mockclient.exceptions.BucketAlreadyExistsException;
import com.vtyhonov.s3mockclient.exceptions.BucketDoesNotExistException;
import com.vtyhonov.s3mockclient.exceptions.DownloadFileException;
import com.vtyhonov.s3mockclient.exceptions.FileAlreadyExistsException;
import com.vtyhonov.s3mockclient.exceptions.FileDoesNotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class S3ServiceImpl implements S3Service {

    private static final String TMP_DIR = "java.io.tmpdir";

    private final AmazonS3 s3Client;

    @Autowired
    public S3ServiceImpl(final AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public List<Bucket> listBuckets() {
        return s3Client.listBuckets();
    }

    public ListObjectsV2Result listFilesInBucket(final String bucketName) {
        checkBucketExists(bucketName);

        return s3Client.listObjectsV2(bucketName);
    }

    @Override
    public Resource getFileFromBucket(final String bucketName, final String filePath, final String targetFileName)
    {
        checkBucketExists(bucketName);
        checkFileExists(bucketName, filePath);

        try {
            S3ObjectInputStream initialStream = s3Client.getObject(bucketName, filePath).getObjectContent();
            Path targetFilePath = new File(System.getProperty(TMP_DIR) + File.separatorChar + targetFileName).toPath();

            Files.copy(
                initialStream,
                targetFilePath,
                StandardCopyOption.REPLACE_EXISTING);

            return new ByteArrayResource(Files.readAllBytes(targetFilePath));
        } catch (IOException e) {
            throw new DownloadFileException(e);
        }
    }

    public Bucket createBucket(final String bucketName) {
        if (s3Client.doesBucketExistV2(bucketName)) {
            throw new BucketAlreadyExistsException(String.format("Bucket %s already exists", bucketName));
        }

        return s3Client.createBucket(bucketName);
    }

    public PutObjectResult putFileToBucket(final String bucketName, final String filePath) {
        checkBucketExists(bucketName);

        if (s3Client.doesObjectExist(bucketName, filePath)) {
            throw new FileAlreadyExistsException(String.format("File %s already exists in bucket %s", filePath, bucketName));
        }

        File fileToExport = new File(filePath);
        return s3Client.putObject(bucketName, fileToExport.getName(), fileToExport);
    }

    public void deleteBucket(final String bucketName) {
        checkBucketExists(bucketName);

        s3Client.deleteBucket(bucketName);
    }

    public void deleteFileInBucket(final String bucketName, final String filePath) {
        checkBucketExists(bucketName);
        checkFileExists(bucketName, filePath);

        s3Client.deleteObject(bucketName, filePath);
    }

    private void checkBucketExists(final String bucketName) {
        if (!s3Client.doesBucketExistV2(bucketName)) {
            throw new BucketDoesNotExistException(String.format("Bucket %s doesn't exist", bucketName));
        }
    }

    private void checkFileExists(final String bucketName, final String filePath) {
        if (!s3Client.doesObjectExist(bucketName, filePath)) {
            throw new FileDoesNotExistException(String.format("File %s doesn't exist in bucket %s", filePath, bucketName));
        }
    }
}
