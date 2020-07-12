package com.cheney.mvvm_demo.base.di;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.cheney.mvvm_demo.constants.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

    @Singleton
    @Provides
    @Named(Constants.NamedKey.PACKAGE_NAME)
    static String providePackageName(Context context) {
        return context.getPackageName();
    }

    @Singleton
    @Provides
    @Named(Constants.NamedKey.VERSION_NAME)
    String provideVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    @Singleton
    @Provides
    @Named(Constants.NamedKey.VERSION_CODE)
    long provideVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            long version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
