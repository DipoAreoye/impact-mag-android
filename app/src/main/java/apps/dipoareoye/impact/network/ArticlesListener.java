package apps.dipoareoye.impact.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.List;

import apps.dipoareoye.impact.entities.NewsArticle;

/**
 * Created by dipoareoye on 25/05/2016.
 */
public interface ArticlesListener {

    void articlesCallback(List<NewsArticle> articles);
}
