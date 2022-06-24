package com.example.dictionary.BookShop.Quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.dictionary.R;

import java.util.List;

public class QuoteActivity extends AppCompatActivity implements CopyListener{
    RecyclerView recyclerView;
    RetrofitManager manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        manager = new RetrofitManager(this);
        manager.getAllQuotes(listener);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.show();

        recyclerView = findViewById(R.id.recycler_quotes);



    }
    private final QuoteResponseListener listener = new QuoteResponseListener() {
        @Override
        public void onFetchQuotes(List<QuoteResponse> response, String message) {
            showData(response);
            dialog.dismiss();
        }

        @Override
        public void onFailure(String message) {
            dialog.dismiss();
            Toast.makeText(QuoteActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(List<QuoteResponse> response) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new QuoteRecyclerAdapter(this,response,this));
    }


    @Override
    public void onCopyClicked(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Copies data",text);
        clipboardManager.setPrimaryClip(data);
        Toast.makeText(QuoteActivity.this, "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
    }
}