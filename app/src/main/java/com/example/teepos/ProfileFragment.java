package com.example.teepos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment {
    private Button btn_tentang, btn_postingan, btn_notifikasi;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_tentang = (Button) getView().findViewById(R.id.btn_tentang);
        btn_postingan = (Button) getView().findViewById(R.id.btn_postingan);
        btn_notifikasi = (Button) getView().findViewById(R.id.btn_notif);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameProfile, fragment);
        fragmentTransaction.commit();
    }
}