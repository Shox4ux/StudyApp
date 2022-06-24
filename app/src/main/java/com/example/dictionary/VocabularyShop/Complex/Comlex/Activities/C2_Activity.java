package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.C2_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.C2_data;

public class C2_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    C2_data c2_data = new C2_data(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c2);
        recyclerView = findViewById(R.id.re_word_list_c2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new C2_Adapter(c2_data.getWords_c2(),this));

    }
}