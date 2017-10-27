package com.gooch.animationdemo.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.AudioRecordManager;
import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentMain4Binding;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main4Fragment extends Fragment {


    private static final String targetDir = Environment.getExternalStorageDirectory()
            .getAbsolutePath();
    private FragmentMain4Binding mInflate;
    private MediaRecorder mRecorder;
    private MediaPlayer mMediaPlayer;
    private File currentFile;

    public Main4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main4, container, false);
        initView();
        return mInflate.getRoot();
    }

    private void initView() {
        mInflate.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initRecord();
//                mRecorder.start();
                currentFile = new File(targetDir, System.currentTimeMillis() + "luyin");
                AudioRecordManager.getInstance().startRecord(currentFile.getAbsolutePath());
            }
        });
        mInflate.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPlayer();
                mMediaPlayer.start();
            }
        });
        mInflate.stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mRecorder.stop();
//                mRecorder.release();
                AudioRecordManager.getInstance().stopRecord();
            }
        });
        mInflate.stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        });
    }

    private void initPlayer() {
        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context
                .AUDIO_SERVICE);

        mMediaPlayer = new MediaPlayer();
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        try {
            mMediaPlayer.setDataSource(currentFile.getAbsolutePath());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRecord() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setAudioChannels(1); // MONO
        mRecorder.setAudioSamplingRate(8000); // 8000Hz
        mRecorder.setAudioEncodingBitRate(64); // seems if change this to
        currentFile = new File(targetDir, System.currentTimeMillis() + "luyin");
        mRecorder.setOutputFile(currentFile.getAbsolutePath());
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
