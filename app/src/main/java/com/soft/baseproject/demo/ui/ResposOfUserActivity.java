package com.soft.baseproject.demo.ui;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.soft.baseproject.demo.R;
import com.soft.baseproject.demo.datas.Respo;
import com.soft.baseproject.demo.net.GitHubApi;

import java.util.List;

import common.base.activitys.BaseNetCallActivity;
import common.base.netAbout.BaseServerResult;
import common.base.netAbout.NetDataAndErrorListener;
import common.base.utils.CommonLog;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 18:47
 * DESC:
 */
public class ResposOfUserActivity extends BaseNetCallActivity<BaseServerResult>{
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
        Log.e("info", TAG + "--> initData() CommonLog.ISDEBUG == " + CommonLog.ISDEBUG);
        //开启这个可以查看网络请求返回时的Log信息，以及生命周期的信息
        LIFE_CIRCLE_DEBUG = true;
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
    protected void dealWithResponse(int requestDataType, BaseServerResult result) {

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
     * 此处来介绍如果本界面所要求的网络请求响应类型T被指定了BaseServerResult
     * 后，如果有其他的网络请求接口响应时并不能序列化成BaseServerResult时，则使用指定的响应类型的监听者
     */
    NetDataAndErrorListener<List<Respo>> listener4DiffTypeResponse;
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
                if (listener4DiffTypeResponse == null) {
                    listener4DiffTypeResponse = createETypeListener();
                }
                GitHubApi.getAllRespoOfUser("feer921", listener4DiffTypeResponse);
                break;
        }
    }

    /**
     * 非本类T的网络响应类型在此处理
     * @param requestDataType 当前网络请求类型
     * @param responseResut 网络请求响应结果 这里为Object对象类型来通用，子类如果处理此回调时，自己强转成预期对象类型
     */
    @Override
    protected void dealWithETypeResponse(int requestDataType, Object responseResut) {
        switch (requestDataType) {//这样来区分当前响应的是哪个网络请求
            case GitHubApi.TYPE_GET_RESPO_OF_USER:
                //之前有Loading对话框，网络请求完成后，得取消loading...
                //uihintAgent见基类BaseActivity
                List<Respo> respos = (List<Respo>) responseResut;
                uiHintAgent.loadDialogDismiss();
                tvRespResult.setText(responseResut == null ? "没有响应数据" : responseResut.toString());
                break;
        }
    }
}
