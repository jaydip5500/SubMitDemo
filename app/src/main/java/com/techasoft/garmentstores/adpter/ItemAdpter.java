package com.techasoft.garmentstores.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.utility.Items;

import java.util.ArrayList;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class ItemAdpter extends ArrayAdapter<String> {

    LayoutInflater inflter;
    Context context;

    public ItemAdpter(Context context, ArrayList<Items> products) {
        super(context, R.layout.item_row_layout);


    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rowView = inflter.inflate(R.layout.item_row_layout, null, true);


        return rowView;
    }
}
