package apps.dipoareoye.impact.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public final class Category {

    private static final HashMap<String, String> categoryMap;
    static
    {
        categoryMap = new HashMap<>();
        categoryMap.put("Editors Choice" , "lead articles");
        categoryMap.put("News" , "news");
        categoryMap.put("Features","features");
        categoryMap.put("Comment","comment");
        categoryMap.put("Film & TV","film-and-television");
        categoryMap.put("Arts","arts");
        categoryMap.put("Music","music");
        categoryMap.put("Gaming","gaming");
        categoryMap.put("Style","style");
        categoryMap.put("Travel","travel");
        categoryMap.put("Food","food");
        categoryMap.put("Science","science");
        categoryMap.put("Sport","sport");
        categoryMap.put("Images","images");
    }

    private Category () {
    }

    public static String getCategory(String category){
        return categoryMap.get(category);
    }

}
