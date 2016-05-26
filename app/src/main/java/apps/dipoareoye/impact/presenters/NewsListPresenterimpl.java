package apps.dipoareoye.impact.presenters;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.models.NewsListModel;
import apps.dipoareoye.impact.models.NewsListModelImpl;
import apps.dipoareoye.impact.network.ArticlesListener;
import apps.dipoareoye.impact.ui.views.NewsListView;

/**
 * Created by dipoareoye on 22/05/2016.
 */
public class NewsListPresenterimpl implements NewsListPresenter {
    private NewsListView view;
    private final NewsListModel model;
    private List<NewsArticle> articleList;

    public NewsListPresenterimpl(NewsListView view , Context context) {
        this(view, new NewsListModelImpl(context));
    }

    NewsListPresenterimpl(NewsListView view, NewsListModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void create() {
        if (articleList == null) {
            articleList = new ArrayList<>();
            model.getNewsList(new ArticlesListener() {
                @Override
                public void articlesCallback(List<NewsArticle> articles) {
                    articleList.addAll(articles);
                    view.setArticles(articleList);
                }
            });
        } else {
            view.setArticles(articleList);
        }
    }

    @Override
    public void setView(NewsListView view) {
        this.view = view;
    }

}
