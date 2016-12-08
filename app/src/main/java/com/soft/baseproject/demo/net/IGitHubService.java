package com.soft.baseproject.demo.net;

import com.soft.baseproject.demo.datas.Respo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 17:38
 * DESC: 访问github的网络接口API
 */
public interface IGitHubService{
    /**
     * 获取github上一个用户的所有项目
     * @param user
     * @return
     */
    @GET("users/{user}/repos")
    Call<List<Respo>> listRepos(@Path("user") String user);
}
