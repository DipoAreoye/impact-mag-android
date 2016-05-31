package apps.dipoareoye.impact.ui;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import apps.dipoareoye.impact.R;
import apps.dipoareoye.impact.entities.Category;
import apps.dipoareoye.impact.presenters.ScreenContainerPresenter;
import apps.dipoareoye.impact.presenters.ScreenContainerPresenterImpl;
import apps.dipoareoye.impact.ui.views.ScreenContainerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public class ScreenContainerImpl implements ScreenContainer, ScreenContainerView {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.activity_content)
    ViewGroup container;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    private ScreenContainerPresenter presenter;

    @VisibleForTesting
    public ScreenContainerImpl(ScreenContainerPresenter presenter) {
        this.presenter = presenter;
    }

    public ScreenContainerImpl() {
        presenter = new ScreenContainerPresenterImpl(this);
    }

    @Override
    public ViewGroup bind(AppCompatActivity activity) {
        activity.setContentView(R.layout.activity_main);
        ButterKnife.bind(this, activity);
        setupDrawerLayout(activity);
        initToolbar(activity);
        presenter.loadCategories();
        return container;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    private void setupDrawerLayout(final AppCompatActivity activity) {

        navigationView.getMenu().getItem(0).setChecked(true);

    }

    private void initToolbar(AppCompatActivity activity) {
        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}