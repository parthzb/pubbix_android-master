package com.pubbix.util.rx.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SchedulerProvider implements SchedulerProviderContract {

  @Override
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }

  @Override
  public Scheduler computation() {
    return Schedulers.computation();
  }

  @Override
  public Scheduler io() {
    return Schedulers.io();
  }
}
