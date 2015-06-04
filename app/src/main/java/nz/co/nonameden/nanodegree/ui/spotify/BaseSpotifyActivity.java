package nz.co.nonameden.nanodegree.ui.spotify;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import nz.co.nonameden.nanodegree.service.SpotifyService;
import nz.co.nonameden.nanodegree.service.compat.MediaBrowserCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaProvider;

/**
 * Created by nonameden on 4/06/15.
 */
abstract class BaseSpotifyActivity extends AppCompatActivity implements MediaProvider {

    private static final String TAG = "BaseSpotifyActivity";

    private MediaBrowserCompat mMediaBrowser;
    private MediaControllerCompat mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMediaBrowser = new MediaBrowserCompat(this,
                new ComponentName(this, SpotifyService.class), mConnectionCallback, null);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mMediaBrowser.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaController != null) {
            mMediaController.unregisterCallback(mMediaControllerCallback);
        }
        mMediaBrowser.disconnect();
    }

    @Override
    public MediaControllerCompat getMediaControllerCompat() {
        return mMediaController;
    }

    @Override
    public MediaBrowserCompat getMediaBrowserCompat() {
        return mMediaBrowser;
    }

    protected void onMediaControllerConnected() {
    }

    protected void onPlaybackStateChanged(PlaybackStateCompat state) {
    }

    private void onMetadataChanged(MediaMetadataCompat metadata) {
    }

    private void connectToSession(MediaSessionCompat.Token token) {
        try {
            mMediaController = new MediaControllerCompat(this, token);
            mMediaController.registerCallback(mMediaControllerCallback);

            onMediaControllerConnected();
        } catch (RemoteException e) {
            Log.e(TAG, "Can't connect to remote service", e);
        }
    }

    // Callback that ensures that we are showing the controls
    private final MediaControllerCompat.Callback mMediaControllerCallback =
            new MediaControllerCompat.Callback() {
                @Override
                public void onPlaybackStateChanged(PlaybackStateCompat state) {
                    BaseSpotifyActivity.this.onPlaybackStateChanged(state);
                }

                @Override
                public void onMetadataChanged(MediaMetadataCompat metadata) {
                    BaseSpotifyActivity.this.onMetadataChanged(metadata);
                }
            };

    private MediaBrowserCompat.ConnectionCallback mConnectionCallback =
            new MediaBrowserCompat.ConnectionCallback() {
                @Override
                public void onConnected() {
                    MediaSessionCompat.Token token = mMediaBrowser.getSessionToken();
                    connectToSession(token);
                }
            };
}
