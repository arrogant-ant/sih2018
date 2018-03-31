package inc.iris.sih2018;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;

import inc.iris.sih2018.logic.User;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final long SPLASH_DISPLAY_LENGTH=1000;
    private static final int ERROR_DIALOG_REQUEST=9001;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
