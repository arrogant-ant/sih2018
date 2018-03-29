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

    private static final int ERROR_DIALOG_REQUEST=9001;

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

        if(isServiceOk())
        {
            startActivity(new Intent(this,MapActivity.class));
        }
        else
            Toast.makeText(this, "Issue with services", Toast.LENGTH_SHORT).show();
    }


    //to check if playservices is upto date
    //TODO place it in splash screen
    boolean isServiceOk()
    {
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(available== ConnectionResult.SUCCESS){
            //everything ok
            return true;
        }
        if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            //error occured but can be resolved
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        //there is an unresolvable error
        return false;
    }

    public void openLogin(View view) {
        startActivity(new Intent(this,Login.class));

    }
}
