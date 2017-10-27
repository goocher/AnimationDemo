package com.gooch.animationdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentSpenBinding;
import com.samsung.android.sdk.pen.document.SpenNoteDoc;
import com.samsung.android.sdk.pen.document.SpenPageDoc;
import com.samsung.android.sdk.pen.engine.SpenSurfaceView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpenFragment extends Fragment {


    private FragmentSpenBinding mBinding;
    private Activity mContext;
    private SpenSurfaceView mSpenSurfaceView;
    private SpenNoteDoc mSpenNoteDoc;
    private SpenPageDoc mSpenPageDoc;

    public SpenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spen, container, false);
//        init();
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

//    private void init() {
//        mSpenSurfaceView = new SpenSurfaceView(mContext);
//        mSpenSurfaceView.setToolTypeAction(SpenSurfaceView.TOOL_FINGER, SpenSurfaceView
// .ACTION_STROKE);
//
//        mBinding.llSpenContainer.addView(mSpenSurfaceView);
//        Display display = mContext.getWindowManager().getDefaultDisplay();
//        Rect rect = new Rect();
//        display.getRectSize(rect);
////        try {
////            mSpenNoteDoc = new SpenNoteDoc(mContext, rect.width(), rect.height());
////        } catch (IOException e) {
////            Toast.makeText(mContext, "Cannot create new NoteDoc.", Toast.LENGTH_SHORT).show();
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        String url = "http://planner-server.oss-cn-beijing.aliyuncs
// .com/analysis/pciture/2017-07-02/upKnowledgeNote1498917868590.png";
//        Observable.just(url)
//                .subscribeOn(Schedulers.io())
//                .map(new Function<String, File>() {
//                    @Override
//                    public File apply(@NonNull String s) throws Exception {
//                        return GetImageInputStream(s);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<File>() {
//                    @Override
//                    public void accept(@NonNull final File file) throws Exception {
//                        mSpenSurfaceView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    try {
//                                        mSpenNoteDoc = new SpenNoteDoc(mContext, file
// .getAbsolutePath(), mSpenSurfaceView.getWidth(), SpenNoteDoc.MODE_WRITABLE, true);
//                                    } catch (SpenUnsupportedTypeException e) {
//                                        e.printStackTrace();
//                                    } catch (SpenUnsupportedVersionException e) {
//                                        e.printStackTrace();
//                                    }
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                if (mSpenNoteDoc.getPageCount() == 0) {
//                                    mSpenPageDoc = mSpenNoteDoc.appendPage();
//                                } else {
//                                    mSpenPageDoc = mSpenNoteDoc.getPage(mSpenNoteDoc
// .getLastEditedPageIndex());
//                                }
////                                    mSpenPageDoc.setBackgroundColor(0xFFD6E6F5);
////                                mSpenPageDoc.setBackgroundImage(file.getAbsolutePath());
//                                mSpenPageDoc.clearHistory();
//                                // Set PageDoc to View.
//                                mSpenSurfaceView.setPageDoc(mSpenPageDoc, true);
//                                mSpenSurfaceView.update();
//
//                            }
//                        });
//                    }
//                });
//        if (!isSpenFeatureEnabled) {
//            mSpenSurfaceView.setToolTypeAction(SpenSurfaceView.TOOL_FINGER, SpenSurfaceView
// .ACTION_STROKE);
//            Toast.makeText(mContext, "Device does not support Spen. \n You can draw stroke by
// finger.", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSpenSurfaceView != null) {
            mSpenSurfaceView.close();
            mSpenSurfaceView = null;
        }
        if (mSpenNoteDoc != null) {
            try {
                mSpenNoteDoc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mSpenNoteDoc = null;
        }
    }

    public File GetImageInputStream(String imageurl) {
        URL url;
        HttpURLConnection connection = null;
        File file = new File(Environment.getExternalStorageDirectory(), "666.png");
        try {
            url = new URL(imageurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream = connection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 8];
            long len = 0;
            int read = 0;
            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
                fileOutputStream.flush();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
