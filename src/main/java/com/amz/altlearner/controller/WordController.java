package com.amz.altlearner.controller;

import com.amz.altlearner.data.entity.Word;
import com.amz.altlearner.data.repos.WordRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {
    private final WordRepo wordRepo;

    public WordController(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }

    @GetMapping("/words")
    public List<Word> getWords() {
        return wordRepo.getAll();
    }
}
