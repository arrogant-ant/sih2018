package inc.iris.sih2018;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import inc.iris.sih2018.logic.VolleySingleton;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {
    private static final String TAG ="Login" ;
    Button btn_signin, btn_register;
    RelativeLayout relativelayout;
    private final String loginURL = "http://www.sih2018.esy.es/login.php";
    private final String regURL = "http://www.sih2018.esy.es/register.php";
    public static String user=null;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Arkhip_font.ttf").setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.login_activity);

        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_register = (Button) findViewById(R.id.btn_register);
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("SIGN IN");
        dialog.setMessage("Please use email-id to Sign in");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.login, null);
        final MaterialEditText editMail = login_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword = login_layout.findViewById(R.id.editPassword);

        dialog.setView(login_layout);
        dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                final String username=editMail.getText().toString();
                final String pass=editPassword.getText().toString();
                //check validation
                if (TextUtils.isEmpty(username)) {
                    Snackbar.make(relativelayout, "Please enter email address", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (TextUtils.isEmpty(pass)) {
                    Snackbar.make(relativelayout, "Please enter Password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }

                if (pass.length() < 6) {
                    Snackbar.make(relativelayout, "Password Too Short", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                final android.app.AlertDialog waitingdialog = new SpotsDialog(Login.this);
                waitingdialog.show();
                //login
                StringRequest request = new StringRequest(Request.Method.POST, loginURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        waitingdialog.cancel();
                        response=response.trim();
                        Log.d(TAG, "onResponse: "+response);

                        switch (response) {
                            //valid Login
                            case "success":
                                Login.user=username;
                                startActivity(new Intent(Login.this,MapActivity.class));
                                break;
                            //invalid login
                            case "failure":
                                Snackbar.make(relativelayout,"WRONG PASSWORD",Snackbar.LENGTH_SHORT).show();
                                showSigninDiaog();
                                break;
                            //username does not exist
                            case "notthere":
                                Snackbar.make(relativelayout,"User does not exists",Snackbar.LENGTH_SHORT).show();
                                showRegisterDialog();
                                break;
                            default:
                                Snackbar.make(relativelayout,"Please try again",Snackbar.LENGTH_SHORT).show();
                                showSigninDiaog();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        waitingdialog.cancel();
                        Snackbar.make(relativelayout,"Some error encountered \nPlease try again",Snackbar.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<>();
                        param.put("user",username);
                        param.put("pass",pass);
                        return param;


                    }
                };
                VolleySingleton.getInstance(Login.this).addToRequestQueue(request);


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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER");
        dialog.setMessage("Please use email-id to register");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.register, null);
        final MaterialEditText editMail = register_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword = register_layout.findViewById(R.id.editPassword);
        final MaterialEditText editName = register_layout.findViewById(R.id.editName);
        final MaterialEditText editPhone = register_layout.findViewById(R.id.Phone);
        dialog.setView(register_layout);

        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                final String username=editMail.getText().toString();
                final String pass=editPassword.getText().toString();
                final String name=editName.getText().toString();
                final String phone= editPhone.getText().toString();
                Toast.makeText(Login.this, "user "+username, Toast.LENGTH_SHORT).show();

                //check validation
                if (TextUtils.isEmpty(username)) {
                    Snackbar.make(relativelayout, "Please enter email address", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (TextUtils.isEmpty(pass)) {
                    Snackbar.make(relativelayout, "Please enter Password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(relativelayout, "Please enter Name", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (phone.length()!=10) {
                    Snackbar.make(relativelayout, "Please enter Phone number", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                if (editPassword.getText().toString().length() < 6) {
                    Snackbar.make(relativelayout, "Password Too Short", Snackbar.LENGTH_SHORT)
                            .show();
                    return;

                }
                final android.app.AlertDialog waitingdialog = new SpotsDialog(Login.this);
                waitingdialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, regURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        waitingdialog.cancel();
                        response=response.trim();
                        Log.d(TAG, "onResponse: "+response);

                        switch (response) {
                            //valid Login
                            case "success":
                                Login.user=username;
                                startActivity(new Intent(Login.this,MapActivity.class));
                                break;
                            //invalid login
                            case "failure":
                                Snackbar.make(relativelayout,"User already exists",Snackbar.LENGTH_LONG).show();
                                showSigninDiaog();
                                break;

                            default:
                                Snackbar.make(relativelayout,"Please try again",Snackbar.LENGTH_LONG).show();
                                showRegisterDialog();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        waitingdialog.cancel();
                        Snackbar.make(relativelayout,"Some error encountered \nPlease try again",Snackbar.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<>();
                        param.put("user",username);
                        param.put("pass",pass);
                        param.put("phone",phone);
                        param.put("name",name);
                        param.put("cat","user");
                        return param;


                    }
                };
                VolleySingleton.getInstance(Login.this).addToRequestQueue(request);


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
