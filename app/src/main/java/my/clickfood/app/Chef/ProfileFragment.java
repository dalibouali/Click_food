package my.clickfood.app.Chef;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import my.clickfood.app.R;
import my.clickfood.app.chef_postDish;
//this fragment contain button psot dish will redirect to post dish activity
public class ProfileFragment extends Fragment {
    Button postdish;
    ConstraintLayout backimg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.chef_profile,null);
        getActivity().setTitle("Post Dish");
        //here we add some animation to background every picture appear for 3s
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
        backimg=v.findViewById(R.id.back1);
        backimg.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        //end of animation


        //button that bring us to post dish activity
        postdish=(Button)v.findViewById(R.id.post_dish);
        postdish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), chef_postDish.class));
            }
        });
        return v;
    }
}
