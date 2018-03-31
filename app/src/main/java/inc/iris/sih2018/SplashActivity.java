package inc.iris.sih2018;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import inc.iris.sih2018.logic.User;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final long SPLASH_DISPLAY_LENGTH=1000;
    private static final int ERROR_DIALOG_REQUEST=9001;
    static double latitude;
    static double longitude;
    private FusedLocationProviderClient mFusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return ;
        }

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE );
        User.user=sharedPreferences.getString(User.PREF_TAG,null);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(isServiceOk())
                {
                    Intent mainIntent = new Intent(SplashActivity.this,MapActivity.class);

                    SplashActivity.this.startActivity(mainIntent);                }
                else
                    Toast.makeText(SplashActivity.this, "Issue with services", Toast.LENGTH_SHORT).show();

                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
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
}
