package com.ssynhtn.library;

import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huangtongnao on 2019/7/16.
 * Email: huangtongnao@gmail.com
 */
public class ReflectFragmentFactory implements FragmentFactory {
    private Class<? extends Fragment> clazz;

    public ReflectFragmentFactory(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Fragment createFragment() {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Fragment();
    }
}
