package com.example.dictionary.BookShop.Books;

import android.content.Context;
import android.widget.Toast;

import com.example.dictionary.BookShop.Books.Models.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class RetrofitManager {


    Context context;
    OkHttpClient.Builder client;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://google-books.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitManager(Context context) {
        this.context = context;
    }
    public void makeInterceptor(){
        client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(interceptor);

    }


    public void getAllBooks(BookResponseListener listener){
        makeInterceptor();
        CallBooks callBooks = retrofit.create(CallBooks.class);
        Call<Root> call = callBooks.callEBooks();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Call is not successful", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetchQuotes(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

                listener.onFailure(t.getMessage());

            }
        });
    }


    private interface CallBooks{
        @Headers({
                "Accept: application/json",
                "x-rapidapi-host: google-books.p.rapidapi.com",
                "x-rapidapi-key: 956cc5331dmsh13346a2512e4033p11f0a5jsn0ee500747538"
        })
        @GET("volumes")
        Call<Root> callEBooks();
    }

}

