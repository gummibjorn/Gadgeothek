package com.gummidev.mario.gadgeothek;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
* Use the {@link ConfigFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFrag extends Fragment {

    /**
     * Use this factory method to create a new instance of
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
        EditText editText = (EditText) view.findViewById(R.id.serverConf);
        editText.setText(LibraryService.getServerAddress());
        return view;
    }
 @Override
    public void onDetach() {
        super.onDetach();
    }
}
