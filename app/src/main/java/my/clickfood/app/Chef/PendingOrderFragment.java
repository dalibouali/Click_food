package my.clickfood.app.Chef;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.clickfood.app.R;

//this fragment is supposed to contain all non confirmed fragment
public class PendingOrderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.chef_pendingorders,null);
        getActivity().setTitle("Pending Orders");
        return v;
    }
}
