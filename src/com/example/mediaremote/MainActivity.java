package com.example.mediaremote;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button connect = (Button)findViewById(R.id.buttonConnect);
		connect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable(){
					public void run(){
						connectSocket("Connect");
					}
				}).start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void connectSocket(String message)
	{
		try
		{
			InetAddress serverAddr_ = InetAddress.getByName("192.168.0.101");
			Socket socket_ = new Socket(serverAddr_, 8001);
		
			PrintWriter output = null;
		
			try
			{
				output = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket_.getOutputStream())),true);
				output.write(message);
				output.flush();//sends message without closing connection
			
			}
			catch (Exception e){}
		}
		catch (UnknownHostException e){}
		catch (IOException e){}
	}

}
