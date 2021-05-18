package com.rxextreme.launcherutility;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.rxextreme.rxlauncherutils.GetAppInfoModel;
import java.util.List;

public class LauncherAdapter extends RecyclerView.Adapter<LauncherAdapter.ViewHolder>{
        Context context;
        List<GetAppInfoModel> appInfoModelList;

    public LauncherAdapter(Context context, List<GetAppInfoModel> appInfoModelList){
            this.context = context;
            this.appInfoModelList = appInfoModelList;
        }
        public static class ViewHolder extends RecyclerView.ViewHolder{

            public CardView cardView;
            public ImageView ivAppIcon;
            public TextView tvAppName, tvAppVersion;
            public TextView tvPackageName;

            public ViewHolder (View view){

                super(view);

                cardView =  view.findViewById(R.id.card_view);
                ivAppIcon = view.findViewById(R.id.imageview);
                tvAppName = view.findViewById(R.id.Apk_Name);
                tvAppVersion =view.findViewById(R.id.Apk_Version_Name);
                tvPackageName = view.findViewById(R.id.Apk_Package_Name);
            }
        }

        @Override
        public LauncherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
         ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_layout,parent,false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position){

            viewHolder.tvAppName.setText(appInfoModelList.get(position).getAppName());
            viewHolder.tvPackageName.setText(appInfoModelList.get(position).getAppPackageName());
            viewHolder.tvAppVersion.setText("Version Name :"+ appInfoModelList.get(position).getAppVersionName()+"\n"+"Version Code :"+ appInfoModelList.get(position).getAppVersionCode() );
            viewHolder.ivAppIcon.setImageDrawable(appInfoModelList.get(position).getAppIcon());

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = context.getPackageManager().getLaunchIntentForPackage(appInfoModelList.get(position).getAppPackageName());
                    if(intent != null){
                        context.startActivity(intent);
                    }
                    else {

                        Toast.makeText(context, appInfoModelList.get(position).getAppName() + " Error, Please Try Again.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        @Override
        public int getItemCount(){

            return appInfoModelList.size();
        }


}
