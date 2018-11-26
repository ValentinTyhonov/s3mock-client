package com.vtyhonov.s3mockclient.controller;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.vtyhonov.s3mockclient.service.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/s3")
@Api(value = "AWS S3", tags = "AWS S3 API", description = "/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(final S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("list-buckets")
    @ApiOperation("List all buckets")
    public List<Bucket> listBuckets() {
        return s3Service.listBuckets();
    }

    @GetMapping("list-files")
    @ApiOperation("List all files in bucket")
    public ListObjectsV2Result listFilesInBucket(@ApiParam(value = "bucket name", required = true)
                                                 @RequestParam("bucket") final String bucketName) {
        return s3Service.listFilesInBucket(bucketName);
    }

    @GetMapping("file")
    @ApiOperation("Get file from S3")
    public ResponseEntity<Resource> fileDownload(@ApiParam(value = "bucket name", required = true)
                                                 @RequestParam("bucket") final String bucketName,
                                                 @ApiParam(value = "file path", required = true)
                                                 @RequestParam("file") final String filePath) {
        String[] pathParts = filePath.split("/");
        String targetFileName = pathParts[pathParts.length - 1];

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + targetFileName);

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(s3Service.getFileFromBucket(bucketName, filePath, targetFileName));
    }

    @PostMapping("bucket")
    @ApiOperation("Create new bucket")
    public Bucket createBucket(@ApiParam(value = "bucket name", required = true)
                               @RequestParam("bucket") final String bucketName) {
        return s3Service.createBucket(bucketName);
    }

    @PostMapping("file")
    @ApiOperation("Put new file in bucket")
    public PutObjectResult putFileToBucket(@ApiParam(value = "bucket name", required = true)
                                           @RequestParam("bucket") final String bucketName,
                                           @ApiParam(value = "file path", required = true)
                                           @RequestParam("file") final String filePath) {
        return s3Service.putFileToBucket(bucketName, filePath);
    }

    @DeleteMapping("bucket")
    @ApiOperation("Delete bucket")
    public void deleteBucket(@ApiParam(value = "bucket name", required = true)
                               @RequestParam("bucket") final String bucketName) {
        s3Service.deleteBucket(bucketName);
    }

    @DeleteMapping("file")
    @ApiOperation("Delete file in bucket")
    public void deleteFileInBucket(@ApiParam(value = "bucket name", required = true)
                                           @RequestParam("bucket") final String bucketName,
                                           @ApiParam(value = "file path", required = true)
                                           @RequestParam("file") final String filePath) {
        s3Service.deleteFileInBucket(bucketName, filePath);
    }
}
