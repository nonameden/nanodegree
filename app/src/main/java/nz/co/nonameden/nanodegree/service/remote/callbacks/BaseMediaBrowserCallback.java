package nz.co.nonameden.nanodegree.service.remote.callbacks;

import android.support.annotation.NonNull;

import nz.co.nonameden.nanodegree.service.compat.MediaBrowserServiceCompat;
import nz.co.nonameden.nanodegree.service.compat.MediaItemCompat;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by nonameden on 6/06/15.
 */
abstract class BaseMediaBrowserCallback<T> implements Callback<T> {

    private final MediaBrowserServiceCompat.Result<MediaItemCompat[]> mResult;

    public BaseMediaBrowserCallback(@NonNull MediaBrowserServiceCompat.Result<MediaItemCompat[]> result) {
        mResult = result;
    }

    @Override
    public void failure(RetrofitError error) {
        mResult.sendResult(new MediaItemCompat[0]);
    }

    protected void deliverResult(MediaItemCompat[] items) {
        mResult.sendResult(items);
    }
}
