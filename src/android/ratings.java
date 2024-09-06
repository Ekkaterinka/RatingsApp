package com.coloz.esptouch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import com.google.android.play:review;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class appStoreReview extends CordovaPlugin {
  private CallbackContext esptouchCallbackContext;
  private final Object mLock = new Object();
  private static final String TAG = "appStoreReview";

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }


  @Override
  public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
    throws JSONException {
    if (action.equals("appStoreReview")) {
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                
                ReviewInfo reviewInfo = task.getResult();
            } else {
             
                @ReviewErrorCode int reviewErrorCode = ((ReviewException) task.getException()).getErrorCode();
            }
        });
    } else {
      callbackContext.error("can not find the function " + action);
    }
    return true;
  }

}