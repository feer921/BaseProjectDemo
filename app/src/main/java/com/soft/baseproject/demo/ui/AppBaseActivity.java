package com.soft.baseproject.demo.ui;

import org.json.JSONObject;

import common.base.activitys.BaseNetCallActivity;

/**
 * User: fee(1176610771@qq.com)
 * Date: 2016-12-07
 * Time: 17:14
 * DESC: 最好项目本身再写一个基类Activity来继承BaseProject的BaseNetCallActivity
 * 以可以做很多通用的处理
 * 因为这里是以github的公开api来请求，可参考https://developer.github.com/v3
 * 而github的网络响应直接某个Java bean的Json对象,这里就直接让其转化成JsonObject来通用各种响应,而实际的企业开发中，一般不是这样返回，而是像这样：
 *  ｛
 *    "status":"1",
 *     "msg":"",
 *     "data":{
 *         xxxxxx,xxxx
 *     }
 *   ｝
 */
public abstract class AppBaseActivity extends BaseNetCallActivity<JSONObject>{
}
