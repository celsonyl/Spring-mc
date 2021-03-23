package com.celso.springmc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucket;

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    public void uploadFile(String path){
        try {
            File file = new File(path);
            log.info("Indo..");
            amazonS3.putObject(new PutObjectRequest(bucket, "teste.jpg", file));
            log.info("Foi!");

        }catch (AmazonServiceException e){
            log.info("Amazon Exception " + e.getErrorMessage());
            log.info("Status Code "+e.getErrorCode());

        }catch (AmazonClientException e){
            log.info("Amazon Cliente Exception " +e.getMessage());
        }
    }
}
