package com.gummidev.mario.gadgeothek;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
 * Use the {@link LoginFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFrag extends Fragment implements View.OnClickListener {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFrag.
     */
    public static LoginFrag newInstance(String param1, String param2) {
        LoginFrag fragment = new LoginFrag();
        return fragment;
    }

    public LoginFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button regButton = (Button) view.findViewById(R.id.email_sign_in_button);
        regButton.setOnClickListener(this);       // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void login() {
        EditText editText = (EditText) getView().findViewById(R.id.email);
        String email = editText.getText().toString();
        editText = (EditText) getView().findViewById(R.id.password);
        String password = editText.getText().toString();

        LibraryService.login(email, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                NavigationView nav = (NavigationView) getActivity().findViewById(R.id.nvView);
                nav.setCheckedItem(R.id.nav_ausleihe_fragment);
                getActivity().setTitle("Ausleihe");
                getFragmentManager().beginTransaction().replace(R.id.flContent, (Fragment) new LoanFrag()).commit();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        login();
    }
}
