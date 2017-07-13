package com.techasoft.garmentstores.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.adpter.ProductListAdapter;
import com.techasoft.garmentstores.model.Product;
import com.techasoft.garmentstores.utility.SharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class ProductListFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public static final String ARG_ITEM_ID = "product_list";

    Activity activity;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;

    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_item_list, container,
                false);
        findViewsById(view);

        setProducts();

        productListAdapter = new ProductListAdapter(activity, products);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(ProductListFragment.this);
       // productListView.setOnItemLongClickListener(this);
        return view;
    }

    private void setProducts() {


        products = new ArrayList<Product>();

    }

    private void findViewsById(View view) {
        productListView = (ListView) view.findViewById(R.id.items);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        Toast.makeText(activity, product.toString(), Toast.LENGTH_LONG).show();
    }

   /* @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                   int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(activity, products.get(position));
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("red");
            button.setImageResource(R.drawable.heart_red);
        } else {
            sharedPreference.removeFavorite(activity, products.get(position));
            button.setTag("grey");
            button.setImageResource(R.drawable.heart_grey);
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }
*/
    @Override
    public void onResume() {
        getActivity().setTitle(R.string.app_name);
        getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }
}