package adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikram.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import modal.ListItem;

/**
 * Created by bikram on 1/13/16.
 */
public class ListAdapter extends ArrayAdapter<ListItem> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ListItem> mGridData = new ArrayList<ListItem>();


    public ListAdapter(Context mContext, int layoutResourceId, ArrayList<ListItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    public void setGridData(ArrayList<ListItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.grid_item_title);
            holder.imageView = (ImageView) row.findViewById(R.id.list_image);
            holder.authorTextView= (TextView) row.findViewById(R.id.list_item_auther);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ListItem item = mGridData.get(position);
        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));
        holder.authorTextView.setText(Html.fromHtml(item.getAuthor()));

        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        return row;

    }
    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView authorTextView;
    }
}
