package com.example.justjava;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

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
        displayMessage(summary);
    }

    public void increment(View view) {
        quantity=quantity+1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity=quantity-1;
        displayQuantity(quantity);
    }
    /**
     calculate price
     */
    private int calculatePrice(){
        int price=quantity*10;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView =findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**Order summary*/
    private String createOrderSummary(int numberOfCoffee){
        int price=calculatePrice();
        String name="Name: Aman Singh";
        int number=numberOfCoffee;
        String priceMessage=name+"\nQunatity= "+number+"\nTotal: ₹"+price+"\nThank You!";
        return priceMessage;

    }

}