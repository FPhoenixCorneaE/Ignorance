package com.livelearn.ignorance.model.bean.article;

import java.util.List;

public class ArticleListModel {

    /**
     * error : false
     * results : [{"_id":"57c67ca8421aa9125d96f543","createdAt":"2016-08-31T14:43:52.969Z","desc":"【Android干货】Matrix详解，这应该是目前最详细的一篇讲解Matrix的中文文章了。","publishedAt":"2016-09-01T11:31:19.288Z","source":"web","type":"Android","url":"http://www.gcssloop.com/2015/02/Matrix_Method/","used":true,"who":"sloop"},{"_id":"57c67e1e421aa9125fa3edc1","createdAt":"2016-08-31T14:50:06.126Z","desc":"Android热修复实践应用--AndFix","publishedAt":"2016-09-01T11:31:19.288Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/c36c9e0ca3fe","used":true,"who":"郑铉"},{"_id":"57c75d23421aa91265f4a40e","createdAt":"2016-09-01T06:41:39.768Z","desc":"Google 放出的依赖注入框架，Tiger，速度是最大优势。","publishedAt":"2016-09-01T11:31:19.288Z","source":"chrome","type":"Android","url":"https://github.com/google/tiger","used":true,"who":"代码家"},{"_id":"57c75e58421aa9125fa3edc9","createdAt":"2016-09-01T06:46:48.127Z","desc":"利用 KeyStore 存储密码，加密 SharedPreference 的数据，保证安全性。","publishedAt":"2016-09-01T11:31:19.288Z","source":"chrome","type":"Android","url":"https://github.com/iamMehedi/Secured-Preference-Store","used":true,"who":"代码家"},{"_id":"57c520df421aa9125d96f534","createdAt":"2016-08-30T13:59:59.568Z","desc":"Android 权限判断工具，并且对小米手机做了特别判断","publishedAt":"2016-08-31T11:41:56.41Z","source":"web","type":"Android","url":"https://github.com/a1018875550/PermissionDispatcher","used":true,"who":"JokAr"},{"_id":"57c53f22421aa9125d96f536","createdAt":"2016-08-30T16:09:06.119Z","desc":"Android7.0新特性添加快速设定","publishedAt":"2016-08-31T11:41:56.41Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s?__biz=MzI1MzI2NDI3Mw==&mid=2247483724&idx=1&sn=ba9c94946c59b12c3dd42ed2040ea689&scene=0#wechat_redirect","used":true,"who":"FMVP"},{"_id":"57c626ff421aa9125fa3edbb","createdAt":"2016-08-31T08:38:23.67Z","desc":"类似 Facebook 闪烁式的 Load View，哇，好漂亮！","publishedAt":"2016-08-31T11:41:56.41Z","source":"chrome","type":"Android","url":"https://github.com/elye/loaderviewlibrary","used":true,"who":"代码家"},{"_id":"57c6275a421aa9125d96f53d","createdAt":"2016-08-31T08:39:54.607Z","desc":"渐进色的 Actionbar 效果，使用场景较少，可以在一些花哨的 App 设计上用得到。","publishedAt":"2016-08-31T11:41:56.41Z","source":"chrome","type":"Android","url":"https://github.com/flouthoc/Gradbar","used":true,"who":"代码家"},{"_id":"57c62807421aa9125fa3edbc","createdAt":"2016-08-31T08:42:47.498Z","desc":"让 TextView PlaceHolder （Hint）文本带有切换过度效果，在显示多个热门搜索的时候可以用得到。","publishedAt":"2016-08-31T11:41:56.41Z","source":"chrome","type":"Android","url":"https://github.com/cctanfujun/HintAnim-EditText","used":true,"who":"代码家"},{"_id":"57c00f6b421aa9126b1a15ba","createdAt":"2016-08-26T17:44:11.569Z","desc":"一个漂亮的 Android 画廊展示效果。","publishedAt":"2016-08-30T11:38:36.625Z","source":"web","type":"Android","url":"https://github.com/Jaouan/Carousel-Browsing-Example","used":true,"who":null}]
     */

    public boolean error;
    /**
     * _id : 57c67ca8421aa9125d96f543
     * createdAt : 2016-08-31T14:43:52.969Z
     * desc : 【Android干货】Matrix详解，这应该是目前最详细的一篇讲解Matrix的中文文章了。
     * publishedAt : 2016-09-01T11:31:19.288Z
     * source : web
     * type : Android
     * url : http://www.gcssloop.com/2015/02/Matrix_Method/
     * used : true
     * who : sloop
     */

    public List<ResultsEntity> results;

    public static class ResultsEntity {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
