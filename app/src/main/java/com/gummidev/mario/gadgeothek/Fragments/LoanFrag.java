package com.gummidev.mario.gadgeothek.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.LoanAdapter;
import com.gummidev.mario.gadgeothek.R;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoanFrag extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LoanAdapter adapter;

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

    public static void hide_keyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if(view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(LibraryService.isLoggedIn()) {

            hide_keyboard(getActivity());

            View view = inflater.inflate(R.layout.fragment_loan, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new LoanAdapter();

            recyclerView.setAdapter(adapter);

            LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
                @Override
                public void onCompletion(List<Loan> input) {
                    adapter.setLoans(input);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(String message) {

                }
            });
            return view;
        }else{
            Toast.makeText(getActivity(), "Login first please!", 3).show();
            return null;
        }

    }
}
