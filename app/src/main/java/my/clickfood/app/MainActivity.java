package my.clickfood.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView) findViewById(R.id.imageView);

        imageView.animate().alpha(1f).setDuration(1000);


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