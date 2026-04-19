package com.bu.admin.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import java.util.Collections;
import java.util.List;

@Slf4j
public class IfEntityManagerSupport{

    @PersistenceContext
    protected EntityManager em;


    public <T> IfPage<T> execIfPageQuery(IfQuery ifQuery) {
        if (ifQuery instanceof IfHqlQuery ifHqlQuery) {
            return execIfPageHQLQuery(ifHqlQuery);
        }
        if (ifQuery instanceof IfSqlQuery ifSqlQuery) {
            return execIfPageSQLQuery(ifSqlQuery);
        }
        else
            return null;
    }

    public <T> List<T> execIfQuery(IfQuery ifQuery) {
        if (ifQuery instanceof IfHqlQuery ifHqlQuery) {
            return execIfHqlQuery(ifHqlQuery);
        }
        if (ifQuery instanceof IfSqlQuery ifSqlQuery) {
            return execIfSqlQuery(ifSqlQuery);
        }
        else
            return Collections.emptyList();
    }

    public <T> T execIfQueryOne(IfQuery ifQuery) {
        List<T> list = execIfQuery(ifQuery);
        if (list == null || list.isEmpty())
            return null;
        else
            return list.getFirst();
    }

    private <T> IfPage<T> execIfPageHQLQuery(final IfHqlQuery ifQuery) {
        IfPage<T> ifPage = new IfPage<>();
        int nPageSize = ifQuery.getPageSize();
        if (nPageSize <= 0)
            nPageSize = 10;
        int nPageNum = ifQuery.getPageNum();
        if (nPageNum <= 0)
            nPageNum = 1;
        ifPage. setPageSize(nPageSize);
        ifPage.setPageNum(nPageNum);
        int totalCount = 0;
        if (ifQuery.getCountHQL() != null && !ifQuery.getCountHQL().equals("")) {
            totalCount = ((Number) ifQuery.fillQueryStmt(
                            em.createQuery(ifQuery.getCountHQL()), true)
                    .getSingleResult()).intValue();
            ifPage.setTotalRecordCount(totalCount);
        } else {
            log.error("没有找到统计总数的HQL");
        }
        ifPage.setDataList(ifQuery
                .fillQueryStmt(em.createQuery(ifQuery.getHQL()), false)
                .setFirstResult((nPageNum - 1) * nPageSize)
                .setMaxResults(nPageSize).getResultList());
        return ifPage;
    }

    private <T> IfPage<T> execIfPageSQLQuery(final IfSqlQuery ifQuery) {
        IfPage<T> ifPage = new IfPage<>();
        int nPageSize = ifQuery.getPageSize();
        if (nPageSize <= 0)
            nPageSize = 10;
        int nPageNum = ifQuery.getPageNum();
        if (nPageNum <= 0)
            nPageNum = 1;
        ifPage. setPageSize(nPageSize);
        ifPage.setPageNum(nPageNum);
        int totalCount = 0;
        NativeQuery<?> query;
        if (ifQuery.getCountSQL() != null) {
            query = em.createNativeQuery(ifQuery.getCountSQL()).unwrap(NativeQuery.class);
            query.addScalar("COUNT", StandardBasicTypes.INTEGER);
            ifQuery.fillQueryStmt(query, true);
            totalCount = ((Number) query.uniqueResult()).intValue();
            ifPage.setTotalRecordCount(totalCount);
        } else {
            throw new HibernateException("没有找到统计总数的SQL");
        }

        query = em.createNativeQuery(ifQuery.getSQL()).unwrap(NativeQuery.class);
        query.setFirstResult((nPageNum - 1) * nPageSize);
        query.setMaxResults(nPageSize);
        ifQuery.fillEntity(query);
        ifQuery.fillQueryStmt(query, false);
        ifPage.setDataList((List<T>) query.list());
        return ifPage;
    }

    private <T> List<T> execIfHqlQuery(final IfHqlQuery ifQuery) {
        return ifQuery.fillQueryStmt(
                em.createQuery(ifQuery.getHQL()),false).getResultList();
    }


    private <T> List<T> execIfSqlQuery(final IfSqlQuery ifQuery) {
        var query = em.createNativeQuery(ifQuery.getSQL()).unwrap(NativeQuery.class);
        ifQuery.fillEntity(query);
        ifQuery.fillQueryStmt(query,false);
        return query.list();
    }

    public Integer execFoCountSql(final IfSqlQuery ifQuery) {
        int totalCount = 0;
        if (ifQuery.getCountSQL() != null) {
            Query query = em.createNativeQuery(ifQuery.getCountSQL());
            ifQuery.fillQueryStmt(query,false);
            totalCount = ((Number) query.getSingleResult())
                    .intValue();
        }
        return totalCount;
    }

    public Integer execFoCountHql(final IfHqlQuery ifQuery) {
        return ((Long)ifQuery.fillQueryStmt(em.createQuery(ifQuery.getHQL()),false).getSingleResult()).intValue();
    }
}
