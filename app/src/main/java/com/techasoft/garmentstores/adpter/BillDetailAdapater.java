package com.techasoft.garmentstores.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.activity.BillDetailActivity;
import com.techasoft.garmentstores.model.HomeScreen;
import com.techasoft.garmentstores.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAYDIP PATEL on 13-07-2017.
 */

public class BillDetailAdapater extends RecyclerView.Adapter<BillDetailAdapater.HomeScreenViewHolder> {

    private final Context mContext;
    private final List<Product> homeScreenArrayList;



    public BillDetailAdapater(Context context, List<Product> homeScreens) {
        this.mContext = context;
        this.homeScreenArrayList = homeScreens;
    }

    @Override
    public BillDetailAdapater.HomeScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new BillDetailAdapater.HomeScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillDetailAdapater.HomeScreenViewHolder holder, final int position) {
        holder.tvitemName.setText(homeScreenArrayList.get(position).getName());
        holder.tvitemprice.setText(homeScreenArrayList.get(position).getPrice());
        holder.tvQua.setText(homeScreenArrayList.get(position).getQuantity());
        holder.tvAmount.setText( String.valueOf(homeScreenArrayList.get(position).getAmount()));

    }

    @Override
    public int getItemCount() {
        return homeScreenArrayList.size();
    }

    public class HomeScreenViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvitemName,tvitemprice,tvQua,tvAmount;


        public HomeScreenViewHolder(View itemView) {
            super(itemView);

            tvitemName = (TextView) itemView.findViewById(R.id.itemName);
            tvitemprice = (TextView) itemView.findViewById(R.id.price);
            tvQua = (TextView) itemView.findViewById(R.id.quantity);
            tvAmount = (TextView) itemView.findViewById(R.id.amount);
        }
    }
}
