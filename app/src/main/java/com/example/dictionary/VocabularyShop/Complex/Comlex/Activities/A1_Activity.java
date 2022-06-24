package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.A1_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.A1_data;

public class A1_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    A1_data data =new A1_data(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        recyclerView = findViewById(R.id.re_word_list_a1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(new A1_Adapter(this,data.getWords_a1()));


    }


}