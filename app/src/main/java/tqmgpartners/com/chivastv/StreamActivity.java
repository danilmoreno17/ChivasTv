package tqmgpartners.com.chivastv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import nagra.nmp.sdk.NMPSDK;
import nagra.nmp.sdk.NMPTrackInfo;
import nagra.nmp.sdk.NMPVideoView;

public class StreamActivity extends AppCompatActivity {
    private NMPVideoView mNMPVideoView = null;
    private int mPausePos = 0;
    private ImageButton btnPlayAndPause;
    private ImageButton btnSubtitles;
    private ImageButton btnAudio;
    private AlertDialog.Builder mSubtitleTracksDialog;
    private AlertDialog.Builder mMultiAudioDialog;
    private int mActiveSubtitleTrack = 0;
    private int mActiveAudioTrack    = 0;
    private int _intTotalSubtitles = 0;
    private int _intTotalAudio = 1;
    private boolean boolStatus=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mNMPVideoView = (NMPVideoView) findViewById(R.id.VideoView);
        NMPSDK.load(getApplicationContext());
        mNMPVideoView.setVideoPath("http://nasatv-lh.akamaihd.net/i/NASA_101@319270/master.m3u8");
        mNMPVideoView.setOnInfoListener(mOnInfoListener);

        btnPlayAndPause = (ImageButton) findViewById(R.id.btn_playandpause);
        btnPlayAndPause.setImageResource(R.drawable.ic_nmp_pause);
        btnPlayAndPause.setOnClickListener(mPlayAndPauseListener);
        btnSubtitles = (ImageButton) findViewById(R.id.btn_subtitles);
        btnSubtitles.setOnClickListener(mLanguagesListener);
        btnAudio = (ImageButton) findViewById(R.id.btn_audio);
        btnAudio.setOnClickListener(mMutliAudioListener);

}
    public void addSubtitleSources(){
        //for(int i = 0; i<_Programme.get_colSubtitles().size();i++)
          //  mNMPVideoView.addSubtitleSource(_Programme.get_colSubtitles().get(i).get_strUrl(),_Programme.get_colSubtitles().get(i).get_strMIME(),_Programme.get_colSubtitles().get(i).get_strLanguage());
    }
    public void setTracks() {
        NMPTrackInfo[] tracks=mNMPVideoView.getNMPTrackInfo();
        ArrayList<NMPTrackInfo> mSubtitleItems = new ArrayList<>();
        ArrayList<NMPTrackInfo> mMultiAudioItems = new ArrayList<>();

        for (int i = 0; i < tracks.length; i++) {
            NMPTrackInfo track = tracks[i];
            switch (track.getTrackType()) {
                case NMPTrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT:
                    mSubtitleItems.add(track);
                    break;
                case NMPTrackInfo.MEDIA_TRACK_TYPE_AUDIO:
                    mMultiAudioItems.add(track);
                    break;
                default:
                    break;
            }
        }
        if (mSubtitleItems.size() > 0) {
            createSubtitleTracksDialog(mSubtitleItems);
            _intTotalSubtitles = mSubtitleItems.size();
        }
        if (mMultiAudioItems.size() > 1) {
            createMultiAudioDialog(mMultiAudioItems);
            _intTotalAudio = mMultiAudioItems.size();
        }
    }
    private void createMultiAudioDialog(final ArrayList<NMPTrackInfo> audioTracks) {
        mMultiAudioDialog = new AlertDialog.Builder(StreamActivity.this);
        mMultiAudioDialog.setTitle("Select audio");
        String audioTrackLanguage;
        int unlabledTrackCount = 0;

        ArrayList<String> tracksArray = new ArrayList<>();
        for (int i = 0; i < audioTracks.size(); i++) {
            if (audioTracks.get(i).getActive()) {
                mActiveAudioTrack = i;
            }
            if(audioTracks.get(i).getLanguage().isEmpty() ) {
                audioTrackLanguage = "audio" + Integer.toString(unlabledTrackCount);
                tracksArray.add(audioTrackLanguage);
                unlabledTrackCount++;
            }
            else {
                tracksArray.add(audioTracks.get(i).getLanguage());
            }
        }

        String[] textArray = tracksArray.toArray(new String[tracksArray.size()]);

        mMultiAudioDialog.setSingleChoiceItems(textArray,
                mActiveAudioTrack,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int xWhich) {
                        if (xWhich == mActiveAudioTrack) {
                            //do nothing, if user select the same track again.
                        } else {
                            NMPTrackInfo selectedTrack = audioTracks.get(xWhich);
                            mNMPVideoView.selectTrack(getTrackID(selectedTrack));
                        }
                        dialog.cancel();
                    }
                });
    }
    private void createSubtitleTracksDialog(final ArrayList<NMPTrackInfo> subtitles) {
        mSubtitleTracksDialog = new AlertDialog.Builder(StreamActivity.this);
        mSubtitleTracksDialog.setTitle("Select Subtitle");
        mActiveSubtitleTrack = 0;

        ArrayList<String> subtitleArray = new ArrayList<>();
        subtitleArray.add("Disable");
        for (int i = 0; i < subtitles.size(); i++) {
            if (subtitles.get(i).getActive()) {
                mActiveSubtitleTrack = i + 1;
            }
            subtitleArray.add(subtitles.get(i).getLanguage());
        }
        String[] textArray = subtitleArray.toArray(new String[subtitleArray.size()]);

        mSubtitleTracksDialog.setSingleChoiceItems(textArray,
                mActiveSubtitleTrack,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int xWhich) {
                        if (xWhich == mActiveSubtitleTrack) {
                            //do nothing, if user select the same track again.
                        } else if (xWhich == 0) {
                            NMPTrackInfo selectedTrack = subtitles.get(mActiveSubtitleTrack - 1);
                            mNMPVideoView.deselectTrack(getTrackID(selectedTrack));
                        } else {
                            NMPTrackInfo selectedTrack = subtitles.get(xWhich - 1);
                            mNMPVideoView.selectTrack(getTrackID(selectedTrack));
                        }
                        dialog.cancel();
                    }
                });
    }
    private int getTrackID(NMPTrackInfo track) {
        NMPTrackInfo[] tracks = mNMPVideoView.getNMPTrackInfo();
        for (int i = 0; i < tracks.length; i++) {
            if (tracks[i] == track) {
                return i;
            }
        }
        return 0;
    }
    private View.OnClickListener mMutliAudioListener = new View.OnClickListener() {
        public void onClick(View v) {
            //NMPTrackInfo[] tracks=mNMPVideoView.getNMPTrackInfo();
            if(_intTotalAudio>1){
                AlertDialog dialog = mMultiAudioDialog.create();
                dialog.show();}else {
                Toast.makeText(getApplicationContext(), "NO MORE TRACKS", Toast.LENGTH_LONG).show();
            }
        }
    };
    private View.OnClickListener mLanguagesListener = new View.OnClickListener() {
        public void onClick(View v) {
            if(_intTotalSubtitles>0){
                AlertDialog dialog = mSubtitleTracksDialog.create();
                dialog.show();}else{
                Toast.makeText(getApplicationContext(), "NO MORE SUBTITLES", Toast.LENGTH_LONG).show();
            }
        }
    };
    private View.OnClickListener mPlayAndPauseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(boolStatus){
                if(mNMPVideoView != null)
                {
                    mPausePos = mNMPVideoView.getCurrentPosition();
                    mNMPVideoView.pause();
                }
                btnPlayAndPause.setImageResource(R.drawable.ic_nmp_play);
                boolStatus=false;
            }else{
                if(mNMPVideoView != null && mPausePos > 0)
                {
                    mNMPVideoView.seekTo(mPausePos);
                    mNMPVideoView.resume();
                    mPausePos = 0;
                }
                btnPlayAndPause.setImageResource(R.drawable.ic_nmp_pause);
                boolStatus=true;
            }
        }
    };
    private MediaPlayer.OnInfoListener mOnInfoListener = new MediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
            addSubtitleSources();
            setTracks();
            return true;
        }
    };
    @Override
    public void onStart() {
        try {
            super.onStart();
            mNMPVideoView.setVideoPath("http://nasatv-lh.akamaihd.net/i/NASA_101@319270/master.m3u8");
            mNMPVideoView.start();
        }
        catch (Exception ex){
            Log.e("error start",ex.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mNMPVideoView != null && mPausePos > 0)
        {
            mNMPVideoView.seekTo(mPausePos);
            mPausePos = 0;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(mNMPVideoView.isPlaying())
            Log.e("VIDEO","funciona");
        if(mNMPVideoView != null)
        {
            mPausePos = mNMPVideoView.getCurrentPosition();
            mNMPVideoView.pause();
        }
    }

}











