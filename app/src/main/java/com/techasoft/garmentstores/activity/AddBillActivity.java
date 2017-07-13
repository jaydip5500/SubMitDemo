package com.techasoft.garmentstores.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.techasoft.garmentstores.MainActivity;
import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.adpter.ItemAdpter;
import com.techasoft.garmentstores.adpter.ProductListAdapter;
import com.techasoft.garmentstores.fragment.AdditemFragment;
import com.techasoft.garmentstores.model.Product;
import com.techasoft.garmentstores.utility.DBHelper;
import com.techasoft.garmentstores.utility.Items;
import com.techasoft.garmentstores.utility.SharedPreference;
import com.techasoft.garmentstores.utility.StorageHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class AddBillActivity  extends AppCompatActivity{


    private FrameLayout container;
    private ListView itemList;
    private TextView billNO,billDate,total;
    private Button generate;
    private LinearLayout container1;
    public static ArrayList<Items> products;
    private SharedPreference sharedPreference;
    private List<Product> favorites;
    private ProductListAdapter productListAdapter;
    private DBHelper mydb;
    public int billNo=01;
    private double totalvalue;
    private boolean visibilityFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container = (FrameLayout) findViewById(R.id.content_frame);





        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(this);

       billNO=(TextView)findViewById(R.id.bill_no); 
       billDate=(TextView)findViewById(R.id.bill_date);
        total=(TextView)findViewById(R.id.total);
        generate=(Button)findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateBillProcess();
            }
        });

        setTotal();
        setBillno();
        setBillDate();

        total.setText("Total Amount:"+String.valueOf(totalvalue));
        itemList=(ListView)findViewById(R.id.items);
        if(favorites!=null) {
            productListAdapter = new ProductListAdapter(this, favorites);
            itemList.setAdapter(productListAdapter);
            generate.setVisibility(View.VISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            visibilityFlag=true;
                generate.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                Fragment fragment = new AdditemFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();


            }
        });
    }

    private void setTotal() {
      if(favorites!=null) {
          for (int j = 0; j < favorites.size(); j++) {


              totalvalue = totalvalue + favorites.get(j).getAmount();

          }
      }
    }

    private void generateBillProcess() {


        mydb = new DBHelper(this);
        mydb.insertBillslistData(billNO.getText().toString(),billDate.getText().toString(),total.getText().toString());
        for (int j=0;j<favorites.size();j++){
            String name=favorites.get(j).getName();

            mydb.insertBillslistItem(billNO.getText().toString(),favorites.get(j).getName(),favorites.get(j).getPrice(),favorites.get(j).getQuantity(),Double.toString(favorites.get(j).getAmount()));
        }
        mydb.close();
        sharedPreference.clearSharedPreferences(this);
        StorageHelper.setBillnoAuto(this,StorageHelper.getBillno(this)+1);

        setBillno();
        startActivity(new Intent(AddBillActivity.this, MainActivity.class));

    }

    private void setBillDate() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy ");
        String strDate = mdformat.format(calendar.getTime());
        billDate.setText("BILL DATE:"+strDate);
    }

    private void setBillno() {


        billNO.setText(""+StorageHelper.getBillno(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (visibilityFlag==true){
            container.setVisibility(View.GONE);
           // startActivity(new Intent(this,AddBillActivity.class));
            onRestart();
        }
        else {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}

