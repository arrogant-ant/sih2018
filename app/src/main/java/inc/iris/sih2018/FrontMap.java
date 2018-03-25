package inc.iris.sih2018;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by shubham on 3/24/2018.
 */

public class FrontMap extends AppCompatActivity
{
    private static final String Tag=FrontMap.class.getSimpleName();
    private FusedLocationProviderClient mFusedLocationClient;
    TextView latitude,longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_map);
       latitude=findViewById(R.id.latitude);
       longitude=findViewById(R.id.longitude);
       mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!checkPermissions())
        {
         getPermissions();
        }
        else
        {

        }
    }

    private void getPermissions() {

       if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION))
       {
           Snackbar.make(findViewById(android.R.id.content),R.string.permission_rationale,Snackbar.LENGTH_INDEFINITE).
                   setAction(android.R.string.ok, new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                      startLocationPermissionRequest();
                       }
                   }).show();

       }


    }

    private void startLocationPermissionRequest() {
    }

    private boolean checkPermissions() {
        int permission_state= ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return permission_state == PackageManager.PERMISSION_GRANTED;
    }
}
