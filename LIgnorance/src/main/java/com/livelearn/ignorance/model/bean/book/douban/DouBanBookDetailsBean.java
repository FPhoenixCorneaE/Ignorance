package com.livelearn.ignorance.model.bean.book.douban;

import java.io.Serializable;
import java.util.List;

public class DouBanBookDetailsBean implements Serializable {

    //网页链接
    private String webUrl;
    //图书id
    private String bookId;
    //图书封面网址
    private String bookCoverUrl;
    //图书名称
    private String bookName;
    //图书作者
    private List<BookAuthorBean> bookAuthorList;
    //出版社
    private String publisher;
    //出版年
    private String pubtime;
    //页数
    private String pages;
    //定价
    private String pricing;
    //装帧
    private String binding;
    //丛书
    private String series;
    //国际标准书号
    private String isbn;
    //豆瓣评分
    private RatingBean rating;
    //内容简介
    private String shortContentIntro;
    private String fullContentIntro;
    //作者简介
    private String shortAuthorIntro;
    private String fullAuthortIntro;
    //章节目录
    private String shortDir;
    private String fullDir;
    //常用标签
    private String sumTagsNum;
    private List<String> commonTagList;
    //丛书信息
    private String seriesInfo;
    //喜欢读该书的人也喜欢读的书
    private List<LikeBookBean> likeBookList;
    //热门短评
    private List<ShortCommentBean> hotShortCommentList;
    //最新短评
    private List<ShortCommentBean> newShortCommentList;
    //书评
    private List<BookReviewBean> bookReviewList;

    public String getWebUrl() {
        return webUrl;
    }

    public DouBanBookDetailsBean setWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public String getBookId() {
        return bookId;
    }

    public DouBanBookDetailsBean setBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public DouBanBookDetailsBean setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public DouBanBookDetailsBean setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public List<BookAuthorBean> getBookAuthorList() {
        return bookAuthorList;
    }

