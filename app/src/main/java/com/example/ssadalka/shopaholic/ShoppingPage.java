package com.example.ssadalka.shopaholic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
/**
 * Created by ssadalka on 25-Jul-15.
 */



public class ShoppingPage extends Activity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1,spinner2,spinner3;
    private ListView listView;
    private RequestQueue queue;
    /*public String[] mNavigationDrawerItemTitles;
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppage);
        listView = (ListView) findViewById(R.id.list);

        /*mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];

        drawerItem[0] = new ObjectDrawerItem("Men");
        drawerItem[1] = new ObjectDrawerItem("Women");
        drawerItem[2] = new ObjectDrawerItem("Kids");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);

        mDrawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
         /*   public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
          /*  public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);*/


        //Toast.makeText(getApplicationContext(), "This is Shopping Page", Toast.LENGTH_SHORT).show();

        queue = Volley.newRequestQueue(this);
        spinner1 = (Spinner) findViewById(R.id.gender_spinner);
        spinner2 = (Spinner) findViewById(R.id.dept_spinner);
        spinner3 = (Spinner) findViewById(R.id.clothing_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.dept_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.clothing_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);


        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

    }

    /** Spinner Callbacks */
   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /* Use the following switch-statement if you want to keep all spinner actions/callbacks together */
        /* The same can be done to the onNothingSelected callback */

        String gender = String.valueOf(spinner1.getSelectedItem());
        String dept = String.valueOf(spinner2.getSelectedItem());
        String cloth_type = String.valueOf(spinner3.getSelectedItem());

       if(!dept.equals("Clothing"))
       {
           spinner3.setVisibility(View.GONE);
       }
       else
       {
           spinner3.setVisibility(View.VISIBLE);
       }

        //Toast.makeText(getApplicationContext(),gender+ "...."+dept,Toast.LENGTH_SHORT).show();
        makeGetRequest(gender, dept, cloth_type);
    }
    public void makeGetRequest(String gender, String dept, String cloth_type)
    {
        String url = "http://172.20.10.9:8080/";
        String query_str = "?keyword=";

        if(dept.equals("Clothing"))
        {
            query_str += gender.toLowerCase() + "_" + cloth_type.toLowerCase();
        }
        else if(dept.equals("Footwear"))
        {
            query_str += gender.toLowerCase() + "_shoes" ;
        }
        else
        {
            query_str += gender.toLowerCase() + "_accessories" ;
        }

        url += query_str;
        Log.v("User",url);
// Request a string response from the provided URL.
       // Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray responseArray = new JSONArray(response);
                            ObjectDrawerItem o1[] = new ObjectDrawerItem[responseArray.length()];
                            String values[] = new String[responseArray.length()];
                            String name = "";
                            double lat = 0;
                            double lon = 0;
                            for (int i = 0; i< responseArray.length(); ++i)
                            {

                                name = responseArray.getJSONObject(i).getString("Name");
                                lat = responseArray.getJSONObject(i).getDouble("Lat");
                                lon = responseArray.getJSONObject(i).getDouble("Long");
                                Log.v("user", "name:" +name);
                               o1[i] = new ObjectDrawerItem(name);
                              //  Log.v("user","lat:"+ Double.toString(lat) );
                               // Log.v("user","long:"+ Double.toString(lon));
                                o1[i].distance = o1[i].calculateDistance(lat,lon);
                              //  Log.v("distance","distance:"+Double.toString(o1[i].distance));
                                values[i] = name+"  "+Double.toString(Math.round((o1[i].distance) *10)/10.0)+" kms";
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);


                            // Assign adapter to ListView
                            listView.setAdapter(adapter);

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(), "dint work", Toast.LENGTH_LONG);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            selectItem(position);
        }

        private void selectItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new CreateFragment();
                    break;
                case 1:
                    //fragment = new ReadFragment();
                    break;
                case 2:
                    //fragment = new HelpFragment();
                    break;

                default:
                    break;
            }

            if (fragment != null) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);

                mDrawerLayout.closeDrawer(mDrawerList);

            } else {
                Log.e("ShoppingPageActivity", "Error in creating fragment");
            }
        }
    }

*/


}
