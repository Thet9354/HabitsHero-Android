package com.sp.madproposal.Model;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

import androidx.annotation.RequiresApi;

import java.util.List;

public class AppUsageTracker {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean hasUsageStatsPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static long getUsageTime(Context context, String packageName) {
        long totalTime = 0;

        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();

        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 1000 * 3600, currentTime);

        for (UsageStats usageStats : stats) {
            if (usageStats.getPackageName().equals(packageName)) {
                totalTime += usageStats.getTotalTimeInForeground();
            }
        }

        return totalTime;
    }

    public static String getAppName(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return pm.getApplicationLabel(ai).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return packageName;
        }
    }
}


