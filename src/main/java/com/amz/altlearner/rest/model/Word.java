package com.amz.altlearner.rest.model;

import com.amz.altlearner.language.english.word.Type;

import java.util.List;

public class Word {
    private final String word;
    private final List<String> resources;
    private final List<Type> types;

    public Word(String word, List<String> resources, List<Type> types) {
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

    public List<Type> getTypes() {
        return types;
    }
}
