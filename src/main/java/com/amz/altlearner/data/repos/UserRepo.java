package com.amz.altlearner.data.repos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amz.altlearner.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepo(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User save(final User User) {
        dynamoDBMapper.save(User);
        return User;
    }

    public List<User> getAll() {
        return new ArrayList<>(dynamoDBMapper.scan(User.class, new DynamoDBScanExpression()));
    }

    public User get(final String user) {
        return dynamoDBMapper.load(User.class, user);
    }

    public void delete(final String user) {
        dynamoDBMapper.delete(dynamoDBMapper.load(User.class, user));
    }

    public void update(final String user, final User userValue) {
        dynamoDBMapper.save(userValue,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("user",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(user)
                                )));
    }
}
