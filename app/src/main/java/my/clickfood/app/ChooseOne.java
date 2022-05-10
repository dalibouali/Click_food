package my.clickfood.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {
    Button Chef ,Customer ,Delivery;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        Chef = (Button) findViewById(R.id.chef);
        Delivery = (Button) findViewById(R.id.delivery);
        Customer = (Button) findViewById(R.id.customer);
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_1), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_3), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_4), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_5), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_6), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_7), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_8), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_9), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_10), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_11), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img_12), 3000);



        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);
        bgimage = findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
        intent = getIntent();


        type = intent.getStringExtra("Home").toString().trim();

        Chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("SignIn")) {
                    Intent loginemail = new Intent(ChooseOne.this, Login.class);
                    loginemail.putExtra("Role","Chef");
                    startActivity(loginemail);
                    finish();
                }

                if (type.equals("SignUp")) {
                    Intent Register = new Intent(ChooseOne.this, Registration.class);
                    Register.putExtra("Role","Chef");
                    startActivity(Register);


                }

            }
        });
        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("SignIn")) {
                    Intent loginemailcust = new Intent(ChooseOne.this, Login.class);
                    loginemailcust.putExtra("Role","Customer");
                    startActivity(loginemailcust);
                    finish();
                }

                if (type.equals("SignUp")) {
                    Intent Registercust = new Intent(ChooseOne.this, Registration.class);
                    Registercust.putExtra("Role","Customer");
                    startActivity(Registercust);
                }
            }
        });

        Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("SignUp")) {
                    Intent Registerdelivery = new Intent(ChooseOne.this, Registration.class);
                    Registerdelivery.putExtra("Role","Delevery_man");
                    startActivity(Registerdelivery);
                }

                if (type.equals("SignIn")) {
                    Intent loginemail = new Intent(ChooseOne.this, Login.class);
                    loginemail.putExtra("Role","Delevery_man");
                    startActivity(loginemail);
                    finish();
                }
            }
        });
    }
}