package apps.dipoareoye.impact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import apps.dipoareoye.impact.entities.NewsArticle;

public class NewsAdapter extends ArrayAdapter<NewsArticle> {

    public NewsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public NewsAdapter(Context context, int resource, List<NewsArticle> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
        }

        NewsArticle p = getItem(position);

        if (p != null) {

        }

        return v;
    }

}