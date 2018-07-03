package edmt.dev.androidgridlayout.Retrofit;
import java.util.List;

import edmt.dev.androidgridlayout.Model.BookService;
import edmt.dev.androidgridlayout.Model.Brands;
import edmt.dev.androidgridlayout.Model.Category;
import edmt.dev.androidgridlayout.Model.Items;
import edmt.dev.androidgridlayout.Technician;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitClient
{




    @GET("technicians")
    Call<List<Technician>> getLostThings();

    @GET("categories")
    Call<List<Category>> getCategoriesList();

    @GET("items")
    Call<List<Items>> getItemList();

    @GET("brands")
    Call<List<Brands>> getItemBrands();

    @POST("bookservice")
    Call<BookService> postBookService(@Body BookService bookService);
}
