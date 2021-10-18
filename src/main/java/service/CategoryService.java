package service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import dto.Category;

public class CategoryService {
    @GET("categories/{id}")
    Call<Category> getCategoryById(@Path("id") Integer id) {
        return null;
    }

    @GET("categories")
    Call<Category> getAllCategories() {
        return null;
    }

    @GET("categories/{id}")
    Call<Category> getCategoryByStringId(@Path("id") String id) {
        return null;
    }
}
