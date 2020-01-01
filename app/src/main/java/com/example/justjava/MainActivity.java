package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;
    boolean cream_checked,choco_checked;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String summary=createOrderSummary(quantity);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order Summary for: "+name);
        intent.putExtra(Intent.EXTRA_TEXT,summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /*Whipped Cream Checkox*/
    public void checkBox(View v){
        CheckBox whipped_cream=findViewById(R.id.whipped);
        cream_checked=whipped_cream.isChecked();
    }

    /*Chocolate Checkbox*/
    public void chocoCheck(View v){
        CheckBox choco=findViewById(R.id.choclate);
        choco_checked=choco.isChecked();
    }


        public void increment(View view) {
            if (quantity <100) {            //Max number of coffee is 100
                quantity = quantity + 1;
                displayQuantity(quantity);
            }
            if (quantity == 100) {
                // Show an error message as a toast
                Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
                // Exit this method early because there's nothing left to do
                return;
            }
        }

    public void decrement(View view) {
        if(quantity>1){    //Min number of coffee is 1
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
    }
    /**
     calculate price
     */
    private int calculatePrice(){
        int price;
        if(cream_checked&&choco_checked){
            price=quantity*30;
        }
        else if(cream_checked){
            price=quantity*25;
        }
        else if(choco_checked){
            price=quantity*25;
        }
        else{
            price=quantity*20;
        }
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView =findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**Order summary*/
    private String createOrderSummary(int numberOfCoffee){
        boolean creamHasChecked,chocoHasChecked;
        int price=calculatePrice();
        int number=numberOfCoffee;
        if(cream_checked){
            creamHasChecked=true;
        }
        else{
           creamHasChecked=false;
        }
        if(choco_checked){
            chocoHasChecked=true;
        }
        else{
            chocoHasChecked=false;
        }
        EditText user=findViewById(R.id.username_view);
        name=user.getText().toString();
        String priceMessage="Name: "+name+"\nAdd Whipped Cream?"+creamHasChecked+"\nAdd Chocolate?"+chocoHasChecked+"\nQunatity= "+number+"\nTotal: ₹"+price+"\nThank You!";
        return priceMessage;

    }



}