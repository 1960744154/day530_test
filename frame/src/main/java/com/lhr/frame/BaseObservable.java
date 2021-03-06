package com.lhr.frame;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObservable implements Observer {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(Object value) {
        Success(value);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    public abstract void Success(Object value);

    public abstract void onFailed(Throwable e);

    public void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
