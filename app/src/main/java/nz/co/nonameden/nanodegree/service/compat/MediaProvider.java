package nz.co.nonameden.nanodegree.service.compat;

import android.support.v4.media.session.MediaControllerCompat;

/**
 * Created by nonameden on 4/06/15.
 */
public interface MediaProvider {
    MediaBrowserCompat getMediaBrowserCompat();
    MediaControllerCompat getMediaControllerCompat();
}
