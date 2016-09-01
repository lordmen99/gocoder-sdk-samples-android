/**
 *
 *  This is sample code provided by Wowza Media Systems, LLC.  All sample code is intended to be a reference for the
 *  purpose of educating developers, and is not intended to be used in any production environment.
 *
 *  IN NO EVENT SHALL WOWZA MEDIA SYSTEMS, LLC BE LIABLE TO YOU OR ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL,
 *  OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 *  EVEN IF WOWZA MEDIA SYSTEMS, LLC HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  WOWZA MEDIA SYSTEMS, LLC SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. ALL CODE PROVIDED HEREUNDER IS PROVIDED "AS IS".
 *  WOWZA MEDIA SYSTEMS, LLC HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 *  Copyright © 2015 Wowza Media Systems, LLC. All rights reserved.
 */

package com.wowza.gocoder.sdk.sampleapp.subscribe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.wowza.gocoder.sdk.api.WowzaGoCoder;
import com.wowza.gocoder.sdk.api.broadcast.WZBroadcast;
import com.wowza.gocoder.sdk.api.broadcast.WZBroadcastConfig;
import com.wowza.gocoder.sdk.api.configuration.WZStreamConfig;
import com.wowza.gocoder.sdk.api.configuration.WowzaConfig;
import com.wowza.gocoder.sdk.api.errors.WZStreamingError;
import com.wowza.gocoder.sdk.api.mp4.WZMP4Broadcaster;
import com.wowza.gocoder.sdk.api.status.WZState;
import com.wowza.gocoder.sdk.api.status.WZStatus;
import com.wowza.gocoder.sdk.sampleapp.GoCoderSDKActivityBase;
import com.wowza.gocoder.sdk.sampleapp.R;
import com.wowza.gocoder.sdk.sampleapp.config.ConfigPrefs;

public class SubscribeActivity extends GoCoderSDKActivityBase {

    private ImageButton playButton;
//    private Button clearButton;
    private Button stopButton;
//    private Button defaultButton;
    private VideoView mVideoView;
//    private EditText editText;
    private String videoAddress;
    private static final String TAG = "videoTest";
    private String keyValue = "videoAddress";
    private String defaultAddress = "rtsp://104.155.175.86:1935/live/myStream";
    private boolean isPlaying = false;


    protected WZBroadcast mWZBroadcast = null;
    private WZMP4Broadcaster mMP4Broadcaster;
    // GoCoder SDK top level interface
    protected static WowzaGoCoder sGoCoderSDK = null;
    protected WZBroadcastConfig mWZBroadcastConfig = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences prefs = this.getSharedPreferences(TAG, 0);

        videoAddress = prefs.getString(keyValue, defaultAddress);

        playButton = (ImageButton)findViewById(R.id.btnPlay);

//        clearButton = (Button)findViewById(R.id.clear);
        stopButton = (Button)findViewById(R.id.stop);
//        defaultButton = (Button)findViewById(R.id.defaultb);

        mVideoView = (VideoView) findViewById(R.id.rtsp_player);

