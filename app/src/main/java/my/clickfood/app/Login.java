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

    //declaring variables
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
            //initialising variables
            email = (TextInputLayout) findViewById(R.id.Lemail);
            pass = (TextInputLayout) findViewById(R.id.Lpassword);
            login = (Button) findViewById(R.id.buttonLogin);
            txt = (TextView) findViewById(R.id.textView3);
            //here we get the extra string wich contain the role of user Chef/Customer
            intent=getIntent();
            type=intent.getStringExtra("Role").toString().trim();

            //get an instance of Authorization table of firebase
            FAuth = FirebaseAuth.getInstance();

            //if login  buton is clicked
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    em = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if (isValid()) {
                        //initialize a progress Dialog that appear when we wait until request is done
                        final ProgressDialog mDialog = new ProgressDialog(Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Logging in...");
                        //showing the dialog
                        mDialog.show();


                        //trying to login with email and password
                        FAuth.signInWithEmailAndPassword(em, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //return true if the Task has completed successfully
                                if (task.isSuccessful()) {
                                    //close the dialog of loading
                                    mDialog.dismiss();
                                    //return true if the user is verified
                                    if (FAuth.getCurrentUser().isEmailVerified()) {
                                        Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                        //if Role is Customer go activity CustomerBottomNavigation
                                        if(type.equals("Customer")){
                                            Intent z = new Intent(Login.this, Customer_BottomNavigation.class);
                                            startActivity(z);
                                            finish();
                                        //if Role is Chef go activity Chef_BottomNavigation
                                        }else if(type.equals("Chef")){
                                            Intent z = new Intent(Login.this, Chef_BottomNavigation.class);
                                            startActivity(z);
                                            finish();

                                        }
                                    //if there is an error in user credentiel
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
                                //if the Task failed to complete
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
            //go to register page
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent Register = new Intent(Login.this, Registration.class);
                    Register.putExtra("Role",type);
                    startActivity(Register);

                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //this is the email pattern to validate email
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //function to validate form of login
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
