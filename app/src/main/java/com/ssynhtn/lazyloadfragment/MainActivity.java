package com.ssynhtn.lazyloadfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup radioGroup = findViewById(R.id.main_rg);
        final ViewPager viewPager = findViewById(R.id.view_pager);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            final int finalI = i;
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI, false);
                }
            });

        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOffscreenPageLimit(radioGroup.getChildCount());
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), radioGroup.getChildCount()));

        radioGroup.check(R.id.rb_home);
    }

}
