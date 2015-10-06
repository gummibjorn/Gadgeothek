package com.gummidev.mario.gadgeothek;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegFrag extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegFrag.
     */
    public static RegFrag newInstance() {
        RegFrag fragment = new RegFrag();
        return fragment;
    }

    public RegFrag() {
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
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        Button regButton = (Button) view.findViewById(R.id.button);
        regButton.setOnClickListener(this);
        return view;
    }

    public void register() {
        EditText editText = (EditText) getView().findViewById(R.id.editText);
        String mail = editText.getText().toString();
        editText = (EditText) getView().findViewById(R.id.editText2);
        String name = editText.getText().toString();
        editText = (EditText) getView().findViewById(R.id.editText3);
        String password = editText.getText().toString();
        editText = (EditText) getView().findViewById(R.id.editText4);
        String matnummer = editText.getText().toString();


        LibraryService.register(mail, password, name, matnummer, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        getFragmentManager().beginTransaction().replace(R.id.flContent, (Fragment) new LoginFrag()).commit();
                    }

                    @Override
                    public void onError(String message) {
                        //TODO ERROR HANDLING
                    }
                }

        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        register();
    }
}
