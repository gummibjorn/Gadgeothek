package com.gummidev.mario.gadgeothek;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoanFrag extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * @return A new instance of fragment LoanFrag.
     */
    public static LoanFrag newInstance(String param1, String param2) {
        LoanFrag fragment = new LoanFrag();
       return fragment;
    }

    public LoanFrag() {
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
        return inflater.inflate(R.layout.fragment_loan, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
