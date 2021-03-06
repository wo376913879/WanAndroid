package com.xiegao.wanandroid.api;


import com.xiegao.wanandroid.HttpUtils.bean.BaseData;
import com.xiegao.wanandroid.bean.ArticleBean;
import com.xiegao.wanandroid.bean.BannerBean;
import com.xiegao.wanandroid.bean.HotBean;
import com.xiegao.wanandroid.bean.LonginBean;
import com.xiegao.wanandroid.bean.OfficalAccontsBean;
import com.xiegao.wanandroid.bean.ProjectBean;
import com.xiegao.wanandroid.bean.ProjectListBean;
import com.xiegao.wanandroid.bean.RegisterBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<BannerBean> getBanner();

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

    /**
     * 首页文章列表
     *
     * @return
     */
    @GET("article/list/{pageNum}/json")
    Observable<ArticleBean> getarticleStringData(@Path("pageNum") int pageNum);

    /**
     * 项目分类
     *
     * @return
     */
//    https://www.wanandroid.com/project/tree/json
    @GET("project/tree/json")
    Observable<ProjectBean> getProjectData();


//   2.2 知识体系下的文章
//    https://www.wanandroid.com/article/list/0/json?cid=60
//
//    方法：GET
//    参数：
//    cid 分类的id，上述二级目录的id
//    页码：拼接在链接上，从0开始。
    @GET("project/list/{pageNum}/json")
    Observable<ProjectListBean> getProjectListData(@Path("pageNum") int pageNum, @Query("cid") int cid);



//    2018-10-13 公众号Tab
//  增加微信公众号 Tab，
//
    @GET("/wxarticle/chapters/json")
    Observable<OfficalAccontsBean> getOfficalAccontsBeanData();

    /*
    查看微信公众号历史文章
   */
    @GET("wxarticle/list/{pageNum}/{cid}/json")
    Observable<ProjectListBean> getWechatChapterArticles(@Path("cid") int cid, @Path("pageNum") int pageNum);



    /**
     * 登录请求
     * @return
     */
    @POST("user/login")
//    Observable<String> getLogin(@Body HashMap<String, String> paramsMap);
    Observable<LonginBean> getLogin(@Query("username") String username, @Query("password") String password);
//    Observable<String> getLogin(@Body RequestBody requestBody);

//    https://www.wanandroid.com/user/register
//
//    方法：POST
//            参数
//    username,password,repassword
    @POST("user/register")
    Observable<RegisterBean> getRegister(@Query("username") String username, @Query("password") String password, @Query("repassword") String repassword);
}
