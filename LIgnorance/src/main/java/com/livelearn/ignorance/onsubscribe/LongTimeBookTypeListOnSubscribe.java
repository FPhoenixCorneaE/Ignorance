package com.livelearn.ignorance.onsubscribe;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.exception.HttpException;
import com.livelearn.ignorance.model.bean.book.BookListModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 久久图书分类列表
 * Created on 2016/9/14.
 */
public class LongTimeBookTypeListOnSubscribe<T> implements Observable.OnSubscribe<List<T>> {
    private String url;

    public LongTimeBookTypeListOnSubscribe(String url) {
        this.url = url;
        if (LogUtils.configAllowLog)
            Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", url);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Subscriber<? super List<T>> subscriber) {
        List<T> expertListDtos = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();

            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + "Okhttp3Utils", doc.toString());

            Element content = doc.select("ul").first();
            Elements links = content.select("li");
            for (Element link : links) {
                Elements temp = link.select("a");
                Elements temp1 = link.select("img");
                Elements temp3 = link.select("p:eq(2)");
                Elements temp4 = link.select("p:eq(3)");
                String relHref = temp.attr("href");
                String relHref1 = temp1.attr("src");
                String relHref2 = temp1.attr("alt");
                String relHref3 = temp3.text();
                String relHref4 = temp4.text();
                if (!relHref.isEmpty()) {
                    BookListModel bookListModel = new BookListModel();
                    bookListModel.setImageUrl(relHref1);
                    bookListModel.setBookName(relHref2);
                    bookListModel.setAuthor(relHref3);
                    bookListModel.setIntroduction(relHref4);
                    bookListModel.setCodeId(relHref);
                    expertListDtos.add((T) bookListModel);
                }
            }
            subscriber.onNext(expertListDtos);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(new HttpException(HttpException.ErrorType.PARSE_DATA_ERROR));
        }
    }
}
