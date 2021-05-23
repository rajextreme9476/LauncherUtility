package com.rxextreme.launcherutility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rxextreme.rxlauncherutils.ApkInfoExtractor;
import com.rxextreme.rxlauncherutils.GetAppInfoModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLaunchAppInfo();
    }

    private void initLaunchAppInfo() {
        ApkInfoExtractor apkInfoExtractor = ApkInfoExtractor.getInstance(getApplicationContext());
        List<GetAppInfoModel> getAppInfoModelList = apkInfoExtractor.getAllInstalledLauncherInfo();
        RecyclerView rvLaunchApps = findViewById(R.id.rv_apps);
        rvLaunchApps.setLayoutManager(new LinearLayoutManager(this));
        LauncherAdapter adapter = new LauncherAdapter(MainActivity.this, getAppInfoModelList);
        rvLaunchApps.setAdapter(adapter);
    }
}