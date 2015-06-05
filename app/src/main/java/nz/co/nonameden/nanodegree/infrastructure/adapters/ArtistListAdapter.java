package nz.co.nonameden.nanodegree.infrastructure.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.infrastructure.adapters.viewholders.ArtistViewHolder;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;

/**
 * Created by nonameden on 5/06/15.
 */
public class ArtistListAdapter extends BaseAdapter {

    private List<MediaItemCompat> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MediaItemCompat getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_artist, parent, false);
            ArtistViewHolder.create(convertView);
        }
        ArtistViewHolder viewHolder = (ArtistViewHolder) convertView.getTag();
        viewHolder.bind(mItems.get(position));
        return convertView;
    }

    public void setItems(Collection<MediaItemCompat> items) {
        mItems.clear();
        if(items!=null && items.size()>0) {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

}
