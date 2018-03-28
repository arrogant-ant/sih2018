package inc.iris.sih2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by lappy on 3/26/2018.
 */

public class nearby_parking extends AppCompatActivity implements nearby_parking_adapter.ClickListener{
    private List<nearby_parking_bean> mParkings = new ArrayList<>();
    private nearby_parking_adapter mParkingAdapter;
    RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_parking);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_booking);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mParkingAdapter=new nearby_parking_adapter(mParkings,this);
        nearby_parking_bean mybean1=new nearby_parking_bean("aditi","0.5 miles","20",20);
        mParkings.add(mybean1);
        nearby_parking_bean mybean2=new nearby_parking_bean("abcg","0.5 miles","20",20);
        mParkings.add(mybean2);
        nearby_parking_bean mybean3=new nearby_parking_bean("akjankaj","0.5 miles","20",20);
        mParkings.add(mybean3);
        nearby_parking_bean mybean4=new nearby_parking_bean("sdjsbd","0.5 miles","20",20);
        mParkings.add(mybean4);
        mParkingAdapter.setClickListener(new nearby_parking_adapter.ClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                Toast.makeText(nearby_parking.this, position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(nearby_parking.this,activity_time.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mParkingAdapter);
    }


    @Override
   public void itemClicked(View v, int position) {
//        Intent intent=new Intent(this,activity_time.class);
////        intent.putExtra("recipient",mUsers.get(position).getName());
////        intent.putExtra("user",user+"a");
//       startActivity(intent);


    }
}
