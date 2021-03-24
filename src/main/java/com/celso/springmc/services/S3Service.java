package com.celso.springmc.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.celso.springmc.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucket;

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    public URI uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        String contentType = multipartFile.getContentType();
        return uploadFile(inputStream,fileName,contentType);

    }

    public URI uploadFile(InputStream inputStream,String fileName,String contentType){
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            log.info("Enviando..");
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
            log.info("Foi!");

            return amazonS3.getUrl(bucket, fileName).toURI();
        }catch (URISyntaxException e){
            throw new FileException("Erro ao converter URL to URI");
        }

    }
}
