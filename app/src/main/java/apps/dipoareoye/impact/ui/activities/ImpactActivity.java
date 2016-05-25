package apps.dipoareoye.impact.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

import apps.dipoareoye.impact.ui.ScreenContainer;
import apps.dipoareoye.impact.ui.ScreenContainerImpl;
import butterknife.ButterKnife;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public abstract class ImpactActivity extends AppCompatActivity {
    private ViewGroup mainFrame;
    private ScreenContainer screenContainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenContainer = createScreenContainer();
        mainFrame = screenContainer.bind(this);
        getLayoutInflater().inflate(getLayout(), mainFrame);
        ButterKnife.bind(this);
    }

    ScreenContainer createScreenContainer() {
        return new ScreenContainerImpl();
    }

    @NonNull
    abstract Integer getLayout();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                screenContainer.getDrawerLayout().openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
