package nz.co.nonameden.nanodegree.service.compat;

/**
 * Created by nonameden on 4/06/15.
 */
import nz.co.nonameden.nanodegree.service.compat.IMediaBrowserServiceCompatCallbacks;
import android.os.Bundle;

/**
 * Media API allows clients to browse through hierarchy of a user’s media collection,
 * playback a specific media entry and interact with the now playing queue.
 * @hide
 */
oneway interface IMediaBrowserServiceCompat {
    void connect(String pkg, in Bundle rootHints, IMediaBrowserServiceCompatCallbacks callbacks);
    void disconnect(IMediaBrowserServiceCompatCallbacks callbacks);

    void addSubscription(String uri, IMediaBrowserServiceCompatCallbacks callbacks);
    void removeSubscription(String uri, IMediaBrowserServiceCompatCallbacks callbacks);
}
