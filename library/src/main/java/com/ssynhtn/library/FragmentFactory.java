package com.ssynhtn.library;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

/**
 * Created by huangtongnao on 2019/7/16.
 * Email: huangtongnao@gmail.com
 */
public interface FragmentFactory extends Serializable {
    Fragment createFragment();
}
