package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.co.nonameden.nanodegree.ui.base.BaseFragment;

/**
 * Created by nonameden on 3/06/15.
 */
public class SpotifySearchFragment extends BaseFragment<SpotifySearchFragment.SpotifySearchCallback> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected SpotifySearchCallback initStubCallback() {
        return new SpotifySearchCallback() {
            @Override
            public void onArtistClicked() {}
        };
    }

    public interface SpotifySearchCallback {
        void onArtistClicked();
    }
}