var exec = require("cordova/exec");
var PLUGIN_NAME = "SamsungProv";

module.exports = {
  checkEligibility: function (success, error) {
    exec(success, error, PLUGIN_NAME, "checkEligibility");
  },
  activateSamsungPay: function (success, error) {
    exec(success, error, PLUGIN_NAME, "activateSamsungPay");
  },
  goToUpdatePage: function (success, error) {
    exec(success, error, PLUGIN_NAME, "goToUpdatePage");
  },
  getVisaWalletInfo: function (success, error) {
    exec(success, error, PLUGIN_NAME, "getVisaWalletInfo");
  },
  addCard: function (success, error, payload) {
    exec(success, error, PLUGIN_NAME, "addCard", [payload]);
  },
  getAllCards: function (success, error) {
    exec(success, error, PLUGIN_NAME, "getAllCards");
  },
  getCardById: function (success, error, cardId) {
    exec(success, error, PLUGIN_NAME, "getCardById", [cardId]);
  },
  setServiceId: function (success, error, serviceId) {
    exec(success, error, PLUGIN_NAME, "setServiceId", [serviceId]);
  },
  getServiceId: function (success, error) {
    exec(success, error, PLUGIN_NAME, "getServiceId");
  },
  init: function (success, error) {
    exec(success, error, PLUGIN_NAME, "init");
  },
  verifyCardIdv: function(success, error, args) {
    exec(success, error, PLUGIN_NAME, 'verifyCardIdv', args);
};
};
