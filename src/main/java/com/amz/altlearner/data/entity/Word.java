package com.amz.altlearner.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@DynamoDBTable(tableName = "words")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Word {

    @DynamoDBHashKey private String word;
    @DynamoDBAttribute private List<String> resources;
    @DynamoDBAttribute private int type;

    public Word() {
    }

    public Word(String word, List<String> resources, int type) {
        this.word = word;
        this.resources = resources;
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
