package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mario on 06.10.15.
 */
public class ViewHolderLoan extends RecyclerView.ViewHolder {
    public View parent;
    public TextView gadName;
    public TextView pickupDate;
    public TextView daysLeft;

    public ViewHolderLoan(View parent, TextView gadName, TextView pickupDate, TextView daysLeft) {
        super(parent);
        this.parent = parent;
        this.gadName = gadName;
        this.pickupDate = pickupDate;
        this.daysLeft = daysLeft;
    }
}
