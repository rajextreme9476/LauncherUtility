package com.rxextreme.rxlauncherutils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class ApkInfoExtractor {

    Context context;

    public ApkInfoExtractor(Context context){

        this.context = context;
    }

    public List<String> GetAllInstalledApkInfo(){

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);

        intent.addCategory(Intent.CATEGORY_HOME);

        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent,0);

        for(ResolveInfo resolveInfo : resolveInfoList){

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if(!isSystemPackage(resolveInfo)){

                ApkPackageName.add(activityInfo.applicationInfo.packageName);
            }
        }

        return ApkPackageName;

    }

    public boolean isSystemPackage(ResolveInfo resolveInfo){

        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPackageName(String ApkTempPackageName){

        Drawable drawable = null;

        try{
            drawable = context.getPackageManager().getApplicationIcon(ApkTempPackageName);

        }
        catch (PackageManager.NameNotFoundException e){

            e.printStackTrace();

           // drawable = ContextCompat.getDrawable(context, R.drawable.ic_launcher);
        }
        return drawable;
    }

    public String GetAppName(String ApkPackageName){

        String Name = "";

        ApplicationInfo applicationInfo;

        PackageManager packageManager = context.getPackageManager();

        try {

            applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);

            if(applicationInfo!=null){

                Name = (String)packageManager.getApplicationLabel(applicationInfo);
            }

        }catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return Name;
    }


    public String GetVersionName(String ApkPackageName){

        String Name = "";

        try {

            //applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(ApkPackageName, 0);

            if(pInfo!=null){

                Name = pInfo.versionName;

            }

        }catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return Name;
    }

    public int GetVersionCode(String ApkPackageName){

        int version = 0;

        try {

            //applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(ApkPackageName, 0);

            if(pInfo!=null){

                version = pInfo.versionCode;

            }

        }catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return version;
    }

    public List<GetAppInfoModel> getAllInstalledLauncherInfo(){

        List<GetAppInfoModel> getAppInfoModelList = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent,0);

        for(ResolveInfo resolveInfo : resolveInfoList){

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if(!isSystemPackage(resolveInfo)){

                GetAppInfoModel getAppInfoModel = new GetAppInfoModel();
                getAppInfoModel.setAppPackageName(activityInfo.applicationInfo.name);
                getAppInfoModel.setAppIcon(getAppIconByPackageName(activityInfo.applicationInfo.packageName));
                getAppInfoModel.setAppName(GetAppName(activityInfo.applicationInfo.packageName));
                getAppInfoModel.setAppVersionCode(GetVersionCode(activityInfo.applicationInfo.packageName));
                getAppInfoModel.setAppVersionName(GetVersionName(activityInfo.applicationInfo.packageName));
                getAppInfoModelList.add(getAppInfoModel);
            }
        }

        return getAppInfoModelList;
    }


    }
