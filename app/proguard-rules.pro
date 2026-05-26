# Team21 AE Consulting — ProGuard Rules
-keep class com.team21.consulting.** { *; }
-keepclassmembers class com.team21.consulting.** { *; }

# WebView JavaScript interface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# AndroidX WebKit
-keep class androidx.webkit.** { *; }
-keep interface androidx.webkit.** { *; }

# Keep WebView
-keepclassmembers class android.webkit.WebView {
    public *;
}

# Suppress warnings
-dontwarn android.webkit.**
-dontwarn androidx.webkit.**
