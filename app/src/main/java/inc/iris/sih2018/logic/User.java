package inc.iris.sih2018.logic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import inc.iris.sih2018.MapActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Sud on 3/30/18.
 */

public class User {
    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String user=null;

    public User(Context mContext) {
        this.mContext = mContext;
        //shared pref
        sharedPreferences = mContext.getSharedPreferences("MyPref",MODE_PRIVATE );
        editor = sharedPreferences.edit();
    }

    public void signOut()
    {
        final android.app.AlertDialog.Builder alertdialog=new android.app.AlertDialog.Builder(mContext);
        alertdialog.setTitle("Logout");
        alertdialog.setMessage("Click Yes To Logout");
        alertdialog.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(new Intent(mContext,MapActivity.class));
                editor.putString("user",null);
                editor.commit();

            }
        });
        alertdialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }
}
