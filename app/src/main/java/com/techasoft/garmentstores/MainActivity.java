package com.techasoft.garmentstores;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.techasoft.garmentstores.activity.AddBillActivity;
import com.techasoft.garmentstores.adpter.HomeScreenAdapater;
import com.techasoft.garmentstores.model.BillDetail;
import com.techasoft.garmentstores.model.HomeScreen;
import com.techasoft.garmentstores.utility.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHomeScreen;
    private ArrayList<HomeScreen> homeScreenArrayList = new ArrayList<>();
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeList();

        rvHomeScreen = (RecyclerView) findViewById(R.id.billslist);
        rvHomeScreen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHomeScreen.setAdapter(new HomeScreenAdapater(this, homeScreenArrayList));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/


                startActivity(new Intent(MainActivity.this,AddBillActivity.class));
            }
        });
    }

    private void initializeList() {



        mydb = new DBHelper(this);


        for (int i = 0; i <mydb.getAllBillsNo().size(); i++) {

            HomeScreen items = new HomeScreen();
            items.setBillno(mydb.getAllBillsNo().get(i));
            items.setBilldate(mydb.getAllBillsDate().get(i));
            items.setTotal(mydb.getAllBillsAmount().get(i));



            homeScreenArrayList.add(items);

           /* ArrayList<BillDetail> subitem = new ArrayList<>();
            for (int j = 0; j < itemname.length; j++) {
                BillDetail item = new BillDetail();
                item.setItem(itemname[j]);
                item.setPerpiece(perpieceprice[j]);
                item.setQuantity(quantity[j]);
                subitem.add(item);
            }
            items.setBillDetailArrayList(subitem);*/

        }
        mydb.close();
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

}
