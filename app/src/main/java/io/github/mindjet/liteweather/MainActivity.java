package io.github.mindjet.liteweather;

import org.jetbrains.annotations.NotNull;

import io.github.mindjet.liteweather.databinding.ActivityMainBinding;
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel;

/**
 * Created by Mindjet on 2017/10/10.
 */

public class MainActivity extends BaseBindingActivity<ActivityMainBinding, MainModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @NotNull
    @Override
    public BaseViewModel<ActivityMainBinding> getViewModel() {
        return new MainViewModel();
    }
}
