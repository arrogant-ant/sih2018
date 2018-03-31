package inc.iris.sih2018;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class nearby_parking_adapter extends RecyclerView.Adapter<nearby_parking_adapter.MyViewHolder> {
    List<nearby_parking_bean> beanList;

    Context context;

    public nearby_parking_adapter(List<nearby_parking_bean> gList, Context c) {
        this.beanList = gList;
        this.context = c;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nearby_parking, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final nearby_parking_bean gallary = beanList.get(position);
        holder.name.setText(gallary.getName());
        holder.distance.setText(gallary.getDistance()+" km away");
        holder.slots.setText(String.valueOf(gallary.getSlots())+" slots available");
        holder.price.setText(String.valueOf(gallary.getCost()));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,activity_time.class);
                i.putExtra("park_Area",gallary.getName());
                i.putExtra("cost",gallary.getCost());
                i.putExtra("slots",gallary.getSlots());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        TextView name;
        TextView distance;
        TextView slots;
        TextView price;
        FrameLayout parentLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.parking_name);
            distance = (TextView) itemView.findViewById(R.id.distance);
            slots = (TextView) itemView.findViewById(R.id.slots);
            price=itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.parking_img);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }

    }

}
