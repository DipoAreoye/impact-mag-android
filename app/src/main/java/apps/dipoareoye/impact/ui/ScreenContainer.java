package apps.dipoareoye.impact.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public interface ScreenContainer {

    ViewGroup bind(AppCompatActivity activity);

    /**
     * Returns the drawerLayout of this window.
     *
     */
    DrawerLayout getDrawerLayout();
}
