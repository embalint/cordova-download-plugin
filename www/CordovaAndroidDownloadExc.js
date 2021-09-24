var exec = require('cordova/exec');

module.exports = {
    download: function (uri, success, failure) {
        exec(success, failure, 'CordovaAndroidDownload', 'download', [uri]);
    },
};
