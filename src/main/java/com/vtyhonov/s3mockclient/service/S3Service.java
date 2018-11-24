package com.vtyhonov.s3mockclient.service;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface S3Service {

    List<Bucket> listBuckets();

    ListObjectsV2Result listFilesInBucket(String bucketName);

    Bucket createBucket(String bucketName);

    PutObjectResult putFileToBucket(String bucketName, String filePath);

    void deleteBucket(String bucketName);

    void deleteFileInBucket(String bucketName, String filePath);
}
