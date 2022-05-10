package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputLayout email, pass;
    Button login;

    TextView txt;
    FirebaseAuth FAuth;
    String em;
    String pwd;
    String type;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            email = (TextInputLayout) findViewById(R.id.Lemail);
            pass = (TextInputLayout) findViewById(R.id.Lpassword);
            login = (Button) findViewById(R.id.buttonLogin);
            txt = (TextView) findViewById(R.id.textView3);
            intent=getIntent();
            type=intent.getStringExtra("Role").toString().trim();


            FAuth = FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    em = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();
                    if (isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Logging in...");
                        mDialog.show();
                        FAuth.signInWithEmailAndPassword(em, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();
                                    if (FAuth.getCurrentUser().isEmailVerified()) {
                                        Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                        if(type.equals("Customer")){
                                            Intent z = new Intent(Login.this, Customer_BottomNavigation.class);
                                            startActivity(z);
                                            finish();
                                        }else if(type.equals("Chef")){
                                            Intent z = new Intent(Login.this, Chef_BottomNavigation.class);
                                            startActivity(z);
                                            finish();

                                        }else{
                                            Intent z = new Intent(Login.this, delivery_Bottom_navigation.class);
                                            startActivity(z);
                                            finish();

                                        }

                                    } else {
                                        AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();
                                            }
                                        }).setTitle("").setMessage("Please Verify your Email").show();

                                    }

                                } else {

                                    mDialog.dismiss();
                                    AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                                    builder.setCancelable(false);
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();
                                        }
                                    }).setTitle("Error").setMessage(task.getException().getMessage()).show();

                                }
                            }
                        });

                    }
                }
            });

            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent Register = new Intent(Login.this, Registration.class);
                    startActivity(Register);

                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid() {
        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalidemail=false,isvalidpassword=false,isvalid=false;
        if (TextUtils.isEmpty(em))
        {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }
        else {
            if (em.matches(emailpattern))
            {
                isvalidemail=true;
            }
            else {
                email.setErrorEnabled(true);
                email.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(pwd))
        {
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        }
        else {
            isvalidpassword=true;
        }
        isvalid = (isvalidemail && isvalidpassword) ? true : false;
        return isvalid;
    }
    }
