#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVPluginResult.h>

@interface Ratings : CDVPlugin

- (void)appStoreReview:(CDVInvokedUrlCommand*)command;

@end