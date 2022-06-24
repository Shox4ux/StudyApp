package com.example.dictionary.VocabularyShop.Complex.Comlex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter.B1_Adapter;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Data.B1_data;

public class B1_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    B1_data b1_data = new B1_data(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1);

        recyclerView = findViewById(R.id.re_word_list_b1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(new B1_Adapter(this,b1_data.getWords_b1()));
    }
}