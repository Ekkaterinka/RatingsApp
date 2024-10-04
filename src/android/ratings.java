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

  protected void appStoreReview(CallbackContext callbackContext) {
        ReviewManager manager = ReviewManagerFactory.create(cordova.getContext());
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(cordova.getActivity(), reviewInfo);
                flow.addOnCompleteListener(launchTask -> {
                    try {
                        if (task.isSuccessful()) {
                            callbackContext.success("Review requested");
                        } else {
                            callbackContext.error("Review failed: ".concat(launchTask.getException().getMessage()));
                        }
                    } catch (Exception e) {
                        callbackContext.error("Exception occurred: ".concat(e.getMessage()));
                    }
                });

            } else {
                @ReviewErrorCode int reviewErrorCode = ((ReviewException) task.getException()).getErrorCode();
                callbackContext.error("reviewErrorCode: "+ reviewErrorCode);
            }
          });
        callbackContext.success();
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      if (action.equals("appStoreReview")) {
          appStoreReview(callbackContext);
          return true;
      }
      return false;
  }
}
