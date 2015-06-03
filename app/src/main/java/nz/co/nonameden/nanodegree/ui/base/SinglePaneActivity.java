package nz.co.nonameden.nanodegree.ui.base;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nz.co.nonameden.nanodegree.R;

/**
 * Created by nonameden on 3/06/15.
 *
 * Idea has been taken from Google IO 2014 app
 */
public abstract class SinglePaneActivity extends AppCompatActivity {

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());

        if (savedInstanceState == null) {
            mFragment = onCreatePane();
            mFragment.setArguments(intentToFragmentArguments(getIntent()));
            getFragmentManager().beginTransaction()
                    .add(getContainerId(), mFragment, "single_pane")
                    .commit();
        } else {
            mFragment = getFragmentManager().findFragmentByTag("single_pane");
        }
    }

    /**
     * Called in <code>onCreate</code> when the fragment constituting this activity is needed.
     * The returned fragment's arguments will be set to the intent used to invoke this activity.
     */
    protected abstract Fragment onCreatePane();

    public Fragment getFragment() {
        return mFragment;
    }

    /**
     * Layout ID which have to be inflated
     */
    protected int getContentViewResId() {
        return R.layout.activity_single_pane;
    }

    /**
     * ViewGroup ID which is gonna contain our Fragment
     */
    protected int getContainerId() {
        return R.id.root_container;
    }

    /**
     * Converts an intent into a {@link android.os.Bundle} suitable for use as fragment arguments.
     */
    private static Bundle intentToFragmentArguments(Intent intent) {
        Bundle arguments = new Bundle();
        if (intent == null) {
            return arguments;
        }

        final Uri data = intent.getData();
        if (data != null) {
            arguments.putParcelable("_uri", data);
        }

        final Bundle extras = intent.getExtras();
        if (extras != null) {
            arguments.putAll(intent.getExtras());
        }

        return arguments;
    }
}
