package com.lhr.frame;

public interface CommonModule<M> {

    void getData(CommonPresenter commonPresenter, int whichApi, M... m);
}
