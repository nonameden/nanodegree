package nz.co.nonameden.nanodegree.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by nonameden on 6/06/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
