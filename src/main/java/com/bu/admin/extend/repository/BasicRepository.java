package com.bu.admin.extend.repository;

import java.util.List;


public interface BasicRepository<T> {

    List<T> find(String hql, Object... params);

    T findEntity(String hql, Object... params);

    int countHql(String hql, Object... params);

    int countSql(String sql, Object... params);
}
