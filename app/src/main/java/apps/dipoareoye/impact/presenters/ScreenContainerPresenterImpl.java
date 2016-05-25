package apps.dipoareoye.impact.presenters;

import java.util.List;

import apps.dipoareoye.impact.entities.Category;
import apps.dipoareoye.impact.models.ScreenContainerModel;
import apps.dipoareoye.impact.models.ScreenContainerModelImpl;
import apps.dipoareoye.impact.ui.ScreenContainer;
import apps.dipoareoye.impact.ui.views.ScreenContainerView;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public class ScreenContainerPresenterImpl implements ScreenContainerPresenter {

    private final ScreenContainerView view;
    private final ScreenContainerModel model;

    public ScreenContainerPresenterImpl(ScreenContainerView view, ScreenContainerModel model) {
        this.view = view;
        this.model = model;
    }

    public ScreenContainerPresenterImpl(ScreenContainerView view) {
        this(view,new ScreenContainerModelImpl());
    }

    @Override
    public void loadCategories() {
        List<Category> categoriesList = model.getCategoriesList();
        view.showCategories(categoriesList);
    }
}