    public DouBanBookDetailsBean setBookAuthorList(List<BookAuthorBean> bookAuthorList) {
        this.bookAuthorList = bookAuthorList;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public DouBanBookDetailsBean setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getPubtime() {
        return pubtime;
    }

    public DouBanBookDetailsBean setPubtime(String pubtime) {
        this.pubtime = pubtime;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public DouBanBookDetailsBean setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public String getPricing() {
        return pricing;
    }

    public DouBanBookDetailsBean setPricing(String pricing) {
        this.pricing = pricing;
        return this;
    }

    public String getBinding() {
        return binding;
    }

    public DouBanBookDetailsBean setBinding(String binding) {
        this.binding = binding;
        return this;
    }

    public RatingBean getRating() {
        return rating;
    }

    public DouBanBookDetailsBean setRating(RatingBean rating) {
        this.rating = rating;
        return this;
    }

    public String getShortContentIntro() {
        return shortContentIntro;
    }

    public DouBanBookDetailsBean setShortContentIntro(String shortContentIntro) {
        this.shortContentIntro = shortContentIntro;
        return this;
    }

    public String getFullContentIntro() {
        return fullContentIntro;
    }

    public DouBanBookDetailsBean setFullContentIntro(String fullContentIntro) {
        this.fullContentIntro = fullContentIntro;
        return this;
    }

    public String getShortAuthorIntro() {
        return shortAuthorIntro;
    }

    public DouBanBookDetailsBean setShortAuthorIntro(String shortAuthorIntro) {
        this.shortAuthorIntro = shortAuthorIntro;
        return this;
    }

    public String getFullAuthortIntro() {
        return fullAuthortIntro;
    }

    public DouBanBookDetailsBean setFullAuthortIntro(String fullAuthortIntro) {
        this.fullAuthortIntro = fullAuthortIntro;
        return this;
    }

    public String getShortDir() {
        return shortDir;
    }

    public DouBanBookDetailsBean setShortDir(String shortDir) {
        this.shortDir = shortDir;
        return this;
    }

    public String getFullDir() {
        return fullDir;
    }

    public DouBanBookDetailsBean setFullDir(String fullDir) {
        this.fullDir = fullDir;
        return this;
    }

    public String getSumTagsNum() {
        return sumTagsNum;
    }

    public DouBanBookDetailsBean setSumTagsNum(String sumTagsNum) {
        this.sumTagsNum = sumTagsNum;
        return this;
    }

    public List<String> getCommonTagList() {
        return commonTagList;
    }

    public DouBanBookDetailsBean setCommonTagList(List<String> commonTagList) {
        this.commonTagList = commonTagList;
        return this;
    }

    public List<LikeBookBean> getLikeBookList() {
        return likeBookList;
    }

    public DouBanBookDetailsBean setLikeBookList(List<LikeBookBean> likeBookList) {
        this.likeBookList = likeBookList;
        return this;
    }

    public List<ShortCommentBean> getHotShortCommentList() {
        return hotShortCommentList;
    }

    public DouBanBookDetailsBean setHotShortCommentList(List<ShortCommentBean> hotShortCommentList) {
        this.hotShortCommentList = hotShortCommentList;
        return this;
    }

    public List<ShortCommentBean> getNewShortCommentList() {
        return newShortCommentList;
    }

    public DouBanBookDetailsBean setNewShortCommentList(List<ShortCommentBean> newShortCommentList) {
        this.newShortCommentList = newShortCommentList;
        return this;
    }

    public List<BookReviewBean> getBookReviewList() {
        return bookReviewList;
    }

    public DouBanBookDetailsBean setBookReviewList(List<BookReviewBean> bookReviewList) {
        this.bookReviewList = bookReviewList;
        return this;
    }

    public String getSeries() {
        return series;
    }

    public DouBanBookDetailsBean setSeries(String series) {
        this.series = series;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public DouBanBookDetailsBean setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getSeriesInfo() {
        return seriesInfo;
    }

    public DouBanBookDetailsBean setSeriesInfo(String seriesInfo) {
        this.seriesInfo = seriesInfo;
        return this;
    }

    @Override
    public String toString() {
        return "DouBanBookDetailsBean{" +
                "webUrl='" + webUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookCoverUrl='" + bookCoverUrl + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthorList=" + bookAuthorList +
                ", publisher='" + publisher + '\'' +
                ", pubtime='" + pubtime + '\'' +
                ", pages='" + pages + '\'' +
                ", pricing='" + pricing + '\'' +
                ", binding='" + binding + '\'' +
                ", series='" + series + '\'' +
                ", isbn='" + isbn + '\'' +
                ", rating=" + rating +
                ", shortContentIntro='" + shortContentIntro + '\'' +
                ", fullContentIntro='" + fullContentIntro + '\'' +
                ", shortAuthorIntro='" + shortAuthorIntro + '\'' +
                ", fullAuthortIntro='" + fullAuthortIntro + '\'' +
                ", shortDir='" + shortDir + '\'' +
                ", fullDir='" + fullDir + '\'' +
                ", sumTagsNum='" + sumTagsNum + '\'' +
                ", commonTagList=" + commonTagList +
                ", seriesInfo='" + seriesInfo + '\'' +
                ", likeBookList=" + likeBookList +
                ", hotShortCommentList=" + hotShortCommentList +
                ", newShortCommentList=" + newShortCommentList +
                ", bookReviewList=" + bookReviewList +
                '}';
    }

    public static class BookAuthorBean implements Serializable {
        //作者链接
        String authorHref;
        //作者名字
        String authorName;

        public String getAuthorHref() {
            return authorHref;
        }

        public BookAuthorBean setAuthorHref(String authorHref) {
            this.authorHref = authorHref;
            return this;
        }

        public String getAuthorName() {
            return authorName;
        }

        public BookAuthorBean setAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        @Override
        public String toString() {
            return "BookAuthorBean{" +
                    "authorHref='" + authorHref + '\'' +
                    ", authorName='" + authorName + '\'' +
                    '}';
        }
    }

    public static class LikeBookBean implements Serializable {
        //喜欢的书的链接
        String likeBookHref;
        //喜欢的书的封面网址
        String likeBookCoverUrl;
        //喜欢的书名字
        String likeBookName;

        public String getLikeBookHref() {
            return likeBookHref;
        }

        public LikeBookBean setLikeBookHref(String likeBookHref) {
            this.likeBookHref = likeBookHref;
            return this;
        }

        public String getLikeBookCoverUrl() {
            return likeBookCoverUrl;
        }

        public LikeBookBean setLikeBookCoverUrl(String likeBookCoverUrl) {
            this.likeBookCoverUrl = likeBookCoverUrl;
            return this;
        }

        public String getLikeBookName() {
            return likeBookName;
        }

        public LikeBookBean setLikeBookName(String likeBookName) {
            this.likeBookName = likeBookName;
            return this;
        }

        @Override
        public String toString() {
            return "LikeBookBean{" +
                    "likeBookHref='" + likeBookHref + '\'' +
                    ", likeBookCoverUrl='" + likeBookCoverUrl + '\'' +
                    ", likeBookName='" + likeBookName + '\'' +
                    '}';
        }
    }

    public static class ShortCommentBean implements Serializable {
        //短评用户链接
        private String commentUserHref;
        //短评用户名字
        private String commentUserName;
        //短评内容
        private String commentContent;
        //短评日期
        private String commentDate;
        //短评用户星级评分
        private String commentUserStarsRating;

        public String getCommentUserHref() {
            return commentUserHref;
        }

        public ShortCommentBean setCommentUserHref(String commentUserHref) {
            this.commentUserHref = commentUserHref;
            return this;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public ShortCommentBean setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
            return this;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public ShortCommentBean setCommentContent(String commentContent) {
            this.commentContent = commentContent;
            return this;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public ShortCommentBean setCommentDate(String commentDate) {
            this.commentDate = commentDate;
            return this;
        }

        public String getCommentUserStarsRating() {
            return commentUserStarsRating;
        }

        public ShortCommentBean setCommentUserStarsRating(String commentUserStarsRating) {
            this.commentUserStarsRating = commentUserStarsRating;
            return this;
        }

        @Override
        public String toString() {
            return "ShortCommentBean{" +
                    "commentUserHref='" + commentUserHref + '\'' +
                    ", commentUserName='" + commentUserName + '\'' +
                    ", commentContent='" + commentContent + '\'' +
                    ", commentDate='" + commentDate + '\'' +
                    ", commentUserStarsRating='" + commentUserStarsRating + '\'' +
                    '}';
        }
    }

    public static class BookReviewBean implements Serializable {
        //书评标题
        String bookReviewTitle;
        //书评链接
        String bookReviewHref;
        //书评创建者头像
        String bookReviewCreateUserAvatarUrl;
        //书评创建者名字
        String bookReviewCreateUserName;
        //书评创建者星级评分
        String bookReviewCreateUserStarsRating;
        //书评创建日期
        String bookReviewCreateDate;
        //书评简短内容
        String bookReviewShortContent;
        //书评全部内容
        String bookReviewFullContent;

        public String getBookReviewTitle() {
            return bookReviewTitle;
        }

        public BookReviewBean setBookReviewTitle(String bookReviewTitle) {
            this.bookReviewTitle = bookReviewTitle;
            return this;
        }

        public String getBookReviewHref() {
            return bookReviewHref;
        }

        public BookReviewBean setBookReviewHref(String bookReviewHref) {
            this.bookReviewHref = bookReviewHref;
            return this;
        }

        public String getBookReviewCreateUserAvatarUrl() {
            return bookReviewCreateUserAvatarUrl;
        }

        public BookReviewBean setBookReviewCreateUserAvatarUrl(String bookReviewCreateUserAvatarUrl) {
            this.bookReviewCreateUserAvatarUrl = bookReviewCreateUserAvatarUrl;
            return this;
        }

        public String getBookReviewCreateUserName() {
            return bookReviewCreateUserName;
        }

        public BookReviewBean setBookReviewCreateUserName(String bookReviewCreateUserName) {
            this.bookReviewCreateUserName = bookReviewCreateUserName;
            return this;
        }

        public String getBookReviewCreateUserStarsRating() {
            return bookReviewCreateUserStarsRating;
        }

        public BookReviewBean setBookReviewCreateUserStarsRating(String bookReviewCreateUserStarsRating) {
            this.bookReviewCreateUserStarsRating = bookReviewCreateUserStarsRating;
            return this;
        }

        public String getBookReviewCreateDate() {
            return bookReviewCreateDate;
        }

        public BookReviewBean setBookReviewCreateDate(String bookReviewCreateDate) {
            this.bookReviewCreateDate = bookReviewCreateDate;
            return this;
        }

        public String getBookReviewShortContent() {
            return bookReviewShortContent;
        }

        public BookReviewBean setBookReviewShortContent(String bookReviewShortContent) {
            this.bookReviewShortContent = bookReviewShortContent;
            return this;
        }

        public String getBookReviewFullContent() {
            return bookReviewFullContent;
        }

        public BookReviewBean setBookReviewFullContent(String bookReviewFullContent) {
            this.bookReviewFullContent = bookReviewFullContent;
            return this;
        }

        @Override
        public String toString() {
            return "BookReviewBean{" +
                    "bookReviewTitle='" + bookReviewTitle + '\'' +
                    ", bookReviewHref='" + bookReviewHref + '\'' +
                    ", bookReviewCreateUserAvatarUrl='" + bookReviewCreateUserAvatarUrl + '\'' +
                    ", bookReviewCreateUserName='" + bookReviewCreateUserName + '\'' +
                    ", bookReviewCreateUserStarsRating='" + bookReviewCreateUserStarsRating + '\'' +
                    ", bookReviewCreateDate='" + bookReviewCreateDate + '\'' +
                    ", bookReviewShortContent='" + bookReviewShortContent + '\'' +
                    ", bookReviewFullContent='" + bookReviewFullContent + '\'' +
                    '}';
        }
    }

    public static class RatingBean implements Serializable {
        //最小评分
        private String minRating;
        //最大评分
        private String maxRating;
        //豆瓣评分
        private String ratingNum;
        //评分人数
        private String ratingSum;
        //5星评价百分比
        private String stars5RatingPer;
        //4星评价百分比
        private String stars4RatingPer;
        //3星评价百分比
        private String stars3RatingPer;
        //2星评价百分比
        private String stars2RatingPer;
        //1星评价百分比
        private String stars1RatingPer;

        public String getMinRating() {
            return minRating;
        }

        public RatingBean setMinRating(String minRating) {
            this.minRating = minRating;
            return this;
        }

        public String getMaxRating() {
            return maxRating;
        }

        public RatingBean setMaxRating(String maxRating) {
            this.maxRating = maxRating;
            return this;
        }

        public String getRatingNum() {
            return ratingNum;
        }

        public RatingBean setRatingNum(String ratingNum) {
            this.ratingNum = ratingNum;
            return this;
        }

        public String getRatingSum() {
            return ratingSum;
        }

        public RatingBean setRatingSum(String ratingSum) {
            this.ratingSum = ratingSum;
            return this;
        }

        public String getStars5RatingPer() {
            return stars5RatingPer;
        }

        public RatingBean setStars5RatingPer(String stars5RatingPer) {
            this.stars5RatingPer = stars5RatingPer;
            return this;
        }

        public String getStars4RatingPer() {
            return stars4RatingPer;
        }

        public RatingBean setStars4RatingPer(String stars4RatingPer) {
            this.stars4RatingPer = stars4RatingPer;
            return this;
        }

        public String getStars3RatingPer() {
            return stars3RatingPer;
        }

        public RatingBean setStars3RatingPer(String stars3RatingPer) {
            this.stars3RatingPer = stars3RatingPer;
            return this;
        }

        public String getStars2RatingPer() {
            return stars2RatingPer;
        }

        public RatingBean setStars2RatingPer(String stars2RatingPer) {
            this.stars2RatingPer = stars2RatingPer;
            return this;
        }

        public String getStars1RatingPer() {
            return stars1RatingPer;
        }

        public RatingBean setStars1RatingPer(String stars1RatingPer) {
            this.stars1RatingPer = stars1RatingPer;
            return this;
        }

        @Override
        public String toString() {
            return "RatingBean{" +
                    "minRating='" + minRating + '\'' +
                    ", maxRating='" + maxRating + '\'' +
                    ", ratingNum='" + ratingNum + '\'' +
                    ", ratingSum='" + ratingSum + '\'' +
                    ", stars5RatingPer='" + stars5RatingPer + '\'' +
                    ", stars4RatingPer='" + stars4RatingPer + '\'' +
                    ", stars3RatingPer='" + stars3RatingPer + '\'' +
                    ", stars2RatingPer='" + stars2RatingPer + '\'' +
                    ", stars1RatingPer='" + stars1RatingPer + '\'' +
                    '}';
        }
    }
}
