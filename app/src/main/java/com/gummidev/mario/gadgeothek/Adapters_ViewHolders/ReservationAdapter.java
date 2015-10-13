package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gummidev.mario.gadgeothek.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Reservation;

/**
 * Created by mario on 06.10.15.
 */
public class ReservationAdapter extends RecyclerView.Adapter<ViewHolderReservation> {

    private List<Reservation> reservations = new ArrayList<Reservation>();


    @Override
    public ViewHolderReservation onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout_reservation, viewGroup, false);
        TextView gadName = (TextView) v.findViewById(R.id.resgadName);
        TextView resDate = (TextView) v.findViewById(R.id.resDate);
        TextView waitPos = (TextView) v.findViewById(R.id.waitPos);
        CheckBox isReady = (CheckBox) v.findViewById(R.id.checkBox);
        ViewHolderReservation  viewHolderReservation = new ViewHolderReservation(v, gadName, resDate, waitPos, isReady);
        return viewHolderReservation;
    }

    @Override
    public void onBindViewHolder(final ViewHolderReservation viewHolderReservation, final int i) {
        viewHolderReservation.gadName.setText(reservations.get(i).getGadget().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        viewHolderReservation.resDate.setText(formatter.format(reservations.get(i).getReservationDate()));
        viewHolderReservation.waitingPos.setText(new Integer(reservations.get(i).getWaitingPosition()).toString());
        viewHolderReservation.isReady.setChecked(reservations.get(i).isReady());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public void remove(int i){
        reservations.remove(i);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
