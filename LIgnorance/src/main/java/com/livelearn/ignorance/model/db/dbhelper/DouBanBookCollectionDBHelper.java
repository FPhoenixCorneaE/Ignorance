package com.livelearn.ignorance.model.db.dbhelper;

import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.DouBanBookCollectionDao;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;

import java.util.List;

/**
 * Created on 2017/5/11.
 */

public class DouBanBookCollectionDBHelper {

    private volatile static DouBanBookCollectionDBHelper mInstance;

    private DouBanBookCollectionDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static DouBanBookCollectionDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (DepartmentDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DouBanBookCollectionDBHelper();
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
    public List<DouBanBookCollection> queryBookCollectionByPage(int pageNum, int pageSize) {
        return GreenDaoManager.getInstance().getSession()
                .getDouBanBookCollectionDao()
                .queryBuilder()
                .offset(pageNum * pageSize)
                .limit(pageSize)
                .build()
                .list();
    }

    /**
     * 查询图书是否已收藏
     */
    public boolean queryBookHasCollected(String bookId) {
        List<DouBanBookCollection> bookList = GreenDaoManager.getInstance().getSession().getDouBanBookCollectionDao().queryBuilder()
                .where(DouBanBookCollectionDao.Properties.Book_id.eq(bookId))
                .build()
                .list();
        return !bookList.isEmpty();
    }

    /**
     * 添加图书收藏
     */
    public boolean addBookCollection(DouBanBookCollection bookCollection) {
        long insert = GreenDaoManager.getInstance().getSession().getDouBanBookCollectionDao().insert(bookCollection);
        return insert != 0;
    }

    /**
     * 取消图书收藏
     */
    public boolean cancelBookCollection(String bookId) {
        List<DouBanBookCollection> bookList = GreenDaoManager.getInstance().getSession().getDouBanBookCollectionDao().queryBuilder()
                .where(DouBanBookCollectionDao.Properties.Book_id.eq(bookId))
                .build()
                .list();
        for (DouBanBookCollection bookCollection : bookList) {
            GreenDaoManager.getInstance().getSession().getDouBanBookCollectionDao().delete(bookCollection);
        }
        return !bookList.isEmpty();
    }
}
