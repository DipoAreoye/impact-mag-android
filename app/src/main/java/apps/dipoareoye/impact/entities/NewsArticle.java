package apps.dipoareoye.impact.entities;

import org.json.JSONObject;

/**
 * Created by dipoareoye on 10/05/2016.
 */
public class NewsArticle {

    private String articleTitle;
    private String articleThumbnailUrl;
    private String articleDescription;
    private String articleTimeStamp;

    public NewsArticle (String articleTitle, String articleThumbnailUrl,
                            String articleDescription, String articleTimeStamp){
        this.articleTitle = articleTitle;
        this.articleThumbnailUrl = articleThumbnailUrl;
        this.articleDescription = articleDescription;
        this.articleTimeStamp = articleTimeStamp;
    }

    public NewsArticle(){}

    public void setArticleTitle(String articleTitle) {

        this.articleTitle = articleTitle;
    }

    public void setArticleThumbnailUrl(String articleThumbnailUrl) {

        this.articleThumbnailUrl = articleThumbnailUrl;
    }

    public void setArticleDescription(String articleDescription) {

        this.articleDescription = articleDescription;
    }

    public void setArticleTimeStamp(String articleTimeStamp) {

        this.articleTimeStamp = articleTimeStamp;
    }


    public String getArticleTitle() {

        return articleTitle;
    }

    public String getArticleThumbnailUrl() {

        return articleThumbnailUrl;
    }

    public String getArticleDescription() {

        return articleDescription;
    }

    public String getArticleTimeStamp() {

        return articleTimeStamp;
    }

    public void create(JSONObject object) {


    }

}


