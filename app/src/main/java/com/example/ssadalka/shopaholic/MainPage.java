package com.example.ssadalka.shopaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by ssadalka on 25-Jul-15.
 */

public class MainPage extends ActionBarActivity{
    ImageView v1,v2,v3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        v1 = (ImageView)findViewById(R.id.bang);
        v2 = (ImageView)findViewById(R.id.mum);
        v3 = (ImageView)findViewById(R.id.del);

        //Toast.makeText(getApplicationContext(),"This is Main Page",Toast.LENGTH_SHORT).show();

    }

    public void GoToShopping(View v){
        //Toast.makeText(getApplicationContext(), "This is Main Page", Toast.LENGTH_SHORT).show();
        switch(v.getId())
        {
            case R.id.bang:
                Intent i1 = new Intent(getApplicationContext(),ShoppingPage.class);
                i1.putExtra("string","Bangalore");
                startActivity(i1);
                break;
            case R.id.mum :
                Intent i2 = new Intent(getApplicationContext(),ShoppingPage.class);
                i2.putExtra("string","Mumbai");
                startActivity(i2);
                break;
            case R.id.del:
                Intent i3 = new Intent(getApplicationContext(),ShoppingPage.class);
                i3.putExtra("string","Delhi");
                startActivity(i3);
                break;

        }
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
