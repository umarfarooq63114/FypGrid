package edmt.dev.androidgridlayout.Retrofit;
import java.util.List;

import edmt.dev.androidgridlayout.Model.BookService;
import edmt.dev.androidgridlayout.Model.Brands;
import edmt.dev.androidgridlayout.Model.Category;
import edmt.dev.androidgridlayout.Model.Customer;
import edmt.dev.androidgridlayout.Model.Faults;
import edmt.dev.androidgridlayout.Model.Items;
import edmt.dev.androidgridlayout.Model.Login;
import edmt.dev.androidgridlayout.Model.MapPost;
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

    @POST("login/user")
    Call<Customer> login(@Body Login login);

    @GET("faults")
    Call<List<Faults>> getFaultList();

    @GET("customer")
    Call<List<Customer>> getCustomerList();

    @GET("brands")
    Call<List<Brands>> getBrandsList();






    @POST("register/user")
    Call<Customer> postCustomer(@Body Customer customer);

    @POST("bookservice")
    Call<BookService> postBookService(@Body BookService bookService);

    @POST("postnear")
    Call<List<Technician>> postMapParameters(@Body MapPost mapPost);



}
