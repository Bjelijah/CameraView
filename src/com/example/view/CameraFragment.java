package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import android.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup;



/**
 * http://www.2cto.com/kf/201501/367226.html
 * @author howell
 *
 *	
 */
@SuppressLint("NewApi")
public class CameraFragment extends Fragment implements SurfaceTextureListener{//FIXME 摄像头横

	private Handler mHandler;
	private HandlerThread mHandlerThread;
	private CameraManager mCamMgr;
	private TextureView mPreTexture;
	private Size mPreviewSize;
	private CaptureRequest.Builder mPreviewBuilder;

	private StateCallback mCameraStateCallback = new StateCallback() {

		@Override
		public void onOpened(CameraDevice camera) {
			try {
				startPreview(camera);
			} catch (CameraAccessException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onError(CameraDevice camera, int error) {
			Log.e("123", "camera error:"+error);
		}

		@Override
		public void onDisconnected(CameraDevice camera) {
		}
	};

	private CameraCaptureSession.StateCallback mSessionCallback = new CameraCaptureSession.StateCallback() {

		@Override
		public void onConfigured(CameraCaptureSession session) {
			try {
				session.setRepeatingRequest(mPreviewBuilder.build(), null, mHandler);
			} catch (CameraAccessException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onConfigureFailed(CameraCaptureSession session) {

		}
	};


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		initLooper();
		View view = inflater.inflate(com.example.cameraviewtest.R.layout.play_fragment, null);
		initUI(view);
		return view;
	}

	private void initLooper(){
		mHandlerThread = new HandlerThread("CAMERA2");
		mHandlerThread.start();
		mHandler = new Handler(mHandlerThread.getLooper());
	}

	private void initUI(View v){
		mPreTexture = (TextureView) v.findViewById(com.example.cameraviewtest.R.id.texture_view);
		mPreTexture.setSurfaceTextureListener(this);
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		try {
			mCamMgr = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

			CameraCharacteristics characteristics = mCamMgr.getCameraCharacteristics("1");

			StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

			mPreviewSize = map.getOutputSizes(SurfaceTexture.class)[0];

			mCamMgr.openCamera("1", mCameraStateCallback, mHandler);
		} catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		return false;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		
	}

	private void startPreview(CameraDevice camera) throws CameraAccessException{
		SurfaceTexture texture = mPreTexture.getSurfaceTexture();
		texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
		Surface surface = new Surface(texture);

		mPreviewBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
		mPreviewBuilder.addTarget(surface);
		camera.createCaptureSession(Arrays.asList(surface), mSessionCallback, mHandler);


	}


}
