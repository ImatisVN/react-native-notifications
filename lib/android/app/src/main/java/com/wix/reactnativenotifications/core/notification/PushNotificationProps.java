package com.wix.reactnativenotifications.core.notification;

import android.os.Bundle;

public class PushNotificationProps {

    protected Bundle mBundle;

    public PushNotificationProps(Bundle bundle) {
        mBundle = bundle;
    }

    public String getTitle() {
        return getBundleStringFirstNotNull("gcm.notification.title", "title");
    }

    public String getBody() {
        return getBundleStringFirstNotNull("gcm.notification.body", "body");
    }

    public String getGuid() {
        return getBundleStringFirstNotNull("message", "guid");
    }

    public String getSound() {
        return getBundleStringFirstNotNull("alertsound", "sound");
    }
    
    public String getChannelId() {
        return getBundleStringFirstNotNull("gcm.notification.android_channel_id", "android_channel_id");
    }

    public Bundle asBundle() {
        return (Bundle) mBundle.clone();
    }

    public boolean isFirebaseBackgroundPayload() {
        return mBundle.containsKey("google.message_id");
    }
    
    public boolean isDataOnlyPushNotification() {
        return getTitle() == null && getBody() == null;
    }

    public Boolean getCriticalAlert() {
        String val = mBundle.getString("criticalalert");
        if(val != null && !val.isEmpty()){
            return val.toLowerCase().equals("true");
        }
        return false;
    }

    public Double getCriticalAlertVolume() {
        String val = mBundle.getString("criticalalertvolume");
        if(val != null && !val.isEmpty()){
            return Double.parseDouble(val);
        }
        return 0.0;
    }

    public Double getVolume() {
        String val = mBundle.getString("volume");
        if(val != null && !val.isEmpty()){
            return Double.parseDouble(val);
        }
        return 0.0;
    }

    public Boolean getOverridemute() {
        String val = mBundle.getString("overridemute");
        if(val != null && !val.isEmpty()){
            return val.toLowerCase().equals("true");
        }
        return false;
    }

    public Boolean getOngoing() {
        return mBundle.getBoolean("ongoing");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        for (String key : mBundle.keySet()) {
            sb.append(key).append("=").append(mBundle.get(key)).append(", ");
        }
        return sb.toString();
    }

    protected PushNotificationProps copy() {
        return new PushNotificationProps((Bundle) mBundle.clone());
    }

    private String getBundleStringFirstNotNull(String key1, String key2) {
        String result = mBundle.getString(key1);
        return result == null ? mBundle.getString(key2) : result;
    }
}
