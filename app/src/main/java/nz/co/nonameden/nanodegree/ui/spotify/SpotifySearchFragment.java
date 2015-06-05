package nz.co.nonameden.nanodegree.ui.spotify;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import nz.co.nonameden.nanodegree.R;
import nz.co.nonameden.nanodegree.infrastructure.adapters.ArtistListAdapter;
import nz.co.nonameden.nanodegree.infrastructure.loaders.AbsNetworkLoader;
import nz.co.nonameden.nanodegree.infrastructure.loaders.ArtistSearchLoader;
import nz.co.nonameden.nanodegree.ui.base.BaseFragment;
import retrofit.RetrofitError;

/**
 * Created by nonameden on 3/06/15.
 */
public class SpotifySearchFragment extends BaseFragment<SpotifySearchFragment.SpotifySearchCallback>
        implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<ArtistsPager>, AbsNetworkLoader.ErrorCallback {

    private static final int LOADER_ARTIST_SEARCH = 100;
    private static final String ARG_SEARCH_QUERY = "arg-search-query";
    private static final long CHARACTER_WAIT_MS = 200; // ms

    private final Handler mHandler = new Handler();
    private EditText mSearchView;
    private ListView mListView;
    private ArtistListAdapter mAdapter;

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
        mListView.setOnItemClickListener(this);
    }

    private final TextWatcher mSearchQueryListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            mHandler.removeCallbacks(mSearchRunnable);
            mHandler.postDelayed(mSearchRunnable, CHARACTER_WAIT_MS);
        }
    };

    private Runnable mSearchRunnable = new Runnable() {
        @Override
        public void run() {
            onSearchQueryChanged();
        }
    };

    private void onSearchQueryChanged() {
        String searchQuery = mSearchView.getText().toString();
        if(TextUtils.isEmpty(searchQuery)) {
            mAdapter.setItems(null);
        } else {
            Bundle arguments = new Bundle();
            arguments.putString(ARG_SEARCH_QUERY, searchQuery);
            getLoaderManager().restartLoader(LOADER_ARTIST_SEARCH, arguments, this);
        }
    }

    @Override
    protected SpotifySearchCallback initStubCallback() {
        return new SpotifySearchCallback() {
            @Override
            public void onArtistClicked(Artist artist) {}
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Artist artist = mAdapter.getItem(position);
        getCallback().onArtistClicked(artist);
    }

    @Override
    public Loader<ArtistsPager> onCreateLoader(int id, Bundle args) {
        String searchQuery = args.getString(ARG_SEARCH_QUERY);
        return new ArtistSearchLoader(getActivity(), searchQuery, this);
    }

    @Override
    public void onLoadFinished(Loader<ArtistsPager> loader, ArtistsPager data) {
        mAdapter.setItems(data.artists.items);
    }

    @Override
    public void onLoaderReset(Loader<ArtistsPager> loader) {
        mAdapter.setItems(null);
    }

    @Override
    public void onNetworkError(RetrofitError error) {

    }

    public interface SpotifySearchCallback {
        void onArtistClicked(Artist artist);
    }
}
