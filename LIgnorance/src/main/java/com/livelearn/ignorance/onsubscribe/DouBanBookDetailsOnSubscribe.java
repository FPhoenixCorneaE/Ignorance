package com.livelearn.ignorance.onsubscribe;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.exception.HttpException;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 豆瓣图书详情
 * Created on 2017/5/9.
 */

public class DouBanBookDetailsOnSubscribe<T> implements Observable.OnSubscribe<T> {

    /**
     * https://book.douban.com/subject/1461903
     */
    private String url;
    private String bookId;

    public DouBanBookDetailsOnSubscribe(String url, String bookId) {
        //获取到需要解析html地址
        this.url = url + bookId;
        this.bookId = bookId;
        if (LogUtils.configAllowLog)
            Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", this.url);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            //开始疯狂的数据抓取啦 这个我就不解释了  大家去看看文档  http://www.open-open.com/jsoup/
            Document doc = Jsoup.connect(url).get();

            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", doc.toString());

            //图书封面网址
            String bookCoverUrl = doc.select(".nbg img").attr("src");
            //图书名称
            String bookName = doc.select(".nbg").attr("title");
            //图书作者
            Element author = doc.getElementById("info").select("span:contains(" + "作者:" + ")").first();
            List<DouBanBookDetailsBean.BookAuthorBean> bookAuthorList = new ArrayList<>();
            if (author != null)
                do {
                    author = author.nextElementSibling();
                    String authorName = author.text();
                    String authorHref = author.attr("href");
                    DouBanBookDetailsBean.BookAuthorBean bookAuthorBean = new DouBanBookDetailsBean.BookAuthorBean()
                            //作者名字
                            .setAuthorName(authorName)
                            //作者链接
                            .setAuthorHref(authorHref);
                    bookAuthorList.add(bookAuthorBean);
                } while ("/".equals(author.nextSibling().outerHtml().trim()));

            //出版社
            Element publisherElement = doc.getElementById("info").select("span:contains(" + "出版社:" + ")").first();
            String publisher = publisherElement != null ? publisherElement.nextSibling().outerHtml().trim() : "";
            //出版年
            Element pubtimeElement = doc.getElementById("info").select("span:contains(" + "出版年:" + ")").first();
            String pubtime = pubtimeElement != null ? pubtimeElement.nextSibling().outerHtml().trim() : "";
            //页数
            Element pagesElement = doc.getElementById("info").select("span:contains(" + "页数:" + ")").first();
            String pages = pagesElement != null ? pagesElement.nextSibling().outerHtml().trim() : "";
            //定价
            Element pricingElement = doc.getElementById("info").select("span:contains(" + "定价:" + ")").first();
            String pricing = pricingElement != null ? pricingElement.nextSibling().outerHtml().trim() : "";
            //装帧
            Element bindingElement = doc.getElementById("info").select("span:contains(" + "装帧:" + ")").first();
            String binding = bindingElement != null ? bindingElement.nextSibling().outerHtml().trim() : "";
            //丛书
            Element seriesElement = doc.getElementById("info").select("span:contains(" + "丛书:" + ")").first();
            String series = seriesElement != null ? seriesElement.nextElementSibling().text().trim() : "";
            //国际标准书号
            Element isbnElement = doc.getElementById("info").select("span:contains(" + "ISBN:" + ")").first();
            String isbn = isbnElement != null ? isbnElement.nextSibling().outerHtml().trim() : "";

            //豆瓣评分
            String ratingNum = doc.select("strong[class=ll rating_num]").text();
            //评价人数
            String ratingSum = doc.select(".rating_sum a").text();
            //5星评价百分比
            String stars5RatingPer = doc.select("div[class=rating_wrap clearbox] span:eq(4)").text();
            //4星评价百分比
            String stars4RatingPer = doc.select("div[class=rating_wrap clearbox] span:eq(8)").text();
            //3星评价百分比
            String stars3RatingPer = doc.select("div[class=rating_wrap clearbox] span:eq(12)").text();
            //2星评价百分比
            String stars2RatingPer = doc.select("div[class=rating_wrap clearbox] span:eq(16)").text();
            //1星评价百分比
            String stars1RatingPer = doc.select("div[class=rating_wrap clearbox] span:eq(20)").text();

            DouBanBookDetailsBean.RatingBean ratingBean =
                    new DouBanBookDetailsBean.RatingBean().setMinRating("0")
                            .setMaxRating("10")
                            .setRatingNum(ratingNum)
                            .setRatingSum(ratingSum)
                            .setStars5RatingPer(stars5RatingPer)
                            .setStars4RatingPer(stars4RatingPer)
                            .setStars3RatingPer(stars3RatingPer)
                            .setStars2RatingPer(stars2RatingPer)
                            .setStars1RatingPer(stars1RatingPer);

            //内容简介
            Elements showFullContentIntro = doc.select("div#link-report div.intro a[class=j a_show_full]");
            String shortContentIntro;
            String fullContentIntro;
            if (showFullContentIntro.size() > 0) {
                shortContentIntro = doc.select("div#link-report span.short").text();
                if (shortContentIntro.contains("."))
                    shortContentIntro = shortContentIntro.substring(0, shortContentIntro.indexOf("."));
                fullContentIntro = doc.select("div#link-report span[class=all hidden]").text();
            } else {
                shortContentIntro = doc.select("div#link-report div.intro").text();
                fullContentIntro = doc.select("div#link-report div.intro").text();
            }

            //作者简介
            Element authorIntroElement = doc.select("span:contains(" + "作者简介" + ")").parents().first();
            String shortAuthorIntro = "";
            String fullAuthortIntro = "";
            if (authorIntroElement != null && authorIntroElement.nextElementSibling() != null) {
                Element authorIntro = authorIntroElement.nextElementSibling();
                if (authorIntro.children().select("a[class=j a_show_full]").size() > 0) {
                    shortAuthorIntro = authorIntro.select("span.short").text();
                    if (shortAuthorIntro.contains("."))
                        shortAuthorIntro = shortAuthorIntro.substring(0, shortAuthorIntro.indexOf("."));
                    fullAuthortIntro = authorIntro.select("span[class=all hidden]").text();
                } else {
                    shortAuthorIntro = authorIntro.select("div.intro").text();
                    fullAuthortIntro = authorIntro.select("div.intro").text();
                }
            }

            //章节目录
            Element shortDirElement = doc.select("div#dir_" + bookId + "_short").first();
            String shortDir = "";
            if (shortDirElement != null) {
                List<TextNode> shortDirList = shortDirElement.textNodes();
                StringBuilder dirBuilder = new StringBuilder();
                for (TextNode textNode : shortDirList) {
                    dirBuilder.append(textNode.text().trim()).append("\n");
                }
                shortDir = dirBuilder.toString();
                if (shortDir.contains("· · · · · ·"))
                    shortDir = shortDir.substring(0, shortDir.indexOf("· · · · · ·"));
                shortDir = shortDir.substring(0, shortDir.lastIndexOf("\n"));
            }
            Element fullDirElement = doc.select("div#dir_" + bookId + "_full").first();
            String fullDir = "";
            if (fullDirElement != null) {
                List<TextNode> fullDirList = fullDirElement.textNodes();
                StringBuilder dirBuilder = new StringBuilder();
                for (TextNode textNode : fullDirList) {
                    dirBuilder.append(textNode.text().trim()).append("\n");
                }
                fullDir = dirBuilder.toString();
                if (fullDir.contains("· · · · · ·"))
                    fullDir = fullDir.substring(0, fullDir.indexOf("· · · · · ·"));
                fullDir = fullDir.substring(0, fullDir.lastIndexOf("\n"));
            }

            //常用标签
            String sumTagsNum = doc.select("div#db-tags-section h2 span").text();
            Elements commonTags = doc.select("div#db-tags-section div.indent a");
            List<String> commonTagList = new ArrayList<>();
            for (Element temp : commonTags) {
                String commonTag = temp.text();
                commonTagList.add(commonTag);
            }

            //丛书信息
            String seriesInfo = doc.select("div[class=subject_show block5] div").text();

            //喜欢读该书的人也喜欢读的书
            Elements likeBooks = doc.select("[class=block5 subject_show knnlike]>[class=content clearfix] dl");
            List<DouBanBookDetailsBean.LikeBookBean> likeBookList = new ArrayList<>();
            for (Element likeBook : likeBooks) {
                String likeBookHref = likeBook.select("dd a[href]").attr("href");
                String likeBookName = likeBook.select("dd a[href]").text();
                String likeBookCoverUrl = likeBook.select("dt img").attr("src");
                if (likeBookCoverUrl.contains("spic")) {
                    //图书封面换成高清大图
                    likeBookCoverUrl = likeBookCoverUrl.replace("spic", "lpic");
                }
                if (!likeBookName.isEmpty()) {
                    DouBanBookDetailsBean.LikeBookBean likeBookBean = new DouBanBookDetailsBean.LikeBookBean()
                            .setLikeBookHref(likeBookHref)
                            .setLikeBookCoverUrl(likeBookCoverUrl)
                            .setLikeBookName(likeBookName);
                    likeBookList.add(likeBookBean);
                }
            }

            //热门短评
            Elements hotShortComments = doc.select("[class=comment-list hot show] ul li");
            List<DouBanBookDetailsBean.ShortCommentBean> hotShortCommentList = new ArrayList<>();
            for (Element temp : hotShortComments) {
                String commentUserHref = temp.select("span.comment-info a").attr("href");
                String commentUserName = temp.select("span.comment-info a").text();
                String commentUserStarsRating = temp.select("span.comment-info span:eq(1)").attr("title");
                String commentDate;
                if (commentUserStarsRating.isEmpty()) {
                    commentDate = temp.select("span.comment-info span:eq(1)").text();
                } else {
                    commentDate = temp.select("span.comment-info span:eq(2)").text();
                }
                String commentContent = temp.select("p.comment-content").text();
                DouBanBookDetailsBean.ShortCommentBean hotShortComment = new DouBanBookDetailsBean.ShortCommentBean()
                        .setCommentUserHref(commentUserHref)
                        .setCommentUserName(commentUserName)
                        .setCommentUserStarsRating(commentUserStarsRating)
                        .setCommentDate(commentDate)
                        .setCommentContent(commentContent);
                hotShortCommentList.add(hotShortComment);
            }

            //最新短评
            Elements newShortComments = doc.select("[class=comment-list new noshow] ul li");
            List<DouBanBookDetailsBean.ShortCommentBean> newShortCommentList = new ArrayList<>();
            for (Element temp : newShortComments) {
                String commentUserHref = temp.select("span.comment-info a").attr("href");
                String commentUserName = temp.select("span.comment-info a").text();
                String commentUserStarsRating = temp.select("span.comment-info span:eq(1)").attr("title");
                String commentDate = temp.select("span.comment-info span:eq(2)").text();
                String commentContent = temp.select("p.comment-content").text();
                DouBanBookDetailsBean.ShortCommentBean newShortComment = new DouBanBookDetailsBean.ShortCommentBean()
                        .setCommentUserHref(commentUserHref)
                        .setCommentUserName(commentUserName)
                        .setCommentUserStarsRating(commentUserStarsRating)
                        .setCommentDate(commentDate)
                        .setCommentContent(commentContent);
                newShortCommentList.add(newShortComment);
            }

            //书评
            Elements bookReviews = doc.select("div.review-list [class=main review-item]");
            List<DouBanBookDetailsBean.BookReviewBean> bookReviewList = new ArrayList<>();
            for (Element temp : bookReviews) {
                String bookReviewTitle = temp.select("a.title-link").text();
                String bookReviewHref = temp.select("a.title-link").attr("href");
                String bookReviewCreateUserAvatarUrl = temp.select("div.header-more img").attr("src");
                String bookReviewCreateUserName = temp.select("a.author").text();
                String bookReviewCreateUserStarsRating = temp.select("span[property=v:rating]").attr("title");
                String bookReviewCreateDate = temp.select("span.main-meta").text();
                String bookReviewShortContent = temp.select("div.short-content").text();
                if (bookReviewShortContent.contains("... ("))
                    bookReviewShortContent = bookReviewShortContent.substring(0, bookReviewShortContent.indexOf("... (") + 3);
                if (bookReviewShortContent.contains(">  没关系，可以显示全文"))
                    bookReviewShortContent = bookReviewShortContent.substring(0, bookReviewShortContent.indexOf(">  没关系，可以显示全文"));
                DouBanBookDetailsBean.BookReviewBean bookReviewBean = new DouBanBookDetailsBean.BookReviewBean()
                        .setBookReviewTitle(bookReviewTitle)
                        .setBookReviewHref(bookReviewHref)
                        .setBookReviewCreateUserAvatarUrl(bookReviewCreateUserAvatarUrl)
                        .setBookReviewCreateUserName(bookReviewCreateUserName)
                        .setBookReviewCreateUserStarsRating(bookReviewCreateUserStarsRating)
                        .setBookReviewCreateDate(bookReviewCreateDate)
                        .setBookReviewShortContent(bookReviewShortContent);
                bookReviewList.add(bookReviewBean);
            }

            DouBanBookDetailsBean douBanBookDetailsBean =
                    new DouBanBookDetailsBean().setWebUrl(url)
                            .setBookId(bookId)
                            .setBookCoverUrl(bookCoverUrl)
                            .setBookName(bookName)
                            .setBookAuthorList(bookAuthorList)
                            .setPublisher(publisher)
                            .setPubtime(pubtime)
                            .setPages(pages)
                            .setPricing(pricing)
                            .setBinding(binding)
                            .setSeries(series)
                            .setIsbn(isbn)
                            .setRating(ratingBean)
                            .setShortContentIntro(shortContentIntro)
                            .setFullContentIntro(fullContentIntro)
                            .setShortAuthorIntro(shortAuthorIntro)
                            .setFullAuthortIntro(fullAuthortIntro)
                            .setShortDir(shortDir)
                            .setFullDir(fullDir)
                            .setSumTagsNum(sumTagsNum)
                            .setCommonTagList(commonTagList)
                            .setSeriesInfo(seriesInfo)
                            .setLikeBookList(likeBookList)
                            .setHotShortCommentList(hotShortCommentList)
                            .setNewShortCommentList(newShortCommentList)
                            .setBookReviewList(bookReviewList);

            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", douBanBookDetailsBean.toString());

            subscriber.onNext((T) douBanBookDetailsBean);
            subscriber.onCompleted();
        } catch (IOException e) {
            subscriber.onError(new HttpException(HttpException.ErrorType.PARSE_DATA_ERROR));
        }
    }
}
