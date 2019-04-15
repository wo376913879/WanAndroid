package com.xiegao.wanandroid.api;


import com.xiegao.wanandroid.HttpUtils.bean.BaseData;
import com.xiegao.wanandroid.bean.BannerBean;
import com.xiegao.wanandroid.bean.HotBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2019/03/23
 *      desc    :
 * </pre>
 */
public interface WanAndroidApi {

    /**
     * 获取banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseData<List<BannerBean>>> getBanner();

    /**
     * 热搜
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseData<List<HotBean>>> getHotSearchData();

    /**
     * 热搜
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<String> getHotSearchStringData();


}
