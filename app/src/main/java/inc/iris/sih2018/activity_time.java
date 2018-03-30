package inc.iris.sih2018;

/**
 * Created by Vinay on 3/27/2018.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import inc.iris.sih2018.logic.Booking;

public class activity_time extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker,btnDatePicker1, btnTimePicker1;
    EditText txtDate, txtTime,txtDate1,txtTime1;
    TextView charges;
    Button book,calculate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    RequestQueue queue;
    Long arrived,departed;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_parking);
        queue= Volley.newRequestQueue(this);
        Intent intent=getIntent();
        final String park_area=intent.getStringExtra("park_area");
        final int cost=intent.getIntExtra("cost",0);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnDatePicker1=(Button)findViewById(R.id.btn_date_out);
        btnTimePicker1=(Button)findViewById(R.id.btn_time_out);
        calculate=findViewById(R.id.calculate);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        txtDate1=(EditText)findViewById(R.id.out_date);
        txtTime1=(EditText)findViewById(R.id.out_time);
        charges=findViewById(R.id.charges);
        book=findViewById(R.id.book);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnDatePicker1.setOnClickListener(this);
        btnTimePicker1.setOnClickListener(this);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               charges.setText(Booking.getBookingCost(arrived,departed,cost));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://sih2018.esy.es/book.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        charges.setText(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
                        charges.setText(error.getMessage());

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                       params.put("parkingname","bit");
                        params.put("entry", String.valueOf(arrived));
                          params.put("exit", String.valueOf(departed));
                        params.put("username","user1");
                        params.put("price", String.valueOf(Booking.getBookingCost(arrived,departed,cost)));

                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });
    }

    @Override
    public void onClick(View v) {
        Calendar arrival=Calendar.getInstance();
        Calendar departure=Calendar.getInstance();


        if (v == btnDatePicker) {

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

                        }
                    }, mYear, mMonth, mDay);
            arrival.set(mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        if (v == btnDatePicker1) {

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

                        }
                    }, mYear, mMonth, mDay);
            departure.set(mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

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
                        }
                    }, mHour, mMinute, false);
            arrival.set(Calendar.HOUR_OF_DAY,mHour);
            arrival.set(Calendar.MINUTE,mMinute);
            timePickerDialog.show();
        }
        if (v == btnTimePicker1) {

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
                        }
                    }, mHour, mMinute, false);
            departure.set(Calendar.HOUR_OF_DAY,mHour);
            departure.set(Calendar.MINUTE,mMinute);
            timePickerDialog.show();
        }
        arrived= arrival.getTimeInMillis();
        departed=arrival.getTimeInMillis();
    }
}

