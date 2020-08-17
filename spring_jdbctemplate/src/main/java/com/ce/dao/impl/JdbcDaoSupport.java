package com.ce.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 此类用于抽取dao中的重复代码
 */
// public class JdbcDaoSupport {
//
//     private JdbcTemplate jdbcTemplate;
//
//     public JdbcTemplate getJdbcTemplate() {
//         return jdbcTemplate;
//     }
//
//     @Autowired
//     public void setDataSource(DataSource dataSource) {
//         if (jdbcTemplate == null) {
//             jdbcTemplate = createJdbcTemplate(dataSource);
//         }
//     }
//
//     private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
//         return new JdbcTemplate(dataSource);
//     }
// }
