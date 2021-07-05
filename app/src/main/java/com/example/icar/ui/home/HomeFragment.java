package com.example.icar.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.icar.activity.AboutUsActivity;
import com.example.icar.activity.AddPlaceActivity;
import com.example.icar.activity.LicenseActivity;
import com.example.icar.R;
import com.example.icar.activity.SelectBillActivity;
import com.example.icar.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView imageView;
    private CardView cardView_addPlace, cardView_license, cardView_about;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageView = binding.imageViewFrgHome;
        imageView.setImageResource(R.drawable.xe);

        // init controls
        cardView_about = binding.cardViewAboutUs;
        cardView_addPlace = binding.cardViewAddPlace;
        cardView_license = binding.cardViewLicense;

        // handle events
        cardView_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about_onClick();
            }
        });

        cardView_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                license_onClick();
            }
        });

        cardView_addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlace_onClick();
            }
        });
        return root;
    }

    private void addPlace_onClick() {
//        Toast.makeText(getContext(), "Car Booking", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), SelectBillActivity.class));
    }

    private void license_onClick() {
//        Toast.makeText(getContext(), "License", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), LicenseActivity.class));
    }

    private void about_onClick() {
//        Toast.makeText(getContext(), "about", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), AboutUsActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}