# Lazy Load Fragment that works with viewpager
It's a drop in replacement, no subclassing required

# Usage

In`FragmentPagerAdapter.getItem()/FragmentStatePagerAdapter.getItem()`，replace `new YourFragment()` with

`LazyLoadFragment.newInstance(YourFragment.class)`

If you need your fragment to automatically start loading in the background after a specified delay, use

`LazyLoadFragment.newInstance(YourFragment.class, DELAY_MILLIS)`

If your fragment needs arguments, please read the demo which contains usage of `FragmentFactory`

[中文说明](readme_cn.md)

<img src="screenshot.gif" width="400px" alt="lazy load fragment in viewpager demo"/>