# android-wallpaper-manager


This project only supports debug builds at this point.


## Installation Instructions (Windows)

Git clone the repo:

    git clone https://github.com/jad-maaz/android-wallpaper-manager

If you use a Samsung device with Auto Blocker, temporarily disable it and enable USB debugging in Developer Options. Alternatively, enable WiFi Debugging to install over WiFi.

Move into the repo and run the following from root:

    ./gradlew assembleDebug
    adb install -r "path\to\repo\android-wallpaper-manager\app\build\outputs\apk\debug\app-debug.apk"

The app should now be installed on your phone.
