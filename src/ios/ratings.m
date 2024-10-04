#import "ratings.h"


@implementation ratings

- (void) appStoreReview:(CDVInvokedUrlCommand *)command{
    CDVPluginResult* pluginResult = nil;
    
    if (@available(iOS 14, *)) {
        [SKStoreReviewController requestReviewInScene: self.viewController.view.window.windowScene];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Review requested"];
        } else if (@available(iOS 10.3, *)){
            [SKStoreReviewController requestReview];
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Review requested"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Review failed"];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

@end