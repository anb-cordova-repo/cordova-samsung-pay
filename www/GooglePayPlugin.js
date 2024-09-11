var exec = require("cordova/exec");
const PLUGIN_NAME = "GoogleProv";

module.exports = {
  checkEligibility: function (success, error) {
    exec(success, error, PLUGIN_HOME, "checkEligibility");
  },
  getActiveWalletId: function (success, error) {
    exec(success, error, PLUGIN_HOME, "getActiveWalletId");
  },
  getStableHardwareId: function (success, error) {
    exec(success, error, PLUGIN_HOME, "getStableHardwareId");
  },
  getEnvironment: function (success, error) {
    exec(success, error, PLUGIN_HOME, "getEnvironment");
  },
  registerDataChangedListener: function (success, error) {
    exec(success, error, PLUGIN_HOME, "registerDataChangedListener");
  },
  pushCardTokenize: function (success, error, args) {
    exec(success, error, PLUGIN_HOME, "pushCardTokenize", args);
  },
  viewCardToken: function (success, error, args) {
    exec(success, error, PLUGIN_HOME, "viewCardToken", args);
  },
  isCardTokenized: function (success, error, args) {
    exec(success, error, PLUGIN_HOME, "isCardTokenized", args);
  },
  getCardTokensList: function (success, error) {
    exec(success, error, PLUGIN_HOME, "getCardTokensList");
  },
  getCardTokenStatus: function (success, error, args) {
    exec(success, error, PLUGIN_HOME, "getCardTokenStatus", args);
  },
};
