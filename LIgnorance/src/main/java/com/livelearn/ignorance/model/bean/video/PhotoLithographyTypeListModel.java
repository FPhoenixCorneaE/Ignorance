package com.livelearn.ignorance.model.bean.video;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyTypeListModel implements Serializable {

    /**
     * msg : 成功
     * ret : {"adv":{"imgURL":"","dataId":"","htmlURL":"","title":""},"pnum":1,"totalRecords":30,"bannerList":[],"records":30,"list":[{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"cce0cf5b8ecf42b7ab249e650107ea8f","description":"@TV娱乐前线：6月21日，上海电影集团项目发布会在上海举行，张纪中出席活动\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cce0cf5b8ecf42b7ab249e650107ea8f","shareURL":"http://h5.svipmovie.com/zxgl/cce0cf5b8ecf42b7ab249e650107ea8f.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0622/cce0cf5b8ecf42b7ab249e650107ea8f_224495_136.jpg","title":"张纪中发布会上谈影视","roomId":""},{"airTime":0,"duration":"00:01:28","loadtype":"video","score":0,"angleIcon":"","dataId":"cbee4f7c76a64b15b5e7f1841c6d6724","description":"@TV娱乐前线：华文映像\u201c初次见面\u201d惊喜连连 ，刘仪伟带来最新项目\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cbee4f7c76a64b15b5e7f1841c6d6724","shareURL":"http://h5.svipmovie.com/zxgl/cbee4f7c76a64b15b5e7f1841c6d6724.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0622/cbee4f7c76a64b15b5e7f1841c6d6724_224479_136.jpg","title":"曝推介酒会刘仪伟出席","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"a5e1e538308a43b998226c70a37165ed","description":"@TV娱乐前线：电影《绝世高手》在上海举办了一场\u201c世界上最辣的发布会\u201d\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=a5e1e538308a43b998226c70a37165ed","shareURL":"http://h5.svipmovie.com/zxgl/a5e1e538308a43b998226c70a37165ed.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0622/a5e1e538308a43b998226c70a37165ed_224471_136.jpg","title":"蔡国庆新电影首演反派","roomId":""},{"airTime":0,"duration":"00:02:04","loadtype":"video","score":0,"angleIcon":"","dataId":"bafdfc83247245fd8bf5948e41f9a712","description":"@TV娱乐前线：音乐颁奖现场群星闪耀，最佳男歌手薛之谦继续逗比\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=bafdfc83247245fd8bf5948e41f9a712","shareURL":"http://h5.svipmovie.com/zxgl/bafdfc83247245fd8bf5948e41f9a712.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0622/bafdfc83247245fd8bf5948e41f9a712_224447_136.jpg","title":"男歌手薛之谦继续逗比","roomId":""},{"airTime":0,"duration":"00:02:01","loadtype":"video","score":0,"angleIcon":"","dataId":"8a7389b6c75e410eb81c174f8ef7bab0","description":"@TV娱乐前线：俞飞鸿优雅现身分享小幸福感，霍思燕满足现在小生活\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=8a7389b6c75e410eb81c174f8ef7bab0","shareURL":"http://h5.svipmovie.com/zxgl/8a7389b6c75e410eb81c174f8ef7bab0.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0622/8a7389b6c75e410eb81c174f8ef7bab0_224407_136.jpg","title":"俞飞鸿现身分享幸福感","roomId":""},{"airTime":0,"duration":"00:01:12","loadtype":"video","score":0,"angleIcon":"","dataId":"145a3e75423142f69412dc32625285ba","description":"@TV娱乐前线：祥子新戏过了把唱戏瘾，颜值担当蔡俊涛勇挑科幻惊悚片\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=145a3e75423142f69412dc32625285ba","shareURL":"http://h5.svipmovie.com/zxgl/145a3e75423142f69412dc32625285ba.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/145a3e75423142f69412dc32625285ba_224375_136.jpg","title":"蔡俊涛挑战科幻惊悚片","roomId":""},{"airTime":0,"duration":"00:01:07","loadtype":"video","score":0,"angleIcon":"","dataId":"4c0ffffa49b34c299dc0bcf83f22ad99","description":"@TV娱乐前线：百变天后蔡依林在咪咕平台正式首发其最新电子舞曲《WeAreOne》\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=4c0ffffa49b34c299dc0bcf83f22ad99","shareURL":"http://h5.svipmovie.com/zxgl/4c0ffffa49b34c299dc0bcf83f22ad99.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/4c0ffffa49b34c299dc0bcf83f22ad99_224295_136.jpg","title":"蔡依林新专尝试新风格","roomId":""},{"airTime":0,"duration":"00:01:20","loadtype":"video","score":0,"angleIcon":"","dataId":"d9f44abbb09a46369a3d57aa4fe52689","description":"@TV娱乐前线：《就想和陌生人说话》强势开机，余少群带伤上阵\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=d9f44abbb09a46369a3d57aa4fe52689","shareURL":"http://h5.svipmovie.com/zxgl/d9f44abbb09a46369a3d57aa4fe52689.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/d9f44abbb09a46369a3d57aa4fe52689_224279_136.jpg","title":"曝余少群出席新戏开机","roomId":""},{"airTime":0,"duration":"00:01:45","loadtype":"video","score":0,"angleIcon":"","dataId":"6aba4b20855446b39d434fa1d2b02496","description":"@TV娱乐前线：卢海鹏童心未泯秀歌舞，庄思明打鼓俨如女汉子\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=6aba4b20855446b39d434fa1d2b02496","shareURL":"http://h5.svipmovie.com/zxgl/6aba4b20855446b39d434fa1d2b02496.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/6aba4b20855446b39d434fa1d2b02496_224223_136.jpg","title":"卢海鹏童心未泯秀歌舞","roomId":""},{"airTime":0,"duration":"00:02:00","loadtype":"video","score":0,"angleIcon":"","dataId":"cb930d0fe892470e979180d0453c5147","description":"@TV娱乐前线：吴镇宇与儿子到孟加拉探访，路途遥远惊呆费曼\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cb930d0fe892470e979180d0453c5147","shareURL":"http://h5.svipmovie.com/zxgl/cb930d0fe892470e979180d0453c5147.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/cb930d0fe892470e979180d0453c5147_224215_136.jpg","title":"吴镇宇带儿子到孟加拉","roomId":""},{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"739e921ccaca432eb32824ae496663b4","description":"@TV娱乐前线：陈慧琳杨千嬅合体超吸睛，大谈带子经尽显满满母爱\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=739e921ccaca432eb32824ae496663b4","shareURL":"http://h5.svipmovie.com/zxgl/739e921ccaca432eb32824ae496663b4.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/739e921ccaca432eb32824ae496663b4_224207_136.jpg","title":"陈慧琳杨千嬅合体吸睛","roomId":""},{"airTime":0,"duration":"00:02:27","loadtype":"video","score":0,"angleIcon":"","dataId":"398256f9e91c4113bcbe0cbe6da48383","description":"@TV娱乐前线：Ivy获任2017CHIMFF宣传大使，自称比得奖还开心\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=398256f9e91c4113bcbe0cbe6da48383","shareURL":"http://h5.svipmovie.com/zxgl/398256f9e91c4113bcbe0cbe6da48383.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/398256f9e91c4113bcbe0cbe6da48383_224191_136.jpg","title":"Ivy获任2017CHIMFF大使","roomId":""},{"airTime":0,"duration":"00:01:46","loadtype":"video","score":0,"angleIcon":"","dataId":"081576f2c8a746b4adb85a097c4f3104","description":"@TV娱乐前线：薛之谦作为首位杜莎夫人蜡像馆音乐区域入驻的艺人亮相上海\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=081576f2c8a746b4adb85a097c4f3104","shareURL":"http://h5.svipmovie.com/zxgl/081576f2c8a746b4adb85a097c4f3104.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/081576f2c8a746b4adb85a097c4f3104_224183_136.jpg","title":"薛之谦赞蜡像造型逼真","roomId":""},{"airTime":0,"duration":"00:01:21","loadtype":"video","score":0,"angleIcon":"","dataId":"f0c33c39c5294956af3f008c63f74f6e","description":"@TV娱乐前线：《我不做大哥好多年》定档，郭采洁追忆猪哥亮\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=f0c33c39c5294956af3f008c63f74f6e","shareURL":"http://h5.svipmovie.com/zxgl/f0c33c39c5294956af3f008c63f74f6e.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/f0c33c39c5294956af3f008c63f74f6e_224175_136.jpg","title":"我不做大哥好多年定档","roomId":""},{"airTime":0,"duration":"00:01:20","loadtype":"video","score":0,"angleIcon":"","dataId":"a50870a66dfa470690039cdba1c4ef3f","description":"@TV娱乐前线：VIXX成员N返韩，机场大秀手指爱心展暖男魅力\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=a50870a66dfa470690039cdba1c4ef3f","shareURL":"http://h5.svipmovie.com/zxgl/a50870a66dfa470690039cdba1c4ef3f.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/a50870a66dfa470690039cdba1c4ef3f_224167_136.jpg","title":"N休闲装返韩亮相机场","roomId":""},{"airTime":0,"duration":"00:01:01","loadtype":"video","score":0,"angleIcon":"","dataId":"be7fdfde2757475ebd002ce4df1120bc","description":"@TV娱乐前线：邓紫棋直播消音疑云？粉丝疑\u201c唱太好被忌妒\u201d\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=be7fdfde2757475ebd002ce4df1120bc","shareURL":"http://h5.svipmovie.com/zxgl/be7fdfde2757475ebd002ce4df1120bc.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/be7fdfde2757475ebd002ce4df1120bc_224159_136.jpg","title":"邓紫棋直播消音粉丝疑","roomId":""},{"airTime":0,"duration":"00:01:24","loadtype":"video","score":0,"angleIcon":"","dataId":"4d1d6d953e9c4251a3b6e78c7a938475","description":"@TV娱乐前线：郭采洁跪地痛哭，同框杨祐宁吊唁猪哥亮\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=4d1d6d953e9c4251a3b6e78c7a938475","shareURL":"http://h5.svipmovie.com/zxgl/4d1d6d953e9c4251a3b6e78c7a938475.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/4d1d6d953e9c4251a3b6e78c7a938475_224151_136.jpg","title":"郭采洁前来吊唁猪哥亮","roomId":""},{"airTime":0,"duration":"00:01:25","loadtype":"video","score":0,"angleIcon":"","dataId":"32024ca172744c68bcd7a435ca39557d","description":"@TV娱乐前线：影响力盛典群星闪耀，马可、汪东城人气爆棚\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=32024ca172744c68bcd7a435ca39557d","shareURL":"http://h5.svipmovie.com/zxgl/32024ca172744c68bcd7a435ca39557d.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/32024ca172744c68bcd7a435ca39557d_224143_136.jpg","title":"影响力盛典马可人气高","roomId":""},{"airTime":0,"duration":"00:02:29","loadtype":"video","score":0,"angleIcon":"","dataId":"3b38c787494c4c5a86f70b5e40322176","description":"@TV娱乐前线：纪录片《山海搜神》打造特色IP，震撼视觉场面冲击\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=3b38c787494c4c5a86f70b5e40322176","shareURL":"http://h5.svipmovie.com/zxgl/3b38c787494c4c5a86f70b5e40322176.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/3b38c787494c4c5a86f70b5e40322176_224135_136.jpg","title":"《山海搜神》创特色IP","roomId":""},{"airTime":0,"duration":"00:01:48","loadtype":"video","score":0,"angleIcon":"","dataId":"106781a81ae348278ea1498856fe777a","description":"@TV娱乐前线：中国远征军威猛战，新动画《大象林旺之一炮成名》\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=106781a81ae348278ea1498856fe777a","shareURL":"http://h5.svipmovie.com/zxgl/106781a81ae348278ea1498856fe777a.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/106781a81ae348278ea1498856fe777a_224119_136.jpg","title":"远征军动画电影发布会","roomId":""},{"airTime":0,"duration":"00:01:57","loadtype":"video","score":0,"angleIcon":"","dataId":"60d8fac025114bb0a7e085c7eff1393e","description":"@TV娱乐前线：文艺电影导演王毓雅新作，《浅爱》将文艺范进行到底\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=60d8fac025114bb0a7e085c7eff1393e","shareURL":"http://h5.svipmovie.com/zxgl/60d8fac025114bb0a7e085c7eff1393e.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/60d8fac025114bb0a7e085c7eff1393e_224111_136.jpg","title":"王毓雅新电影《浅爱》","roomId":""},{"airTime":0,"duration":"00:01:19","loadtype":"video","score":0,"angleIcon":"","dataId":"df42fe61bd9b485f8f7b196e0d918db5","description":"@TV娱乐前线：辣妈韩彩英机场时尚少女感十足，美腿抢镜\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=df42fe61bd9b485f8f7b196e0d918db5","shareURL":"http://h5.svipmovie.com/zxgl/df42fe61bd9b485f8f7b196e0d918db5.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/df42fe61bd9b485f8f7b196e0d918db5_224103_136.jpg","title":"辣妈韩彩英少女感十足","roomId":""},{"airTime":0,"duration":"00:01:16","loadtype":"video","score":0,"angleIcon":"","dataId":"75670140802c468aa95c815a8437fc47","description":"@TV娱乐前线：真功夫演绎年轻个性新态度，袁成杰俞灏明尽秀个性光芒\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=75670140802c468aa95c815a8437fc47","shareURL":"http://h5.svipmovie.com/zxgl/75670140802c468aa95c815a8437fc47.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/75670140802c468aa95c815a8437fc47_224095_136.jpg","title":"曝MyLogo人物年度大赏","roomId":""},{"airTime":0,"duration":"00:02:23","loadtype":"video","score":0,"angleIcon":"","dataId":"3217406ed0dc40568f0302fa711a13c4","description":"@TV娱乐前线：张晋新戏饰演金发警察，惊险打斗刷新警匪片新高度\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=3217406ed0dc40568f0302fa711a13c4","shareURL":"http://h5.svipmovie.com/zxgl/3217406ed0dc40568f0302fa711a13c4.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/3217406ed0dc40568f0302fa711a13c4_224087_136.jpg","title":"张晋新作刷警匪片新标","roomId":""},{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"c7290c09fd9a44dd97a3974b36a0b2f1","description":"@TV娱乐前线：天王猪哥亮告别秀，张菲郭采洁杨祐宁送最后一程\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=c7290c09fd9a44dd97a3974b36a0b2f1","shareURL":"http://h5.svipmovie.com/zxgl/c7290c09fd9a44dd97a3974b36a0b2f1.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/c7290c09fd9a44dd97a3974b36a0b2f1_224079_136.jpg","title":"猪哥亮告别好友送祝福","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"7f82b459b22142808acb4abda92b65a9","description":"@TV娱乐前线：柳俊烈和宋康昊合作感荣幸《出租车司机》重现韩历史悲剧\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7f82b459b22142808acb4abda92b65a9","shareURL":"http://h5.svipmovie.com/zxgl/7f82b459b22142808acb4abda92b65a9.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/7f82b459b22142808acb4abda92b65a9_224071_136.jpg","title":"柳俊烈和宋康昊首合作","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"52fad095d21e4a029e4bfcf5c557ab38","description":"@TV娱乐前线：成家班举行40周年纪念活动，成龙：所有荣誉都属于成家班\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=52fad095d21e4a029e4bfcf5c557ab38","shareURL":"http://h5.svipmovie.com/zxgl/52fad095d21e4a029e4bfcf5c557ab38.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/52fad095d21e4a029e4bfcf5c557ab38_224039_136.jpg","title":"成家班举行40周年庆典","roomId":""},{"airTime":0,"duration":"00:01:01","loadtype":"video","score":0,"angleIcon":"","dataId":"80c06f6267d441a08125b2cb53895e54","description":"@TV娱乐前线：王力宏男神成奶爸无怨尤，甘心跪地当马被骑\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=80c06f6267d441a08125b2cb53895e54","shareURL":"http://h5.svipmovie.com/zxgl/80c06f6267d441a08125b2cb53895e54.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/80c06f6267d441a08125b2cb53895e54_224031_136.jpg","title":"王力宏男神一心成奶爸","roomId":""},{"airTime":0,"duration":"00:01:53","loadtype":"video","score":0,"angleIcon":"","dataId":"fb979690059e4c6ca07b66762fc3b7cd","description":"@TV娱乐前线：演员尹素贞告别仪式，众亲友致意场面动容\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=fb979690059e4c6ca07b66762fc3b7cd","shareURL":"http://h5.svipmovie.com/zxgl/fb979690059e4c6ca07b66762fc3b7cd.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/fb979690059e4c6ca07b66762fc3b7cd_224023_136.jpg","title":"尹素贞离世众亲友致意","roomId":""},{"airTime":0,"duration":"00:02:47","loadtype":"video","score":0,"angleIcon":"","dataId":"74c1dadc29e445c58c4fc6a76334f339","description":"@TV娱乐前线：电影《建军大业》重返上海，众主演谈拍戏感受\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=74c1dadc29e445c58c4fc6a76334f339","shareURL":"http://h5.svipmovie.com/zxgl/74c1dadc29e445c58c4fc6a76334f339.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0620/74c1dadc29e445c58c4fc6a76334f339_223951_136.jpg","title":"《建军大业》重返上海","roomId":""}],"totalPnum":640}
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

    public static PhotoLithographyTypeListModel objectFromData(String str) {

        return new Gson().fromJson(str, PhotoLithographyTypeListModel.class);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PhotoLithographyTypeListModel{" +
                "msg='" + msg + '\'' +
                ", ret=" + ret +
                ", code='" + code + '\'' +
                '}';
    }

    public static class RetBean implements Serializable {
        /**
         * adv : {"imgURL":"","dataId":"","htmlURL":"","title":""}
         * pnum : 1
         * totalRecords : 30
         * bannerList : []
         * records : 30
         * list : [{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"cce0cf5b8ecf42b7ab249e650107ea8f","description":"@TV娱乐前线：6月21日，上海电影集团项目发布会在上海举行，张纪中出席活动\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cce0cf5b8ecf42b7ab249e650107ea8f","shareURL":"http://h5.svipmovie.com/zxgl/cce0cf5b8ecf42b7ab249e650107ea8f.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0622/cce0cf5b8ecf42b7ab249e650107ea8f_224495_136.jpg","title":"张纪中发布会上谈影视","roomId":""},{"airTime":0,"duration":"00:01:28","loadtype":"video","score":0,"angleIcon":"","dataId":"cbee4f7c76a64b15b5e7f1841c6d6724","description":"@TV娱乐前线：华文映像\u201c初次见面\u201d惊喜连连 ，刘仪伟带来最新项目\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cbee4f7c76a64b15b5e7f1841c6d6724","shareURL":"http://h5.svipmovie.com/zxgl/cbee4f7c76a64b15b5e7f1841c6d6724.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0622/cbee4f7c76a64b15b5e7f1841c6d6724_224479_136.jpg","title":"曝推介酒会刘仪伟出席","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"a5e1e538308a43b998226c70a37165ed","description":"@TV娱乐前线：电影《绝世高手》在上海举办了一场\u201c世界上最辣的发布会\u201d\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=a5e1e538308a43b998226c70a37165ed","shareURL":"http://h5.svipmovie.com/zxgl/a5e1e538308a43b998226c70a37165ed.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0622/a5e1e538308a43b998226c70a37165ed_224471_136.jpg","title":"蔡国庆新电影首演反派","roomId":""},{"airTime":0,"duration":"00:02:04","loadtype":"video","score":0,"angleIcon":"","dataId":"bafdfc83247245fd8bf5948e41f9a712","description":"@TV娱乐前线：音乐颁奖现场群星闪耀，最佳男歌手薛之谦继续逗比\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=bafdfc83247245fd8bf5948e41f9a712","shareURL":"http://h5.svipmovie.com/zxgl/bafdfc83247245fd8bf5948e41f9a712.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0622/bafdfc83247245fd8bf5948e41f9a712_224447_136.jpg","title":"男歌手薛之谦继续逗比","roomId":""},{"airTime":0,"duration":"00:02:01","loadtype":"video","score":0,"angleIcon":"","dataId":"8a7389b6c75e410eb81c174f8ef7bab0","description":"@TV娱乐前线：俞飞鸿优雅现身分享小幸福感，霍思燕满足现在小生活\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=8a7389b6c75e410eb81c174f8ef7bab0","shareURL":"http://h5.svipmovie.com/zxgl/8a7389b6c75e410eb81c174f8ef7bab0.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0622/8a7389b6c75e410eb81c174f8ef7bab0_224407_136.jpg","title":"俞飞鸿现身分享幸福感","roomId":""},{"airTime":0,"duration":"00:01:12","loadtype":"video","score":0,"angleIcon":"","dataId":"145a3e75423142f69412dc32625285ba","description":"@TV娱乐前线：祥子新戏过了把唱戏瘾，颜值担当蔡俊涛勇挑科幻惊悚片\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=145a3e75423142f69412dc32625285ba","shareURL":"http://h5.svipmovie.com/zxgl/145a3e75423142f69412dc32625285ba.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/145a3e75423142f69412dc32625285ba_224375_136.jpg","title":"蔡俊涛挑战科幻惊悚片","roomId":""},{"airTime":0,"duration":"00:01:07","loadtype":"video","score":0,"angleIcon":"","dataId":"4c0ffffa49b34c299dc0bcf83f22ad99","description":"@TV娱乐前线：百变天后蔡依林在咪咕平台正式首发其最新电子舞曲《WeAreOne》\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=4c0ffffa49b34c299dc0bcf83f22ad99","shareURL":"http://h5.svipmovie.com/zxgl/4c0ffffa49b34c299dc0bcf83f22ad99.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/4c0ffffa49b34c299dc0bcf83f22ad99_224295_136.jpg","title":"蔡依林新专尝试新风格","roomId":""},{"airTime":0,"duration":"00:01:20","loadtype":"video","score":0,"angleIcon":"","dataId":"d9f44abbb09a46369a3d57aa4fe52689","description":"@TV娱乐前线：《就想和陌生人说话》强势开机，余少群带伤上阵\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=d9f44abbb09a46369a3d57aa4fe52689","shareURL":"http://h5.svipmovie.com/zxgl/d9f44abbb09a46369a3d57aa4fe52689.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/d9f44abbb09a46369a3d57aa4fe52689_224279_136.jpg","title":"曝余少群出席新戏开机","roomId":""},{"airTime":0,"duration":"00:01:45","loadtype":"video","score":0,"angleIcon":"","dataId":"6aba4b20855446b39d434fa1d2b02496","description":"@TV娱乐前线：卢海鹏童心未泯秀歌舞，庄思明打鼓俨如女汉子\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=6aba4b20855446b39d434fa1d2b02496","shareURL":"http://h5.svipmovie.com/zxgl/6aba4b20855446b39d434fa1d2b02496.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/6aba4b20855446b39d434fa1d2b02496_224223_136.jpg","title":"卢海鹏童心未泯秀歌舞","roomId":""},{"airTime":0,"duration":"00:02:00","loadtype":"video","score":0,"angleIcon":"","dataId":"cb930d0fe892470e979180d0453c5147","description":"@TV娱乐前线：吴镇宇与儿子到孟加拉探访，路途遥远惊呆费曼\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cb930d0fe892470e979180d0453c5147","shareURL":"http://h5.svipmovie.com/zxgl/cb930d0fe892470e979180d0453c5147.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/cb930d0fe892470e979180d0453c5147_224215_136.jpg","title":"吴镇宇带儿子到孟加拉","roomId":""},{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"739e921ccaca432eb32824ae496663b4","description":"@TV娱乐前线：陈慧琳杨千嬅合体超吸睛，大谈带子经尽显满满母爱\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=739e921ccaca432eb32824ae496663b4","shareURL":"http://h5.svipmovie.com/zxgl/739e921ccaca432eb32824ae496663b4.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/739e921ccaca432eb32824ae496663b4_224207_136.jpg","title":"陈慧琳杨千嬅合体吸睛","roomId":""},{"airTime":0,"duration":"00:02:27","loadtype":"video","score":0,"angleIcon":"","dataId":"398256f9e91c4113bcbe0cbe6da48383","description":"@TV娱乐前线：Ivy获任2017CHIMFF宣传大使，自称比得奖还开心\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=398256f9e91c4113bcbe0cbe6da48383","shareURL":"http://h5.svipmovie.com/zxgl/398256f9e91c4113bcbe0cbe6da48383.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/398256f9e91c4113bcbe0cbe6da48383_224191_136.jpg","title":"Ivy获任2017CHIMFF大使","roomId":""},{"airTime":0,"duration":"00:01:46","loadtype":"video","score":0,"angleIcon":"","dataId":"081576f2c8a746b4adb85a097c4f3104","description":"@TV娱乐前线：薛之谦作为首位杜莎夫人蜡像馆音乐区域入驻的艺人亮相上海\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=081576f2c8a746b4adb85a097c4f3104","shareURL":"http://h5.svipmovie.com/zxgl/081576f2c8a746b4adb85a097c4f3104.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/081576f2c8a746b4adb85a097c4f3104_224183_136.jpg","title":"薛之谦赞蜡像造型逼真","roomId":""},{"airTime":0,"duration":"00:01:21","loadtype":"video","score":0,"angleIcon":"","dataId":"f0c33c39c5294956af3f008c63f74f6e","description":"@TV娱乐前线：《我不做大哥好多年》定档，郭采洁追忆猪哥亮\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=f0c33c39c5294956af3f008c63f74f6e","shareURL":"http://h5.svipmovie.com/zxgl/f0c33c39c5294956af3f008c63f74f6e.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/f0c33c39c5294956af3f008c63f74f6e_224175_136.jpg","title":"我不做大哥好多年定档","roomId":""},{"airTime":0,"duration":"00:01:20","loadtype":"video","score":0,"angleIcon":"","dataId":"a50870a66dfa470690039cdba1c4ef3f","description":"@TV娱乐前线：VIXX成员N返韩，机场大秀手指爱心展暖男魅力\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=a50870a66dfa470690039cdba1c4ef3f","shareURL":"http://h5.svipmovie.com/zxgl/a50870a66dfa470690039cdba1c4ef3f.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/a50870a66dfa470690039cdba1c4ef3f_224167_136.jpg","title":"N休闲装返韩亮相机场","roomId":""},{"airTime":0,"duration":"00:01:01","loadtype":"video","score":0,"angleIcon":"","dataId":"be7fdfde2757475ebd002ce4df1120bc","description":"@TV娱乐前线：邓紫棋直播消音疑云？粉丝疑\u201c唱太好被忌妒\u201d\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=be7fdfde2757475ebd002ce4df1120bc","shareURL":"http://h5.svipmovie.com/zxgl/be7fdfde2757475ebd002ce4df1120bc.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/be7fdfde2757475ebd002ce4df1120bc_224159_136.jpg","title":"邓紫棋直播消音粉丝疑","roomId":""},{"airTime":0,"duration":"00:01:24","loadtype":"video","score":0,"angleIcon":"","dataId":"4d1d6d953e9c4251a3b6e78c7a938475","description":"@TV娱乐前线：郭采洁跪地痛哭，同框杨祐宁吊唁猪哥亮\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=4d1d6d953e9c4251a3b6e78c7a938475","shareURL":"http://h5.svipmovie.com/zxgl/4d1d6d953e9c4251a3b6e78c7a938475.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/4d1d6d953e9c4251a3b6e78c7a938475_224151_136.jpg","title":"郭采洁前来吊唁猪哥亮","roomId":""},{"airTime":0,"duration":"00:01:25","loadtype":"video","score":0,"angleIcon":"","dataId":"32024ca172744c68bcd7a435ca39557d","description":"@TV娱乐前线：影响力盛典群星闪耀，马可、汪东城人气爆棚\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=32024ca172744c68bcd7a435ca39557d","shareURL":"http://h5.svipmovie.com/zxgl/32024ca172744c68bcd7a435ca39557d.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/32024ca172744c68bcd7a435ca39557d_224143_136.jpg","title":"影响力盛典马可人气高","roomId":""},{"airTime":0,"duration":"00:02:29","loadtype":"video","score":0,"angleIcon":"","dataId":"3b38c787494c4c5a86f70b5e40322176","description":"@TV娱乐前线：纪录片《山海搜神》打造特色IP，震撼视觉场面冲击\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=3b38c787494c4c5a86f70b5e40322176","shareURL":"http://h5.svipmovie.com/zxgl/3b38c787494c4c5a86f70b5e40322176.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/3b38c787494c4c5a86f70b5e40322176_224135_136.jpg","title":"《山海搜神》创特色IP","roomId":""},{"airTime":0,"duration":"00:01:48","loadtype":"video","score":0,"angleIcon":"","dataId":"106781a81ae348278ea1498856fe777a","description":"@TV娱乐前线：中国远征军威猛战，新动画《大象林旺之一炮成名》\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=106781a81ae348278ea1498856fe777a","shareURL":"http://h5.svipmovie.com/zxgl/106781a81ae348278ea1498856fe777a.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/106781a81ae348278ea1498856fe777a_224119_136.jpg","title":"远征军动画电影发布会","roomId":""},{"airTime":0,"duration":"00:01:57","loadtype":"video","score":0,"angleIcon":"","dataId":"60d8fac025114bb0a7e085c7eff1393e","description":"@TV娱乐前线：文艺电影导演王毓雅新作，《浅爱》将文艺范进行到底\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=60d8fac025114bb0a7e085c7eff1393e","shareURL":"http://h5.svipmovie.com/zxgl/60d8fac025114bb0a7e085c7eff1393e.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/60d8fac025114bb0a7e085c7eff1393e_224111_136.jpg","title":"王毓雅新电影《浅爱》","roomId":""},{"airTime":0,"duration":"00:01:19","loadtype":"video","score":0,"angleIcon":"","dataId":"df42fe61bd9b485f8f7b196e0d918db5","description":"@TV娱乐前线：辣妈韩彩英机场时尚少女感十足，美腿抢镜\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=df42fe61bd9b485f8f7b196e0d918db5","shareURL":"http://h5.svipmovie.com/zxgl/df42fe61bd9b485f8f7b196e0d918db5.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/df42fe61bd9b485f8f7b196e0d918db5_224103_136.jpg","title":"辣妈韩彩英少女感十足","roomId":""},{"airTime":0,"duration":"00:01:16","loadtype":"video","score":0,"angleIcon":"","dataId":"75670140802c468aa95c815a8437fc47","description":"@TV娱乐前线：真功夫演绎年轻个性新态度，袁成杰俞灏明尽秀个性光芒\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=75670140802c468aa95c815a8437fc47","shareURL":"http://h5.svipmovie.com/zxgl/75670140802c468aa95c815a8437fc47.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/75670140802c468aa95c815a8437fc47_224095_136.jpg","title":"曝MyLogo人物年度大赏","roomId":""},{"airTime":0,"duration":"00:02:23","loadtype":"video","score":0,"angleIcon":"","dataId":"3217406ed0dc40568f0302fa711a13c4","description":"@TV娱乐前线：张晋新戏饰演金发警察，惊险打斗刷新警匪片新高度\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=3217406ed0dc40568f0302fa711a13c4","shareURL":"http://h5.svipmovie.com/zxgl/3217406ed0dc40568f0302fa711a13c4.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/3217406ed0dc40568f0302fa711a13c4_224087_136.jpg","title":"张晋新作刷警匪片新标","roomId":""},{"airTime":0,"duration":"00:01:47","loadtype":"video","score":0,"angleIcon":"","dataId":"c7290c09fd9a44dd97a3974b36a0b2f1","description":"@TV娱乐前线：天王猪哥亮告别秀，张菲郭采洁杨祐宁送最后一程\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=c7290c09fd9a44dd97a3974b36a0b2f1","shareURL":"http://h5.svipmovie.com/zxgl/c7290c09fd9a44dd97a3974b36a0b2f1.shtml?fromTo=shoujimovie","pic":"http://y3.cnliveimg.com:8080/image/itv/2017/0621/c7290c09fd9a44dd97a3974b36a0b2f1_224079_136.jpg","title":"猪哥亮告别好友送祝福","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"7f82b459b22142808acb4abda92b65a9","description":"@TV娱乐前线：柳俊烈和宋康昊合作感荣幸《出租车司机》重现韩历史悲剧\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7f82b459b22142808acb4abda92b65a9","shareURL":"http://h5.svipmovie.com/zxgl/7f82b459b22142808acb4abda92b65a9.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/7f82b459b22142808acb4abda92b65a9_224071_136.jpg","title":"柳俊烈和宋康昊首合作","roomId":""},{"airTime":0,"duration":"00:01:35","loadtype":"video","score":0,"angleIcon":"","dataId":"52fad095d21e4a029e4bfcf5c557ab38","description":"@TV娱乐前线：成家班举行40周年纪念活动，成龙：所有荣誉都属于成家班\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=52fad095d21e4a029e4bfcf5c557ab38","shareURL":"http://h5.svipmovie.com/zxgl/52fad095d21e4a029e4bfcf5c557ab38.shtml?fromTo=shoujimovie","pic":"http://y1.cnliveimg.com:8080/image/itv/2017/0621/52fad095d21e4a029e4bfcf5c557ab38_224039_136.jpg","title":"成家班举行40周年庆典","roomId":""},{"airTime":0,"duration":"00:01:01","loadtype":"video","score":0,"angleIcon":"","dataId":"80c06f6267d441a08125b2cb53895e54","description":"@TV娱乐前线：王力宏男神成奶爸无怨尤，甘心跪地当马被骑\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=80c06f6267d441a08125b2cb53895e54","shareURL":"http://h5.svipmovie.com/zxgl/80c06f6267d441a08125b2cb53895e54.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/80c06f6267d441a08125b2cb53895e54_224031_136.jpg","title":"王力宏男神一心成奶爸","roomId":""},{"airTime":0,"duration":"00:01:53","loadtype":"video","score":0,"angleIcon":"","dataId":"fb979690059e4c6ca07b66762fc3b7cd","description":"@TV娱乐前线：演员尹素贞告别仪式，众亲友致意场面动容\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=fb979690059e4c6ca07b66762fc3b7cd","shareURL":"http://h5.svipmovie.com/zxgl/fb979690059e4c6ca07b66762fc3b7cd.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0621/fb979690059e4c6ca07b66762fc3b7cd_224023_136.jpg","title":"尹素贞离世众亲友致意","roomId":""},{"airTime":0,"duration":"00:02:47","loadtype":"video","score":0,"angleIcon":"","dataId":"74c1dadc29e445c58c4fc6a76334f339","description":"@TV娱乐前线：电影《建军大业》重返上海，众主演谈拍戏感受\u2026","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=74c1dadc29e445c58c4fc6a76334f339","shareURL":"http://h5.svipmovie.com/zxgl/74c1dadc29e445c58c4fc6a76334f339.shtml?fromTo=shoujimovie","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0620/74c1dadc29e445c58c4fc6a76334f339_223951_136.jpg","title":"《建军大业》重返上海","roomId":""}]
         * totalPnum : 640
         */

        private AdvBean adv;
        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<?> bannerList;
        private List<ListBean> list;

        public static RetBean objectFromData(String str) {

            return new Gson().fromJson(str, RetBean.class);
        }

        public AdvBean getAdv() {
            return adv;
        }

        public void setAdv(AdvBean adv) {
            this.adv = adv;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<?> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<?> bannerList) {
            this.bannerList = bannerList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "RetBean{" +
                    "adv=" + adv +
                    ", pnum=" + pnum +
                    ", totalRecords=" + totalRecords +
                    ", records=" + records +
                    ", totalPnum=" + totalPnum +
                    ", bannerList=" + bannerList +
                    ", list=" + list +
                    '}';
        }

        public static class AdvBean implements Serializable {
            /**
             * imgURL :
             * dataId :
             * htmlURL :
             * title :
             */

            private String imgURL;
            private String dataId;
            private String htmlURL;
            private String title;

            public static AdvBean objectFromData(String str) {

                return new Gson().fromJson(str, AdvBean.class);
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getHtmlURL() {
                return htmlURL;
            }

            public void setHtmlURL(String htmlURL) {
                this.htmlURL = htmlURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "AdvBean{" +
                        "imgURL='" + imgURL + '\'' +
                        ", dataId='" + dataId + '\'' +
                        ", htmlURL='" + htmlURL + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }

        public static class ListBean implements Serializable {
            /**
             * airTime : 0
             * duration : 00:01:47
             * loadtype : video
             * score : 0
             * angleIcon :
             * dataId : cce0cf5b8ecf42b7ab249e650107ea8f
             * description : @TV娱乐前线：6月21日，上海电影集团项目发布会在上海举行，张纪中出席活动…
             * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=cce0cf5b8ecf42b7ab249e650107ea8f
             * shareURL : http://h5.svipmovie.com/zxgl/cce0cf5b8ecf42b7ab249e650107ea8f.shtml?fromTo=shoujimovie
             * pic : http://y2.cnliveimg.com:8080/image/itv/2017/0622/cce0cf5b8ecf42b7ab249e650107ea8f_224495_136.jpg
             * title : 张纪中发布会上谈影视
             * roomId :
             */

            private int airTime;
            private String duration;
            private String loadtype;
            private int score;
            private String angleIcon;
            private String dataId;
            private String description;
            private String loadURL;
            private String shareURL;
            private String pic;
            private String title;
            private String roomId;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadtype() {
                return loadtype;
            }

            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "airTime=" + airTime +
                        ", duration='" + duration + '\'' +
                        ", loadtype='" + loadtype + '\'' +
                        ", score=" + score +
                        ", angleIcon='" + angleIcon + '\'' +
                        ", dataId='" + dataId + '\'' +
                        ", description='" + description + '\'' +
                        ", loadURL='" + loadURL + '\'' +
                        ", shareURL='" + shareURL + '\'' +
                        ", pic='" + pic + '\'' +
                        ", title='" + title + '\'' +
                        ", roomId='" + roomId + '\'' +
                        '}';
            }
        }
    }
}
