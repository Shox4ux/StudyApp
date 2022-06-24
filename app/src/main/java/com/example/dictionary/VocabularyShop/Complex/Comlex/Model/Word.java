package com.example.dictionary.VocabularyShop.Complex.Comlex.Model;

public class Word {
    String word;
    String transcription;
    String translation;
    String example;
    String uz;

    public Word(String word, String transcription, String translation, String example, String uz) {
        this.word = word;
        this.transcription = transcription;
        this.translation = translation;
        this.example = example;
        this.uz = uz;
    }

    public String getWord() {
        return word;
    }

    public String getTranscription() {
        return transcription;
    }

    public String getTranslation() {
        return translation;
    }

    public String getExample() {
        return example;
    }

    public String getUz() {
        return uz;
    }
}
