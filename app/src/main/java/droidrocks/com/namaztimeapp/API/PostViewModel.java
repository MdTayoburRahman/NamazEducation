package droidrocks.com.namaztimeapp.API;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class PostViewModel extends ViewModel {
    private static final String TAG = "RETROFIT_TAG";
    RequestQueue requestQueue;

    private MutableLiveData<List<PostDataModel>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<PostDataModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }


    public void MakeVolleyApiCall(Context context){
        List<PostDataModel> list = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET,
                Constance.API_BASE_URL,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String date = jsonObject.getString("date_gmt");
                            String title = jsonObject.getJSONObject("title").getString("rendered");
                            String content = jsonObject.getJSONObject("content").getString("rendered");
                            String image_url = jsonObject.getJSONObject("yoast_head_json").getJSONArray("og_image").getJSONObject(0).getString("url");
                            list.add(new PostDataModel(title,image_url,date,content));
                        }

                        if (list.size()>0){
                            listMutableLiveData.postValue(list);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "MakeVolleyApiCall: Catch "+e.getMessage());
                        listMutableLiveData.postValue(null);
                    }


                },error -> {
            error.printStackTrace();
            Log.d(TAG, "MakeVolleyApiCall: Error "+error.getMessage());
            listMutableLiveData.postValue(null);
        });
        requestQueue.add(request);
    }

/*    public void MakeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constance.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        service.getAllPostData().enqueue(new Callback<PostDataModel>() {
            @Override
            public void onResponse(@NonNull Call<PostDataModel> call, @NonNull Response<PostDataModel> response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                List<PostDataModel> list = Arrays.asList(gson.fromJson(response.toString(), PostDataModel[].class));
                listMutableLiveData.postValue(list);
                Log.d(TAG, "onResponse: "+response.code());
            }

            @Override
            public void onFailure(Call<PostDataModel> call, Throwable t) {
                listMutableLiveData.postValue(null);
                t.printStackTrace();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }*/

    public void MakeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constance.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        service.getAllPostData().enqueue(new Callback<List<PostDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostDataModel>> call, @NonNull Response<List<PostDataModel>> response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                List<PostDataModel> list = Arrays.asList(gson.fromJson(response.toString(), PostDataModel[].class));
                listMutableLiveData.postValue(list);
                Log.d(TAG, "onResponse: "+response.code());
            }

            @Override
            public void onFailure(@NonNull Call<List<PostDataModel>> call, @NonNull Throwable t) {
                listMutableLiveData.postValue(null);
                t.printStackTrace();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
    //?per_page=20
    public interface GitHubService {
        @GET("posts?per_page=2")
        Call<List<PostDataModel>> getAllPostData();

    }

}


