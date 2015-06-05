package nz.co.nonameden.nanodegree.service.remote.callbacks;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import nz.co.nonameden.nanodegree.infrastructure.shared.Constants;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import retrofit.client.Response;

/**
 * Created by nonameden on 6/06/15.
 */
public class ArtistSearchCallback extends BaseMediaBrowserCallback<ArtistsPager> {

    public ArtistSearchCallback(@NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        super(result);
    }

    @Override
    public void success(ArtistsPager artistsPager, Response response) {
        MediaItemCompat[] items = new MediaItemCompat[artistsPager.artists.items.size()];
        for (int i = 0; i < items.length; ++i) {
            Artist artist = artistsPager.artists.items.get(i);
            MediaDescriptionCompat mediaDescription = new MediaDescriptionCompat.Builder()
                    .setMediaId(Constants.Media.ARTIST + artist.id)
                    .setTitle(artist.name)
                    .setIconUri(artist.images.size() < 2 ? null : Uri.parse(artist.images.get(1).url))
                    .build();
            items[i] = new MediaItemCompat(mediaDescription, MediaItemCompat.FLAG_BROWSABLE);
        }

        deliverResult(items);
    }
}
