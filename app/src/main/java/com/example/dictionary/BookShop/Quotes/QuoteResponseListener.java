package com.example.dictionary.BookShop.Quotes;

import android.hardware.lights.LightsManager;

import java.util.List;

public interface QuoteResponseListener {

    void onFetchQuotes(List<QuoteResponse> response, String message);
    void onFailure(String message);

}
