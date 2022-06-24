package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.C1_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.C1_data;

public class C1_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    C1_data c1_data = new C1_data(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        recyclerView = findViewById(R.id.re_word_list_c1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new C1_Adapter(c1_data.getWords_c1(),this));
    }
}