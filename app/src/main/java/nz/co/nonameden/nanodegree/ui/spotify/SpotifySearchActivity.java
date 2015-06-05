package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;

public class SpotifySearchActivity extends BaseSpotifyActivity
        implements SpotifySearchFragment.SpotifySearchCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_search);
    }

    @Override
    public void onArtistClicked(MediaItemCompat mediaItem) {

    }
}
