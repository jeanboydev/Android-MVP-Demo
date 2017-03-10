# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Develop\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

##--------------------------------------通用配置---------------------------------------------------
# 代码压缩级别
-optimizationpasses 5

# 使用大小写混合
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
# 混淆时预校验
-dontpreverify
# 记录日志
-verbose
-dontshrink

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-assumenosideeffects class android.util.Log {
     public static boolean isLoggable(java.lang.String, int);
     public static int v(...);
     public static int i(...);
     public static int w(...);
     public static int d(...);
     public static int e(...);
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment

-keep class * implements android.os.Serializable {*;}
-keep class * implements java.io.Serializable {*;}

-keep public class * implements android.os.Parcelable {*;}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# Keep enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep view
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepattributes *Annotation* -keepattributes SourceFile,LineNumberTable

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-keep public class * extends java.lang.Exception

# Keep the support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-keep class * extends android.support.design.widget.CoordinatorLayout$Behavior {
    *;
}

-keep interface android.support.** { *; }

-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }

-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

-keep class android.support.v8.** { *; }
-keep interface android.support.v8.** { *; }

-keep class android.support.v8.renderscript.** { *; }
-keep interface android.support.v8.renderscript.** { *; }

# Allow obfuscation of android.support.v7.internal.view.menu.**
# to avoid problem on Samsung 4.2.2 devices with appcompat v21
# see https://code.google.com/p/android/issues/detail?id=78377
-keep class !android.support.v7.internal.view.menu.**,android.support.** {*;}

-keep class * extends android.support.v7.internal.view.menu.MenuBuilder
-keep class * implements android.support.v7.internal.view.menu.MenuBuilder
-keep class android.support.v7.internal.view.menu.MenuBuilder
##--------------------------------------通用配置---------------------------------------------------


##--------------------------------------App 配置---------------------------------------------------
# R 文件
-keep public class com.jeanboy.app.mvpdemo.R$*{
		public static final int *;
}

# model
-keep class com.jeanboy.app.mvpdemo.cache.database.model.** { *; }

##--------------------------------------App 配置---------------------------------------------------

##greenDAO
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

-dontwarn rx.**
-keep class rx.** { *; }
-dontwarn net.sqlcipher.database.SQLiteOpenHelper
-keep class net.sqlcipher.database.SQLiteOpenHelper
-dontwarn net.sqlcipher.database.SQLiteDatabase
-keep class net.sqlcipher.database.SQLiteDatabase
-dontwarn net.sqlcipher.database.SQLiteStatement
-keep class net.sqlcipher.database.SQLiteStatement
-dontwarn org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper
-keep class org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper
-dontwarn net.sqlcipher.database.SQLiteDatabase$CursorFactory

##FastJson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

##OkHttp
-dontwarn okhttp3.**

##Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}