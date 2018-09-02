/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.jastjava;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import com.example.android.jastjava.R;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int javaPrice = 5;
    int wippedCreamPrice = 1;
    int chocolatePrice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int price = javaPrice;
        CheckBox wipped_cream_checkbox = findViewById(R.id.wipped_cream_checkbox);
        boolean hasWippedCream = wipped_cream_checkbox.isChecked();

        CheckBox chocolate_checkbox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate_checkbox.isChecked();

        EditText name_edit_text = findViewById(R.id.name_edit_text);
        String name = name_edit_text.getText().toString();

        if (hasWippedCream) {
            price += wippedCreamPrice;
        }
        if (hasChocolate) {
            price += chocolatePrice;
        }

        String priceMessage = createOrderSummary(price, quantity, hasWippedCream, hasChocolate, name);
        displayMessage(priceMessage);
    }

    /**
     * damatebis funqcia
     */
    public void increment(View view) {

        Context context = getApplicationContext();
        CharSequence text = "თქვენ შეგიძიათ შეუკვეთოთ მაქსიმუმ 100 ყავა";
        int duration = Toast.LENGTH_SHORT;

        if ((quantity + 1) > 100) {
            Toast toast = Toast.makeText(context , text , duration);
            toast.show();
            return;
        } else {
            quantity +=1 ;
        }

        displayQuantity(quantity);
    }

    /**
     * gamoklebis funqcia
     */
    public void decrement(View view) {

        Context context = getApplicationContext();
        CharSequence text = "თქვენ შეგიძიათ შეუკვეთოთ მინიმუმ 1 ყავა";
        int duration = Toast.LENGTH_SHORT;



        if ((quantity - 1) < 1) {
            Toast toast = Toast.makeText(context , text , duration);
            toast.show();
            return;
        } else {
            quantity -=1 ;
        }

        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     * @input quantity of products
     * @input price of products
     */
    private int calculatePrice(int quantity, int price) {
        int priceTotal = quantity * price;
        return priceTotal;
    }

    /**
     * Create finali order summery
     */
    public String createOrderSummary(int price, int quantity, boolean hasWhippedCream, boolean hasChocolate, String name) {


        int totalPrice = calculatePrice(quantity, price);

        String message = "Name: " + name;
        message += "\nAdd Wipped Cream? " + hasWhippedCream;
        message += "\nAdd Chocolate? " + hasChocolate;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + totalPrice;
        message += "\nThank You!";
        return message;
    }
}