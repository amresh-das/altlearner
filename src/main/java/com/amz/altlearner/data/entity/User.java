package com.amz.altlearner.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;

@DynamoDBTable(tableName = "user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
    @DynamoDBAttribute private String provider;
    @DynamoDBAttribute private String name;
    @DynamoDBHashKey private String email;

    public User() {
    }

    public User(String provider, String name, String email) {
        this.provider = provider;
        this.name = name;
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
