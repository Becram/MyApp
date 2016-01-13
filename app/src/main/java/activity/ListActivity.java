package activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
//import org.apache.http.client.HttpClient;


import com.example.bikram.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import adapter.ListAdapter;
import modal.ListItem;

/**
 * Created by bikram on 1/13/16.
 */
public class ListActivity extends AppCompatActivity {
    private static final String TAG = ListActivity.class.getSimpleName();
    private GridView mGridView;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private ListAdapter mGridAdapter;
    private ArrayList<ListItem> mGridData;
    private String FEED_URL = "http://pustakalaya.org//api/listAllAudioBook/1/110/author/asc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        Toast.makeText(getApplication(),"listview",Toast.LENGTH_LONG).show();


        mListView = (ListView) findViewById(R.id.listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        mGridData = new ArrayList<>();
        mGridAdapter = new ListAdapter(this, R.layout.list_layout_row, mGridData);
        mListView.setAdapter(mGridAdapter);
        new AsyncHttpTask().execute(FEED_URL);
        mProgressBar.setVisibility(View.VISIBLE);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Get item at position
                ListItem item = (ListItem) parent.getItemAtPosition(position);

                //Pass the image title and url to DetailsActivity
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                //Start details activity
                startActivity(intent);
            }
        });


    }


    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;

            try {
                // Create Apache HttpClient
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
//                int statusCode = httpResponse.getStatusLine().getStatusCode();
                URL url = new URL(FEED_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int statusCode=conn.getResponseCode();
                Log.d("response code", String.valueOf(statusCode));

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    parseResult(String.valueOf(response));
                    Log.d("respose", String.valueOf(response));
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            if (result == 1) {
                mGridAdapter.setGridData(mGridData);
            } else {
                Toast.makeText(ListActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("content");
            String countArray = response.getString("count");
            Log.d("counting",String.valueOf(countArray));


            Log.d("post",String.valueOf(posts));
            ListItem item;
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");
                String image = post.optString("cover");
                String author=post.optString("author");
                String ImageUrl="http://pustakalaya.org"+image;
//                Log.d("ImageURL",ImageUrl);
                item = new ListItem();
                item.setTitle(title);
                item.setImage(ImageUrl);
                item.setAuthor(author);
//                JSONArray attachments = post.getJSONArray("attachments");
//                if (null != attachments && attachments.length() > 0) {
//                    JSONObject attachment = attachments.getJSONObject(0);
//                    if (attachment != null)
//                        item.setImage(attachment.getString("url"));
//                }
                mGridData.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
