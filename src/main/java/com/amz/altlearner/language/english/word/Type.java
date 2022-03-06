package com.amz.altlearner.language.english.word;

public enum Type {
    NOUN(1), // 00000001
    PRONOUN(2), // 00000010
    VERB(4), // 00000100
    ADJECTIVE(8), // 00001000
    ADVERB(16), // 00010000
    PREPOSITION(32), // 00100000
    CONJUNCTION(64), // 01000000
    INTERJECTION(128); // 10000000

    private final int value;
    Type(int value) {
        this.value = value;
    }

    public boolean is(final int number) {
        return (this.value & number) > 0;
    }

    public int addType(final int number) {
        return number | this.value;
    }
}
