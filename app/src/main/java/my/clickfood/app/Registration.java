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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class Registration extends AppCompatActivity {

    String[] Tunis = {"Tunis1", "Sidi hessine", "El Marsa","El Kram","Le Bardo","La Goulette","Cartage"};
    String[] Ariana = {"Ettadhamen","La Sokra","Ariana","Raoued", "Kalet El Andalous", "Sidi Thabet","El Mnihla"};


    String[] Tunis1 = {"El medina ", "El hafsia", "Souk el nhas", "Beb mnara", "el sabbaghine"};

    String[] Sidi_hessine = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};
    String[] El_Marsa = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};
    String[] El_Kram = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};
    String[] Le_Bardo = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};
    String[] La_Goulette = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};
    String[] Carthage = {"El Omrane ", "El Tahrir", "Cité El Khadhra", "Beb Souika ", "Beb Saadoun"};



    TextInputLayout fname, lname, localadd, emaill, pass, cmpass, Mobileno;
    Spinner statespin, City, Suburban;
    Button Signin, Email, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String statee;
    String cityy;
    String suburban;
    String email;
    String password;
    String firstname;
    String lastname;
    String Localaddress;
    String confirmpass;
    String mobileno;
    Intent i;
    String role ="" ;

    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        try {
            mDialog = new ProgressDialog(Registration.this);
            mDialog.setMessage("Registering please wait...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);

            fname = (TextInputLayout) findViewById(R.id.Firstname);
            lname = (TextInputLayout) findViewById(R.id.Lastname);
            localadd = (TextInputLayout) findViewById(R.id.Localaddress);
            emaill = (TextInputLayout) findViewById(R.id.Email);
            pass = (TextInputLayout) findViewById(R.id.Pwd);
            cmpass = (TextInputLayout) findViewById(R.id.Cpass);
            Signin = (Button) findViewById(R.id.Signin);
            statespin = (Spinner) findViewById(R.id.Statee);
            City = (Spinner) findViewById(R.id.Citys);
            Suburban = (Spinner) findViewById(R.id.Suburban);
            Mobileno = (TextInputLayout) findViewById(R.id.Mobileno);

            Email = (Button) findViewById(R.id.emaill);




            //choose one item of state spin
            statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    statee = value.toString().trim();
                    if (statee.equals("Tunis")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Tunis) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }

                    if (statee.equals("Ariana")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Ariana) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //choose one item of cityspin
            City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    cityy = value.toString().trim();
                    if (cityy.equals("Tunis1")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Tunis1) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Sidi hessine")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Sidi_hessine) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("El Marsa")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : El_Marsa) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //choose one itm of suburban spin
            Suburban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    suburban = value.toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
            FAuth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emaill.getEditText().getText().toString().trim();
                    password = pass.getEditText().getText().toString().trim();
                    firstname = fname.getEditText().getText().toString().trim();
                    lastname = lname.getEditText().getText().toString().trim();
                    Localaddress = localadd.getEditText().getText().toString().trim();
                    confirmpass = cmpass.getEditText().getText().toString().trim();
                    mobileno = Mobileno.getEditText().getText().toString().trim();

                    if (isValid()) {

                        mDialog.show();

                        FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                    final HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("Role", role);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            HashMap<String, String> hashMappp = new HashMap<>();
                                            hashMappp.put("City", cityy);
                                            hashMappp.put("ConfirmPassword", confirmpass);
                                            hashMappp.put("EmailID", email);
                                            hashMappp.put("FirstName", firstname);
                                            hashMappp.put("LastName", lastname);
                                            hashMappp.put("Mobileno", mobileno);
                                            hashMappp.put("Password", password);
                                            hashMappp.put("LocalAddress", Localaddress);
                                            hashMappp.put("State", statee);
                                            hashMappp.put("Suburban", suburban);
                                            FirebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                mDialog.dismiss();
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                                                builder.setMessage("Registered Successfully,Please Verify your Email");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        dialog.dismiss();


                                                                    }
                                                                });
                                                                AlertDialog alert = builder.create();
                                                                alert.show();

                                                            } else {
                                                                mDialog.dismiss();
                                                                AlertDialog.Builder builder=new AlertDialog.Builder(Registration.this);
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
                                            });
                                        }
                                    });

                                } else {
                                    mDialog.dismiss();
                                    AlertDialog.Builder builder=new AlertDialog.Builder(Registration.this);
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
        } catch (Exception e) {
            mDialog.dismiss();
            Toast.makeText(this, "ERROR"+e.getMessage(), Toast.LENGTH_LONG).show();
        }


        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                finish();
            }
        });




    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        emaill.setErrorEnabled(false);
        emaill.setError("");
        fname.setErrorEnabled(false);
        fname.setError("");
        lname.setErrorEnabled(false);
        lname.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");
        cmpass.setErrorEnabled(false);
        cmpass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");

        boolean isValidfirstname = false, isValidlastname = false, isValidaddress = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false;
        if (TextUtils.isEmpty(firstname)) {
            fname.setErrorEnabled(true);
            fname.setError("FirstName is required");
        } else {
            isValidfirstname = true;
        }
        if (TextUtils.isEmpty(lastname)) {
            lname.setErrorEnabled(true);
            lname.setError("LastName is required");
        } else {
            isValidlastname = true;
        }
        if (TextUtils.isEmpty(email)) {
            emaill.setErrorEnabled(true);
            emaill.setError("Email is required");
        } else {
            if (email.matches(emailpattern)) {
                isValidemail = true;
            } else {
                emaill.setErrorEnabled(true);
                emaill.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(mobileno)) {
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Mobile number is required");
        } else {
            if (mobileno.length() < 8) {
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Invalid mobile number");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(password)) {
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                pass.setErrorEnabled(true);
                pass.setError("Password too weak");
                cmpass.setError("password too weak");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpass)) {
            cmpass.setErrorEnabled(true);
            cmpass.setError("Confirm Password is required");
        } else {
            if (!password.equals(confirmpass)) {
                pass.setErrorEnabled(true);
                pass.setError("Password doesn't match");
                cmpass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(Localaddress)) {
            localadd.setErrorEnabled(true);
            localadd.setError("Local Address is required");
        } else {
            isValidaddress = true;
        }
        isvalid = (isValidfirstname && isValidlastname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isValidaddress) ? true : false;
        return isvalid;
    }
}