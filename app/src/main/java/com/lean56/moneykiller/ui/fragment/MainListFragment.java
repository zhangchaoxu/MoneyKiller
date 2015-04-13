package com.lean56.moneykiller.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lean56.moneykiller.R;

/**
 * Fragment that appears in the "content_frame"
 */
public class MainListFragment extends Fragment {

    public static final String ARG_PLANET_NUMBER = "planet_number";

    public static Fragment newInstance(int position) {
        Fragment fragment = new MainListFragment();
        Bundle args = new Bundle();
        args.putInt(MainListFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_list, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String navi = getResources().getStringArray(R.array.navigation_array)[i];

        TextView tv  = ((TextView) rootView.findViewById(R.id.tv_main_list));
        tv.setText(navi);

        getActivity().setTitle(navi);
        return rootView;
    }

}
