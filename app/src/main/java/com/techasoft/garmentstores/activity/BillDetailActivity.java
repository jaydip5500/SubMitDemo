package com.techasoft.garmentstores.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.techasoft.garmentstores.MainActivity;
import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.adpter.BillDetailAdapater;
import com.techasoft.garmentstores.model.Product;
import com.techasoft.garmentstores.utility.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by JAYDIP PATEL on 13-07-2017.
 */

public class BillDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Context context;
    private RecyclerView rvBillDetail;
    private TextView billno,totalAmount;
    private DBHelper mydb;
    private ArrayList<String> array_itemName;
    private ArrayList<String> array_itemPrice;
    private ArrayList<String> array_itemQua;
    private ArrayList<String> array_itemAmount;
    List<Product> products;
    private String billNO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_detail_activity);


        context = BillDetailActivity.this;


        Intent intent = getIntent();
         billNO=intent.getStringExtra("bill_no");
        String billDATE=intent.getStringExtra("bill_date");
        String billAMOUNT=intent.getStringExtra("bill_total");


        billno=(TextView)findViewById(R.id.bill_no);
        totalAmount=(TextView)findViewById(R.id.total_amount);

        billno.setText("Bill NO:"+billNO+"\n"+billDATE);
        totalAmount.setText(billAMOUNT);


        mydb = new DBHelper(this);




        int size= mydb.getitemNAme(Integer.parseInt(billNO)).size();
        products=new ArrayList<Product>();
        for(int i=0;i<size ; i++){
            Product product1 = new Product( mydb.getitemNAme(Integer.parseInt(billNO)).get(i).toString(),mydb.getitemprice(Integer.parseInt(billNO)).get(i).toString(),mydb.getitemquan(Integer.parseInt(billNO)).get(i).toString(),Double.parseDouble(mydb.getitemamount(Integer.parseInt(billNO)).get(i).toString()));
            //  Product product1=new Product("hello","dddd","dsddss",4400.44);

            products.add(product1);
        mydb.close();

        }

        rvBillDetail = (RecyclerView) findViewById(R.id.rv_bill_detail);
        rvBillDetail.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvBillDetail.setAdapter(new BillDetailAdapater(context,products));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
