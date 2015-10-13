package com.gummidev.mario.gadgeothek.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.gummidev.mario.gadgeothek.OnFragmentInteractionListener;
import com.gummidev.mario.gadgeothek.R;
import com.gummidev.mario.gadgeothek.Adapters_ViewHolders.ReservationAdapter;

import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Reservation;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ResFrag extends Fragment {
   private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ReservationAdapter adapter;
    private Toolbar toolbar;
    private FloatingActionButton FAB;

    /**
     * Use this factory method to create a new instance of
     * @return A new instance of fragment ResFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ResFrag newInstance(String param1, String param2) {
        ResFrag fragment = new ResFrag();
        return fragment;
    }

    public ResFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_res, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.resRecyclerView);

        FAB = (FloatingActionButton) view.findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Gadgets");
                getFragmentManager().beginTransaction().replace(R.id.flContent, (Fragment) new GadgetFrag()).commit();
            }
        });

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ReservationAdapter();

        recyclerView.setAdapter(adapter);

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                adapter.setReservations(input);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });

        return view;
    }
}
