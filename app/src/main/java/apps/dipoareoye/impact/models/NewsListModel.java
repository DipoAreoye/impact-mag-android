package apps.dipoareoye.impact.models;

import com.android.volley.Response;

import java.util.List;

import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.network.ArticlesListener;

/**
 * Created by dipoareoye on 22/05/2016.
 */
public interface NewsListModel {

    void getNewsList(String url ,ArticlesListener listener);
}
