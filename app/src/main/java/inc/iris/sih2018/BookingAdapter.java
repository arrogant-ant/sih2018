package inc.iris.sih2018;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import inc.iris.sih2018.logic.Booking;
import inc.iris.sih2018.logic.BookingStatus;
import inc.iris.sih2018.logic.ParkingSlot;

/**
 * Created by Sud on 3/18/18.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

   Booking records[];
   Context context;

    public BookingAdapter(Booking[] records,Context context) {
        this.records = records;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.row_booking,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Booking booking=records[position];
        ParkingSlot slot=booking.getSlot();
        holder.name.setText(slot.getName());
        holder.address.setText(slot.getAddress());
        holder.cost.setText(String.valueOf(booking.getBookingCost()));
        holder.timeSlot.setText(booking.getTimeSlot());
        if(booking.getStatus()==BookingStatus.PARKED)
            holder.option.setImageResource(R.mipmap.ic_video);
        else
            holder.option.setImageResource(R.mipmap.ic_navigation);



    }

    @Override
    public int getItemCount() {
        return records.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView parkingImage, option;
        TextView name,address,timeSlot,cost;
        public ViewHolder(View itemView) {
            super(itemView);
            parkingImage=itemView.findViewById(R.id.parking_img);
            option=itemView.findViewById(R.id.option_img);
            name=itemView.findViewById(R.id.parking_name);
            address=itemView.findViewById(R.id.parking_address);
            timeSlot=itemView.findViewById(R.id.time_slot);
            cost=itemView.findViewById(R.id.cost);


        }
    }
}
