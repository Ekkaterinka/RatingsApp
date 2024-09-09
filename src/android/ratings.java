package com.microline.zont.ratings;

import com.google.android.gms.tasks.*;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;


public class ratings extends CordovaPlugin {

  protected void appStoreReview(int numberVisits, CallbackContext callbackContext) {
      if (numberVisits >10) {
        ReviewManager manager = ReviewManagerFactory.create(cordova.getContext());
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
