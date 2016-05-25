package apps.dipoareoye.impact.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public final class Category {

    private static final Map<String, String> categoryMap;
    static
    {
        categoryMap = new HashMap<>();
        categoryMap.put("Editors Choice" , "editors_choice_url");
    }

    private Category () {
    }

    public static Map<String,String> getCategoriesMap(){
        return categoryMap;
    }

}
