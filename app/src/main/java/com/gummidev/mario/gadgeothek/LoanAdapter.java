package com.gummidev.mario.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Loan;

/**
 * Created by mario on 06.10.15.
 */
public class LoanAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Loan> loans = new ArrayList<Loan>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout, viewGroup, false);
        TextView gadName = (TextView) v.findViewById(R.id.gadName);
        TextView pickupDate = (TextView) v.findViewById(R.id.pickupDate);
        TextView daysLeft = (TextView) v.findViewById(R.id.daysLeft);
        ViewHolder viewHolder = new ViewHolder(v, gadName, pickupDate, daysLeft);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.gadName.setText(loans.get(i).getGadget().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        viewHolder.pickupDate.setText(formatter.format(loans.get(i).getPickupDate()));
        viewHolder.daysLeft.setText(formatter.format(loans.get(i).overDueDate()));
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
