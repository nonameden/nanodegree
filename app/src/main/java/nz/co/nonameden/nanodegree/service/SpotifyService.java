package nz.co.nonameden.nanodegree.service;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;

import nz.co.nonameden.nanodegree.infrastructure.shared.Constants;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import nz.co.nonameden.nanodegree.service.remote.SpotifyWrapper;

/**
 * Created by nonameden on 4/06/15.
 */
public class SpotifyService extends MediaBrowserServiceCompat {

    // It is using my MediaBrowserServiceCompat which is based on native source code with small
    // modifications to support previous platforms

    private MediaSessionCompat mSession;
    private SpotifyWrapper mSpotifyWrapper;

    @Override
    public void onCreate() {
        super.onCreate();

        mSpotifyWrapper = new SpotifyWrapper();

        // Start a new MediaSession
        ComponentName eventReceiver = new ComponentName(getPackageName(), MediaNotificationManager.class.getName());
        PendingIntent buttonReceiverIntent = PendingIntent.getBroadcast(
                this,
                0,
                new Intent(Constants.ACTION_MEDIA_BUTTONS),
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mSession = new MediaSessionCompat(this, "SpotifyService", eventReceiver, buttonReceiverIntent);
        setSessionToken(mSession.getSessionToken());
        mSession.setCallback(new MediaSessionCallback());
        mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return new BrowserRoot("MEDIA_ROOT", rootHints);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<MediaItemCompat[]> result) {
        mSpotifyWrapper.processMediaBowserRequest(parentId, result);
    }

    private final class MediaSessionCallback extends MediaSessionCompat.Callback {

    }
}
