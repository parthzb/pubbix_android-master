package com.pubbix.util.brick;

import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.brick.ViewModel;
import com.wayfair.brickkit.padding.BrickPadding;
import com.wayfair.brickkit.size.BrickSize;

public abstract class ViperBrick<V extends ViewModel> extends BaseBrick
        implements ViewModel.ViewModelUpdateListener {
    protected final V viewModel;

    public ViperBrick(V viewModel, BrickSize spanSize, BrickPadding padding) {
        super(spanSize, padding);
        this.viewModel = viewModel;
        if (viewModel != null) {
            viewModel.addUpdateListener(this);
            setHidden(viewModel.getDataModel() != null);
        }
    }

    public ViperBrick(V viewModel, BrickSize spanSize) {
        this(viewModel, spanSize, new ZeroBrickPadding());
    }

    public V getViewModel() {
        return viewModel;
    }

    @Override
    public void onChange() {
        setHidden(viewModel.getDataModel() != null);
        refreshItem();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ViperBrick && getLayout() == ((ViperBrick) obj).getLayout()
                && viewModel.equals(((ViperBrick) obj).getViewModel());
    }
}
