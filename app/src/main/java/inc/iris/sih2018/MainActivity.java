package inc.iris.sih2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void openBookings(View view) {
        startActivity(new Intent(this, Bookings.class));
    }

    public void openMap(View view) {
        startActivity(new Intent(this,MapActivity.class));
    }
}
