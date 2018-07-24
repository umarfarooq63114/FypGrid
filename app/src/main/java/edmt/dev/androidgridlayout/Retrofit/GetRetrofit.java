package edmt.dev.androidgridlayout.Retrofit;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class GetRetrofit {

    /*public static final String BASE_URL = "http://192.168.0.39/crudwithapi/public/api/";
    static String token="";
    private static Retrofit retrofit;
    static {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        Retrofit.Builder builder1=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit =builder1.client(builder.build()).build();
    }
    public static Retrofit getRetrofit()
    {
        return retrofit;
    }*/






     private static Retrofit ourInstance;
    static String token="";

    public static Retrofit getInstance() {
        if (ourInstance == null) {
            OkHttpClient.Builder okhttp=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {

                    Request request=chain.request();

                    request.newBuilder().addHeader("Authorization","Bearer "+token);

                   return chain.proceed(request);

                }
            });

            ourInstance = new Retrofit.Builder()
                    .client(okhttp.build())
                    .baseUrl("http://192.168.10.4/crud/public/api/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return ourInstance;
    }

    private GetRetrofit() {
    }



}
