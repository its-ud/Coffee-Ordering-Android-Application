

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int qty=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void increment(View view) {
        qty++;
        displayQuantity(qty);
    }

    public void decrement(View view) {
        qty--;
        if(qty<0) qty=0;
        displayQuantity(qty);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        CheckBox whippedCreamCheckBox = findViewById(R.id.WhippeCream_checkbox);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        EditText Name = findViewById(R.id.name_field);
        String name= Name.getText().toString();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //intent for email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For Coffee for " + name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        ///

        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, name));
    }

    private String createOrderSummary(int price , boolean addWhippedCream , boolean addChocolate, String name)
    {
        String s="";
        s+=name;
        s+="\nQuantity:" + qty;
        s+="\nhasWhippedCream:" + addWhippedCream;
        s+="\nhasChocolate:" + addChocolate;
        s+="\nTotal: $" + price;
        s+="\nThanks You!";
        return s;
    }


    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice() {
        int pr = qty * 5;
        return pr;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}