package com.livelearn.ignorance.model.db.dbhelper;

import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.LongTimeBookCollectionDao;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;

import java.util.List;

/**
 * Created on 2017/5/11.
 */

public class LongTimeBookCollectionDBHelper {

    private volatile static LongTimeBookCollectionDBHelper mInstance;

    private LongTimeBookCollectionDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static LongTimeBookCollectionDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (LongTimeBookCollectionDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new LongTimeBookCollectionDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 分页查询已收藏的图书，每页10条数据
     *
     * @param pageNum  页数
     * @param pageSize 每页数据大小
     */
    public List<LongTimeBookCollection> queryBookCollectionByPage(int pageNum, int pageSize) {
        return GreenDaoManager.getInstance().getSession()
                .getLongTimeBookCollectionDao()
                .queryBuilder()
                .offset(pageNum * pageSize)
                .limit(pageSize)
                .build()
                .list();
    }

    /**
     * 查询图书是否已收藏
     */
    public boolean queryBookHasCollected(String bookName) {
        List<LongTimeBookCollection> bookList = GreenDaoManager.getInstance().getSession().getLongTimeBookCollectionDao().queryBuilder()
                .where(LongTimeBookCollectionDao.Properties.Book_name.eq(bookName))
                .build()
                .list();
        return !bookList.isEmpty();
    }

    /**
     * 添加图书收藏
     */
    public boolean addBookCollection(LongTimeBookCollection bookCollection) {
        long insert = GreenDaoManager.getInstance().getSession().getLongTimeBookCollectionDao().insert(bookCollection);
        return insert != 0;
    }

    /**
     * 取消图书收藏
     */
    public boolean cancelBookCollection(String bookName) {
        List<LongTimeBookCollection> bookList = GreenDaoManager.getInstance().getSession().getLongTimeBookCollectionDao().queryBuilder()
                .where(LongTimeBookCollectionDao.Properties.Book_name.eq(bookName))
                .build()
                .list();
        for (LongTimeBookCollection bookCollection : bookList) {
            GreenDaoManager.getInstance().getSession().getLongTimeBookCollectionDao().delete(bookCollection);
        }
        return !bookList.isEmpty();
    }
}
