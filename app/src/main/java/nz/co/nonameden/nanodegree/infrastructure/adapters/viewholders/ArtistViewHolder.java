package nz.co.nonameden.nanodegree.infrastructure.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kaaes.spotify.webapi.android.models.Artist;
import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.infrastructure.utils.SpotifyImageHelper;

/**
 * Created by nonameden on 5/06/15.
 */
public class ArtistViewHolder {

    private ImageView mImageView;
    private TextView mTextView;

    public static void create(@NonNull View view) {
        ArtistViewHolder viewHolder = new ArtistViewHolder(view);
        view.setTag(viewHolder);
    }

    private ArtistViewHolder(@NonNull View view) {
        mImageView = (ImageView) view.findViewById(R.id.image);
        mTextView = (TextView) view.findViewById(R.id.text);
    }

    public void bind(Artist artist) {
        String imageUrl = SpotifyImageHelper.getBestImageUrl(artist.images, mImageView.getHeight());
        Picasso.with(mImageView.getContext())
                .load(imageUrl)
                .placeholder(new ColorDrawable(Color.GRAY))
                .into(mImageView);
        mTextView.setText(artist.name);
    }
}
