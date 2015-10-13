package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by mario on 06.10.15.
 */
public class ViewHolderReservation extends RecyclerView.ViewHolder {
    public View parent;
    public TextView gadName;
    public TextView resDate;
    public TextView waitingPos;
    public CheckBox isReady;

    public ViewHolderReservation(View parent, TextView gadName, TextView resDate, TextView waitingPos, CheckBox isReady) {
        super(parent);
        this.parent = parent;
        this.gadName = gadName;
        this.resDate = resDate;
        this.waitingPos = waitingPos;
        this.isReady = isReady;
    }
}
