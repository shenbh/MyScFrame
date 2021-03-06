# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# copyright zhonghanwen
#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
## Optimization is turned off by default. Dex does not like code run
## through the ProGuard optimize and preverify steps (and performs some
## of these optimizations on its own).
## 关闭优化（原因见上边的原英文注释）
#-dontoptimize
##混淆时不使用大小写混合类名
#-dontusemixedcaseclassnames
##不跳过library中的非public的类
#-dontskipnonpubliclibraryclasses
##打印混淆的详细信息
#-verbose
#
##保留注解中的参数
#-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
#
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#-keep public class com.google.android.vending.licensing.ILicensingService
#-dontnote com.android.vending.licensing.ILicensingService
#-dontnote com.google.vending.licensing.ILicensingService
#-dontnote com.google.android.vending.licensing.ILicensingService
#
##不混淆包含native方法的类的类名以及native方法名
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
##不混淆View中的setXxx()和getXxx()方法，以保证属性动画正常工作
#-keepclassmembers public class * extends android.view.View {
#    void set*(***);
#    *** get*();
#}
#
##不混淆Activity中参数是View的方法，例如，一个控件通过android:onClick="clickMethodName"绑定点击事件，混淆后会导致点击事件失效
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
#
## 不混淆枚举类中的values()和valueOf()方法
## 使用enum类型时需要注意避免以下两个方法混淆，因为enum类的特殊性，以下两个方法会被反射调用，
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
##保持 Parcelable 不被混淆
#-keepclassmembers class * implements android.os.Parcelable {
#    public static final ** CREATOR;
#}
#
## 不混淆R文件中的所有静态字段，以保证正确找到每个资源的id
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
#
##不混淆Javascript接口方法
#-keepclassmembers class * {
#    @android.webkit.JavascriptInterface <methods>;
#}
##不对android.support包下的代码警告（如果我们打包的版本低于support包下某些类的使用版本，会出现警告的问题）
#-dontnote android.support.**
#-dontwarn android.support.**
#
## 不混淆Keep类
#-keep class android.support.annotation.Keep
## 不混淆使用了注解的类及类成员
#-keep @android.support.annotation.Keep class * {*;}
## 如果类中有使用了注解的方法，则不混淆类和类成员
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <methods>;
#}
## 如果类中有使用了注解的字段，则不混淆类和类成员
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <fields>;
#}
## 如果类中有使用了注解的构造函数，则不混淆类和类成员
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <init>(...);
#}

#---------------------------------以上是系统默认proguard-android.txt所带----------------------------------



#代码混淆压缩比，在0~7之间，默认为5，一般不需要更改
-optimizationpasses 5
#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers
#进行预校验，可加快混淆速度
-dontpreverify
-printmapping proguardMapping.txt
#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
##与上一条一起使用
#-renamesourcefileattribute SourceFile
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}
-keep interface android.support.** { *; }


-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 所有View的子类及其子类的get、set方法都不进行混淆
#保持自定义控件指定规则的方法不被混淆
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）
-keep public class * implements java.io.Serializable {
        public *;
}

#不混淆Serializable接口的子类中指定的某些成员变量和方法
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持R文件不被混淆，否则，你的反射是获取不到资源id的
-keep class **.R$* {*;}

# 不混淆R类里及其所有内部static类中的所有static变量字段，$是用来分割内嵌类与其母体的标志
-keep public class **.R$*{
   public static final int *;
}

-keepclassmembers class * {
    void *(*Event);
}

#保持自定义控件类不被混淆，指定格式的构造方法不去混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep class org.** {*;}
-keep class com.android.**{*;}


#v7 不混淆
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-dontwarn android.support.v7.**

# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

# support-v7-appcompat
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

# support-v7-cardview.pro
# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }

-dontwarn android.net.http.**
-keep class org.apache.http.** { *;}

#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
#过滤js
-keepattributes *JavascriptInterface*
#保护WebView对HTML页面的API不被混淆
-keep class **.Webview2JsInterface { *; }
#WebView中使用了JS调用，需要添加如下配置
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
    public void *(android.webkit.WebView,java.lang.String);
    public void openFileChooser(...);
    public void onShowFileChooser(...);
}
#----------------------------------------------------------------------------


#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------实体类---------------------------------
#修改成你对应的包名
-keep class com.shenbh.myscframe.config.** { *; }

#---------------------------------反射相关的类和方法-----------------------
#在这下面写反射相关的类和方法，没有就不用写！




#---------------------------------与js互相调用的类------------------------
#在这下面写与js互相调用的类，没有就去掉这句话！

#---------------------------------自定义View的类------------------------
#在这下面写自定义View的类的类，没有就去掉这句话！

#---------------------------------第三方包-------------------------------

#glide3.7
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#BaseRecyclerViewAdapterHelper2.9.40
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

#permission:1.1.2
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionYes <methods>;
}
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionNo <methods>;
}

#utilcode:1.20.3
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#barlibrary:2.3.0
-keep class com.gyf.barlibrary.* {*;}

#eventbus3.1.1
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#butterknife8.8.1
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

##Luban1.1.7  https://github.com/Curzibn/Luban/issues/83
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.ArrayQueueField* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#Retrofit2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions

# Retrolambda
-dontwarn java.lang.invoke.*

#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keepclassmembers class rx.android.**{*;}

#Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
-keep class org.xz_sale.entity.**{*;}
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
# 这是你定义的实体类
#-keep class com.sunloto.shandong.bean.** { *; }

#fastjson1.1.70android https://github.com/alibaba/fastjson/issues/1173
-keepattributes Signature
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }
-keep class * implements com.shenbh.scframe.utils.IUnProguard

#okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#RxLifeCycle
-dontwarn com.trello.rxlifecycle2.**

#leakcanary1.6.1 https://github.com/square/leakcanary/blob/master/leakcanary-android/consumer-proguard-rules.pro
-dontwarn com.squareup.haha.guava.**
-dontwarn com.squareup.haha.perflib.**
-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
-keep class com.squareup.haha.** { *; }
-keep class com.squareup.leakcanary.** { *; }
# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

