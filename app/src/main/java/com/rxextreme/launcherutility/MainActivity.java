package com.rxextreme.launcherutility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rxextreme.rxlauncherutils.ApkInfoExtractor;
import com.rxextreme.rxlauncherutils.GetAppInfoModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvLaunchApps;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLaunchAppInfo();
    }

    private void initLaunchAppInfo() {
        ApkInfoExtractor apkInfoExtractor = new ApkInfoExtractor(this);
        List<GetAppInfoModel> getAppInfoModelList = apkInfoExtractor.getAllInstalledLauncherInfo();
        rvLaunchApps = findViewById(R.id.rv_apps);
        recyclerViewLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        rvLaunchApps.setLayoutManager(recyclerViewLayoutManager);
        adapter = new LauncherAdapter(MainActivity.this, getAppInfoModelList);
        rvLaunchApps.setAdapter(adapter);
    }
}