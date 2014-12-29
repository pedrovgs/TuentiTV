package com.github.pedrovgs.tuentitv.ui.activity;

import android.os.Bundle;
import com.github.pedrovgs.tuentitv.R;
import com.github.pedrovgs.tuentitv.ui.fragment.DetailFragment;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DetailActivity extends BaseActivity {

  public static final String ID_EXTRA = "id_extra";

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.detail_activity);
    super.onCreate(savedInstanceState);
    String id = getContentId();
    DetailFragment detailFragment =
        (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
    detailFragment.setContentId(id);
  }

  private String getContentId() {
    return getIntent().getExtras().getString(ID_EXTRA);
  }

  @Override protected List getModules() {
    return new LinkedList();
  }
}
