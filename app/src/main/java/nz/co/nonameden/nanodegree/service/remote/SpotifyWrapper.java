package nz.co.nonameden.nanodegree.service.remote;

import android.support.annotation.NonNull;

import kaaes.spotify.webapi.android.SpotifyApi;
import nz.co.nonameden.nanodegree.infrastructure.shared.Constants;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import nz.co.nonameden.nanodegree.service.remote.callbacks.ArtistSearchCallback;
import nz.co.nonameden.nanodegree.service.remote.callbacks.TopTracksCallback;

/**
 * Created by nonameden on 5/06/15.
 */
public class SpotifyWrapper {

    private final SpotifyApi mSpotifyApi;

    public SpotifyWrapper() {
        mSpotifyApi = new SpotifyApi();
    }

    public void processMediaBrowserRequest(@NonNull String parentId, @NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        if(parentId.startsWith(Constants.Media.SEARCH_ARTIST)) {
            String searchQuery = parentId.substring(Constants.Media.SEARCH_ARTIST.length());
            onSearchArtist(searchQuery, result);
        } else if (parentId.startsWith(Constants.Media.ARTIST)) {
            String artistId = parentId.substring(Constants.Media.ARTIST.length());
            onGetTopSongs(artistId, result);
        } else {
            result.sendResult(new MediaItemCompat[0]);
        }
    }

    private void onSearchArtist(String searchQuery, MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        result.detach();
        mSpotifyApi.getService().searchArtists(searchQuery, new ArtistSearchCallback(result));
    }

    private void onGetTopSongs(String artistId, MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        result.detach();
        mSpotifyApi.getService().getArtistTopTrack(artistId, new TopTracksCallback(result));
    }

}
