package activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bikram.myapplication.R;

import java.io.IOException;

/**
 * Created by bikram on 1/13/16.
 */
public class MediaPlay extends AppCompatActivity {
    private Button play_but,stop_but;
    private  MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);

        play_but = (Button) findViewById(R.id.play);
        stop_but = (Button) findViewById(R.id.stop);

       play_but.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mPlayer = new MediaPlayer();
               String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";
               //  Set the audio stream type of the media player

               mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

               //Set the data source as a content Uri:

               try {
                   mPlayer.setDataSource(url);
               } catch (IllegalArgumentException e) {
                   Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
               } catch (SecurityException e) {
                   Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
               } catch (IllegalStateException e) {
                   Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               //Prepare the player for playback, synchronously:
               try {
                   mPlayer.prepare();
               } catch (IllegalStateException e) {
                   Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
               } catch (IOException e) {
                   Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
               }

               mPlayer.start();

           }
       });

        stop_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                if(mPlayer!=null && mPlayer.isPlaying()){
                    mPlayer.stop();
                }

            }
        });


    }
}
