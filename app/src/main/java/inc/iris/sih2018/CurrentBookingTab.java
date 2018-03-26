package inc.iris.sih2018;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;

import inc.iris.sih2018.logic.Booking;
import inc.iris.sih2018.logic.BookingStatus;
import inc.iris.sih2018.logic.ParkingSlot;
import inc.iris.sih2018.logic.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBookingTab extends Fragment {


    private RecyclerView recyclerView;
    private Booking records[];
    public CurrentBookingTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking_tab, container, false);
        recyclerView=view.findViewById(R.id.recycler_booking);
        records=getBookingRecords();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new BookingAdapter(records,getActivity()));
        return view;
    }

    public Booking[] getBookingRecords() {
        long currentTime=Calendar.getInstance().getTimeInMillis();
        ParkingSlot slot=new ParkingSlot("1","BIT","Sindri","12212:111",400,1);
        Booking record=new Booking(currentTime,currentTime+60000,currentTime+60*60000,slot,"jh 01 aa 1234", BookingStatus.PARKED);
        Booking records[]={record,record};
        //TODO use volley to get records
       /* StringRequest request=new StringRequest(Request.Method.GET, "url", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(request);*/


       return records;
    }
}
