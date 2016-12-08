package com.soft.baseproject.demo.net;

import com.soft.baseproject.demo.datas.Respo;

import java.util.List;

import common.base.netAbout.BaseApi;
import common.base.netAbout.NetDataAndErrorListener;
import retrofit2.Call;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 17:39
 * DESC:
 * 要简单点可以直接继承BaseProject的BaseApi，这样可以方便的调用Retrofit去请求了
 * 详情可参考BaseApi
 */
public class GitHubApi extends BaseApi{
    //这里需要定义全局的网络请求类型，用来当界面上网络请求回来时，是哪个网络请求回来了
    //在BaseApi基类中已区分出了一般APP中都有的几大类的网络请求 分类，如：关于用户的网络请求、关于账户的网络请求
    //也可以自己重新定义，只要全局惟一就行
    public static final int TYPE_GET_RESPO_OF_USER = API_CATEGORY_ABOUT_USER * 100;
    private static IGitHubService gitHubService;

    /**
     * 去github上获取某个用户(用户名)的所有项目仓库
     * @param userName 用户名
     * @param callback 网络回调
     */
    public static void getAllRespoOfUser(String userName, NetDataAndErrorListener<List<Respo>> callback) {
        callback.requestType = TYPE_GET_RESPO_OF_USER;
        Call<List<Respo>> call = getGitHubService().listRepos(userName);
        //则可以发起请求了
        doCall(call, callback, false);//该方法为BaseApi中的，所以为什么要继承一下BaseAPi就这么方便的完成了一个请求接口
    }

    private static IGitHubService getGitHubService() {
        if (gitHubService == null) {
            gitHubService = getApiService(IGitHubService.class);
        }
        return gitHubService;
    }
}
