package com.amz.altlearner.data.repos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amz.altlearner.data.entity.Word;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WordRepo {

    private final DynamoDBMapper dynamoDBMapper;

    public WordRepo(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Word save(final Word word) {
        dynamoDBMapper.save(word);
        return word;
    }

    public List<Word> getAll() {
        return new ArrayList<>(dynamoDBMapper.scan(Word.class, new DynamoDBScanExpression()));
    }

    public Word get(final String word) {
        return dynamoDBMapper.load(Word.class, word);
    }

    public void delete(final String word) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Word.class, word));
    }

    public void update(final String word, final Word Word) {
        dynamoDBMapper.save(Word,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("word",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(word)
                                )));
    }
}
