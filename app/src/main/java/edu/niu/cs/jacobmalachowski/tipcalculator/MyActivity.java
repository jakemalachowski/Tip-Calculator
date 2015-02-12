package edu.niu.cs.jacobmalachowski.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


public class MyActivity extends Activity {

    private SeekBar skbar;
    private TextView percentageTV;
    private TextView totalBill;
    private TextView numPeople;
    private TextView costPP;
    private DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        skbar = (SeekBar)findViewById(R.id.tipSB);
        percentageTV = (TextView)findViewById(R.id.percentageTV);
        totalBill = (TextView)findViewById(R.id.totalET);
        numPeople = (TextView)findViewById(R.id.numPeople);
        costPP = (TextView)findViewById(R.id.costPPResultTV);


        totalBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    setTotalPerPerson();
                } catch (NumberFormatException e) {
                    costPP.setText(String.valueOf(0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        percentageTV.setText(skbar.getProgress() + " %");

        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentageTV.setText(skbar.getProgress() + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                try {
                    setTotalPerPerson();
                } catch (NumberFormatException e) {
                    costPP.setText(String.valueOf(0));
                }

            }
        });

        numPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    setTotalPerPerson();
                } catch (NumberFormatException e) {
                    costPP.setText(String.valueOf(0));
                }

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTotalPerPerson()
    {
        //get values from the TextViews
        double totalBillValue = Double.parseDouble(totalBill.getText().toString());
        double tipPercentageValue = (double)skbar.getProgress() / 100;
        int numPeopleValue = Integer.parseInt(numPeople.getText().toString());


        double tip = totalBillValue * tipPercentageValue;
        double perPersonValue = ((totalBillValue + tip) / numPeopleValue);
        costPP.setText(df.format(perPersonValue));
    }
}
