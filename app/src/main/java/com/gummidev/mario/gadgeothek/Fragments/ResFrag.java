package com.gummidev.mario.gadgeothek.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gummidev.mario.gadgeothek.MainActivity;
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
    private ActionBar toolbar;
    private FloatingActionButton FAB;
    private DrawerLayout mDrawer;

    /**
     * Use this factory method to create a new instance of
     *
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

        final View view = inflater.inflate(R.layout.fragment_res, container, false);

        if (LibraryService.isLoggedIn()) {
            recyclerView = (RecyclerView) view.findViewById(R.id.resRecyclerView);

            mDrawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mDrawer.openDrawer(GravityCompat.START);
                }
            });


            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                    LibraryService.deleteReservation(adapter.getReservations().get(viewHolder.getAdapterPosition()), new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean input) {
                            adapter.remove(viewHolder.getAdapterPosition());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "Reservation deleted", 2).show();
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });
                }
            };


            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

            itemTouchHelper.attachToRecyclerView(recyclerView);

            adapter = new ReservationAdapter();


            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

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

            FAB = (FloatingActionButton) view.findViewById(R.id.fab);
            FAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GadgetFrag frag = GadgetFrag.newInstance();
                    getFragmentManager().beginTransaction().replace(R.id.flContent, frag).addToBackStack("Gadgets").commit();
                    frag.setReslist(adapter.getReservations());
                }
            });
            return view;
        } else {
            Toast.makeText(getActivity(), "Login first please!", 3).show();
            return null;
        }

    }

    @Override
    public void onStart() {
        super.onResume();
        getActivity().setTitle("Reservation");
    }
}

