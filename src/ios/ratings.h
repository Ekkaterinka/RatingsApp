#import <Cordova/CDVPluginResult.h>
#import <Cordova/CDVPlugin.h>
#import <StoreKit/StoreKit.h>

@interface ratings : CDVPlugin

- (void)appStoreReview:(CDVInvokedUrlCommand*)command;

@end