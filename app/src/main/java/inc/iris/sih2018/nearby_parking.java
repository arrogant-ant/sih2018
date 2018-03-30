package inc.iris.sih2018;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by lappy on 3/26/2018.
 */

public class nearby_parking extends AppCompatActivity {
    private List<nearby_parking_bean> mParkings = new ArrayList<>();
    private nearby_parking_adapter mParkingAdapter;
    RecyclerView recyclerView;
    private Toolbar toolbar;
    RequestQueue queue;
    Context context=this;
    JSONObject details;
    String park_name;
    int avail_slots,cost;
    double latitude,longitude;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_parking);
        queue = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_booking);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mParkingAdapter = new nearby_parking_adapter(mParkings, this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://sih2018.esy.es/user_current.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++) {
                        details = (JSONObject) jsonArray.get(i);
                        park_name = details.getString("park_name");
                        avail_slots = details.getInt("available_slots");
                        latitude = details.getDouble("latitude");
                        longitude = details.getLong("longitude");
                        cost=details.getInt("cost");
                        nearby_parking_bean mybean1 = new nearby_parking_bean(park_name, "0.5 miles", avail_slots, 20);
                        mParkings.add(mybean1);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("user","jaya4svm@gmail.com");
                params.put("pass","abc");
                params.put("category","admin");
                return params;
            }
        };
        queue.add(stringRequest);




        nearby_parking_bean mybean2 = new nearby_parking_bean("abcg", "0.5 miles", "20", 20);
        mParkings.add(mybean2);
        nearby_parking_bean mybean3 = new nearby_parking_bean("akjankaj", "0.5 miles", "20", 20);
        mParkings.add(mybean3);
        nearby_parking_bean mybean4 = new nearby_parking_bean("sdjsbd", "0.5 miles", "20", 20);
        mParkings.add(mybean4);

        recyclerView.setAdapter(mParkingAdapter);
    }

}
