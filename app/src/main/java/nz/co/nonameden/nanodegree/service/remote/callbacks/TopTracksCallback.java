package nz.co.nonameden.nanodegree.service.remote.callbacks;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaDescriptionCompat;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import nz.co.nonameden.nanodegree.infrastructure.shared.Constants;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import retrofit.client.Response;

/**
 * Created by nonameden on 6/06/15.
 */
public class TopTracksCallback extends BaseMediaBrowserCallback<Tracks> {

    public TopTracksCallback(@NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        super(result);
    }

    @Override
    public void success(Tracks tracks, Response response) {
        MediaItemCompat[] items = new MediaItemCompat[tracks.tracks.size()];
        for (int i = 0; i < items.length; ++i) {
            Track track = tracks.tracks.get(i);
            MediaDescriptionCompat mediaDescription = new MediaDescriptionCompat.Builder()
                    .setMediaId(Constants.Media.TRACK + track.id)
                    .setTitle(track.name)
                    .setSubtitle(track.album.name)
                    .setIconUri(track.album.images.size() < 2 ? null : Uri.parse(track.album.images.get(1).url))
                    .build();
            items[i] = new MediaItemCompat(mediaDescription, MediaItemCompat.FLAG_PLAYABLE);
        }

        deliverResult(items);
    }
}
