package nz.co.nonameden.nanodegree.infrastructure.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;

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

    public void bind(MediaItemCompat item) {
        MediaDescriptionCompat description = item.getDescription();
        Picasso.with(mImageView.getContext())
                .load(description.getIconUri())
                .placeholder(new ColorDrawable(Color.GRAY))
                .into(mImageView);
        mTextView.setText(description.getTitle());
    }
}
