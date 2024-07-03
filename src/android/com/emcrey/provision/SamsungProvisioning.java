package com.emcrey.provision;

import android.content.Context;
import android.text.TextUtils;

import com.emcrey.payment.ISamProvSDK;
import com.emcrey.payment.SamProvSDK;
import com.emcrey.payment.card.CardInfo;
import com.emcrey.payment.card.Result;
import com.emcrey.payment.card.Scheme;
import com.emcrey.payment.listeners.CardResultListener;
import com.emcrey.payment.listeners.GetCardsListener;
import com.emcrey.payment.listeners.GetVisaInfoListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

public class SamsungProvisioning extends CordovaPlugin {

    private CallbackContext callbackContext;
    private ISamProvSDK iSamProvSDK;
    private final Gson gson = new Gson();
    private String ServiceId;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("checkEligibility")) {
            this.checkEligibility();
            return true;
        }
        if (action.equals("addCard")) {
            this.addCard(args);
            return true;
        }
        if (action.equals("activateSamsungPay")) {
            this.activateSamsungPay();
            return true;
        }
        if (action.equals("goToUpdatePage")) {
            this.goToUpdatePage();
            return true;
        }
        if (action.equals("getVisaWalletInfo")) {
            this.getVisaWalletInfo();
            return true;
        }
        if (action.equals("getAllCards")) {
            this.getAllCards();
            return true;
        }
        if (action.equals("getCardById")) {
            this.getCardById(args);
            return true;
        }
        if (action.equals("setServiceId")) {
            this.setServiceId(args);
            return true;
        }
        if (action.equals("init")) {
            this.init();
            return true;
        }
        return false;
    }

    private void init(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        if (!TextUtils.isEmpty(ServiceId)) {
            this.iSamProvSDK = SamProvSDK.initSamsungPrevisioning(cordova.getActivity(), ServiceId);
            callbackContext.success("Samsung SDK initialized with Service ID: " + ServiceId);
        } else {
            callbackContext.error("Service ID not set");
        }
    }

    public void checkEligibility() {
        iSamProvSDK.checkEligibility((available, result) - > {
            JSONObject object = new JSONObject();
            try {
                object.put("available", available);
                object.put("result", gson.toJson(result));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            callbackContext.success(object);
        });
    }

    private void setServiceId(JSONArray args) throws JSONException {
        String newServiceId = args.getString(0);
        if (!TextUtils.isEmpty(newServiceId)) {
            this.ServiceId = newServiceId;
            this.init(this.cordova, this.webView);
            callbackContext.success("Service ID set to " + newServiceId);
        } else {
            callbackContext.error("Service ID is empty");
        }
    }

    private void addCard(JSONArray args) throws JSONException {
        String payload = (String) args.get(0);
        Scheme scheme = Scheme.valueOf((String) args.get(1));
        iSamProvSDK.addCard(payload, scheme, new CardResultListener() {
            @Override
            public void onSuccess(CardInfo cardInfo) {
                String card = gson.toJson(cardInfo);
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("cardInfo", card);
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.success(object);
            }

            @Override
            public void onFail(Result result) {

                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("result", gson.toJson(result));
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.error(object);
            }
        });
    }

    private void getVisaWalletInfo() {
        iSamProvSDK.getVisaWalletInfo(new GetVisaInfoListener() {
            @Override
            public void onSuccess(String clientDeviceId, String clientWalletAccountId) {
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("clientDeviceId", clientDeviceId);
                    object.put("clientWalletAccountId", clientWalletAccountId);
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.success(object);
            }

            @Override
            public void onFail(Result result) {
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("result", gson.toJson(result));
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.error(object);
            }
        });
    }

    private void getAllCards() {
        iSamProvSDK.getAllCards(new GetCardsListener() {
            @Override
            public void onSuccess(List < CardInfo > list) {
                Type listType = new TypeToken < List < CardInfo >> () {}.getType();
                String card = gson.toJson(list, listType);
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("cards", card);
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.success(object);
            }

            @Override
            public void onFail(Result result) {
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("result", gson.toJson(result));
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.error(object);
            }
        });
    }

    private void getCardById(JSONArray args) throws JSONException {
        String cardId = (String) args.get(0);
        iSamProvSDK.getCardById(cardId, new CardResultListener() {
            @Override
            public void onSuccess(CardInfo cardInfo) {
                String card = gson.toJson(cardInfo);
                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("cardInfo", card);
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.success(object);
            }

            @Override
            public void onFail(Result result) {

                JSONObject object;
                try {
                    object = new JSONObject();
                    object.put("result", gson.toJson(result));
                } catch (JSONException e) {
                    object = new JSONObject();
                }
                callbackContext.error(object);
            }
        });
    }

    private void activateSamsungPay() {
        iSamProvSDK.activateSamsungPay();
    }

    private void goToUpdatePage() {
        iSamProvSDK.goToUpdatePage();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onReset() {

    }
}