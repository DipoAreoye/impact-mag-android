package apps.dipoareoye.impact.ui.recyclers;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apps.dipoareoye.impact.R;
import apps.dipoareoye.impact.entities.NewsArticle;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dipoareoye on 24/05/2016.
 */
public class ArticleRecyclerView extends RecyclerView.Adapter<ArticleRecyclerView.ArticleViewHolder> {
    private List<NewsArticle> items;

    public ArticleRecyclerView(List<NewsArticle> items) {
        if (items == null) {
            throw new NullPointerException(
                    "items must not be null");
        }
        this.items = items;
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.article_list_item,
                        parent,
                        false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        final NewsArticle article = items.get(position);
        holder.title.setText(article.getArticleTitle());
        holder.desc.setText(article.getArticleDescription());
        holder.time.setText(article.getArticleTimeStamp());
        if (!TextUtils.isEmpty(article.getArticleThumbnailUrl())) {
            holder.media.setVisibility(View.VISIBLE);
            Picasso.with(holder.media.getContext()).load(article.getArticleThumbnailUrl()).into(holder.media);
        } else {
            holder.media.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public NewsArticle getItem(int position) {
        return items.get(position);
    }

    final static class ArticleViewHolder extends RecyclerView.ViewHolder {
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
}
