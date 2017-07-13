package com.techasoft.garmentstores.adpter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.techasoft.garmentstores.R;
import com.techasoft.garmentstores.activity.AddBillActivity;
import com.techasoft.garmentstores.model.Product;
import com.techasoft.garmentstores.utility.SharedPreference;

import java.util.List;

/**
 * Created by JAYDIP PATEL on 12-07-2017.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    private Context context;
    List<Product> products;
    SharedPreference sharedPreference;

    public ProductListAdapter(Context context, List<Product> products) {
        super(context, R.layout.item_row_layout, products);
        this.context = context;
        this.products = products;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        TextView amount;
        ImageButton remove,add,minus;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_layout, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView
                    .findViewById(R.id.item_name);
            holder.productDescTxt = (TextView) convertView
                    .findViewById(R.id.item_quantity);
            holder.productPriceTxt = (TextView) convertView
                    .findViewById(R.id.item_price);
            holder.amount = (TextView) convertView
                    .findViewById(R.id.item_tamount);
            holder.remove = (ImageButton) convertView
                    .findViewById(R.id.remove);
            holder.add = (ImageButton) convertView
                    .findViewById(R.id.decrese);
            holder.minus = (ImageButton) convertView
                    .findViewById(R.id.add_value);
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("Alert");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Are you sure you want to delete this item")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                    sharedPreference.removeFavorite(context, products.get(position));
                                    remove(products.get(position));
                                    context.startActivity(new Intent(context,AddBillActivity.class));

                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }



                });




            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product product = (Product) getItem(position);
        holder.productNameTxt.setText(product.getName());
        holder.productDescTxt.setText(product.getQuantity());
        holder.amount.setText(product.getAmount()+"");
        holder.productPriceTxt.setText(product.getPrice());

       /* holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quan=Integer.valueOf(holder.productDescTxt.getText().toString());
                quan=quan+1;
                holder.amount.setText(Integer.valueOf(holder.productPriceTxt.getText().toString())*quan);
                context.startActivity(new Intent(context,AddBillActivity.class));
            }
        });*/

        if (checkFavoriteItem(product)) {
          /*  holder.favoriteImg.setImageResource(R.drawable.heart_red);
            holder.favoriteImg.setTag("red");*/
        } else {
         /*   holder.favoriteImg.setImageResource(R.drawable.heart_grey);
            holder.favoriteImg.setTag("grey");*/
        }

        return convertView;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Product checkProduct) {
        boolean check = false;
        List<Product> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (Product product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(Product product) {
        super.add(product);
        products.add(product);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Product product) {
        super.remove(product);
        products.remove(product);
        notifyDataSetChanged();
    }
}