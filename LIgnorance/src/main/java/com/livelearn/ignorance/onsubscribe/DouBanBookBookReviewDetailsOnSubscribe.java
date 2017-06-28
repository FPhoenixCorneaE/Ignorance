package com.livelearn.ignorance.onsubscribe;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.exception.HttpException;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * 豆瓣图书书评详情
 * Created on 2017/5/16.
 */

public class DouBanBookBookReviewDetailsOnSubscribe<T> implements Observable.OnSubscribe<T> {

    /**
     * https://book.douban.com/review/1476522/
     */
    private String url;

    public DouBanBookBookReviewDetailsOnSubscribe(String url) {
        this.url = url;
        if (LogUtils.configAllowLog)
            Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", this.url);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            //开始疯狂的数据抓取啦 这个我就不解释了  大家去看看文档  http://www.open-open.com/jsoup/
            Document doc = Jsoup.connect(url).get();

            //为了划分段落这里先把换行符<br>替换成"$$$"，后面再把"$$$"替换成"\n"
            String temp = doc.html().replace("<br>", "$$$");
            doc = Jsoup.parse(temp);

            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", doc.toString());

            String bookReviewTitle = doc.select("div#content span[property=v:summary]").text();
            String bookReviewHref = doc.select("a[class=avatar author-avatar left]").attr("href");
            String bookReviewCreateUserAvatarUrl = doc.select("a[class=avatar author-avatar left] img").attr("src");
            String bookReviewCreateUserName = doc.select("header.main-hd span[property=v:reviewer]").text();
            String bookReviewCreateUserStarsRating = doc.select("header.main-hd span[property=v:rating]").text();
            String bookReviewCreateDate = doc.select("header.main-hd span[property=v:dtreviewed]").text();
            String bookReviewFullContent = doc.select("div[property=v:description]").text();
            if (bookReviewFullContent.contains("$$$")) {
                bookReviewFullContent = bookReviewFullContent.replace("$$$", "\n");
            }

            DouBanBookDetailsBean.BookReviewBean bookReviewBean = new DouBanBookDetailsBean.BookReviewBean()
                    .setBookReviewTitle(bookReviewTitle)
                    .setBookReviewHref(bookReviewHref)
                    .setBookReviewCreateUserAvatarUrl(bookReviewCreateUserAvatarUrl)
                    .setBookReviewCreateUserName(bookReviewCreateUserName)
                    .setBookReviewCreateUserStarsRating(bookReviewCreateUserStarsRating)
                    .setBookReviewCreateDate(bookReviewCreateDate)
                    .setBookReviewFullContent(bookReviewFullContent);

            subscriber.onNext((T) bookReviewBean);
            subscriber.onCompleted();
        } catch (IOException e) {
            subscriber.onError(new HttpException(HttpException.ErrorType.PARSE_DATA_ERROR));
        }
    }
}
