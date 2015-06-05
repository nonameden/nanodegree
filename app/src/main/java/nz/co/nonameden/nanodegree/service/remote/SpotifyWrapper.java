package nz.co.nonameden.nanodegree.service.remote;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import nz.co.nonameden.nanodegree.infrastructure.shared.Constants;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nonameden on 5/06/15.
 */
public class SpotifyWrapper {

    private final SpotifyApi mSpotifyApi;

    public SpotifyWrapper() {
        mSpotifyApi = new SpotifyApi();
    }

    public void processMediaBowserRequest(@NonNull String parentId, @NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        if(parentId.startsWith(Constants.Media.SEARCH_ARTIST)) {
            String searchQuery = parentId.substring(Constants.Media.SEARCH_ARTIST.length());
            onSearchArtist(searchQuery, result);
        }
    }

    private void onSearchArtist(String searchQuery, MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        try {
            result.detach();
            mSpotifyApi.getService().searchArtists(searchQuery, new ArtistSearchCallback(result));
        } catch (RetrofitError e) {
            //TODO: handle it in more elegant way
            result.sendResult(new MediaItemCompat[0]);
        }
    }

    private class ArtistSearchCallback implements Callback<ArtistsPager> {

        private final MediaBrowserServiceCompat.Result<MediaItemCompat[]> mResult;

        public ArtistSearchCallback(@NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
            mResult = result;
        }

        @Override
        public void success(ArtistsPager artistsPager, Response response) {
            MediaItemCompat[] items = new MediaItemCompat[artistsPager.artists.items.size()];
            for(int i=0; i<items.length; ++i) {
                Artist artist = artistsPager.artists.items.get(i);
                MediaDescriptionCompat mediaDescription = new MediaDescriptionCompat.Builder()
                        .setMediaId(Constants.Media.ARTIST + artist.id)
                        .setTitle(artist.name)
                        .setIconUri(artist.images.size() < 2 ? null : Uri.parse(artist.images.get(1).url))
                        .build();
                items[i] = new MediaItemCompat(mediaDescription, MediaItemCompat.FLAG_BROWSABLE);
            }

            mResult.sendResult(items);

        }

        @Override
        public void failure(RetrofitError error) {
            mResult.sendResult(new MediaItemCompat[0]);
        }
    }
}
