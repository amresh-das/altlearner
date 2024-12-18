package com.amz.altlearner.data;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DyanmoDbConfig {
    @Value("${aws.dynamodb.endpoint}")
    private String dynamodbEndpoint;
    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public DynamoDBMapper dynamoDBMapper(final AWSStaticCredentialsProvider awsCredentials) {
        return new DynamoDBMapper(buildAmazonDynamoDB(awsCredentials));
    }

    private AmazonDynamoDB buildAmazonDynamoDB(final AWSStaticCredentialsProvider awsCredentials) {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint,awsRegion))
                .withCredentials(awsCredentials)
                .build();
    }
}
