package edmt.dev.androidgridlayout.Retrofit;
import java.util.List;

import edmt.dev.androidgridlayout.Technician;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface RetrofitClient
{
    @GET("technicians")
    Call<List<Technician>> getLostThings();
}
