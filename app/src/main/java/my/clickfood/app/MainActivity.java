package my.clickfood.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


//this is the first activity will be shown for three second then MainMenu activity will be displayed
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent =new Intent(MainActivity.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        },3000);





    }
}