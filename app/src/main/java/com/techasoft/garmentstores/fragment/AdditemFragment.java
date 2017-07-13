package com.techasoft.garmentstores.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.activity.AddBillActivity;
import com.techasoft.garmentstores.model.Product;
import com.techasoft.garmentstores.utility.Items;
import com.techasoft.garmentstores.utility.SharedPreference;
import com.techasoft.garmentstores.utility.StorageHelper;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.R.id.parent;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class AdditemFragment extends Fragment  {

    private Spinner spinner1;
    int minteger = 0;
    double iamount=00.0;
    double pamount=00.0;
    double famount=00.0;
    private TextView displayInteger,priceItem,amount;
    private Button increse,decrease,add,cancel;


    List<Product> products;
     SharedPreference sharedPreference;

   Context context = getActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreference = new SharedPreference();
        //  initializeFilter();

        /*rvFilterName.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvFilterName.setAdapter(new FilterNameAdapter(getActivity()));*/
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_item_layout, container, false);


        displayInteger = (TextView)view.findViewById(
                R.id.integer_number);
        priceItem=(TextView)view.findViewById(R.id.price);
        amount=(TextView)view.findViewById(R.id.amount);
        increse=(Button)view.findViewById(R.id.increase);
        decrease=(Button)view.findViewById(R.id.decrease);
        add=(Button)view.findViewById(R.id.add);
        cancel=(Button)view.findViewById(R.id.cancel);
        spinner1 = (Spinner)view.findViewById(R.id.spinner_item);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(displayInteger.getText().toString().equals("0")){
                    Toast.makeText(getActivity(),"Select Quantity please",Toast.LENGTH_LONG).show();
                }else {


                    SetProfuct();


                    startActivity(new Intent(getActivity(), AddBillActivity.class));

                }
            }
        });



        increse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minteger==5){
                    display(minteger);
                }
                else {

                minteger = minteger + 1;
                display(minteger);
            }
            }
        });


            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    if (minteger==0){
                        display(minteger);
                    }

                    else {
                        minteger = minteger - 1;
                        display(minteger);
                    }
                }
            });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddBillActivity.class));
            }
        });

        return view;
    }

    private void SetProfuct() {


         Product product1 = new Product(spinner1.getSelectedItem().toString(),priceItem.getText().toString(),displayInteger.getText().toString(),Double.parseDouble( amount.getText().toString()));
      //  Product product1=new Product("hello","dddd","dsddss",4400.44);
        products=new ArrayList<Product>();
        products.add(product1);
        sharedPreference.addFavorite(getActivity(), products.get(0));

    }


    private void display(int number) {

        displayInteger.setText("" + number);

        pamount=Double.parseDouble(priceItem.getText().toString());
        iamount=(double)number;

        famount=pamount*iamount;
        amount.setText("Total Amount: "+Double.toString(famount));
    }



    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            String firstItem = String.valueOf(spinner1.getSelectedItem());



            String[] price = {"200.99","300","400","500","600","700","800","999.99","150","250"};

            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected


                priceItem.setText("Item Price: "+price[i]);



            } else {

                priceItem.setText(price[i]);


            }
        }




        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }


    }
}