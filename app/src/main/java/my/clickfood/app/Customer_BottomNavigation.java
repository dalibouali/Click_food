package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import my.clickfood.app.CustomerPack.CustomerCartFragment;
import my.clickfood.app.CustomerPack.CustomerHomeFragment;
import my.clickfood.app.CustomerPack.CustomerOrdersFragment;
import my.clickfood.app.CustomerPack.CustomerProfileFragment;
import my.clickfood.app.CustomerPack.CustomerTrackFragment;


//same as Chef Button_Navigation_Bottom with different fragment to call
public class Customer_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bottom_navigation);
        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            loadcustomerfragment(new CustomerHomeFragment());

    }



    //navigate to the right fragment base on selected item of navigation bottom
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       Fragment fragment=null;
       switch(item.getItemId()){
           case R.id.custhome:
               fragment=new CustomerHomeFragment();
               break;
           case R.id.Order:
               fragment=new CustomerOrdersFragment();
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