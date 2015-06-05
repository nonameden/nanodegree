package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v7.app.AppCompatActivity;

import nz.co.nonameden.nanodegree.R;

/**
 * Created by nonameden on 6/06/15.
 */
public class SpotifyTopTracksActivity extends AppCompatActivity {

    public static final String EXTRA_ARTIST_MEDIA_DESCRIPTION = "extra-artist-media-description";

    private MediaDescriptionCompat mArtistMediaDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Just because in feature stage we gonna support tablet with 2-pane
        // we gonna pass artist directly and nit through argument

        setContentView(R.layout.activity_spotify_top_tracks);

        mArtistMediaDescription = getIntent().getParcelableExtra(EXTRA_ARTIST_MEDIA_DESCRIPTION);
        assert mArtistMediaDescription!=null : "Artist Media Description is required";
    }
}
