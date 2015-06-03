package nz.co.nonameden.nanodegree.ui.spotify;

import android.app.Fragment;

import nz.co.nonameden.nanodegree.ui.base.SinglePaneActivity;

public class SpotifySearchActivity extends SinglePaneActivity {

    @Override
    protected Fragment onCreatePane() {
        return new SpotifySearchFragment();
    }
}
