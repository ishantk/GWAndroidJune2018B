package com.auribises.gw2018b;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class JSONParserActivity extends AppCompatActivity {

    StringBuffer response = new StringBuffer();
    BookFetchTask task;

    ArrayList<Book> bookList = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparser);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        task = new BookFetchTask();
        task.execute();
    }


    /*class MyTask extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }*/

    class BookFetchTask extends AsyncTask{

        // What so ever we want to run async with the activity write it in doInBackground method
        // this method below cannot perform any changes on UI
        // eg: Don't creates Toast in this method
        @Override
        protected Object doInBackground(Object[] objects) {

            try{

                // Create the URL
                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");

                // Send the Request to the Server
                URLConnection urlConnection = url.openConnection();

                // Read response from the Server
                InputStream inputStream = urlConnection.getInputStream();

                // Read the data from Server line by line
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                String line = "";
                while( (line = buffer.readLine()) != null ){
                    response.append(line+"\n");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        // In order to update UI, we use this method
        // Will be executed only after doInBackground has finished its operation
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(JSONParserActivity.this,"Response: "+response.toString(),Toast.LENGTH_LONG).show();

            // Parse JSON Data as Java Objects

            try {

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    Book book = new Book();
                    book.price = jObj.getString("price");
                    book.name = jObj.getString("name");
                    book.author = jObj.getString("author");

                    bookList.add(book);
                }


                // Code here to add data in adapter
                for(Book book : bookList){
                    Log.i("BOOK",book.toString());
                    adapter.add(book.toString());
                }




                listView.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


}
