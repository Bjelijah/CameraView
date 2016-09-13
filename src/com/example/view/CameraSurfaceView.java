package com.example.view;

import com.example.action.CameraInterface;
import com.example.action.CameraInterface.CamOpenOverCallback;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView implements Callback,CamOpenOverCallback {
	private static final String TAG = "CameraSurfaceView";  

	Context mContext;  
	SurfaceHolder mSurfaceHolder; 


	public CameraSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mSurfaceHolder = getHolder();
		mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
		mSurfaceHolder.addCallback(this);  

	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		 Log.i(TAG, "surfaceCreated...");  
		 new Thread(){
			 public void run() {
				 CameraInterface.getInstance().doOpenCamera(CameraSurfaceView.this);
			 };
		 }.start();
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		Log.i(TAG, "surfaceChanged...");  
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		CameraInterface.getInstance().doStopCamera();
	}

	public SurfaceHolder getSurfaceHolder(){
		return mSurfaceHolder;
	}


	@Override
	public void cameraHasOpened() {
		// TODO Auto-generated method stub
		float previewRate = 1.33f;
		CameraInterface.getInstance().doStartPreview(getHolder(), previewRate);
	}
}
