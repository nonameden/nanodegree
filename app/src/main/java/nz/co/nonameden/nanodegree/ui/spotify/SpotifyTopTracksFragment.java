package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import nz.co.nonameden.nanodegree.ui.base.BaseFragment;

/**
 * Created by nonameden on 6/06/15.
 */
public class SpotifyTopTracksFragment extends BaseFragment<SpotifyTopTracksFragment.Callback> {

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spotify_top_songs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.list);
    }

    @Override
    protected Callback initStubCallback() {
        return new Callback() {
            @Override
            public MediaBrowserCompat getMediaBrowserCompat() {
                return null;
            }

            @Override
            public void onTrackClicked(MediaItemCompat item) {}
        };
    }

    public interface Callback {
        MediaBrowserCompat getMediaBrowserCompat();
        void onTrackClicked(MediaItemCompat item);
    }
}
