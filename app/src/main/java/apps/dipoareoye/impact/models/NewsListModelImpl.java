package apps.dipoareoye.impact.models;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import apps.dipoareoye.impact.entities.Const;
import apps.dipoareoye.impact.entities.NewsArticle;
import apps.dipoareoye.impact.network.ArticlesListener;

/**
 * Created by dipoareoye on 25/05/2016.
 */
public class NewsListModelImpl implements NewsListModel {

    private Context context;
    private JSONArray jsonArticles;
    private ArrayList<NewsArticle> articles = new ArrayList<>();
    private ArticlesListener articlesListener;
    private String category= "";
    private String rootUrl = "http://impactnottingham.com/wp-json/wp/v2/posts?filter[category_name]=";
    private RequestQueue requestQueue;

    public NewsListModelImpl(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void getNewsList(String category, final ArticlesListener articleListener) {
        this.articlesListener = articleListener;

        try {
            this.category = URLEncoder.encode(category,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = rootUrl + this.category +"&page=1&per_page=30";

        requestQueue.cancelAll(this);
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                jsonArticles = response;

                if (jsonArticles != null) {
                    articles.clear();
                }
                parseArticleResponse();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsArrayRequest.setTag(this);
        requestQueue.add(jsArrayRequest);
    }

    private void parseArticleResponse() {

        int count = articles.size();


        if (articles.size() == jsonArticles.length()){
            articlesListener.articlesCallback(articles);
            return;
        }

        try {
            NewsArticle article  = new NewsArticle();

            String title = jsonArticles.getJSONObject(count)
                    .getJSONObject(Const.ARTICLE_TITLE_KEY).getString("rendered");
            article.setArticleTitle(Html.fromHtml(title).toString());

            String desc = jsonArticles.getJSONObject(count)
                    .getJSONObject(Const.ARTICLE_DESC_KEY).getString("rendered");
            article.setArticleDescription(Html.fromHtml(desc).toString());

            String date = jsonArticles.getJSONObject(count).getString(Const.ARTICLE_DATE_KEY);
            article.setArticleTimeStamp(formatDate(date));

            String rootImageUrl = jsonArticles.getJSONObject(count).
                    getJSONObject(Const.ARTICLE_LINKS_KEY).getJSONArray(Const.ROOT_IMG_KEY)
                    .getJSONObject(0).getString("href");

            requestImageUrl(rootImageUrl, article);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String formatDate(String date) {
    //2016-05-24T23:06:11

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date articleDate = sdf.parse(date);

            String relativeDate = DateUtils.getRelativeTimeSpanString(articleDate.getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS,DateUtils.FORMAT_ABBREV_RELATIVE).toString();
            return relativeDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void requestImageUrl (String rootUrl , final NewsArticle article) {

        final NewsArticle newsArticle = article;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (com.android.volley.Request.Method.GET, rootUrl, null,
                        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String url = parseImageResponse(response);

                newsArticle.setArticleThumbnailUrl(url);
                articles.add(newsArticle);
                parseArticleResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonObjectRequest.setTag(this);
        requestQueue.add(jsonObjectRequest);
    }

    private String parseImageResponse(JSONArray response){

        String url = "";

        try {
             url = response.getJSONObject(0)
                    .getJSONObject(Const.MEDIA_DETAILS_KEY)
                     .getJSONObject(Const.MEDIA_SIZES_KEY)
                    .getJSONObject(Const.MEDA_THUMBNAIL_SMALL)
                    .getString(Const.MEDIA_URL_KEY);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return url;
    }


}





