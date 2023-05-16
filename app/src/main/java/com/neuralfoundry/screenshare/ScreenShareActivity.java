package com.neuralfoundry.screenshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.IBinder;

public class ScreenShareActivity extends AppCompatActivity {

    public static MediaProjectionManager mMediaProjectionManager;
    public static MediaProjection mMediaProjection;
    public  static Intent mResultData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_share);

        //assign media project manager variable
        mMediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234 &&  resultCode == RESULT_OK) {
            Intent intent = new Intent(this, CaptureScreenService.class);
            intent.putExtra("code",resultCode);
            startForegroundService(intent);
        }
    }
}

