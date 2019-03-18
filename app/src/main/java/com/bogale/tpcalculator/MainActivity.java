package com.bogale.tpcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double total = 0.0;
    double sheet_price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** This method is called when button is pressed
     *
     * @param View
     */

    public void calculateTP (View View){
        calculateTotal();
        displayTotal();

    }

    /** calculate total
     *
     */

    private void calculateTotal ()
    {
        total = 0.00;
        sheet_price = 0.00;
        double coupon_one = 0.00;
        double coupon_two = 0.00;
        double n_rool_fromET = 0.00;
        double n_sheet_fromET = 0.00;
        double n_price_fromET = 0;
        double n_coupon1_fromET = 0;
        double n_coupon2_fromET = 0;

        EditText rolls = (EditText)findViewById(R.id.editNumberOfRolls);
        String get_rolls = rolls.getText().toString();
        n_rool_fromET = n_rool_fromET + Double.parseDouble(get_rolls);

        EditText sheets = (EditText)findViewById(R.id.editSheetsPerRoll);
        String get_sheets = sheets.getText().toString();
        n_sheet_fromET = n_sheet_fromET + Double.parseDouble(get_sheets);

        EditText price = (EditText)findViewById(R.id.editPriceBeforediscount);
        String get_price = price.getText().toString();
        n_price_fromET = n_price_fromET + Double.parseDouble(get_price);

        EditText coupon_1 = (EditText)findViewById(R.id.editCoupon_1);
        String get_coupon1 = coupon_1.getText().toString();
        n_coupon1_fromET = n_coupon1_fromET + Double.parseDouble(get_coupon1);

        EditText coupon_2 = (EditText)findViewById(R.id.edit_coupon2);
        String get_coupon2 = coupon_2.getText().toString();
        n_coupon2_fromET = n_coupon2_fromET + Double.parseDouble(get_coupon2);

        total = (n_price_fromET * ((100.0 - n_coupon1_fromET )/100) - n_coupon2_fromET );
        sheet_price = (total / (n_rool_fromET *n_sheet_fromET ))*100;

    }

    /** Display the total price
     *
     */
    private void displayTotal()
    {
        TextView totalTextView = (TextView) findViewById(R.id.amountTotal);
        totalTextView.setText("$ " + String.format("%.2f", total ));
        TextView sheetTextView = (TextView) findViewById(R.id.amountSheet);
        sheetTextView.setText(String.format("%.2f",sheet_price) + " cents" );
    }

}