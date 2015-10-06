package com.gummidev.mario.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.hsr.mge.gadgeothek.service.LibraryService;

/**
 * Created by mario on 06.10.15.
 */
public class LoanAdapter extends RecyclerView.Adapter<ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //TODO: RecycleView for loaned Gadgets
        /*LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout, viewGroup, false);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ViewHolder viewHolder = new ViewHolder(v, textView);
        return viewHolder*/
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
