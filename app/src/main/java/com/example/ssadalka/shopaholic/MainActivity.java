package com.example.ssadalka.shopaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    EditText eid,pwd;
    Button sin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eid = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.password);
        sin = (Button)findViewById(R.id.signbutton);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eid.getText().toString().equals("mygola") && pwd.getText().toString().equals("mygola")) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    try {
                        Intent myintent = new Intent(getApplicationContext(), MainPage.class);

                        startActivity(myintent);
                    }
                    catch(NullPointerException e)
                    {
                        Toast.makeText(getApplicationContext(),"No Intent",Toast.LENGTH_SHORT).show();

                    }

                }
                else
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
            }
        });
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
