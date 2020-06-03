package com.lhr.frame;

import io.reactivex.disposables.Disposable;

public interface CommonPresenter<P> extends CommonView {

    void getData(int whichApi, P... pP);
    void addDisposeable(Disposable disposable);
}
