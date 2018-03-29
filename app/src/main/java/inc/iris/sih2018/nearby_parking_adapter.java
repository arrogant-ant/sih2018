package inc.iris.sih2018;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class nearby_parking_adapter extends  RecyclerView.Adapter<nearby_parking_adapter.MyViewHolder> {
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
//    public void setFilter(List<nearby_parking_bean> user){
//        beanList=new ArrayList<>();
//        beanList.addAll(user);
//        notifyDataSetChanged();
//    }
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
    //        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Bitmap bitmap = BitmapFactory.decodeResource(imageView.getResources(),R.drawable.images);
//            imageView.setImageBitmap(transform(bitmap));
            //itemView.setOnClickListener(this);
        }
//        public Bitmap transform(Bitmap source) {
//            int size = Math.min(source.getWidth(),source.getHeight());
//            int x = (source.getWidth()-size)/2;
//            int y = (source.getHeight()-size)/2;
//            Bitmap bitmap = Bitmap.createBitmap(source,x,y,size,size);
//            if(bitmap!=source){
//                source.recycle();
//
//            }
//            Bitmap nbitmap = Bitmap.createBitmap(size,size,source.getConfig());
//            Canvas c = new Canvas(nbitmap);
//            Paint p = new Paint();
//            BitmapShader bs = new BitmapShader(bitmap,BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//            p.setShader(bs);
//            p.setColor(Color.BLACK);
//            p.setShadowLayer(4.0f,0.0f,2.0f, Color.GRAY);
//            p.setAntiAlias(true);
//            float r = size/2f;
//            c.drawCircle(r,r,r,p);
//            bitmap.recycle();
//            return nbitmap;
//

    //    }
        @Override
        public void onClick(View v) {
            //if (clickListener != null) {
            //    clickListener.itemClicked(v, getPosition());
            AlertDialog.Builder dialog=new AlertDialog.Builder(context);
            dialog.setTitle("BOOK");
            dialog.setMessage("Please Confirm To Book");
            LayoutInflater inflater=LayoutInflater.from(context);
            View register_layout=inflater.inflate(R.layout.register,null);
            final MaterialEditText editMail=register_layout.findViewById(R.id.editEmail);
            final MaterialEditText editPassword=register_layout.findViewById(R.id.editPassword);
            final MaterialEditText editName=register_layout.findViewById(R.id.editName);
            final MaterialEditText editPhone=register_layout.findViewById(R.id.Phone);
            dialog.setView(register_layout);
            dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    //check validation
                    if(TextUtils.isEmpty(editMail.getText().toString()))
                    {
                        Snackbar.make(relativelayout,"Please enter email address",Snackbar.LENGTH_SHORT)
                                .show();
                        return;

                    }
                    if(TextUtils.isEmpty(editPassword.getText().toString()))
                    {
                        Snackbar.make(relativelayout,"Please enter Password",Snackbar.LENGTH_SHORT)
                                .show();
                        return;

                    }
                    if(TextUtils.isEmpty(editName.getText().toString()))
                    {
                        Snackbar.make(relativelayout,"Please enter Name",Snackbar.LENGTH_SHORT)
                                .show();
                        return;

                    }
                    if(TextUtils.isEmpty(editPhone.getText().toString()))
                    {
                        Snackbar.make(relativelayout,"Please enter Phone number",Snackbar.LENGTH_SHORT)
                                .show();
                        return;

                    }
                    if(editPassword.getText().toString().length()<6)
                    {
                        Snackbar.make(relativelayout,"Password Too Short",Snackbar.LENGTH_SHORT)
                                .show();
                        return;

                    }

                }
            });
            dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
    }

}
