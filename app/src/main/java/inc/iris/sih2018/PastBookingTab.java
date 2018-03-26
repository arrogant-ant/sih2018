package inc.iris.sih2018;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import inc.iris.sih2018.logic.Booking;
import inc.iris.sih2018.logic.BookingStatus;
import inc.iris.sih2018.logic.ParkingSlot;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastBookingTab extends Fragment {


    private RecyclerView recyclerView;
    private Booking[] records;

    public PastBookingTab() {
        // Required empty public construct <string name="permission_rationale">Location permissions are needed for core functionality of PARKME!!!</string>or
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking_tab, container, false);
        recyclerView=view.findViewById(R.id.recycler_booking);
        records=getBookingRecords();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new BookingAdapter(records));
        return view;
    }

    public Booking[] getBookingRecords() {
        long currentTime= Calendar.getInstance().getTimeInMillis();
        ParkingSlot slot=new ParkingSlot("1","BIT","Sindri","12212:111",400,1);
        Booking record=new Booking(currentTime,currentTime+60000,currentTime+60*60000,slot,"jh 01 aa 1234", BookingStatus.COMPLETED);
        Booking records[]={record,record,record};
        return records;
    }
}

