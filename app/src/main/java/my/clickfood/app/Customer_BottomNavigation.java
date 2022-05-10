package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.clickfood.app.CustomerPack.CustomerCartFragment;
import my.clickfood.app.CustomerPack.CustomerHomeFragment;
import my.clickfood.app.CustomerPack.CustomerProfileFragment;
import my.clickfood.app.CustomerPack.CustomerTrackFragment;

public class Customer_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bottom_navigation);
        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       Fragment fragment=null;
       switch(item.getItemId()){
           case R.id.custhome:
               fragment=new CustomerHomeFragment();
               break;
           case R.id.Order:
               fragment=new CustomerHomeFragment();
               break;
           case R.id.cart:
               fragment=new CustomerCartFragment();
               break;
           case R.id.Profile:
               fragment=new CustomerProfileFragment();
               break;
           case R.id.Track:
               fragment=new CustomerTrackFragment();
               break;

       }
       return loadcustomerfragment(fragment);
    }

    private boolean loadcustomerfragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}