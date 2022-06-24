package com.example.dictionary.BookShop.Books;

import com.example.dictionary.BookShop.Books.Models.Root;

public interface BookResponseListener {
    void onFetchQuotes(Root response, String message);
    void onFailure(String message);
}
