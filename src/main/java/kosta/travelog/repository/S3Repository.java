package kosta.travelog.repository;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public class S3Repository {
    private final AmazonS3 s3;
    private final String urlPrefix;
    private final String bucketName;

    public S3Repository() {
        s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        bucketName = "travelog-bucket";
        urlPrefix = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, Regions.AP_NORTHEAST_2.getName());
    }

    public String add(String fileName, InputStream imageStream, ObjectMetadata metadata) {
        s3.putObject(bucketName, fileName, imageStream, metadata);
        return urlPrefix + fileName;
    }
}
