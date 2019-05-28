package com.example.restclient;
//package com.vogella.android.recyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restclient.models.Element;
import com.squareup.picasso.Picasso;

public class MyMuseumsRecyclerViewAdapter extends RecyclerView.Adapter<MyMuseumsRecyclerViewAdapter.ViewHolder> {
    private List<Element> values;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public ImageView imageView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.textView1);
            imageView = (ImageView) v.findViewById(R.id.imageView1);
        }
    }

    public void add(int position, Element item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyMuseumsRecyclerViewAdapter(List<Element> myDataset, Context context) {
        values = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyMuseumsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycler_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position).getAdrecaNom();
        holder.txtHeader.setText(name);
        Picasso.with(context).load(values.get(position).getImatge().get(0)).into(holder.imageView);

    }
    //public void Click(Track track){
        //Intent intent = new Intent(context, TrackInfo.class);
        //intent.putExtra(EXTRA_MESSAGE, track);
        //context.startActivity(intent);
    //}
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}