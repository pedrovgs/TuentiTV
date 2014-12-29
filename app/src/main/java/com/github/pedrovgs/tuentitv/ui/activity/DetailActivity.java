package com.github.pedrovgs.tuentitv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.recommendation.service.RecommendationService;
import com.github.pedrovgs.tuentitv.ui.fragment.DetailFragment;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailActivity extends BaseActivity {

  public static final String ID_EXTRA = DetailFragment.ID_EXTRA;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.detail_activity);
    startRecommendationService();
  }

  private void startRecommendationService() {
    Intent service = new Intent(this, RecommendationService.class);
    startService(service);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