        mWZBroadcast = getBroadcast();
        mWZBroadcastConfig = getBroadcastConfig();
        sGoCoderSDK = WowzaGoCoder.getInstance();
        if (sGoCoderSDK != null) {
            mMP4Broadcaster = new WZMP4Broadcaster();
            mWZBroadcastConfig.setVideoBroadcaster(mMP4Broadcaster);
            mWZBroadcastConfig.setAudioEnabled(false); // audio not yet supported


        }

//        editText = (EditText)findViewById(R.id.rtspurlText);
//        editText.setText(videoAddress);

//        defaultButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//
////                editText.setText(defaultAddress);
//                SharedPreferences appSettings =  getSharedPreferences(TAG, 0);
//                SharedPreferences.Editor prefEditor = appSettings.edit();
//                prefEditor.putString(keyValue, defaultAddress);
//                prefEditor.commit();
//
//
//            }
//        });

//        stopButton.setOnClickListener(new View.OnClickListener() {
//protected WZBroadcast mWZBroadcast = null;
//            public void onClick(View arg0) {
//
//                mVideoView.stopPlayback();
//                playButton.setImageResource(R.drawable.ic_start);
//
//
//            }
//        });

//        playButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//
//                if (isPlaying == false) {
//    //                videoAddress = editText.getText().toString();
//                    SharedPreferences appSettings = getSharedPreferences(TAG, 0);
//                    SharedPreferences.Editor prefEditor = appSettings.edit();
//                    prefEditor.putString(keyValue, videoAddress);
//                    prefEditor.commit();
//                    //PlayRtspStream("rtsp://10.0.0.32:5004/aac5.sdp");
//                    PlayRtspStream(videoAddress);
//                    playButton.setImageResource(R.drawable.ic_stop);
//                    isPlaying = true;
//
//                    if (mWZBroadcast.getStatus().isIdle()) {
////                        mMP4Broadcaster.setFileDescriptor(getFD(mMP4FileUri));
//                        mWZBroadcastConfig.setAudioEnabled(false);
//
//                        WZStreamingError configValidationError = mWZBroadcastConfig.validateForBroadcast();
//                        if (configValidationError == null) {
//                            mWZBroadcast.startBroadcast(mWZBroadcastConfig, this);
//                        }
//                    } else if (mWZBroadcast.getStatus().isRunning()) {
//                        if (mVideoView.isPlaying()) {
//                            mVideoView.pause();
//                        }
//                        mWZBroadcast.endBroadcast(this);
//                    }
//
//                } else {
//                    mVideoView.stopPlayback();
//                    playButton.setImageResource(R.drawable.ic_start);
//                    isPlaying = false;
//                }
//
//
//            }
//        });

//        clearButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//
//                editText.setText("");
//                SharedPreferences appSettings =  getSharedPreferences(TAG, 0);
//                SharedPreferences.Editor prefEditor = appSettings.edit();
//                prefEditor.putString(keyValue, "");
//                prefEditor.commit();
//
//
//            }
//        });


    }

    public void onToggleBroadcast(View v) {

        if (isPlaying == false) {
            //                videoAddress = editText.getText().toString();
            SharedPreferences appSettings = getSharedPreferences(TAG, 0);
            SharedPreferences.Editor prefEditor = appSettings.edit();
            prefEditor.putString(keyValue, videoAddress);
            prefEditor.commit();
            //PlayRtspStream("rtsp://10.0.0.32:5004/aac5.sdp");
            PlayRtspStream(videoAddress);
            playButton.setImageResource(R.drawable.ic_stop);
            isPlaying = true;

//            if (mWZBroadcast.getStatus().isIdle()) {
////                        mMP4Broadcaster.setFileDescriptor(getFD(mMP4FileUri));
//                mWZBroadcastConfig.setAudioEnabled(false);
//
//                WZStreamingError configValidationError = mWZBroadcastConfig.validateForBroadcast();
//                if (configValidationError == null) {
//
//                    prefEditor.putString("wz_live_stream_name", WowzaConfig.DEFAULT_STREAM + "specialist");
//                    mWZBroadcast.startBroadcast(mWZBroadcastConfig, this);
//                }
//            } else if (mWZBroadcast.getStatus().isRunning()) {
//                if (mVideoView.isPlaying()) {
//                    mVideoView.pause();
//                }
//                mWZBroadcast.endBroadcast(this);
//            }

        } else {
            mVideoView.stopPlayback();
            playButton.setImageResource(R.drawable.ic_start);
            isPlaying = false;
        }

    }

    private void PlayRtspStream(String rtspUrl){
        mVideoView.setVideoPath(rtspUrl);
        //mVideoView.setVideoURI(Uri.parse(rtspUrl));
        mVideoView.requestFocus();
        mVideoView.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subscribe, menu);
        return true;
    }

    /**
     * Update the state of the UI controls
     */
    private void syncUIControlState() {
        boolean disableControls = (mWZBroadcast == null ||
                !(mWZBroadcast.getStatus().isIdle() || mWZBroadcast.getStatus().isRunning()));

        if (disableControls) {
//            mBtnBroadcast.setEnabled(false);
//            mBtnSettings.setEnabled(false);
//            mBtnLoop.setEnabled(false);
//            mBtnFileSelect.setEnabled(false);
        } else {
            boolean isStreaming = mWZBroadcast.getStatus().isRunning();
//            mBtnBroadcast.setState(isStreaming);
//            mBtnBroadcast.setEnabled(mMP4FileUri != null);

//            mBtnSettings.setEnabled(!isStreaming);
//            mBtnFileSelect.setEnabled(!isStreaming);
        }
    }

    /**
     * WZStatusCallback interface methods
     */

    @Override
    public void onWZStatus(final WZStatus goCoderStatus) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                switch(goCoderStatus.getState()) {
                    case WZState.IDLE:
                        // Clear the "keep screen on" flag
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//                        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
//                            mMediaPlayer.stop();
//                        }
                        break;
                    case WZState.RUNNING:
                        // Keep the screen on while we are broadcasting
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                        mVideoView.seekTo(0);
                        mVideoView.start();
                        break;
                }
//                mStatusView.setStatus(goCoderStatus);
                syncUIControlState();
            }
        });
    }

    @Override
    public void onWZError(final WZStatus goCoderStatus) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
//                mStatusView.setStatus(goCoderStatus);
                syncUIControlState();
            }
        });
    }
}