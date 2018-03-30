package inc.iris.sih2018;

/**
 * Created by Vinay on 3/27/2018.
 */

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import inc.iris.sih2018.logic.Booking;
import inc.iris.sih2018.logic.User;

public class activity_time extends AppCompatActivity implements
        View.OnClickListener {

    Button entryDatePicker, entryTimePicker, exitDatePicker, exitTimePicker;
    EditText txtDate, txtTime,txtDate1,txtTime1;
    TextView charges;
    Button book,calculate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    RequestQueue queue;
    Long arrived,departed;
    Context context=this;
    Calendar arrival, departure;
    private static final String TAG = "activity_time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_parking);
        queue= Volley.newRequestQueue(this);
        Intent intent=getIntent();
        final String park_area=intent.getStringExtra("park_Area");
        final int cost=intent.getIntExtra("cost",0);
        final int available_slots=intent.getIntExtra("slots",0);
        entryDatePicker =(Button)findViewById(R.id.btn_date);
        entryTimePicker =(Button)findViewById(R.id.btn_time);
        exitDatePicker =(Button)findViewById(R.id.btn_date_out);
        exitTimePicker =(Button)findViewById(R.id.btn_time_out);
        calculate=findViewById(R.id.calculate);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        txtDate1=(EditText)findViewById(R.id.out_date);
        txtTime1=(EditText)findViewById(R.id.out_time);
        charges=findViewById(R.id.charges);
        book=findViewById(R.id.book);
        entryDatePicker.setOnClickListener(this);
        entryTimePicker.setOnClickListener(this);
        exitDatePicker.setOnClickListener(this);
        exitTimePicker.setOnClickListener(this);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrived= arrival.getTimeInMillis();
                departed=departure.getTimeInMillis();
               charges.setText(String.valueOf(Booking.getBookingCost(arrived,departed,cost)));
            }
        });
        arrival=Calendar.getInstance();
        departure=Calendar.getInstance();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (available_slots == 0) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Waiting booking confirmation");
                    builder.setMessage("Do you want to make a waiting booking?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //add string request to waiting php
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://sih2018.esy.es/book.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(context, response, Toast.LENGTH_LONG).show();


                            //charges.setText(response);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                            // charges.setText(error.getMessage());

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            arrived = arrival.getTimeInMillis();
                            departed = departure.getTimeInMillis();
                            String price = String.valueOf(Booking.getBookingCost(arrived, departed, cost));
                            Log.d(TAG, "getParams: " + arrived + " " + departed + " " + park_area + " " + User.user);
                            Map<String, String> params = new HashMap<>();
                            params.put("parkingname", park_area);
                            params.put("entry", String.valueOf(arrived));
                            params.put("exit", String.valueOf(departed));
                            params.put("username", User.user);
                            params.put("price", price);

                            return params;
                        }
                    };
                    queue.add(stringRequest);


                }
            }
        });
    }

    @Override
    public void onClick(View v) {



        if (v == entryDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            arrival.set(year,monthOfYear,dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);

            datePickerDialog.show();
        }
        if (v == exitDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate1.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            departure.set(year,monthOfYear,dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);

            datePickerDialog.show();
        }
        if (v == entryTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                            arrival.set(Calendar.HOUR_OF_DAY,hourOfDay);
                            arrival.set(Calendar.MINUTE,minute);
                        }
                    }, mHour, mMinute, false);

            timePickerDialog.show();
        }
        if (v == exitTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime1.setText(hourOfDay + ":" + minute);
                            departure.set(Calendar.HOUR_OF_DAY,hourOfDay);
                            departure.set(Calendar.MINUTE,minute);
                        }
                    }, mHour, mMinute, false);

            timePickerDialog.show();
        }

    }
}

