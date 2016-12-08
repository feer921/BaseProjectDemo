package com.soft.baseproject.demo.ui;

import android.view.View;
import android.widget.TextView;

import com.soft.baseproject.demo.R;
import com.soft.baseproject.demo.datas.Respo;
import com.soft.baseproject.demo.net.GitHubApi;

import java.util.List;

import common.base.activitys.BaseNetCallActivity;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 18:47
 * DESC:
 */
public class ResposOfUserActivity extends BaseNetCallActivity<List<Respo>>{
    TextView tvRespResult;

    /**
     * 获取当前Activity需要填充、展示的内容视图，如果各子类提供，则由基类来填充，如果不提供，各子类也可自行处理
     *
     * @return 当前Activity需要展示的内容视图资源ID
     */
    @Override
    protected int getProvideContentViewResID() {
        return R.layout.activity_user_respo;
    }

    /**
     * 如果子类在getProvideContentViewResID()方法提供了视图资源，那么子类的初始化视图可在此方法中完成
     */
    @Override
    protected void initViews() {
                                         //因为基类BaseActivity已经实现了View.OnClickListener
        findViewById(R.id.tv_to_request).setOnClickListener(this);
        tvRespResult = findAviewById(R.id.tv_response_result);
    }

    @Override
    protected void initData() {
        super.initData();
        //这一步需要调用,表明本界面会有网络请求并且需要监听网络响应
        initNetDataListener();
    }

    /**
     * 重写该方法，这里就是网络请求成功的回调
     * 注：该方法已经是在主线程中了
     * @param requestDataType 这个请求类型，即为区分当前的响应是哪一个网络请求
     * @param result
     */
    @Override
    protected void dealWithResponse(int requestDataType, List<Respo> result) {
        switch (requestDataType) {//这样来区分当前响应的是哪个网络请求
            case GitHubApi.TYPE_GET_RESPO_OF_USER:
                //之前有Loading对象框，网络请求完成后，得取消loading...
                //uihintAgent见基类BaseActivity
                uiHintAgent.loadDialogDismiss();
                tvRespResult.setText(result == null ? "没有响应数据" : result.toString());
                break;
        }
        super.dealWithResponse(requestDataType, result);
    }

    /**
     * 重写该方法,这里就是网络请求失败（可能没有网络或者其他异常）时的回调
     * @param curRequestDataType
     * @param errorInfo
     */
    @Override
    protected void dealWithErrorResponse(int curRequestDataType, String errorInfo) {
        super.dealWithErrorResponse(curRequestDataType, errorInfo);
        switch (curRequestDataType) {//同理请求失败时，也要区分是哪个网络请求失败了
            case GitHubApi.TYPE_GET_RESPO_OF_USER:
                uiHintAgent.loadDialogDismiss();
                tvRespResult.setText("请求失败:\n" + errorInfo);
                break;
        }
    }

    /**
     * 点击事件重写基类的onClick()方法就行了
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_to_request:
                //没错，BaseProject框架就是考虑到了APP上的各种UI、使用场景，loading也不例外
                //详见BaseActivity
                showCommonLoading("正在请求,请稍候.....");
                //这里就是网络请求了 其中的参数netDataAndErrorListener是BaseNetCallActivity里面的，为什么说本框架能让开发
                //变简单点，就是各基类基本上考虑了APP的各种通用逻辑
                GitHubApi.getAllRespoOfUser("feer921", netDataAndErrorListener);
                break;
        }
    }
}
