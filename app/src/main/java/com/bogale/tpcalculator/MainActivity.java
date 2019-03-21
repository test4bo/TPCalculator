package com.bogale.tpcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double total = 0.0;
    double sheet_price = 0.0;
    String errorMessage = "Tofu Vic buy price is 0.25 cents per sheet.";

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

    /** Reset button is pressed
     *
     */
    public void resetAll (View View){
        resetTotal();
        resetDisplay();
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
        double aNumber = ParseDouble(get_rolls);
        if (aNumber == 0)  {
            errorMessage = "errorMessage = Frst 3 fields cannot be blank!";
        }
        else {
            n_rool_fromET = n_rool_fromET + aNumber;
        }
        EditText sheets = (EditText)findViewById(R.id.editSheetsPerRoll);
        String get_sheets = sheets.getText().toString();
        double bNumber = ParseDouble(get_sheets);
        if (bNumber == 0) {
            errorMessage = "errorMessage = Frst 3 fields cannot be blank!";
        }
        else {
            n_sheet_fromET = n_sheet_fromET + bNumber;
        }

        EditText price = (EditText)findViewById(R.id.editPriceBeforediscount);
        String get_price = price.getText().toString();
        double cNumber = ParseDouble(get_price);
        if (cNumber == 0)  {
            errorMessage = "Frst 3 fields cannot be blank!";
        }
        else{
            n_price_fromET = n_price_fromET + cNumber;
        }

        EditText coupon_1 = (EditText)findViewById(R.id.editCoupon_1);
        String get_coupon1 = coupon_1.getText().toString();
        n_coupon1_fromET = n_coupon1_fromET + ParseDouble(get_coupon1);

        EditText coupon_2 = (EditText)findViewById(R.id.editCoupon_2);
        String get_coupon2 = coupon_2.getText().toString();
        n_coupon2_fromET = n_coupon2_fromET + ParseDouble(get_coupon2);

        total = (n_price_fromET * ((100.0 - n_coupon1_fromET )/100) - n_coupon2_fromET );
        if (total > 0) {
            sheet_price = (total / (n_rool_fromET * n_sheet_fromET)) * 100;
        }
        else sheet_price = 0;

    }

    /** Handle empty input
     *
     */

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -999.0;
            }
        }
        else
            return 0;

        }
    /** Display the total price
     *
     */
    private void resetDisplay()
    {
        TextView numberRolls = findViewById(R.id.editNumberOfRolls);
        numberRolls.setText("");
        TextView numberSheets = findViewById(R.id.editSheetsPerRoll);
        numberSheets.setText("");
        TextView priceActual = findViewById(R.id.editPriceBeforediscount);
        priceActual.setText("");
        TextView coupon_01 = findViewById(R.id.editCoupon_1);
        coupon_01.setText("");
        TextView coupon_02 = findViewById(R.id.editCoupon_2);
        coupon_02.setText("");
        TextView errorTextView = findViewById(R.id.errorMessage);
        errorTextView.setTextColor(Color.BLACK);
        errorTextView.setText("Fill some number and tap Calculate");
        TextView totalTextView = findViewById(R.id.amountTotal);
        totalTextView.setText("");
        TextView sheetTextView = findViewById(R.id.amountSheet);
        sheetTextView.setText("");

    }

    private void displayTotal()
    {
        TextView totalTextView = findViewById(R.id.amountTotal);
        totalTextView.setText("$ " + String.format("%.2f", total ));
        TextView sheetTextView = findViewById(R.id.amountSheet);
        sheetTextView.setText(String.format("%.2f",sheet_price) + " cents" );
        TextView errorTextView = findViewById(R.id.errorMessage);
        errorTextView.setTextColor(Color.RED);
        errorTextView.setText(errorMessage);
    }

    private void resetTotal(){
        total = 0.0;
        sheet_price = 0.0;

    }


}