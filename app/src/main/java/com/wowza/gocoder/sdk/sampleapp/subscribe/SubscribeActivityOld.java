//package com.wowza.gocoder.sdk.sampleapp.subscribe;
//
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.ActionBarActivity;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import com.wowza.gocoder.sdk.sampleapp.R;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//
//public class SubscribeActivity extends ActionBarActivity {
//
//    Button playButton;
//    Button rtspButton;
//    VideoView videoView;
//    TextView jniText;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_subscribe);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.subscribe, menu);
//
//        jniText = (TextView)this.findViewById(R.id.jnitext);
//        jniText.setText( getVersions() );
//
//        playButton = (Button)this.findViewById(R.id.mp4_play);
//        playButton.setOnClickListener(new Button.OnClickListener(){
//            public void onClick(View v) {
//                PlayRtspStream("http://10.128.0.2:1935/live/myStream/playlist.m3u8");
//            }
//        });
//
//
//        rtspButton = (Button)this.findViewById(R.id.rtsp_play);
//        rtspButton.setOnClickListener(new Button.OnClickListener(){
//            public void onClick(View v) {
//                PlayRtspStream("rtsp://10.128.0.2:1935/live/myStream");
//            }
//        });
//
//        videoView = (VideoView)this.findViewById(R.id.rtsp_player);
//
//        return true;
//    }
//
//    String getVersions() {
//        ByteBuffer bvers = ByteBuffer.allocateDirect(4 * 3).order(ByteOrder.nativeOrder());
//        getVersions(bvers);
//        int[] vers = new int[3];
//        bvers.asIntBuffer().get(vers);
//        return "LIBAVFORMAT_VERSION_MAJOR: "+vers[2]+"\nLIBAVCODEC_VERSION_MAJOR: "+vers[1]+"\nLIBAVUTIL_VERSION_MAJOR: "+vers[0];
//    }
//
//    //play rtsp stream
//    private void PlayRtspStream(String rtspUrl){
//        videoView.setVideoURI(Uri.parse(rtspUrl));
//        videoView.requestFocus();
//        videoView.start();
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_subscribe, container,
//                    false);
//            return rootView;
//        }
//    }
//    public native String stringFromJNI();
//    public native void getVersions(ByteBuffer b);
//    static {
//        System.loadLibrary("ffmpeg");
//        System.loadLibrary("ffmpeg-jni");
//    }
//}