var exec = require('cordova/exec');

exports.appStoreReview= function (numberVisits,sufficientNumberVisits, success, error) {
    exec(success, error, 'ratings', 'appStoreReview', [numberVisits,sufficientNumberVisits]);
};
