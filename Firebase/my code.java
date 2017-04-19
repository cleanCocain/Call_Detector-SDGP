
@Override
public void onTokenRefresh() {
    // Get updated InstanceID token.
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    Log.d(TAG, "Refreshed token: " + refreshedToken);

    // If you want to send messages to this application instance or
    // manage this apps subscriptions on the server side, send the
    // Instance ID token to your app server.
    sendRegistrationToServer(refreshedToken);
}
<service
    android:name=".MyFirebaseMessagingService">
    <intent-filter>
        <action android:name="call.firebase.MESSAGING_EVENT"/>
    </intent-filter>
</service>
<!-- Set custom default icon. This is used when no icon is set for incoming notification messages.
    
<meta-data
    android:name="call.firebase.messaging.default_notification_icon"
    android:resource="@drawable/ic_stat_ic_notification" />
<!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
     notification message. 
<meta-data
    android:name="call.firebase.messaging.default_notification_color"
    android:resource="@color/colorAccent" />

@Override
public void onMessageReceived(RemoteMessage remoteMessage) {
    // ...

    // TODO(developer): Handle FCM messages here.
    // Not getting messages here? See why this may be: 
    Log.d(TAG, "From: " + remoteMessage.getFrom());

    // Check if message contains a data payload.
    if (remoteMessage.getData().size() > 0) {
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        if (/* Check if data needs to be processed by long running job */ true) {
            // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
            scheduleJob();
        } else {
          
            handleNow();
        }

    }

    
    if (remoteMessage.getNotification() != null) {
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
    }

}