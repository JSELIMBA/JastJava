/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.jastjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        createOrderSummary(price, quantity, hasWippedCream, hasChocolate, name);

    }

    /**
     * damatebis funqcia
     */
    public void increment(View view) {

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.max_quantity);
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
        CharSequence text = getString(R.string.min_quantity);
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
    public void createOrderSummary(int price, int quantity, boolean hasWhippedCream, boolean hasChocolate, String name) {


        int totalPrice = calculatePrice(quantity, price);

        String message = "\n" + getString(R.string.name) + name;
        message += "\n" + getString(R.string.add_wipped_cream) + hasWhippedCream;
        message += "\n" + getString(R.string.add_chocolate) + hasChocolate;
        message += "\n" + getString(R.string.text_quantity_message) + quantity;
        message += "\n" + getString(R.string.total) + totalPrice;
        message += "\n" + getString(R.string.thanckyou);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , getString(R.string.java_order_subject) + name);
        intent.putExtra(Intent.EXTRA_TEXT , message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}