package inc.iris.sih2018;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {
    Button btn_signin,btn_register;
    RelativeLayout relativelayout;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Arkhip_font.ttf").setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.login_activity);

        relativelayout=(RelativeLayout)findViewById(R.id.relativelayout);
        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_register=(Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSigninDiaog();
            }
        });

    }

    private void showSigninDiaog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("SIGN IN");
        dialog.setMessage("Please use email-id to Sign in");
        LayoutInflater inflater=LayoutInflater.from(this);
        View login_layout=inflater.inflate(R.layout.login,null);
        final MaterialEditText editMail=login_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword=login_layout.findViewById(R.id.editPassword);

        dialog.setView(login_layout);
        dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                btn_signin.setEnabled(false);

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

                if(editPassword.getText().toString().length()<6)
                {
                    Snackbar.make(relativelayout,"Password Too Short",Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                final android.app.AlertDialog waitingdialog=new SpotsDialog(Login.this);
                waitingdialog.show();
                //login


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

    private void showRegisterDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER");
        dialog.setMessage("Please use email-id to register");
        LayoutInflater inflater=LayoutInflater.from(this);
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
