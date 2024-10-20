# Samsung Pay Plugin for Android 

A Cordova plugin that provides integration with Samsung Pay services.

## Installation

```bash
cordova plugin add https://github.com/anb-cordova-repo/Samsung-pay-plugin.git
```

## Usage

The Samsung Pay Plugin provides several methods to interact with Samsung Pay services:

### 1. Check Eligibility

```javascript
cordova.plugins.SamsungProv.checkEligibility(success, error);
```

### 2. Add Card

```javascript
cordova.plugins.SamsungProv.addCard(payload, success, error);
```

### 3. Get Card By Id

```javascript
cordova.plugins.SamsungProv.getCardById(cardId, success, error);
```

### 4. Activate Samsung Pay

```javascript
cordova.plugins.SamsungProv.activateSamsungPay(success, error);
```

### 5. Go To Update Page

```javascript
cordova.plugins.SamsungProv.goToUpdatePage(success, error);
```

### 6. Get Visa Wallet Info

```javascript
cordova.plugins.SamsungProv.getVisaWalletInfo(success, error);
```

### 7. Get All Cards

```javascript
cordova.plugins.SamsungProv.getAllCards(success, error);
```

### 8. set Service Id

```javascript
cordova.plugins.SamsungProv.setServiceId(setServiceId,success, error);
```

### 9. init

```javascript
cordova.plugins.SamsungProv.init(success, error);
```

### 10. add Co Badge Card

```javascript
cordova.plugins.SamsungProv.addCoBadgeCard(payload,success, error);
```

## Platforms Supported

- Android

## Authors

- Mohammad Al Nassri - anb
- Ahmad Al Nsour - anb


## License

This project is licensed under the MIT License.
