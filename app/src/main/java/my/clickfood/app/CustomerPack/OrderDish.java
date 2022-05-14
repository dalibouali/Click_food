package my.clickfood.app.CustomerPack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import my.clickfood.app.Chef.Chef;
import my.clickfood.app.Chef.UpdateDishModel;
import my.clickfood.app.Customer;
import my.clickfood.app.Customer_BottomNavigation;
import my.clickfood.app.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


//this Order
public class OrderDish extends AppCompatActivity {


    String RandomId, ChefID;
    ImageView imageView;
    ElegantNumberButton additem;
    TextView Foodname, ChefName, ChefLoaction, FoodQuantity, FoodPrice, FoodDescription;
    DatabaseReference databaseReference, dataaa, chefdata, reference, data, dataref;
    String State, City, Sub, dishname;
    int dishprice;
    String custID;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dish);

    }
}