package adapter;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikram.myapplication.R;

import java.util.List;

import modal.RecycleItem;

/**
 * Created by bikram on 1/12/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List <RecycleItem> contactList;
    private Context mContext;

    public RecyclerAdapter(Context context,List<RecycleItem> contactList) {
        this.contactList = contactList;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecycleItem ci = contactList.get(position);
        MyViewHolder.vName.setText(ci.title);
        MyViewHolder.Img.setImageResource(ci.image);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private static TextView  vName;
        private static ImageView Img;


        public MyViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.textView_recycle);
            Img= (ImageView) v.findViewById(R.id.imageView_recycle);
        }
    }
}
