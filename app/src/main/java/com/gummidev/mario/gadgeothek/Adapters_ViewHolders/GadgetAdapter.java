package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gummidev.mario.gadgeothek.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

/**
 * Created by mario on 06.10.15.
 */
public class GadgetAdapter extends RecyclerView.Adapter<ViewHolderGadget> {

    private List<Gadget> gadgets = new ArrayList<>();


    @Override
    public ViewHolderGadget onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.rowlayout_gadget, viewGroup, false);
        TextView gadName = (TextView) v.findViewById(R.id.gad_gadName);
        TextView manufacturer = (TextView) v.findViewById(R.id.gadManufacturer);
        TextView price = (TextView) v.findViewById(R.id.gadPrice);
        Button button = (Button) v.findViewById(R.id.addReservation);
        ViewHolderGadget viewHolderGadget = new ViewHolderGadget(v, gadName, manufacturer, price, button);
        return viewHolderGadget;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGadget holder, final int i) {
        holder.name.setText(gadgets.get(i).getName());
        holder.manufacturer.setText(gadgets.get(i).getManufacturer());
        holder.price.setText(new Double(gadgets.get(i).getPrice()).toString());
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LibraryService.reserveGadget(gadgets.get(i), new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            Toast.makeText(v.getContext(), "Reservation successful", 2).show();
                        } else {
                            Toast.makeText(v.getContext(), "Too many reservations or allready reserved", 2).show();
                        }
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public void setGadgets(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }
}
