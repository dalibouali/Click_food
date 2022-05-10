package my.clickfood.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {
    Button signin,signup;
    ImageView bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final Animation zoomin= AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout= AnimationUtils.loadAnimation(this,R.anim.zoomout);

        bgimage=findViewById(R.id.backmenu);
        bgimage.setAnimation(zoomin);
        bgimage.setAnimation(zoomout);

        //Some animations to background
        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage.startAnimation(zoomin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // End Animation

        signin = (Button) findViewById(R.id.SignwithEmail);

        signup = (Button) findViewById(R.id.Signup);
//if Sign in with Email clicked
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signemail = new Intent(MainMenu.this, ChooseOne.class);
                signemail.putExtra("Home", "SignIn");
                startActivity(signemail);
                finish();
            }
        });


        //if Signup Clicked

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainMenu.this, ChooseOne.class);
                signup.putExtra("Home", "SignUp");
                startActivity(signup);
                finish();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}