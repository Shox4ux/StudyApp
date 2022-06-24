package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.A2_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.A2_data;

public class A2_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    A2_data data =new A2_data(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        recyclerView = findViewById(R.id.re_word_list_a2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(new A2_Adapter(this,data.getWords_a2()));
    }
}