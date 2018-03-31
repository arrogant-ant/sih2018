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
   String tag;

    public BookingAdapter(Booking[] records,Context context,String tag) {
        this.records = records;
        this.context=context;
        this.tag=tag;
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
        //todo remove address
        holder.cost.setText(String.valueOf(booking.getBookingCost()));
        holder.timeSlot.setText(booking.getTimeSlot());
        if(booking.getType().equals("CURRENT"))
            holder.option.setImageResource(R.mipmap.ic_video);
        else
            holder.option.setImageResource(R.mipmap.ic_navigation);
        if(tag.equals("CurrentBookingTab")) {
            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Cancel Booking");
                    builder.setMessage("Cancel booking?. You  will be charged 10% of booking cost");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //add string request to cancel  php
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return records.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView parkingImage, option;
        TextView name,address,timeSlot,cost,status;
        TextView name,address,timeSlot,cost;
        FrameLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            parkingImage=itemView.findViewById(R.id.parking_img);
            option=itemView.findViewById(R.id.option_img);
            name=itemView.findViewById(R.id.parking_name);
            address=itemView.findViewById(R.id.parking_address);
            timeSlot=itemView.findViewById(R.id.time_slot);
            cost=itemView.findViewById(R.id.cost);
            status=itemView.findViewById(R.id.status);


            layout=itemView.findViewById(R.id.booking_layout);

        }
    }
}
