package com.lhr.frame;

 public interface CommonView<V> {

     void onSuccess(int whichApi, V... pV);
     void onFailed(int whichApi, Throwable throwable);
}
