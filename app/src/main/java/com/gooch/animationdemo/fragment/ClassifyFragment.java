package com.gooch.animationdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.gooch.animationdemo.Banner;
import com.gooch.animationdemo.BannerAdapter;
import com.gooch.animationdemo.CommonUtils;
import com.gooch.animationdemo.R;
import com.gooch.animationdemo.ZoomTransformer;
import com.gooch.animationdemo.data.ClassifyBean;
import com.gooch.animationdemo.databinding.FragmentClassifyBinding;
import com.gooch.animationdemo.databinding.LinearItemBinding;
import com.gooch.animationdemo.net.ApiConfig;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends Fragment {


    private FragmentClassifyBinding mBinding;
    private DelegateAdapter mDelegateAdapter;
    private Activity mActivity;
    private List<ClassifyBean.DataEntity> mDataEntities;
    private String[] mStrings;

    public ClassifyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classify, container, false);
        init();
        initData();
        return mBinding.getRoot();
    }

    private void initData() {
        EasyHttp.get(ApiConfig.CLASSIFI_URL)
                .params("appkey", ApiConfig.APP_KEY)
                .params("build", ApiConfig.BUILD)
                .params("mobi_app", ApiConfig.MOBI_APP)
                .params("platform", ApiConfig.PLATFORM)
                .params("ts", ApiConfig.TS)
                .params("sign", ApiConfig.SIGN)
                .execute(new SimpleCallBack<List<ClassifyBean.DataEntity>>() {
                    @Override
                    public void onError(ApiException e) {
                        Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(List<ClassifyBean.DataEntity> dataEntities) {
                        mDataEntities = dataEntities;
                        mDelegateAdapter.notifyDataSetChanged();
                    }

                });
//        mClassifyBean = CommonUtils.jsonToBean(CommonUtils.getJson(AnimApplication
//                        .getInstance(), "classify.json"),
//                ClassifyBean.class);
    }

    private void init() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this.getContext());
        mBinding.rvContent.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mBinding.rvContent.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        mDelegateAdapter = new DelegateAdapter(layoutManager, false);
        initGridLayout();
        initLinearLayout();
        mBinding.rvContent.setAdapter(mDelegateAdapter);
    }

    private void initLinearLayout() {
        final LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setDividerHeight(20);
        mDelegateAdapter.addAdapter(new DelegateAdapter.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.linear_item, parent,
                        false);
                Banner banner = (Banner) view.findViewById(R.id.banner);
                return new LinearViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                LinearViewHolder linearViewHolder = (LinearViewHolder) holder;


                if (mDataEntities == null) {
                    return;
                }
                if (position == 0) {
                    linearViewHolder.mItemBinding.banner.setVisibility(View.VISIBLE);
                    final List<ImageView> imageViews = new ArrayList<>();
                    List<ClassifyBean.DataEntity.BannerEntity.BottomEntity> bottom =
                            mDataEntities.get(0).banner.bottom;
                    for (int i = 0; i < bottom.size(); i++) {
                        ImageView view = new ImageView(mActivity);
                        imageViews.add(view);
                    }
                    BannerAdapter adapter = new BannerAdapter(getContext(), imageViews);
                    adapter.setStrings(bottom);
                    linearViewHolder.mItemBinding.banner.setAdapter(adapter);
                    linearViewHolder.mItemBinding.banner.setPageTransformer(true, new
                            ZoomTransformer());
                    linearViewHolder.mItemBinding.banner.setPageMargin(1);
                    CommonUtils.controlViewPagerSpeed(mActivity, linearViewHolder.mItemBinding
                            .banner, 2000);
                } else {
                    linearViewHolder.mItemBinding.banner.setVisibility(View.GONE);
                }

            }

            @Override
            public int getItemCount() {
                return mStrings.length - 2;
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return linearLayoutHelper;
            }

            class LinearViewHolder extends RecyclerView.ViewHolder {
                public LinearItemBinding mItemBinding;

                public LinearViewHolder(View itemView) {
                    super(itemView);
                    mItemBinding = DataBindingUtil.bind(itemView);
                }

                public LinearItemBinding getItemBinding() {
                    return mItemBinding;
                }

                public void setItemBinding(LinearItemBinding itemBinding) {
                    mItemBinding = itemBinding;
                }
            }
        });
    }

    private void initGridLayout() {
        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(6);
        gridLayoutHelper.setAutoExpand(true);
        gridLayoutHelper.setVGap(20);
        mStrings = getResources().getStringArray(R.array.grid_item);
        mDelegateAdapter.addAdapter(new DelegateAdapter.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.grid_item, null);
                return new GridViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                GridViewHolder gridViewHolder = (GridViewHolder) holder;
                gridViewHolder.mTextView.setText(mStrings[position]);
                gridViewHolder.mTextView.setCompoundDrawablePadding(10);
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_category_album);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                gridViewHolder.mTextView.setCompoundDrawables(null,
                        drawable, null, null);
            }

            @Override
            public int getItemCount() {
                return mStrings.length;
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return gridLayoutHelper;
            }

            class GridViewHolder extends RecyclerView.ViewHolder {
                public TextView mTextView;

                public GridViewHolder(View itemView) {
                    super(itemView);
                    mTextView = (TextView) itemView.findViewById(R.id.tv_item);
                }
            }
        });
    }

}
