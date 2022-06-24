package com.example.dictionary.VocabularyShop.Complex.Comlex.Data;

import android.content.Context;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Model.Word;

import java.util.ArrayList;

public class B1_data {
    ArrayList<Word> words_b1;
    Context context;

    public B1_data(Context context) {
        this.context = context;
    }

    public ArrayList<Word> getWords_b1() {
        words_b1 = new ArrayList<>();
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_1),context.getResources().getString(R.string.a1transcription_1),context.getResources().getString(R.string.a1translation_1),context.getResources().getString(R.string.a1example_1), context.getResources().getString(R.string.a1uz_1)));
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_2),context.getResources().getString(R.string.a1transcription_2),context.getResources().getString(R.string.a1translation_2),context.getResources().getString(R.string.a1example_2), context.getResources().getString(R.string.a1uz_2)));
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_3),context.getResources().getString(R.string.a1transcription_3),context.getResources().getString(R.string.a1translation_3),context.getResources().getString(R.string.a1example_3), context.getResources().getString(R.string.a1uz_3)));
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_4),context.getResources().getString(R.string.a1transcription_4),context.getResources().getString(R.string.a1translation_4),context.getResources().getString(R.string.a1example_4), context.getResources().getString(R.string.a1uz_4)));
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_5),context.getResources().getString(R.string.a1transcription_5),context.getResources().getString(R.string.a1translation_5),context.getResources().getString(R.string.a1example_5), context.getResources().getString(R.string.a1uz_5)));
        words_b1.add(new Word(context.getResources().getString(R.string.a1word_6),context.getResources().getString(R.string.a1transcription_6),context.getResources().getString(R.string.a1translation_6),context.getResources().getString(R.string.a1example_6), context.getResources().getString(R.string.a1uz_6)));



        return words_b1;
    }
}
