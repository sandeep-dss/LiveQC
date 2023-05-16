package com.neuralfoundry.screenshare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CaptureScreenService extends Service {
    public CaptureScreenService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int resultCode = intent.getIntExtra("code", 1234);
        Intent mData = ScreenShareActivity.mResultData;
        ScreenShareActivity.mMediaProjection = ScreenShareActivity.mMediaProjectionManager.getMediaProjection(resultCode, mData);

        return super.onStartCommand(intent, flags, startId);
       }
}
