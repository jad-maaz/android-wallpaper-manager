# android-wallpaper-manager


This project only supports debug builds at this point.


## Installation Instructions (Windows)

Gradle is included in the source code but adb will have to installed. To install adb, open PowerShell and winget Platform Tools:

    winget install Google.PlatformTools

Add it to your PATH to call adb from anywhere.

Download the repo:

    git clone https://github.com/jad-maaz/android-wallpaper-manager

If you use a Samsung device with Auto Blocker, temporarily disable it and enable USB debugging in Developer Options. Alternatively, enable WiFi Debugging to install over WiFi.

Move into the repo and run the following from root:

    ./gradlew assembleDebug

    adb install -r "path\to\repo\android-wallpaper-manager\app\build\outputs\apk\debug\app-debug.apk"

The app should now be installed on your phone.


## Overview

### Scope
Rotate through chosen lockscreen and home wallpaper directories, randomly choosing a new wallpaper for both on a set timer.

### Current Limitations
The timer to rotate pictures is currently hardcoded to 30 minutes and cannot be modified in-app.
