package apps.dipoareoye.impact.ui.recyclers;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

import apps.dipoareoye.impact.R;
import apps.dipoareoye.impact.entities.NewsArticle;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public class ArticleRecyclerView extends RecyclerView.Adapter<ArticleRecyclerView.MainViewHolder> {
    private List<NewsArticle> items;

    private Typeface tf;


    public ArticleRecyclerView(List<NewsArticle> items , Typeface tf) {
        if (items == null) {
            throw new NullPointerException(
                    "items must not be null");
        }
        this.items = items;
        this.tf = tf;
    }


    @Override
    public  MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 0 :
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.article_header_item,
                            parent,
                            false);
                return new HeaderViewHolder(itemView);
            default:
                 itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.article_list_item,
                                parent,
                                false);
                return new ArticleViewHolder(itemView);

        }

    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final NewsArticle article = items.get(position);

        if (position == 0) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;

            viewHolder.title.setText(article.getArticleTitle());
            viewHolder.desc.setText(article.getArticleDescription());
            viewHolder.categoryLabel.setTypeface(tf,Typeface.BOLD);
            viewHolder.categoryLabel.setText(article.getArticleCategory().toUpperCase());
            viewHolder.time.setText(article.getArticleTimeStamp());

            if (!TextUtils.isEmpty(article.getArticleThumbnailUrl())) {
                viewHolder.media.setVisibility(View.VISIBLE);
                Picasso.with(viewHolder.media.getContext()).load(article.getArticleThumbnailUrl()).into(viewHolder.media);
            } else {
                viewHolder.media.setVisibility(View.GONE);
            }

        } else {

            ArticleViewHolder viewHolder = (ArticleViewHolder) holder;

            viewHolder.title.setText(article.getArticleTitle());
            viewHolder.desc.setText(article.getArticleDescription());
            viewHolder.time.setText(article.getArticleTimeStamp());
            if (!TextUtils.isEmpty(article.getArticleThumbnailUrl())) {
                viewHolder.media.setVisibility(View.VISIBLE);
                Picasso.with(viewHolder.media.getContext()).load(article.getArticleThumbnailUrl()).into(viewHolder.media);
            } else {
                viewHolder.media.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }


    public NewsArticle getItem(int position) {
        return items.get(position);
    }

    final static class HeaderViewHolder extends MainViewHolder {
        @Bind(R.id.article_title)
        TextView title;
        @Bind(R.id.article_desc)
        TextView desc;
        @Bind(R.id.article_thumbnail)
        ImageView media;
        @Bind(R.id.article_time_label)
        TextView time;
        @Bind(R.id.article_category_label)
        TextView categoryLabel;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    final static class ArticleViewHolder extends MainViewHolder {
        @Bind(R.id.article_title)
        TextView title;
        @Bind(R.id.article_desc)
        TextView desc;
        @Bind(R.id.article_thumbnail)
        ImageView media;
        @Bind(R.id.article_time_label)
        TextView time;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static abstract class MainViewHolder extends  RecyclerView.ViewHolder {
        public MainViewHolder(View v) {
            super(v);
        }
    }

    public void refill(List<NewsArticle> items) {
        this.items.clear();
        this.items.addAll(items);

        notifyDataSetChanged();
    }
}
