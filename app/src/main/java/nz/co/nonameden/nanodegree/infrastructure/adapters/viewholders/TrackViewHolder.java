package nz.co.nonameden.nanodegree.infrastructure.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.infrastructure.models.TrackViewModel;

/**
 * Created by nonameden on 5/06/15.
 */
public class TrackViewHolder {

    private ImageView mImageView;
    private TextView mTrackNameView;
    private final TextView mAlbumNameView;

    public static void create(@NonNull View view) {
        TrackViewHolder viewHolder = new TrackViewHolder(view);
        view.setTag(viewHolder);
    }

    private TrackViewHolder(@NonNull View view) {
        mImageView = (ImageView) view.findViewById(R.id.image);
        mTrackNameView = (TextView) view.findViewById(R.id.track_name);
        mAlbumNameView = (TextView) view.findViewById(R.id.album_name);
    }

    public void bind(TrackViewModel track) {
        Picasso.with(mImageView.getContext())
                .load(track.getSmallImageUrl())
                .placeholder(new ColorDrawable(Color.GRAY))
                .into(mImageView);
        mTrackNameView.setText(track.getName());
        mAlbumNameView.setText(track.getAlbumName());
    }
}
