package com.example.ssadalka.shopaholic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by ssadalka on 25-Jul-15.
 */


public class ShoppingPage extends Activity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppage);

        Toast.makeText(getApplicationContext(), "This is Shopping Page", Toast.LENGTH_SHORT).show();

        spinner1 = (Spinner) findViewById(R.id.gender_spinner);
        spinner2 = (Spinner) findViewById(R.id.dept_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.dept_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(this);
    }

    /** Spinner Callbacks */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /* Use the following switch-statement if you want to keep all spinner actions/callbacks together */
        /* The same can be done to the onNothingSelected callback */
        String gender = String.valueOf(spinner1.getSelectedItem());
        String dept = String.valueOf(spinner2.getSelectedItem());

        Toast.makeText(getApplicationContext(),gender+ "...."+dept,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
