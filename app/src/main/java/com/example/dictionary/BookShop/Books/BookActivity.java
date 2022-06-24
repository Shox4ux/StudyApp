package com.example.dictionary.BookShop.Books;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.BookShop.Books.Models.Root;
import com.example.dictionary.R;

public class BookActivity extends AppCompatActivity {
    RetrofitManager manager;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        manager = new RetrofitManager(this);
        manager.getAllBooks(listener);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.show();


        recyclerView = findViewById(R.id.book_recycler);


    }

    private final BookResponseListener listener = new BookResponseListener() {
        @Override
        public void onFetchQuotes(Root response, String message) {
            showData(response);
            progressDialog.dismiss();
        }

        @Override
        public void onFailure(String message) {
            progressDialog.dismiss();
            Toast.makeText(BookActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };

    private void showData(Root response) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EBookAdapter(this,response.getItems()));
    }
}


























