package ipl.android.sec.com.ipl2017;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private String jsoncontent ;
    private String parsedString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, jsoncontent, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(getApplicationContext(),parsedString,Toast.LENGTH_LONG).show();
            }
        });

        jsoncontent = assetJSONFile("team.json",this);
        parsedString = parsejsonString(jsoncontent);

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

    public  String assetJSONFile (String filename, Context context)  {
        AssetManager manager = context.getAssets();
        InputStream file = null;
        byte[] formArray=null;
        try {
            file = manager.open(filename);
             formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new String(formArray);
    }

    public String parsejsonString(String jsonstr)  {

        StringBuilder str= new StringBuilder();

        if(jsonstr!=null){

            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(jsonstr);
                JSONArray match = jsonObj.getJSONArray("match");
                for(int i=0; i<match.length();i++){
                    JSONObject c = match.getJSONObject(i);
                    str.append(" "+c.getInt("match_No"));
                    str.append(" "+c.getString("team_A"));
                    str.append(" "+c.getString("team_B"));
                    str.append(" "+c.getString("vanue"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Getting JSON Array node

        }

        return str.toString();
    }

}
