package com.soft.baseproject.demo.ui;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.soft.baseproject.demo.R;

import common.base.activitys.BaseSplashActiviity;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 16:23
 * DESC: 一个应用的闪屏页，只要继承BaseProject中的BaseSplashActivity，就可以实现闪屏页了
 */
public class SplashActivity extends BaseSplashActiviity{
    @Override
    protected void jumpToTargetActivity() {
        //跳转Activity的方法，基类中有
        jumpToActivity(MainActivity.class);
        //闪屏页过渡完了，结束自己
        finishSelf(true);
    }

    /**
     * 倒计时
     * 注：该倒计时的初始值为闪屏动画持续时间加上1秒
     * eg.: 闪屏时间为 5000毫秒==5秒，则倒计时为6秒
     *
     * @param decreaseSencond 当前倒计数(递减秒数)，从闪屏动画时间的秒数+1 ~ 0
     */
    @Override
    protected void countDownTimes(int decreaseSencond) {

    }

    /**
     * 获取当前Activity需要填充、展示的内容视图，如果各子类提供，则由基类来填充，如果不提供，各子类也可自行处理
     *
     * @return 当前Activity需要展示的内容视图资源ID
     */
    @Override
    protected int getProvideContentViewResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        //也可以不赋值基类中的一些变量的值
        toAnimView = findAviewById(R.id.iv_splash_pic);
        tvBtnJumpOverSplash = findAviewById(R.id.tv_jump_over_splash);
        //super.initViews()需要最后调用
        super.initViews();
    }

    @Override
    protected Animation getAnimation4BackgroudImsg() {
        return AnimationUtils.loadAnimation(mContext, R.anim.splash_anim);
    }
}
