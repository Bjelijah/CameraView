package com.example.action;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class TestSocket {
	private static TestSocket mInstance = null;
	public static TestSocket getInstance(){
		if (mInstance == null) {
			mInstance = new TestSocket();
		}
		return mInstance;
	}
	private TestSocket(){}
	
	DatagramSocket socket;  
	InetAddress address;
	
	public void init(){
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName("192.168.128.108");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendPacket(byte [] data,int len){
		DatagramPacket pack = new DatagramPacket(data, len,address,8823);
		try {
			socket.send(pack);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
