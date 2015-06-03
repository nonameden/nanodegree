package nz.co.nonameden.nanodegree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toast mAppToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.spotify_streamer).setOnClickListener(this);
        findViewById(R.id.scores_app).setOnClickListener(this);
        findViewById(R.id.library_app).setOnClickListener(this);
        findViewById(R.id.build_it_bigger).setOnClickListener(this);
        findViewById(R.id.xyz_reader).setOnClickListener(this);
        findViewById(R.id.capstone).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                onUnhandledViewClicked(v);
                break;
        }
    }

    private void onUnhandledViewClicked(View v) {
        if(v instanceof TextView) {
            CharSequence text = ((TextView) v).getText();
            if(mAppToast!=null) {
                mAppToast.cancel();
            }
            String message = String.format(Locale.ENGLISH, "This button will launch %s", text);
            mAppToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            mAppToast.show();
        }
    }
}
