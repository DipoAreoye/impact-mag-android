package apps.dipoareoye.impact.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import apps.dipoareoye.impact.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dipoareoye on 30/05/2016.
 */
public class ArticleActivity extends AppCompatActivity {
    public static final String EXTRA_ARTICLE = "article";
    public static final String EXTRA_CATEGORY = "category";

    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.progressBar)
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        final String articleUrl = extras.getString(EXTRA_ARTICLE);
        final String articleCategory = extras.getString(EXTRA_CATEGORY);

        if (articleUrl != null) {

            webView.setWebChromeClient(new WebClient());
            webView.loadUrl(articleUrl);


        } else {
            finish();
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(articleCategory);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    public class WebClient extends android.webkit.WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {
            if (progress < 100 && progressbar.getVisibility() == ProgressBar.GONE) {
                progressbar.setVisibility(ProgressBar.VISIBLE);
                webView.setVisibility(View.VISIBLE);
            }

            if (progress == 100) {
                progressbar.setVisibility(ProgressBar.GONE);
                progressbar.setVisibility(View.GONE);
            }
        }
    }
}
