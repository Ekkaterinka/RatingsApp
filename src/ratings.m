#import "ratings.h"


@implementation ratings

- (void) appStoreReview:(CDVInvokedUrlCommand *)command{
    CDVPluginResult* pluginResult = nil;
    NSNumber* numberVisits = [command.arguments objectAtIndex:0];
    
    if (@available(iOS 10.3, *)) {
        if ([numberVisits intValue] >= 10) {
            [SKStoreReviewController requestReview];
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"AppStoreReview start"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"AppStoreReview error"];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    }
}

@end