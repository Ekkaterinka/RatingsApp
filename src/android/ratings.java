package com.coloz.ratings;

import com.google.android.play:review;

package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ratings extends CordovaPlugin {

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }
  
  protected void appStoreReview(int numberVisits, CallbackContext callbackContext) {
      if (numberVisits != null && numberVisits >= 10) {
        ReviewManager manager = ReviewManagerFactory.create(this);
          Task<ReviewInfo> request = manager.requestReviewFlow();
          request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                
                ReviewInfo reviewInfo = task.getResult();
            } else {
             
                @ReviewErrorCode int reviewErrorCode = ((ReviewException) task.getException()).getErrorCode();
            }
          });
          callbackContext.success(numberVisits);
      } else {
          callbackContext.error("Expected one non-empty string argument.");
      }
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      if (action.equals("appStoreReview")) {
          int numberVisits = args.getInt(0);
          appStoreReview(numberVisits, callbackContext);
          return true;
      }
      return false;
  }
}
