package nz.co.nonameden.nanodegree.service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;

/**
 * Created by nonameden on 4/06/15.
 */
public class SpotifyService extends MediaBrowserServiceCompat {

    // It is using my MediaBrowserServiceCompat which is based on native source code with small
    // modifications to support previous platforms

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<MediaItemCompat[]> result) {

    }
}
