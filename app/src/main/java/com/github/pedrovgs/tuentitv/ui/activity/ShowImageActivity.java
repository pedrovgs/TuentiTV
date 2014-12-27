package com.github.pedrovgs.tuentitv.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.InjectView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tuenti.tuentitv.R;
import java.util.LinkedList;
import java.util.List;

public class ShowImageActivity extends BaseActivity {

  public static final String IMAGE_URL_EXTRA = "image_url_extra";

  @InjectView(R.id.iv_media_element) ImageView iv_media_element;
  @InjectView(R.id.pb_loading) ProgressBar pb_loading;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.show_image_activity);
    String imageUrl = getImageUrlFromExtras();
    Picasso.with(this).load(imageUrl).into(iv_media_element, new Callback() {
      @Override public void onSuccess() {
        pb_loading.setVisibility(View.GONE);
      }

      @Override public void onError() {
        finish();
      }
    });
  }

  @Override protected List getModules() {
    return new LinkedList();
  }

  public String getImageUrlFromExtras() {
    return getIntent().getExtras().getString(IMAGE_URL_EXTRA);
  }
}
