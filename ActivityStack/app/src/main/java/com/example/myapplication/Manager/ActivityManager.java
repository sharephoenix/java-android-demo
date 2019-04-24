package com.example.myapplication.Manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {

    private static ActivityManager manager;

    // 堆栈 集合
    public static List<List<BaseActivity>> stacks= new ArrayList<List<BaseActivity>>();

    //方法同步，调用效率低
    public static synchronized ActivityManager instance(){
        if(manager==null){
            manager = new ActivityManager();
        }
        return manager;
    }

    public void presentActivity() {
        stacks.add(createNavigator());
    }

    public void addActivity(BaseActivity activity){
        List<BaseActivity> navigator = getNavigator();
        if (navigator != null) {
            navigator.add(activity);
        } else {
            stacks.add(createNavigator());
            navigator = getNavigator();
            navigator.add(activity);
        }
    }

    public void removeActivity(BaseActivity activity){
        List<BaseActivity> navigator = getNavigator();
        navigator.remove(activity);
        if (navigator.size() == 0) {
            stacks.remove(navigator);
        }
    }

    // 清理当前堆栈
    public void finish(){
        List<BaseActivity> navigator = getNavigator();
        for (BaseActivity activity : navigator) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        stacks.remove(navigator);
    }

    // 获取 activity 的当前堆栈
    public List<BaseActivity> getNavigator(BaseActivity activity) {
        for (List<BaseActivity> navigator : stacks) {
            if (navigator.contains(activity)) {
                return navigator;
            }
        }
        return null;
    }


    // 创建新的堆栈
    private List<BaseActivity> createNavigator() {
        return new ArrayList<BaseActivity>();
    }

    // 获取当前堆栈
    public List<BaseActivity> getNavigator() {
        int count = stacks.size();
        if (count > 0) {
            return stacks.get(count -1);
        } else {
            return null;
        }
    }

}