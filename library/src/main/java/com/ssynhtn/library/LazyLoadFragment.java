package com.ssynhtn.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


/**
 * Created by huangtongnao on 2018/3/7.
 * 和ViewPager配合使用的懒加载Fragment
 */

public class LazyLoadFragment extends Fragment {

    private static final String EXTRA_DELAY = "EXTRA_DELAY";
    private static final String EXTRA_FRAG_FACTORY = "EXTRA_FRAG_FACTORY";
    private static final String EXTRA_CUSTOM_LAYOUT = "EXTRA_CUSTOM_LAYOUT";
    private String TAG = LazyLoadFragment.class.getSimpleName();

    public static LazyLoadFragment newInstance(Class<? extends Fragment> clazz) {
        return newInstance(clazz, -1);
    }

    public static LazyLoadFragment newInstance(Class<? extends Fragment> clazz, long delay) {
        return newInstance(new ReflectFragmentFactory(clazz), delay);
    }


    public static LazyLoadFragment newInstance(FragmentFactory factory) {
        return newInstance(factory, -1);
    }

    public static LazyLoadFragment newInstance(FragmentFactory factory, long delay) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_FRAG_FACTORY, factory);
        args.putLong(EXTRA_DELAY, delay);

        LazyLoadFragment fragment = new LazyLoadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static LazyLoadFragment newInstance(FragmentFactory factory, long delay, int customLayoutId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_FRAG_FACTORY, factory);
        args.putLong(EXTRA_DELAY, delay);
        args.putInt(EXTRA_CUSTOM_LAYOUT, customLayoutId);

        LazyLoadFragment fragment = new LazyLoadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = R.layout.fragment_lazy_load;
        Bundle args = getArguments();
        if (args != null) {
            layoutId = args.getInt(EXTRA_CUSTOM_LAYOUT, layoutId);
        }

        return inflater.inflate(layoutId, container, false);
    }


    private View progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelProviders.of(this).get(LazyLoadViewModel.class).liveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    addFragment("delayedAdd");
                }
            }
        });

        progressBar = view.findViewById(R.id.progress);
        boolean hasFragment = getChildFragmentManager().findFragmentById(R.id.container) != null;
        if (hasFragment) {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            return;
        }

        if (getUserVisibleHint()) {
            addFragment("onViewCreated");
        } else {
            long delay = 0;
            if (getArguments() != null) {
                delay = getArguments().getLong(EXTRA_DELAY);
            }
            
            if (delay >= 0) {
                ViewModelProviders.of(this).get(LazyLoadViewModel.class).addFragmentDelayed(delay);
            }
            
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getUserVisibleHint()) {
            addFragment("onResume");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.d(TAG, "setVisibilityHint " + isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            addFragment("setUserVisibleHint");
        }
    }



    private void addFragment(String cause) {
        Log.d(TAG, "ready to add fragment by cause " + cause);
        if (getChildFragmentManager().findFragmentById(R.id.container) == null) {
            getChildFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .add(R.id.container, createFragment()).commit();
            Log.d(TAG, "added fragment by cause " + cause);
            if (progressBar != null) {
                progressBar.animate().alpha(0).setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime)).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        progressBar.setVisibility(View.GONE);
                    }
                }).start();
            }
        }
    }


    private Fragment createFragment() {
        Bundle args = getArguments();
        if (args != null) {
            FragmentFactory factory = (FragmentFactory) args.getSerializable(EXTRA_FRAG_FACTORY);
            if (factory != null) {
                return factory.createFragment();
            }
        }

        return new Fragment();
    }

}
