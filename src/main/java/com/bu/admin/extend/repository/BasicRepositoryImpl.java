package com.bu.admin.extend.repository;

import com.bu.admin.repository.*;

import java.util.List;


public class BasicRepositoryImpl<T> extends IfEntityManagerSupport implements BasicRepository<T> {

    @Override
    public List<T> find(String hql, Object... params) {
        IfHqlQuery query = new IfHqlQuery(hql);
        this.setQueryParams(query, params);
        return this.execIfQuery(query);
    }


    @Override
    public T findEntity(String hql, Object... params) {
        IfHqlQuery query = new IfHqlQuery(hql);
        query.setPageSize(1);
        query.setPageNum(1);
        this.setQueryParams(query, params);
        IfPage<T> ifPage = this.execIfPageQuery(query);
        return ifPage.getDataList() != null && !ifPage.getDataList().isEmpty() ? (T) ifPage.getDataList().getFirst() : null;
    }

    @Override
    public int countHql(String hql, Object... params) {
        IfHqlQuery query = new IfHqlQuery(hql);
        this.setQueryParams(query, params);
        return this.execFoCountHql(query);
    }

    @Override
    public int countSql(String sql, Object... params) {
        IfSqlQuery query = new IfSqlQuery(sql);
        this.setQueryParams(query, params);
        return this.execFoCountSql(query);
    }


    @Override
    public IfPage<T> execIfPageQuery(IfQuery ifQuery) {
        return super.execIfPageQuery(this.resetFoQuery(ifQuery));
    }

    @Override
    public List<T> execIfQuery(IfQuery ifQuery) {
        return super.execIfQuery(this.resetFoQuery(ifQuery));
    }


    private void setQueryParams(IfQuery query, Object... params) {
        for (int i = 0; i < params.length; i++) {
            query.setObject(i, params[i]);
        }
    }

    public IfQuery resetFoQuery(IfQuery foQuery) {
        if (foQuery.getSortFields() != null && foQuery.getSortFields().length > 0) {
            if (foQuery instanceof IfHqlQuery query) {
                query.setHQL(this.setSortFieldsToSQL(query.getHQL(), query.getSortFields()));
                return query;
            }
            if (foQuery instanceof IfSqlQuery query) {
                query.setSQL(this.setSortFieldsToSQL(query.getSQL(), query.getSortFields()));
                return query;
            }
        }
        return foQuery;
    }

    private String setSortFieldsToSQL(String sql, String[] sortFields) {
        if (sql.contains("order by")) {
            sql += ",";
        } else {
            sql += " order by ";
        }
        StringBuilder sqlBuilder = new StringBuilder(sql);
        for (int i = 0; i < sortFields.length; i += 2) {
            sqlBuilder.append(sortFields[i]).append(" ").append(sortFields[i + 1]);
            if (i + 2 < sortFields.length) {
                sqlBuilder.append(",");
            }
        }
        sql = sqlBuilder.toString();
        return sql;
    }

}
