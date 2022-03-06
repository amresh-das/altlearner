package com.amz.altlearner.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;

@DynamoDBTable(tableName = "login")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Login {
    @DynamoDBHashKey private String id;
    @DynamoDBAttribute private String time;

    public Login() {
    }

    public Login(final String id, final String time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }
}
