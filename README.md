# LauncherUtility

Gradle dependency:

Add it in your root build.gradle at the end of repositories:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.rajextreme9476:LauncherUtility:1.0'
	}
  ```
  
  Usage :

Initialize RxOrientation in your Application's class onCreate

```
 ApkInfoExtractor apkInfoExtractor = ApkInfoExtractor.getInstance(getApplicationContext());
 List<GetAppInfoModel> getAppInfoModelList = apkInfoExtractor.getAllInstalledLauncherInfo();
 ```
 
 UI
 
 ![image](https://firebasestorage.googleapis.com/v0/b/rx0353.appspot.com/o/Screenshot_20210523-230007.jpg?alt=media&token=96290448-8070-4087-9d1f-0ba1d234317e)

 
 
 
