package com.neuralfoundry.screenshare;

import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.view.Surface;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.constants.ZegoPublishChannel;

public class VideoCaptureScreen extends ZegoVideoCaptureCallback  {

    private static MediaProjection  mMediaProjection;
    private static int width;
    private static int height;
    private static ZegoExpressEngine engine;

    private static boolean mIsCapturing = false;

    public  static Surface mSurface;
    private Handler mHandler;
    private VirtualDisplay mVirtualDisplay;

    public VideoCaptureScreen(MediaProjection mMediaProjection, int i, int i1, ZegoExpressEngine engine) {
        this.mMediaProjection = mMediaProjection;
        width = i;
        height = i1;
        this.engine = engine;
    }

    @Override
    public void onStart(ZegoPublishChannel channel) {
        super.onStart(channel);
        if(engine != null && !mIsCapturing && mMediaProjection != null) {
            mIsCapturing = true;
            SurfaceTexture texture = engine.getCustomVideoCaptureSurfaceTexture();
            texture.setDefaultBufferSize(width, height);
            mSurface = new Surface(texture);
            mVirtualDisplay = mMediaProjection.createVirtualDisplay("ScreenCapture", width, height, 1, DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC, mSurface, null, mHandler);
        }
    }
}
