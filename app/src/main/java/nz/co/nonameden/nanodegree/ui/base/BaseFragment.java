package nz.co.nonameden.nanodegree.ui.base;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by nonameden on 3/06/15.
 */
public abstract class BaseFragment<T> extends Fragment {

    private final T STUB_CALLBACK = initStubCallback();
    private T mCallback = STUB_CALLBACK;

    public T getCallback() {
        return mCallback;
    }

    protected abstract T initStubCallback();

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback=(T) activity;
        } catch (ClassCastException e) {
            throw new IllegalStateException(
                    activity.getClass().getSimpleName()
                    + " does not implement callback interface for "
                    + getClass().getSimpleName(), e
            );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallback = STUB_CALLBACK;
    }
}
