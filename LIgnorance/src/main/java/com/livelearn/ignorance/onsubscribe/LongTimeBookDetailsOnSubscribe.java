package com.livelearn.ignorance.onsubscribe;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.exception.HttpException;
import com.livelearn.ignorance.model.bean.book.BookDetailsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * 久久图书详情
 * <p>
 * 其实这里面的玩法还很多
 * 这是jsop的中文文档 http://www.open-open.com/jsoup/  再牛逼的数据都能抓取
 * 其实doc.select(".bookcover h1:eq(1)");  ()里面的数据完全可以通过接口定义  达到完全控制的效果
 * 我是懒得写了  但是这个需求还是提一下  很nice的  装逼必备啊
 */
public class LongTimeBookDetailsOnSubscribe<T> implements Observable.OnSubscribe<T> {
    private String url;

    public LongTimeBookDetailsOnSubscribe(String url) {
        //获取到需要解析html地址
        this.url = url;
        if (LogUtils.configAllowLog)
            Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", url);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            //开始疯狂的数据抓取啦 这个我就不解释了  大家去看看文档  http://www.open-open.com/jsoup/
            Document doc = Jsoup.connect(url).get();

            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", doc.toString());

            Elements bookReadUrl = doc.select("meta[property=og:novel:read_url]");
            Elements bookIntroduction = doc.select(".con");
            Elements bookImageUrl = doc.select(".bookcover img");
            Elements bookName = doc.select(".bookcover h1:eq(1)");
            Elements bookAuthor = doc.select(".bookcover p:eq(2)");
            Elements bookType = doc.select(".bookcover p:eq(3)");
            Elements bookLength = doc.select(".bookcover p:eq(4)");
            Elements bookProgress = doc.select(".bookcover p:eq(5)");
            Elements bookUpdateTime = doc.select(".bookcover p:eq(6)");
            String[] strs = url.split("/");
            String bookDownload = "http://www.txt99.cc/home/down/txt/id/" + ((strs[strs.length - 1]));

            BookDetailsModel bookDetails = new BookDetailsModel();
            bookDetails.setBookImageUrl(bookImageUrl.attr("src"));
            bookDetails.setBookName(bookName.text());
            bookDetails.setBookAuthor(bookAuthor.text());
            bookDetails.setBookType(bookType.text());
            bookDetails.setBookLength(bookLength.text());
            bookDetails.setBookProgress(bookProgress.text());
            bookDetails.setBookUpdateTime(bookUpdateTime.text());
            bookDetails.setBookDownload(bookDownload);
            bookDetails.setBookIntroduction(bookIntroduction.html());
            bookDetails.setBookReadUrl(bookReadUrl.attr("content"));

            subscriber.onNext((T) bookDetails);

            subscriber.onCompleted();
        } catch (IOException e) {
            subscriber.onError(new HttpException(HttpException.ErrorType.PARSE_DATA_ERROR));
        }
    }
}
