package inc.iris.sih2018;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inc.iris.sih2018.logic.Booking;
import inc.iris.sih2018.logic.BookingStatus;
import inc.iris.sih2018.logic.ParkingSlot;
import inc.iris.sih2018.logic.Parse;
import inc.iris.sih2018.logic.User;
import inc.iris.sih2018.logic.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBookingTab extends Fragment {

    private static final String TAG = "CurrentBookingTab";

    private RecyclerView recyclerView;
    private String url ="http://www.sih2018.esy.es/user_current.php";
    public CurrentBookingTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking_tab, container, false);
        recyclerView=view.findViewById(R.id.recycler_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //
        requestDate(User.user);
        return view;
    }
    private void requestDate(final String user)
    {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseResponse(response);
                //Toast.makeText(getActivity(), "res "+response, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: "+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<>();
                param.put("user",user);
                param.put("TYPE","CURRENT");
                return param;
            }
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }


    private void parseResponse(String response) {
        recyclerView.setAdapter(new BookingAdapter(Parse.getBooking(response),getActivity(),TAG));

    }

}
