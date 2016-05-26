package apps.dipoareoye.impact.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import apps.dipoareoye.impact.entities.Const;
import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.network.ArticlesListener;

/**
 * Created by dipoareoye on 25/05/2016.
 */
public class NewsListModelImpl implements NewsListModel {

    private Context context;
    private String url = "http://impactnottingham.com/wp-json/wp/v2/posts?filter%5Bnews%5D=lead%20articles&page=1&per_page=30";
    private JSONArray jsonArticles;
    private ArrayList<NewsArticle> articles = new ArrayList<>();
    private ArticlesListener articlesListener;

    public NewsListModelImpl(Context context){
        this.context = context;
    }

    @Override
    public void getNewsList(final ArticlesListener articleListener) {
        this.articlesListener = articleListener;

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                jsonArticles = response;
                parseArticleResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsArrayRequest);
    }

    private void parseArticleResponse() {

        int count = articles.size();

        if (count == jsonArticles.length()){
            articlesListener.articlesCallback(articles);
            return;
        }

        try {
            NewsArticle article  = new NewsArticle();

            String title = jsonArticles.getJSONObject(count).getJSONObject(Const.ARTICLE_TITLE_KEY).getString("rendered");
            article.setArticleTitle(title);

            String desc = jsonArticles.getJSONObject(count).getJSONObject(Const.ARTICLE_DESC_KEY).getString("rendered");
            article.setArticleDescription(desc);

            String date = jsonArticles.getJSONObject(count).getString(Const.ARTICLE_DATE_KEY);
            article.setArticleTimeStamp(date);

            String rootImageUrl = jsonArticles.getJSONObject(count).getJSONObject(Const.ARTICLE_LINKS_KEY).getJSONArray(Const.ROOT_IMG_KEY).getJSONObject(0).getString("href");

            requestImageUrl(rootImageUrl, article);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void requestImageUrl (String rootUrl , final NewsArticle article) {

        final NewsArticle newsArticle = article;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, rootUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url = parseImageResponse(response);
                newsArticle.setArticleThumbnailUrl(url);
                Log.d(null,url);
                articles.add(newsArticle);
                parseArticleResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);

    }

    private String parseImageResponse(JSONArray response){

        String url = "";

        try {

             url = response.getJSONObject(0)
                    .getJSONObject(Const.MEDIA_DETAILS_KEY)
                     .getJSONObject(Const.MEDIA_SIZES_KEY)
                    .getJSONObject(Const.MEDA_TWO_THIRD)
                    .getString(Const.MEDIA_URL_KEY);

            Log.d(null,url);

        } catch (JSONException e) {

            Log.d(this.getClass().toString() ,"json Parse Error" );
        }

        return url;
    }


}





