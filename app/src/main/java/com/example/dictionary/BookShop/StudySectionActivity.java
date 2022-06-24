package com.example.dictionary.BookShop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dictionary.BookShop.Books.BookActivity;
import com.example.dictionary.BookShop.Quotes.QuoteActivity;
import com.example.dictionary.R;

public class StudySectionActivity extends AppCompatActivity {
    CardView bookCard,quoteCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        quoteCard = findViewById(R.id.quote_card);
        bookCard = findViewById(R.id.book_card);

        bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudySectionActivity.this, BookActivity.class));
            }
        });


        quoteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudySectionActivity.this, QuoteActivity.class));
            }
        });
    }

}