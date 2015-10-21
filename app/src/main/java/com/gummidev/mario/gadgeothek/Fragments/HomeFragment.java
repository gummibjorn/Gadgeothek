package com.gummidev.mario.gadgeothek.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.gummidev.mario.gadgeothek.R;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton login = (ImageButton) view.findViewById(R.id.imageButton2);
        ImageButton loans = (ImageButton) view.findViewById(R.id.imageButton);
        ImageButton reg = (ImageButton) view.findViewById(R.id.imageButton3);
        ImageButton settings = (ImageButton) view.findViewById(R.id.imageButton4);

        final NavigationView nvDrawer = (NavigationView) getActivity().findViewById(R.id.nvView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvDrawer.setCheckedItem(R.id.nav_login_fragment);
                getActivity().setTitle("Login");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, (Fragment) new LoginFrag()).addToBackStack("Login").commit();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvDrawer.setCheckedItem(R.id.nav_registration_fragment);
                getActivity().setTitle("Registration");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, (Fragment) new RegFrag()).addToBackStack("Registration").commit();
            }
        });
        loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvDrawer.setCheckedItem(R.id.nav_ausleihe_fragment);
                getActivity().setTitle("Loans");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, (Fragment) new LoanFrag()).addToBackStack("Loans").commit();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvDrawer.setCheckedItem(R.id.nav_einstellung_fragment);
                getActivity().setTitle("Settings");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, (Fragment) new ConfigFrag()).addToBackStack("Settings").commit();
            }
        });
        return view;
    }
}
