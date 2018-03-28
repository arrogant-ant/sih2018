package inc.iris.sih2018;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class nearby_parking_adapter extends  RecyclerView.Adapter<nearby_parking_adapter.MyViewHolder> {
    List<nearby_parking_bean> beanList;
    private ClickListener clickListener;
    Context context;

    public nearby_parking_adapter(List<nearby_parking_bean> gList, Context c) {
        this.beanList = gList;
        this.context = c;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nearby_parking, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        nearby_parking_bean gallary = beanList.get(position);
        holder.name.setText(gallary.getName());
        holder.distance.setText(gallary.getDistance());
        holder.slots.setText(gallary.getSlots());
//        holder.cost.setText(gallary.getCost());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public interface ClickListener {
        void itemClicked(View v, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        TextView name;
        TextView distance;
        TextView slots;
        //  TextView cost;

        MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.parking_name);
            distance = (TextView) itemView.findViewById(R.id.distance);
            slots = (TextView) itemView.findViewById(R.id.slots);
            //  cost = (TextView) itemView.findViewById(R.id.costss);
            imageView = (ImageView) itemView.findViewById(R.id.parking_img);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
            else
            {
                Toast.makeText(context, "not clicked", Toast.LENGTH_SHORT).show();
            }
        }

    }
}