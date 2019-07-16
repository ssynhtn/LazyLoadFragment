package com.ssynhtn.lazyloadfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by huangtongnao on 2019/3/13.
 * Email: huangtongnao@gmail.com
 */
public class SimpleFragment extends Fragment {

    private static final String EXTRA_POSITION = "EXTRA_POSITION";
    private static final String TAG = SimpleFragment.class.getSimpleName();


    public static SimpleFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putString(EXTRA_POSITION, Integer.toString(position));

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.text);
        String position = "no position specified";
        Bundle args = getArguments();
        if (args != null) {
            position = args.getString(EXTRA_POSITION, position);
        }

        if (textView != null) {
            textView.setText(position);
        }
        Toast.makeText(getActivity(), position + " created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        log("onResume", getUserVisibleHint());
    }

    @Override
    public void onPause() {
        super.onPause();
        log("onPause", getUserVisibleHint());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy", getUserVisibleHint());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log("setUserVisibleHint", isVisibleToUser);
    }

    private void log(String cause, boolean isVisibleToUser) {
        String position = "no position specified";
        if (getArguments() != null) {
            position = getArguments().getString(EXTRA_POSITION, position);
        }
        Log.d(TAG, position + ", " + cause + ", visible " + isVisibleToUser);
    }
}
