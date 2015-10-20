package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gummidev.mario.gadgeothek.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Loan;

/**
 * Created by mario on 06.10.15.
 */
public class LoanAdapter extends RecyclerView.Adapter<ViewHolderLoan> {

    private List<Loan> loans = new ArrayList<Loan>();


    @Override
    public ViewHolderLoan onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout_loan, viewGroup, false);
        TextView gadName = (TextView) v.findViewById(R.id.gadName);
        TextView pickupDate = (TextView) v.findViewById(R.id.pickupDate);
        TextView daysLeft = (TextView) v.findViewById(R.id.daysLeft);
        ViewHolderLoan viewHolderLoan = new ViewHolderLoan(v, gadName, pickupDate, daysLeft);


        return viewHolderLoan;
    }

    @Override
    public void onBindViewHolder(final ViewHolderLoan viewHolderLoan, final int i) {
        viewHolderLoan.gadName.setText(loans.get(i).getGadget().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        viewHolderLoan.pickupDate.setText(formatter.format(loans.get(i).getPickupDate()));
        viewHolderLoan.daysLeft.setText(formatter.format(loans.get(i).overDueDate()));
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
