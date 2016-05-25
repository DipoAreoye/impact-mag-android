package apps.dipoareoye.impact.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

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
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArticleRecyclerView adapter;
    private GestureDetectorCompat gestureDetector;
    private NewsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        presenter = createPresenter();
        presenter.create();

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
            presenter = new NewsListPresenterimpl(this,getApplicationContext());
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    }

        @Override
    public void setArticles(List<NewsArticle> articles) {
        adapter = new ArticleRecyclerView(articles);
        recyclerView.setAdapter(adapter);
    }
}
