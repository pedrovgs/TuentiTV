package com.github.pedrovgs.tuentitv.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
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

  private static final int ALPHA_ANIMATION_DURATION = 1000;

  @InjectView(R.id.iv_media_element) ImageView iv_media_element;
  @InjectView(R.id.pb_loading) ProgressBar pb_loading;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.show_image_activity);
    super.onCreate(savedInstanceState);
    String imageUrl = getImageUrlFromExtras();
    Picasso.with(this).load(imageUrl).into(iv_media_element, new Callback() {
      @Override public void onSuccess() {
        animateImageView();
        updateImageViewBackground();
        hideLoading();
      }

      @Override public void onError() {
        finish();
      }
    });
  }

  private void animateImageView() {
    AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
    alphaAnimation.setDuration(ALPHA_ANIMATION_DURATION);
    alphaAnimation.setFillAfter(true);
    iv_media_element.startAnimation(alphaAnimation);
    iv_media_element.setVisibility(View.VISIBLE);
  }

  private void updateImageViewBackground() {
    int backgroundColor = getResources().getColor(R.color.third_color);
    iv_media_element.setBackgroundColor(backgroundColor);
  }

  private void hideLoading() {
    pb_loading.setVisibility(View.GONE);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }

  public String getImageUrlFromExtras() {
    return getIntent().getExtras().getString(IMAGE_URL_EXTRA);
  }
}
