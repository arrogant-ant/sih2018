package inc.iris.sih2018;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void openBookings(View view) {
        startActivity(new Intent(this, Bookings.class));
    }
    public void openNearbyParkings(View view) {
        startActivity(new Intent(this, nearby_parking.class));
    }

    public void openMap(View view) {

            startActivity(new Intent(this,MapActivity.class));

    }


    //to check if playservices is upto date
    //TODO place it in splash screen


    public void openLogin(View view) {
        startActivity(new Intent(this,Login.class));

    }
}
