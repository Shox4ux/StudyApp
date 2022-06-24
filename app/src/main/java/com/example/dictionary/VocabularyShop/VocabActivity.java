package com.example.dictionary.VocabularyShop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.R;

public class VocabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);
    }


//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.image_vocab_card:
//                Intent netIntent = new Intent(getApplicationContext(),ImageVocabActivity.class);
//                startActivity(netIntent);
//                break;
//            case R.id.level_vocab_card:
//                Intent etIntent = new Intent(getApplicationContext(), ComplexVocabularyActivity.class);
//                startActivity(etIntent);
//                break;
//
//        }
//    }

}