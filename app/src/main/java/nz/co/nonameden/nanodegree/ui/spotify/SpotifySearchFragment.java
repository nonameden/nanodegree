package nz.co.nonameden.nanodegree.ui.spotify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.infrastructure.adapters.ArtistListAdapter;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import nz.co.nonameden.nanodegree.ui.base.BaseFragment;

/**
 * Created by nonameden on 3/06/15.
 */
public class SpotifySearchFragment extends BaseFragment<SpotifySearchFragment.SpotifySearchCallback> {

    private EditText mSearchView;
    private ListView mListView;
    private ArtistListAdapter mAdapter;
    private final TextWatcher mSearchQueryListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            onSearchQueryChanged(s.toString());
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArtistListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spotify_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSearchView = (EditText) view.findViewById(R.id.search_query);
        mSearchView.addTextChangedListener(mSearchQueryListener);

        mListView = (ListView) view.findViewById(R.id.list);
        mListView.setAdapter(mAdapter);
    }

    private void onSearchQueryChanged(String searchQuery) {
        if(TextUtils.isEmpty(searchQuery)) {
            //TODO: Clean
        } else {
            MediaBrowserCompat mediaBrowser = getCallback().getMediaBrowserCompat();
            if (mediaBrowser != null) {
                mediaBrowser.subscribe("SEARCH_ARTIST:" + searchQuery, new MediaBrowserCompat.SubscriptionCallback() {
                    @Override
                    public void onChildrenLoaded(@NonNull String parentId, @NonNull List<MediaItemCompat> children) {
                        mAdapter.setItems(children);
                    }
                });
            }
        }
    }

    @Override
    protected SpotifySearchCallback initStubCallback() {
        return new SpotifySearchCallback() {
            @Override
            public void onArtistClicked() {}

            @Override
            public MediaBrowserCompat getMediaBrowserCompat() {
                return null;
            }
        };
    }

    public interface SpotifySearchCallback {
        void onArtistClicked();
        MediaBrowserCompat getMediaBrowserCompat();
    }
}
