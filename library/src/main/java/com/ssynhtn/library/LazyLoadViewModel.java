package com.ssynhtn.library;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by huangtongnao on 2019/6/4.
 * Email: huangtongnao@gmail.com
 */
public class LazyLoadViewModel extends ViewModel {
    public MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    private Handler handler = new Handler();

    public void addFragmentDelayed(long delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.setValue(true);
            }
        }, delay);
    }
}
