package io.github.mindjet.liteweather;

import android.databinding.ViewDataBinding;

import io.github.mindjet.livemvvm.model.BaseModel;
import io.github.mindjet.livemvvm.view.BaseActivity;

/**
 * Created by Mindjet on 2017/10/10.
 */

public abstract class BaseBindingActivity<B extends ViewDataBinding, M extends BaseModel> extends BaseActivity<B, M> {
    @Override
    public int getVariableId() {
        return io.github.mindjet.liteweather.BR.data;
    }
}
