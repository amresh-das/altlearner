package com.amz.altlearner.data.repos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amz.altlearner.data.entity.Login;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepo {

    private final DynamoDBMapper dynamoDBMapper;

    public LoginRepo(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Login save(final Login login) {
        dynamoDBMapper.save(login);
        return login;
    }

    public List<Login> getAll() {
        return new ArrayList<>(dynamoDBMapper.scan(Login.class, new DynamoDBScanExpression()));
    }

    public Login get(final String login) {
        return dynamoDBMapper.load(Login.class, login);
    }

    public void delete(final String login) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Login.class, login));
    }

    public void update(final String login, final Login loginValue) {
        dynamoDBMapper.save(loginValue,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("login",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(login)
                                )));
    }
}
