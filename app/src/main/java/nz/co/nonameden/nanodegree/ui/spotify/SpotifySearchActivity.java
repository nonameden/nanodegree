package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kaaes.spotify.webapi.android.models.Artist;
import nz.co.nonameden.nanodegree.R;

public class SpotifySearchActivity extends AppCompatActivity
        implements SpotifySearchFragment.SpotifySearchCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_search);
    }

    @Override
    public void onArtistClicked(Artist artist) {

    }
}
