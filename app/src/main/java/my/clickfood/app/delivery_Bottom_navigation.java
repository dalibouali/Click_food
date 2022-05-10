package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.clickfood.app.delivery.DeliveryPending;
import my.clickfood.app.delivery.DeliveryShipOrder;

public class delivery_Bottom_navigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_bottom_navigation);
        BottomNavigationView navigationView=findViewById(R.id.delivery_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch(item.getItemId()){
            case R.id.shiporders:
                fragment=new DeliveryShipOrder();
                break;
            case R.id.pendingOrders:
                fragment=new DeliveryPending();
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