package com.lean56.moneykiller.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base Fragment
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Get content view to be used when {@link #onCreate(android.os.Bundle)} is called
     *
     * @return layout resource id
     */
    protected abstract int getContentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), null);
    }
}
