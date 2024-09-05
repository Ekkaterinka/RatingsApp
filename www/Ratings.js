var exec = require('cordova/exec');

exports.appStoreReview= function (arg0, success, error) {
    exec(success, error, 'ratings', 'AppStoreReview', [arg0]);
};
