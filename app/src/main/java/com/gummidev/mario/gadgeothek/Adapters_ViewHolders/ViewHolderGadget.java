package com.gummidev.mario.gadgeothek.Adapters_ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mario on 13.10.15.
 */
public class ViewHolderGadget extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView manufacturer;
    public TextView price;
    public Button addButton;
    public View homeView;

    public ViewHolderGadget(View parent, TextView name, TextView manufacturer, TextView price, Button addButton) {
        super(parent);
        this.homeView=parent;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.addButton = addButton;
    }

}
