package com.github.pedrovgs.tuentitv;

import android.app.Application;
import android.content.Intent;
import com.github.pedrovgs.tuentitv.di.RootModule;
import com.github.pedrovgs.tuentitv.service.RecommendationService;
import dagger.ObjectGraph;
import java.util.List;

/**
 * Application component extension created to work as main Android in this APK. This class ins
 * going
 * to be used to implement dependency injectino using Dagger.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class TuentiTvApplication extends Application {

  private ObjectGraph applicationObjectGraph;

  @Override public void onCreate() {
    super.onCreate();
    initializeDependencyInjector();
    startRecommendationService();
  }

  /**
   * Using a RootModule created with the this application component we have created an ObjectGraph
   * using this module and every linked module with an application scope.
   */
  private void initializeDependencyInjector() {
    RootModule rootModule = new RootModule(this);
    applicationObjectGraph = ObjectGraph.create(rootModule);
    applicationObjectGraph.inject(this);
  }

  /**
   * Method used to provide new ObjectGraphs based on application object graph using plus method.
   * This new graph is going to provide a new ObjectGraph ready to provide dependencies referenced
   * in the application graph and also other dependencies provided by the list of modules passed as
   * argument in this method.
   */
  public ObjectGraph plus(List<Object> modules) {
    return applicationObjectGraph.plus(modules.toArray());
  }

  private void startRecommendationService() {
    Intent intent = new Intent(this, RecommendationService.class);
    startService(intent);
  }
}
