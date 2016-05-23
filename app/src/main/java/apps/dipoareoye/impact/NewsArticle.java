package apps.dipoareoye.impact;

/**
 * Created by dipoareoye on 10/05/2016.
 */
public class NewsArticle {

    private String articleTitle;
    private String articleThumbnail;
    private String articleDescription;
    private String articleTimeStamp;


    public void setArticleTitle(String title) {
        articleTitle = title;
    }

    public void setArticleThumbnail(String image) {
        articleThumbnail = image;
    }

    public void setArticleDescription(String description) {
        articleDescription = description;
    }

    public void setArticleTimestamp(String time) {
        articleTimeStamp = time;

    }

    public String getArticleTitle() {

        return articleTitle;
    }

    public String getArticleThumbnail() {

        return articleThumbnail;
    }

    public String getArticleDescription() {

        return articleDescription;
    }

    public String getArticleTimeStamp() {

        return articleTimeStamp;
    }


}


