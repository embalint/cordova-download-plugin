package com.heartade;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import android.provider.MediaStore;
import android.os.Build;
import android.content.ContentValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.CookieManager;


/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaAndroidMediaStore extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {
            //String message = args.getString(0);
            this.download(args.getString(0), callbackContext);
            return true;
        }
        return false;
    }

    private void download(JSONObject jsonObject) {

        try {
        Log.d("emil", "start method" + jsonObject.toString());
        //String url = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4";
        String url = jsonObject.getString("videoUrl");
        Log.d("emil url", url.toString());
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse((url)));
        //String title = URLUtil.guessFileName(url, null, null);
        request.setTitle("testEmil");
        request.setDescription("Downloading file");
        String cookie = CookieManager.getInstance().getCookie(url);
        request.addRequestHeader("Cookie", cookie);
        //request.addRequestHeader("Authorization", 'Bearer ' + token);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "testinho.mp4");

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);

        } catch (JSONException e){
            Log.d("jsonException", e.toString());
        }

    }

}
