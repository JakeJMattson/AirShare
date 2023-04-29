# AirShare

Send text over your local network

## The Problem

If you want to get text from your phone to your PC, you usually have to use some other service.
You will probably send yourself an email, post the text into a cloud app like discord, or just try to copy it down manually.
All of these are awkward.

## The solution

This app creates a simple POST endpoint and then copies whatever it receives to the system clipboard.

## How I use it

By writing a shortcut on my phone, I can send my clipboard data to the endpoint created by this app.
Then I use an NFC tag to trigger this shortcut. So I can transfer my clipboard from phone to PC by just tapping the tag.

## How you use it

To run the app you need have hava Java 11+ installed. If you don't know anything about Java, you can use a 
[Java installer](https://adoptium.net/temurin/releases/?version=17).

You can download the `airshare.jar` file from GitHub releases, or build it yourself. Then you can run the app from your terminal
```
java -jar airshare.jar
```

## The technical bits

The endpoint defaults to `ip:6583`, but the port can be changed by passing in your own argument
```
java -jar airshare.jar 1234
```
