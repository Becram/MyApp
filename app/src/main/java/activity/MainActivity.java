package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bikram.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerAdapter;
import modal.RecycleItem;

public class MainActivity extends AppCompatActivity {

    private Button audio_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        RecyclerAdapter ca = new RecyclerAdapter(getApplicationContext(),createList());
        recList.setAdapter(ca);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        audio_button= (Button) findViewById(R.id.audio_button);
        audio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(MainActivity.this,MediaPlay.class);
                startActivity(in);
            }
        });
    }

    private List<RecycleItem> createList() {
        String[] testString= {"cool", "thing","Replace"," with"};
        int[]  IconId={R.drawable.add198,R.drawable.business189,R.drawable.checked21,R.drawable.light96};
        List<RecycleItem> result = new ArrayList<>();
        for (int x = 0; x < testString.length; x++) {
            RecycleItem ci = new RecycleItem();
            ci.title = testString[x];
            ci.image=IconId[x];

            result.add(ci);

        }

        return result;


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
