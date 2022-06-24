package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.B2_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.B2_data;

public class B2_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    B2_data b2_data = new B2_data(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2);

        recyclerView = findViewById(R.id.re_word_list_b2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( new B2_Adapter(this,b2_data.getWords_b2()));
    }
}