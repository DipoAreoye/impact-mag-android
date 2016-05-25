package apps.dipoareoye.impact.models;

import android.app.Application;
import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<NewsArticle> articles;

    public NewsListModelImpl(Context context){
        this.context = context;
    }

    @Override
    public void getNewsList(final ArticlesListener articleListener) {

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                jsonArticles = response;

                parseArticleResponse();

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

    private void parseArticleResponse() {

        int count = articles.size();

        String title = jsonArticles.getJSONObject(count).getString(Const.)


    }

    private List<NewsArticle> parseResponse(int index) {

        try {
            String attachmentUrl = jsonArticles.getJSONObject(index).getJSONObject(Const.ROOT_IMG_KEY).getString("href");
            requestImageurl(attachmentUrl, index);


        } catch (JSONException e) {

            Log.d(this.getClass().toString() ,"json Parse Error" );
        }

    }

    private void requestImageurl (String rootUrl , int index) {

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, rootUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url = parseImageResponse(response);


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
                    .getJSONObject(Const.MEDA_TWO_THIRD)
                    .getString(Const.MEDIA_URL_KEY);

        } catch (JSONException e) {

            Log.d(this.getClass().toString() ,"json Parse Error" );
        }

        return url;
    }


}





