#import "ratings.h"


@implementation ratings

- (void) appStoreReview:(CDVInvokedUrlCommand *)command{
    CDVPluginResult* pluginResult = nil;
    NSNumber* numberVisits = [command.arguments objectAtIndex:0];
    NSNumber* sufficientNumberVisits = [command.arguments objectAtIndex:1];
    
    if (@available(iOS 10.3, *)) {
        
        if (numberVisits >= sufficientNumberVisits) {
            [SKStoreReviewController requestReview];
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"AppStoreReview start"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"The condition did not pass"];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    }
}

@end