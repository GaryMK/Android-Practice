# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class bjfu.it.mukaikai.weather.bean.CityInfoWeather** { *; }
-keep class bjfu.it.mukaikai.weather.bean.IPSee** { *; }
-keep class bjfu.it.mukaikai.weather.bean.SKWeather** { *; }

# bean 下面的所有类的所有内容都keep
#-keep class bjfu.it.mukaikai.weather.bean.** { *; }
# 更详细规则： https://www.guardsquare.com/en/products/proguard/manual/usage
#Google Play 注册地址： https://play.google.com/apps/publish/signup