package activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bikram.myapplication.R;

/**
 * Created by bikram on 1/13/16.
 */
public class AudioBookDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.abookdetail_layout, container, false);
        return rootView;
    }
}
