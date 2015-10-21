package com.gummidev.mario.gadgeothek.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gummidev.mario.gadgeothek.R;

import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFrag extends Fragment {

    /**
     * Use this factory method to create a new instance of
     *
     * @return A new instance of fragment ConfigFrag.
     */
    public static ConfigFrag newInstance(String param1, String param2) {
        ConfigFrag fragment = new ConfigFrag();
        return fragment;
    }

    public ConfigFrag() {
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

        View view = inflater.inflate(R.layout.fragment_config, container, false);
        final EditText editText = (EditText) view.findViewById(R.id.serverConf);
        editText.setText(LibraryService.getServerAddress());
        Button save = (Button) view.findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.setServerAddress(editText.getText().toString());
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("remote_gadgeothek", editText.getText().toString());
                editor.commit();
                Toast.makeText(getActivity(), "Server Changed", Toast.LENGTH_SHORT).show();
                getActivity().setTitle("Login");
                getFragmentManager().beginTransaction().replace(R.id.flContent, (Fragment) new LoginFrag()).addToBackStack("Login").commit();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        getActivity().setTitle("Settings");
        super.onResume();
    }
}
