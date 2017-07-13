package com.techasoft.garmentstores.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techasoft.garmentstores.activity.BillDetailActivity;
import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.model.HomeScreen;

import java.util.ArrayList;

/**
 * Created by JAYDIP PATEL on 13-07-2017.
 */

public class HomeScreenAdapater extends RecyclerView.Adapter<HomeScreenAdapater.HomeScreenViewHolder> {

    private final Context mContext;
    private final ArrayList<HomeScreen> homeScreenArrayList;



    public HomeScreenAdapater(Context context, ArrayList<HomeScreen> homeScreens) {
        this.mContext = context;
        this.homeScreenArrayList = homeScreens;
    }

    @Override
    public HomeScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_list_item_row, parent, false);
        return new HomeScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeScreenViewHolder holder, final int position) {
        holder.tvBillNo.setText(homeScreenArrayList.get(position).getBillno());
        holder.tvBillDate.setText(homeScreenArrayList.get(position).getBilldate());
        holder.tvTotal.setText(homeScreenArrayList.get(position).getTotal());
        holder.cvBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BillDetailActivity.class);
                intent.putExtra("bill_no", homeScreenArrayList.get(position).getBillno());
                intent.putExtra("bill_date", homeScreenArrayList.get(position).getBilldate());
                intent.putExtra("bill_total", homeScreenArrayList.get(position).getTotal());
               /* intent.putExtra("item_name",homeScreenArrayList.get(position).getBillDetailArrayList().get(position).getItem());
                intent.putExtra("per_piece",homeScreenArrayList.get(position).getBillDetailArrayList().get(position).getPerpiece());
                intent.putExtra("quantity",homeScreenArrayList.get(position).getBillDetailArrayList().get(position).getQuantity());
               */ mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeScreenArrayList.size();
    }

    public class HomeScreenViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvBillNo, tvBillDate, tvTotal;
        private final CardView cvBill;

        public HomeScreenViewHolder(View itemView) {
            super(itemView);
           cvBill = (CardView) itemView.findViewById(R.id.cv_bill);
            tvBillNo = (TextView) itemView.findViewById(R.id.bill_no);
            tvBillDate = (TextView) itemView.findViewById(R.id.bill_date);
            tvTotal = (TextView) itemView.findViewById(R.id.bill_amount);
        }
    }
}
