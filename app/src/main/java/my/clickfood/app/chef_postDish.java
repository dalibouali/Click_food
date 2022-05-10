package my.clickfood.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import my.clickfood.app.Chef.Chef;
import my.clickfood.app.Chef.FoodDetails;

public class chef_postDish extends AppCompatActivity {

    ImageButton imageButton;
    Button post_dish;
    Spinner Dishes;
    TextInputLayout desc,qty,pri;
    String description ,quantity ,price ,dishes ;
    Uri imageuri;
    private Uri mcropimageuri;
    FirebaseStorage storage;
    StorageReference storageRefence;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,dataa;
    FirebaseAuth Fauth;
    StorageReference ref;
    String ChefId,RandomUID,State,City,Suburban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_post_dish);

        storage=FirebaseStorage.getInstance();
        storageRefence=storage.getReference();
        Dishes=(Spinner)findViewById(R.id.dishes);
        desc=(TextInputLayout) findViewById(R.id.description);
        qty=(TextInputLayout) findViewById(R.id.quantity);
        pri=(TextInputLayout) findViewById(R.id.price);
        post_dish=(Button) findViewById(R.id.post);
        Fauth=FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getInstance().getReference("FoodDetails");
        try{
            String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataa=firebaseDatabase.getInstance().getReference("Chef").child(userid);
            dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Chef cheff=snapshot.getValue(Chef.class);
                    State=cheff.getState();
                    City=cheff.getCity();
                    Suburban=cheff.getSuburban();
                    imageButton=(ImageButton) findViewById(R.id.imageupload);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v);
                        }
                    });


                    post_dish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dishes = Dishes.getSelectedItem().toString().trim();
                            description = desc.getEditText().getText().toString().trim();
                            quantity = qty.getEditText().getText().toString().trim();
                            price = pri.getEditText().getText().toString().trim();

                            if (isValid()) {
                                uploadImage();
                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch(Exception e){
            Log.e("error : ",e.getMessage());
        }

    }

    private void uploadImage() {
        if(imageuri!=null){
            final ProgressDialog progressDialog=new ProgressDialog(chef_postDish.this);
            progressDialog.setTitle("Uplloading .....");
            progressDialog.show();
            RandomUID= UUID.randomUUID().toString();
            ref=storageRefence.child(RandomUID);
            ChefId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FoodDetails info =new FoodDetails(dishes,quantity,price,description,String.valueOf(uri),RandomUID,ChefId);
                            firebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Suburban).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID)
                                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(chef_postDish.this,"Dish posted successfully !",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(chef_postDish.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }
    }

    private boolean isValid() {
        desc.setErrorEnabled(false);
        desc.setError("");
        qty.setErrorEnabled(false);
        qty.setError("");
        pri.setErrorEnabled(false);
        pri.setError("");
        boolean isValiddescription=false,isValidPrice=false,isValidQuantity=false,isvalid=false;
        if(TextUtils.isEmpty(description)){
            desc.setErrorEnabled(true);
            desc.setError("Description is Required");

        }else{
            desc.setError(null);
            isValiddescription=true;
        }
        if(TextUtils.isEmpty(description)){
            qty.setErrorEnabled(true);
            qty.setError("Enter number of Plates or items");

        }else{
            desc.setError(null);
            isValidQuantity=true;
        }
        if(TextUtils.isEmpty(description)){
            pri.setErrorEnabled(true);
            pri.setError("Please Mention Price");

        }else{
            pri.setError(null);
            isValidPrice=true;
        }
        isvalid=(isValiddescription && isValidQuantity && isValidPrice)?true:false;
        return isvalid;

    }

    private  void startCropImageActivity(Uri imageuri){
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    private  void onSelectImageClick(View v){
        CropImage.startPickImageActivity(this);
    }
    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageuri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                mcropimageuri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

            } else {

                startCropImageActivity(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageButton) findViewById(R.id.imageupload)).setImageURI(result.getUri());
                Toast.makeText(this, "Cropped Successfully", Toast.LENGTH_SHORT).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed" + result.getError(), Toast.LENGTH_SHORT).show();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mcropimageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCropImageActivity(mcropimageuri);
        } else {
            Toast.makeText(this, "cancelling,required permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

}