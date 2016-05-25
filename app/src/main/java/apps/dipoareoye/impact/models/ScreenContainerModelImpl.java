package apps.dipoareoye.impact.models;

import java.util.List;
import java.util.Map;

import apps.dipoareoye.impact.entities.Category;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public class ScreenContainerModelImpl implements ScreenContainerModel {
    private Map<String,String> categories;

    public ScreenContainerModelImpl(){
        this.categories = Category.getCategoriesMap();
    }

    @Override
    public List<Category> getCategoriesList() {
        return null;
    }
}
