package com.tuenti.tuentitv.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;
import com.tuenti.tuentitv.TuentiTvApplication;
import com.tuenti.tuentitv.di.ActivityModule;
import dagger.ObjectGraph;
import java.util.List;

/**
 * Base activity created to be extended by every activity in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every activity.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public abstract class BaseActivity extends FragmentActivity {

  private ObjectGraph activityObjectGraph;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeDependencyInjectorForActivityScopeGraph();
    injectViews();
  }

  /**
   * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
   * declared one in the activity. This new graph will be destroyed once the activity lifecycle
   * finish.
   *
   * This is the key of how to use Activity scope dependency injection.
   */
  private void initializeDependencyInjectorForActivityScopeGraph() {
    TuentiTvApplication app = (TuentiTvApplication) getApplication();
    List modules = getModules();
    modules.add(new ActivityModule(this));
    activityObjectGraph = app.plus(modules);
    activityObjectGraph.inject(this);
  }

  /**
   * Method created to inject dependencies in components attached to Activities like Fragments or
   * Views.
   */
  public void inject(Object object) {
    activityObjectGraph.inject(object);
  }

  /**
   * Abstract method implemented by every Activity and used to declare which modules are going to
   * provide dependencies for this component.
   */
  protected abstract List getModules();

  /**
   * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
   * value.
   */
  private void injectViews() {
    ButterKnife.inject(this);
  }
}
