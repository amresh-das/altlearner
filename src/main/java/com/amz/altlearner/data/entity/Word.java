package com.amz.altlearner.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "words")
public class Word {

    @DynamoDBHashKey
    private String word;
    @DynamoDBAttribute
    private List<String> resources;
    @DynamoDBAttribute
    private List<String> types;

    public Word() {
    }

    public Word(String word, List<String> resources, List<String> types) {
        this.word = word;
        this.resources = resources;
        this.types = types;
    }

    public String getWord() {
        return word;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
