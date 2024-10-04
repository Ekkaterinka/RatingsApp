var exec = require('cordova/exec');

exports.appStoreReview= function (success, error) {
    exec(success, error, 'ratings', 'appStoreReview', []);
};
