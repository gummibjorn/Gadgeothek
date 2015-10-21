package com.gummidev.mario.gadgeothek.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.GadgetAdapter;
import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.LoanAdapter;
import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.ReservationAdapter;
import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.ViewHolderGadget;
import com.gummidev.mario.gadgeothek.R;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.domain.Reservation;
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
    private List<Reservation> reslist;

    /**
     * Use this factory method to create a new instance of
     *
     * @return A new instance of fragment LoanFrag.
     */
    public static GadgetFrag newInstance() {
        GadgetFrag fragment = new GadgetFrag();
        return fragment;
    }

    public GadgetFrag() {
        // Required empty public constructor
    }

    public List<Reservation> getReslist() {
        return reslist;
    }

    public void setReslist(List<Reservation> reslist) {
        this.reslist = reslist;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gadgets, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.gadgetRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GadgetAdapter();

        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                boolean isReserved = false;
                List<Gadget> filteredinput = new ArrayList<Gadget>();
                for (Gadget g : input) {
                    for (Reservation res : reslist) {
                        if (res.getGadget().getInventoryNumber().equals(g.getInventoryNumber())) {
                            isReserved = true;
                        }
                    }
                    if (!isReserved) {
                        filteredinput.add(g);
                    }
                    isReserved=false;
                }
                adapter.setGadgets(filteredinput);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });

        return view;
    }
     @Override
    public void onStart() {
        getActivity().setTitle("Gadgets");
        super.onResume();
    }
}
