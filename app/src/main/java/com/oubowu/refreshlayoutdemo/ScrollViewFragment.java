package com.oubowu.refreshlayoutdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oubowu.refreshlayoutdemo.refresh.RefreshLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScrollViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScrollViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RefreshLayout mRefreshLayout;
    private ScrollView mScrollView;
    private ImageView mImage;


    public ScrollViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScrollViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScrollViewFragment newInstance(String param1, String param2) {
        ScrollViewFragment fragment = new ScrollViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scroll_view, container, false);

        mImage = (ImageView) view.findViewById(R.id.image);

        Glide.with(getActivity()).load(R.mipmap.ic_night_song).placeholder(R.mipmap.ic_night_song).dontAnimate().into(mImage);

        mRefreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout);
//        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("MainActivity", "70行-onRefresh(): " + "正在刷新");
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MainActivity", "63行-run(): " + "刷新完毕");
                        if (getActivity() != null) {
                            Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
                            mRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 2000);
            }
        });

        mScrollView = (ScrollView) view.findViewById(R.id.view);
        //        mScrollView.setOnTouchListener(mRefreshLayout.new ScrollViewOnTouchListener());
        // 不需要处理其他的滑动事件的话
        mRefreshLayout.handleTargetOffset(mScrollView);

        return view;
    }

}
