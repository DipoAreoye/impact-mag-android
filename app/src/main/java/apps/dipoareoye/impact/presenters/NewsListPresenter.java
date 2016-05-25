package apps.dipoareoye.impact.presenters;

import apps.dipoareoye.impact.ui.views.NewsListView;

/**
 * Created by dipoareoye on 22/05/2016.
 */
public interface NewsListPresenter extends Presenter {

    void create();

    void setView(NewsListView view);
}
