package com.sachi.railapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    Activity activity;

    ArrayList<CollegeDataClass> collegeDataClasses = new ArrayList<>();
    ListView listView;
    CustomListAdapter customListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listC);
        activity = this;
        loadData();
    }

    private void loadData() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("http://universities.hipolabs.com/search?country=India")
                .method("GET",null)
                .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("API Responce","Failed "+ e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Log.d("API Responce","Success");
                    String respo =response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(respo);

                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String country = jsonObject.getString("domains");
                            JSONArray jsonArrayDomen = new JSONArray(country);
                            String domen;
                            String name = jsonObject.getString("name");
                            String state = jsonObject.getString("state-province");
                            for (int j = 0;j<jsonArrayDomen.length();j++){
                                JSONObject jsonObject1 = jsonArrayDomen.getJSONObject(j);
                                 domen = jsonObject1.getString("domains");
                                collegeDataClasses.add(new CollegeDataClass(domen,name,state));
                            }




                            //Log.d("Api Responce Data","Country:-"+domen);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customListAdapter = new CustomListAdapter(activity,collegeDataClasses);
                                listView.setAdapter(customListAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
    }


}
