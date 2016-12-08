package com.soft.baseproject.demo.app;

import common.base.BaseApplication;
import common.base.netclients.RetrofitClient;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 15:58
 * DESC: 继承BaseProject中的BaseApplication就可以使用一些通用功能了
 * 比如说，写喜好配置数据，详情参考BaseApplication源码
 */
public class DemoApplication extends BaseApplication{
    @Override
    public void onCreate() {
        //如果要使用BaseProject的网络请求，则先把服务端的网络地址host配置一下
        RetrofitClient.HOST_BASE_URL = "https://api.github.com/";

        super.onCreate();
    }
}
