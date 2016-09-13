package com.example.cameraviewtest;

import com.example.action.CameraInterface;
import com.example.action.CameraInterface.CamOpenOverCallback;
import com.example.view.CameraGLSurfaceView;
import com.example.view.CameraSurfaceView;
import com.example.view.CameraTextureView;
import com.example.view.YUVGLSurfaceView;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements Callback {

	Button btn1,btn2;
	CameraGLSurfaceView camGlSV;
	CameraTextureView camTV;
	CameraSurfaceView camSV;
	YUVGLSurfaceView yuvSV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		int r = getWindowManager().getDefaultDisplay().getRotation();
//		CameraInterface.getInstance().setRotation(r);
		
		setContentView(R.layout.activity_main);
		
//		btn1 = (Button) findViewById(R.id.btn1);
//		btn1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//	
//		btn2 = (Button) findViewById(R.id.btn2);

//		camSV = (CameraGLSurfaceView) findViewById(R.id.glsurface_view);
		
		/**
		 * 
		 * */
//		camTV = (CameraTextureView) findViewById(R.id.textureview);
//		new Thread(){
//			public void run() {
//				CameraInterface.getInstance().doOpenCamera(MainActivity.this);
//			};
//		}.start();

		
//		yuvSV = (YUVGLSurfaceView) findViewById(R.id.yuv_surfaceview);
	}
	

//	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		
		int r = getWindowManager().getDefaultDisplay().getRotation();
		Log.i("123", "on configuration change r="+r);
//		CameraInterface.getInstance().setRotation(r);
		
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("123", "surface crated");
	
		
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("123", "surface destroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
	
		super.onPause();
		
	}

	
	
	
}
