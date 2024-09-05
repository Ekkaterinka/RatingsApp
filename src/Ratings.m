#import "Ratings.h"
#import <Cordova/CDVPlugin.h>
#import <StoreKit/StoreKit.h>

@implementation Ratings

- (void)appStoreReview:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* numberVisits = [command.arguments objectAtIndex:0];

    if (numberVisits > 10 && @available(iOS 10.3, *)) {
        [SKStoreReviewController requestReview];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"AppStoreReview start"];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end