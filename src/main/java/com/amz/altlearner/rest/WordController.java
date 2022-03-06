package com.amz.altlearner.rest;

import com.amz.altlearner.data.repos.WordRepo;
import com.amz.altlearner.language.english.word.Type;
import com.amz.altlearner.rest.model.Word;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("words")
public class WordController {
    private final WordRepo wordRepo;

    public WordController(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }

    @GetMapping
    public List<Word> getWords() {
        return wordRepo.getAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @GetMapping("/{word}")
    public Word getWord(@PathVariable("word") final String word) {
        return toModel(wordRepo.get(word));
    }

    @PutMapping
    public Word addWord(@RequestBody final Word word) {
        return toModel(wordRepo.save(toEntity(word)));
    }

    @PostMapping("/{word}")
    public void updateWord(@PathVariable("word") final String word, @RequestBody final Word wordBody) {
        wordRepo.update(word, toEntity(wordBody));
    }

    @DeleteMapping("/{word}")
    public void deleteWord(@PathVariable("word") final String word) {
        wordRepo.delete(word);
    }

    private List<Type> toTypes(final int type) {
        return Arrays.stream(Type.values()).filter(t -> t.is(type)).collect(Collectors.toList());
    }

    private int fromTypes(List<Type> types) {
        int t = 0;
        for (final Type tp : types) {
            t = tp.addType(t);
        }
        return t;
    }

    private com.amz.altlearner.data.entity.Word toEntity(@RequestBody Word word) {
        return new com.amz.altlearner.data.entity.Word(word.getWord(), word.getResources(), fromTypes(word.getTypes()));
    }

    private Word toModel(com.amz.altlearner.data.entity.Word w) {
        return new Word(w.getWord(), w.getResources(), toTypes(w.getType()));
    }

}
