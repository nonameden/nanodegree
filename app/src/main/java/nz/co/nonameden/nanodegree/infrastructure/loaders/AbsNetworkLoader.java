package nz.co.nonameden.nanodegree.infrastructure.loaders;

import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nonameden on 6/06/15.
 */
public abstract class AbsNetworkLoader<T> extends Loader<T> implements Callback<T> {

    protected final SpotifyService mSpotifyService;
    private final ErrorCallback mErrorCallback;
    private T mData;

    public AbsNetworkLoader(Context context, @NonNull ErrorCallback errorCallback) {
        super(context);

        mErrorCallback = errorCallback;
        mSpotifyService = new SpotifyApi(
                Executors.newSingleThreadExecutor(),
                new UiThreadExecutor()
        ).getService();
    }

    @Override
    protected void onStartLoading() {
        if(mData != null)
            deliverResult(mData);

        if(takeContentChanged() || mData == null)
            forceLoad();
    }

    @Override
    protected void onForceLoad() {
        executeNetworkRequest();
    }

    @Override
    protected void onStopLoading() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //TODO: maybe add some handling later
        } else {
            cancelLoad();
        }
    }


    @Override
    public void deliverResult(T data) {
        if(isReset()){
            releaseData(data);
            return;
        }

        T oldData = mData;
        mData = data;

        if(isStarted()) {
            super.deliverResult(data);
        }

        if(oldData != null) {
            releaseData(oldData);
        }
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if(mData != null){
            releaseData(mData);
            mData = null;
        }
    }

    protected void releaseData(T data) {}

    /**
     * Here we should make all our network calls and use this Loader as callback for them
     */
    protected abstract void executeNetworkRequest();

    @Override
    public void success(T data, Response response) {
        if(isAbandoned()) return;
        deliverResult(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void failure(RetrofitError error) {
        if (isAbandoned()) return;

        mErrorCallback.onNetworkError(error);
        deliverResult(null);
    }

    public interface ErrorCallback {
        void onNetworkError(RetrofitError error);
    }

    private class UiThreadExecutor implements Executor {
        private final Handler mHandler = new Handler();

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    }
}