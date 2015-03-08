package com.labs.nakama.fivecard;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
   @Override
   public IBinder onBind(Intent arg0) {
      return mBinder;
   }

   // Binder given to clients
   private final IBinder mBinder = new LocalBinder();
   
   /**
    * Class used for the client Binder.  Because we know this service always
    * runs in the same process as its clients, we don't need to deal with IPC.
    */
   public class LocalBinder extends Binder {
       MyService getService() {
           // Return this instance of LocalService so clients can call public methods
           return MyService.this;
       }
   }

   
   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
      // Let it continue running until it is stopped.
	 //  String ip = getInetIp();
	   Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
	   return START_STICKY;
   }
   @Override
   public void onDestroy() {
      super.onDestroy();
      Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
   }
   
   public String getLocalIpAddress(){
	   try {
	       for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();  
	       en.hasMoreElements();) {
	       NetworkInterface intf = en.nextElement();
	           for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) 
	           {
	        	    InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                return inetAddress.getHostAddress().toString();
	                }
	           }
	       }
	       } catch (Exception ex) {
	          Log.e("IP Address", ex.toString());
	      }
	      return null;
	}
   
   public String getInetIp()
   {
	   try
	   {
		   String add = getLocalIpAddress();
		   Log.e("IP Address", add);
		   return add;
	   }
	   catch (Exception ex)
	   {
		   Log.e("getInetIP", ex.toString());
	   }
	   return null;
   }
   
   protected String wifiIpAddress(Context context) {
	   String ipAddressString;
	   try {
		   WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
		   int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

	    // Convert little-endian to big-endianif needed
		   if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
			   ipAddress = Integer.reverseBytes(ipAddress);
		   }

		   byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

		   
	    
	        ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
	    } catch (UnknownHostException ex) {
	        Log.e("WIFIIP", "Unable to get host address.");
	        ipAddressString = "WIFI is off";
	    }

	    return ipAddressString;
	}
   
   public static boolean isSharingWiFi(Context context)
   {
       try
       {
    	   WifiManager manager = (WifiManager) context.getSystemService(WIFI_SERVICE);
           Method method = manager.getClass().getDeclaredMethod("isWifiApEnabled");
           method.setAccessible(true); //in the case of visibility change in future APIs
           return (Boolean) method.invoke(manager);
       }
       catch (Exception ex)
       {
    	   Log.e("AP setting", ex.toString());
       }

       return false;
   }
}
