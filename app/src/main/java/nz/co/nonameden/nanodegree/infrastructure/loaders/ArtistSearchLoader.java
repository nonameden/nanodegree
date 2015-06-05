package nz.co.nonameden.nanodegree.infrastructure.loaders;

import android.content.Context;
import android.support.annotation.NonNull;

import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * Created by nonameden on 6/06/15.
 */
public class ArtistSearchLoader extends AbsNetworkLoader<ArtistsPager> {

    private final String mSearchQuery;

    public ArtistSearchLoader(Context context, String searchQuery, @NonNull ErrorCallback errorCallback) {
        super(context, errorCallback);

        mSearchQuery = searchQuery;
    }

    @Override
    protected void executeNetworkRequest() {
        mSpotifyService.searchArtists(mSearchQuery, this);
    }
}
