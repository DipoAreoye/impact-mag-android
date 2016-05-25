package apps.dipoareoye.impact.models;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.network.ArticlesListener;

/**
 * Created by dipoareoye on 25/05/2016.
 */
public class NewsListModelImpl implements NewsListModel {

    private Context context;
    private String url = "http://impactnottingham.com/wp-json/wp/v2/posts?filter%5Bnews%5D=lead%20articles&page=1&per_page=30";

    public NewsListModelImpl(Context context){
        this.context = context;
    }

    @Override
    public void getNewsList(final ArticlesListener articleListener) {

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final List<NewsArticle> articles = parseResponse(response);
                articleListener.articlesCallback(articles);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsArrayRequest);
    }

    private List<NewsArticle> parseResponse(JSONArray response) {
        final List<NewsArticle> items = new ArrayList<>();

        for (int i = 0 ; i < response.length(); i++ ) {

            imagePath =

        }


    }


}


