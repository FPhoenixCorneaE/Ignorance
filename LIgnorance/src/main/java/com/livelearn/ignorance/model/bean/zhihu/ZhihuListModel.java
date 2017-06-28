package com.livelearn.ignorance.model.bean.zhihu;

import java.util.List;

public class ZhihuListModel {

    /**
     * date : 20160905
     * stories : [{"images":["http://pic3.zhimg.com/740708bb7f00f37022554549a5d98d06.jpg"],"type":0,"id":8761946,"ga_prefix":"090511","title":"每天都吃吃吃，但你知道你是怎么判断食物可以下咽的吗？"},{"images":["http://pic3.zhimg.com/62053946c00e5851d825578b6a771312.jpg"],"type":0,"id":8762376,"ga_prefix":"090510","title":"除了感慨「这人可真胖」，相扑还有这些看点"},{"title":"跑了一年超市，找出了一堆好吃又不容易长胖的零食","ga_prefix":"090509","images":["http://pic1.zhimg.com/185936d4d20876604b38fbf06ba71cf4.jpg"],"multipic":true,"type":0,"id":8761556},{"images":["http://pic2.zhimg.com/3843bc53237c6c377a3dbd7bba251f6d.jpg"],"type":0,"id":8762366,"ga_prefix":"090508","title":"虽然天天有人说，但二手车行业的「风口」可能还早"},{"images":["http://pic2.zhimg.com/cc6e59178d67aa2200d22370cb9e6469.jpg"],"type":0,"id":8762336,"ga_prefix":"090507","title":"大夏天的还要穿一身龙袍，皇上不怕起痱子吗？"},{"images":["http://pic4.zhimg.com/2d5ead23aaa2f1cbeb850d13b9f1536b.jpg"],"type":0,"id":8761598,"ga_prefix":"090507","title":"今后的谷歌旗下产品，再也不见 Nexus 的身影"},{"title":"看国产科幻电影，总是觉得非常\u2026\u2026尴尬","ga_prefix":"090507","images":["http://pic4.zhimg.com/3853f73993b17d409fe24e3856b33173.jpg"],"multipic":true,"type":0,"id":8761966},{"images":["http://pic4.zhimg.com/34a63c91ff9b196b0fe6e2e70cf50eb7.jpg"],"type":0,"id":8762261,"ga_prefix":"090507","title":"读读日报 24 小时热门 TOP 5 · 那些年语文课本里的美食"},{"images":["http://pic4.zhimg.com/f9dbf5358ac668ca8bf6f41f40b26bc3.jpg"],"type":0,"id":8762185,"ga_prefix":"090506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/800376357e9d65594d90887daf3a65e3.jpg","type":0,"id":8761556,"ga_prefix":"090509","title":"跑了一年超市，找出了一堆好吃又不容易长胖的零食"},{"image":"http://pic3.zhimg.com/0bdf50f519c1ecf3947dd95adcbbe8a6.jpg","type":0,"id":8761598,"ga_prefix":"090507","title":"今后的谷歌旗下产品，再也不见 Nexus 的身影"},{"image":"http://pic4.zhimg.com/aa03614ce0838f2d458e2a69f0a47c9f.jpg","type":0,"id":8761966,"ga_prefix":"090507","title":"看国产科幻电影，总是觉得非常\u2026\u2026尴尬"},{"image":"http://pic2.zhimg.com/a380aa37b44dd558e8a72dd444dfd2e5.jpg","type":0,"id":8762261,"ga_prefix":"090507","title":"读读日报 24 小时热门 TOP 5 · 那些年语文课本里的美食"},{"image":"http://pic4.zhimg.com/6d1c019b083d8c7fd74a54e99606ffdf.jpg","type":0,"id":8756104,"ga_prefix":"090417","title":"知乎好问题 · 家用路由器会遭受攻击吗？"}]
     */

    public String date;
    /**
     * images : ["http://pic3.zhimg.com/740708bb7f00f37022554549a5d98d06.jpg"]
     * type : 0
     * id : 8761946
     * ga_prefix : 090511
     * title : 每天都吃吃吃，但你知道你是怎么判断食物可以下咽的吗？
     */

    public List<StoriesEntity> stories;
    /**
     * image : http://pic4.zhimg.com/800376357e9d65594d90887daf3a65e3.jpg
     * type : 0
     * id : 8761556
     * ga_prefix : 090509
     * title : 跑了一年超市，找出了一堆好吃又不容易长胖的零食
     */

    public List<TopStoriesEntity> top_stories;

    public static class StoriesEntity {
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
        public List<String> images;
    }

    public static class TopStoriesEntity {
        public String image;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }
}
