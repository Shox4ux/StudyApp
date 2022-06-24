package com.example.dictionary.BookShop.Books.Models;

import java.util.List;

public class Root {
        public String kind;
        public int totalItems;
        public List<Item> items;

        public String getKind() {
                return kind;
        }

        public int getTotalItems() {
                return totalItems;
        }

        public List<Item> getItems() {
                return items;
        }
}
