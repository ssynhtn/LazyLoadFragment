# 无需继承的懒加载Fragment

# 使用方法

在`FragmentPagerAdapter.getItem()`中，将你的Fragment替换成

`LazyLoadFragment.newInstance(YourFragment.class, -1)`

如果需要Fragment延迟若干秒后自动加载，可以使用

`LazyLoadFragment.newInstance(YourFragment.class, DELAY_MILLIS)`

如果你的Fragment需要传入参数，具体使用请参考demo中对`FragmentFactory`的使用

<img src="screenshot.gif" width="400px" alt="lazy load fragment in viewpager demo"/>