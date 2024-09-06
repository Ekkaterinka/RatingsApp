cordova.define("cordova-plugin-app-ratings-in-store.appStoreReview", function(require, exports, module) {
var exec = require('cordova/exec');

exports.appStoreReview= function (numberVisits,sufficientNumberVisits, success, error) {
    exec(success, error, 'ratings', 'appStoreReview', [numberVisits,sufficientNumberVisits]);
};

});