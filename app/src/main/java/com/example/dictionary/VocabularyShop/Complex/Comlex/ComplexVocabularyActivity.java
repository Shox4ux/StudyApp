package com.example.dictionary.VocabularyShop.Complex.Comlex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.A1_Activity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.A2_Activity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.B1_Activity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.B2_Activity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.C1_Activity;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Activities.C2_Activity;

public class ComplexVocabularyActivity extends AppCompatActivity implements View.OnClickListener {
    CardView A1,A2,B1,B2,C1,C2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_complex);
        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        C1 =findViewById(R.id.C1);
        C2 = findViewById(R.id.C2);

        A1.setOnClickListener(this);
        A2.setOnClickListener(this);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        C1.setOnClickListener(this);
        C2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.A1:
                startActivity(new Intent(ComplexVocabularyActivity.this, A1_Activity.class));
                break;

            case R.id.A2:
                startActivity(new Intent(ComplexVocabularyActivity.this, A2_Activity.class));
                break;

            case R.id.B1:
                startActivity(new Intent(ComplexVocabularyActivity.this, B1_Activity.class));
                break;

            case R.id.B2:
                startActivity(new Intent(ComplexVocabularyActivity.this, B2_Activity.class));
                break;
            case R.id.C1:
                startActivity(new Intent(ComplexVocabularyActivity.this, C1_Activity.class));
                break;
            case R.id.C2:
                startActivity(new Intent(ComplexVocabularyActivity.this, C2_Activity.class));
                break;

        }
    }
}