package com.ssynhtn.library;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by huangtongnao on 2019/6/4.
 * Email: huangtongnao@gmail.com
 */
public class LazyLoadViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    private Handler handler = new Handler();

    public LazyLoadViewModel(@NonNull Application application) {
        super(application);
    }

    public void addFragmentDelayed(long delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.setValue(true);
            }
        }, delay);
    }
}
