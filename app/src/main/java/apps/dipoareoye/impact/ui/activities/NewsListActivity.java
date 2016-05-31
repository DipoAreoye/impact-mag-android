package apps.dipoareoye.impact.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import apps.dipoareoye.impact.PresenterHolder;
import apps.dipoareoye.impact.R;

import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.presenters.NewsListPresenter;
import apps.dipoareoye.impact.presenters.NewsListPresenterimpl;
import apps.dipoareoye.impact.ui.recyclers.ArticleRecyclerView;
import apps.dipoareoye.impact.ui.views.NewsListView;
import butterknife.Bind;

public class NewsListActivity extends ImpactActivity implements RecyclerView.
        OnItemTouchListener, NewsListView {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    Typeface toolbarTypeface;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArticleRecyclerView adapter;
    private GestureDetectorCompat gestureDetector;
    private NewsListPresenter presenter;
    private String selectedCategory;
    private Resources r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        r = getResources();
        toolbarTypeface = Typeface.createFromAsset(getAssets(), "Humanst-1.ttf");
        toolbarTitle.setTypeface(toolbarTypeface);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        // allows for optimizations if all items are of the same size:
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(this);
        gestureDetector =
                new GestureDetectorCompat(this, new RecyclerViewDemoOnGestureListener());

        selectedCategory = r.getString(R.string.editor_choice);
        presenter = createPresenter();
        presenter.create(selectedCategory);


    }

    @NonNull
    @Override
    Integer getLayout() {
        return R.layout.activity_article_list;
    }

    public NewsListPresenter createPresenter() {
        NewsListPresenter presenter = PresenterHolder.getInstance().getPresenter
                (NewsListPresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new NewsListPresenterimpl(this, getApplicationContext());
        }
        return presenter;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    drawerLayout.closeDrawers();
                    return true;
                }

                String category = menuItem.getTitle().toString();

                selectedCategory = (category == null) ? selectedCategory : category;
                presenter.create(selectedCategory);

                drawerLayout.closeDrawers();
                clearList();

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class RecyclerViewDemoOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            onClick(view);
            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
        }
    }

    private void onClick(View view) {
        int position = recyclerView.getChildLayoutPosition(view);

        position = position < 0 ? 0 : position;

        NewsArticle data = adapter.getItem(position);
        Intent startIntent = new Intent(this, ArticleActivity.class);
        startIntent.putExtra(ArticleActivity.EXTRA_ARTICLE, data.getArticleUrl());
        startIntent.putExtra(ArticleActivity.EXTRA_CATEGORY,data.getArticleCategory());
        startActivity(startIntent);

    }

    private void clearList() {

        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setArticles(List<NewsArticle> articles) {

        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        if (adapter == null) {
            adapter = new ArticleRecyclerView(articles , toolbarTypeface);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.scrollTo(0, 0);
            adapter.refill(articles);
        }

    }

}