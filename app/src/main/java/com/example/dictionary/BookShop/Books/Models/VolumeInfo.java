package com.example.dictionary.BookShop.Books.Models;

import java.util.List;

public class VolumeInfo{
    public String title;
    public List<String> authors;
    public ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }
}