package com.gummidev.mario.gadgeothek.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.GadgetAdapter;
import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.LoanAdapter;
import com.gummidev.mario.gadgeothek.R;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GadgetFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GadgetFrag extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private GadgetAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * @return A new instance of fragment LoanFrag.
     */
    public static GadgetFrag newInstance(String param1, String param2) {
        GadgetFrag fragment = new GadgetFrag();
       return fragment;
    }

    public GadgetFrag() {
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
        
        View view  = inflater.inflate(R.layout.fragment_gadgets, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.gadgetRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GadgetAdapter();

        recyclerView.setAdapter(adapter);

        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                adapter.setGadgets(input);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });

        return view;
    }
}
