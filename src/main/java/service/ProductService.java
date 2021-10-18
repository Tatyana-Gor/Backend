package service;
import dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import dto.Category;
import java.util.List;

public class ProductService {
    @GET("products")
    Call<List<Product>> getProducts() {
        return null;
    }

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id) {
        return null;
    }

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest) {
        return null;
    }

    @PUT("products")
    Call<Product> updateProduct(@Body Product updateProductRequest) {
        return null;
    }

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id) {
        return null;
    }
}
