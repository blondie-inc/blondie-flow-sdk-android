# Blondie SDK

Get started on integrating the Blondie into your native Android app through these guides:

## Installation

Install Blondie to perform automations right from your iOS app.

### Step 1 - Install Blondie SDK

Before you start, you need to make sure you have an access to the Blondie. If you are using Maven, add the following to your build.gradle file:

```
implementation 'com.blondie.sdk:blondie-android:1.0.0'
```

### Step 2 - Initialize Blondie

First, you'll need to get your Blondie Flow ID and an API key. To find these, just add an SDK trigger to your Blondie Flow.

Then initialize Blondie SDK by importing Library and adding the following to your application delegate:

```java
import com.blondie.sdk.Blondie;
  
public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Blondie.setApiKey("<Your JWT API Key>");
    }
}
```

## Configuration

Here’s how to configure Blondie for Android:

### Select an environment

You can select the Blondie Flow environment to use. It is very handy when you need to test a particular feature before rolling out to production.

By default we select `production` environment, but you can always change it by calling one of:

```java
Blondie.useDevelopmentEnvironment();
Blondie.useTestEnvironment();
Blondie.useProductionEnvironment();
```

### Use a custom Blondie Flow instance

In order to integrate Blondie SDK with a custom Blondie Flow instance you can set the base url by calling:

```java
Blondie.setBaseUrl("https://custom.flow.url");
```

### Disable offline mode

By default Blondie SDK works both in both online and offline mode. In order to work without an internet connection, Blondie SDK keeps a queue of events to sync when the connection becomes available again.

You can disable that behaviour by calling:

```java
Blondie.disableOfflineMode()
```

### Disable auto retries

By default Blondie SDK performs automatic retries if an error occurs during a request to the Blondie Flow, so that you don't need to worry about missing an event.

You can disable that behaviour by calling:

```java
Blondie.disableAutoRetries();
```

## Tracking events

You can log events in Blondie that record what users do in your app and when they do it. For example, you could record the data a user submitted in your mobile app, and when they submitted it.

```java
import com.blondie.sdk.Blondie;
import com.blondie.sdk.BlondieEvent;
  
BlondieEvent event = new BlondieEvent("Short Form Submitted");
event.set("amount", 1234);
event.set("customer.phone", "+3712654321");
event.set("customer.email", "demo@example.com");
Blondie.triggerEvent(event);
```
