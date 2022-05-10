package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.clickfood.app.Chef.HomeFragment;
import my.clickfood.app.Chef.OrderFragment;
import my.clickfood.app.Chef.PendingOrderFragment;
import my.clickfood.app.Chef.ProfileFragment;

public class Chef_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_bottom_navigation);
        BottomNavigationView navigationView=findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    //here on any item of the menu selected the fragment will be changed
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch ((item.getItemId())) {
            case R.id.chefHome:
                fragment = new HomeFragment();
                break;
            case R.id.chefProfile:
                fragment = new ProfileFragment();
                break;
            case R.id.pendingOrders:
                fragment = new PendingOrderFragment();
                break;
            case R.id.Orders:
                fragment = new HomeFragment();
                break;
        }
        return loadcheffragment( fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }


}

