/*     */ package com.pitaya.codegen.util;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.model.TableDTO;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DBHelper
/*     */ {
/*  27 */   private static DataSource instance = null;
/*     */   
/*  29 */   private static Map<String, TableDTO> tableMap = new HashMap<>();
/*     */   public static DataSource createDataSource(String host, String username, String password, String dbName, Integer port) {
/*  31 */     BasicDataSource ds = new BasicDataSource();
/*  32 */     ds.setUsername(username);
/*  33 */     ds.setPassword(password);
/*  34 */     if (port.intValue() == 3306) {
/*  35 */       ds.setDriverClassName("com.mysql.jdbc.Driver");
/*  36 */       ds.setValidationQuery("select 1");
/*  37 */       ds.setUrl("jdbc:mysql://" + host + ":" + port + "/" + dbName + "?autoReconnect=true&autoReconnectForPools=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useInformationSchema=true");
/*     */     } else {
/*  39 */       ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
/*     */       
/*  41 */       ds.setUrl("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=" + host + ")(PORT=" + port + "))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=" + dbName + ")))");
/*     */     } 
/*     */     
/*  44 */     return (DataSource)ds;
/*     */   }
/*     */   
/*     */   public static DataSource getDataSource() {
/*  48 */     if (instance != null) {
/*  49 */       return instance;
/*     */     }
/*  51 */     Properties props = AppContext.getProps();
/*  52 */     String instanceName = props.getProperty("db.instanceName", "");
/*  53 */     String host = props.getProperty("db.host", "localhost");
/*  54 */     String username = props.getProperty("db.username", "root");
/*  55 */     String password = props.getProperty("db.password", "");
/*  56 */     String port = props.getProperty("db.port", "3306");
/*  57 */     instance = createDataSource(host, username, password, instanceName, Integer.valueOf(Integer.parseInt(port)));
/*  58 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*  62 */   public static JdbcTemplate getJdbcTemplate() { return new JdbcTemplate(getDataSource()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<TableDTO> getTables() {
/*  67 */     JdbcTemplate tpl = getJdbcTemplate();
/*  68 */     List<TableDTO> tables = (List<TableDTO>)tpl.execute(new ConnectionCallback<List<TableDTO>>()
/*     */         {
/*     */           public List<TableDTO> doInConnection(Connection conn) throws SQLException, DataAccessException
/*     */           {
/*  72 */             List<TableDTO> tables = new ArrayList<>();
/*  73 */             DatabaseMetaData metaData = conn.getMetaData();
/*  74 */             ResultSet rs = metaData.getTables(conn.getCatalog(), null, null, new String[] { "TABLE" });
/*  75 */             while (rs.next()) {
/*  76 */               TableDTO t = new TableDTO();
/*  77 */               t.setName(rs.getString("TABLE_NAME"));
/*  78 */               t.setRemark(rs.getString("REMARKS"));
/*  79 */               tables.add(t);
/*     */               
/*  81 */               tableMap.put(t.getName(), t);
/*     */             }
                 rs = metaData.getTables(conn.getCatalog(), null, null, new String[] { "VIEW" });
    /*  75 */             while (rs.next()) {
        /*  76 */               TableDTO t = new TableDTO();
        /*  77 */               t.setName(rs.getString("TABLE_NAME"));
        /*  78 */               t.setRemark(rs.getString("REMARKS"));
        /*  79 */               tables.add(t);
        /*     */
        /*  81 */               tableMap.put(t.getName(), t);
        /*     */             }
    /*  83 */             return tables;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  88 */     return tables;
/*     */   }
/*     */   public static List<Map<String, Object>> getTableColumns(String tableName) {
/*  91 */     JdbcTemplate tpl = getJdbcTemplate();
/*  92 */     return (List<Map<String, Object>>)tpl.query("select * from " + tableName + " limit 0", new ResultSetExtractor<List<Map<String, Object>>>()
/*     */         {
/*     */           public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException
/*     */           {
/*  96 */             ResultSetMetaData rsmd = rs.getMetaData();
/*     */             
/*  98 */             List<Map<String, Object>> result = new ArrayList<>();
/*     */             
/* 100 */             for (int i = 1; i <= rsmd.getColumnCount(); i++) {
/* 101 */               Map<String, Object> meta = new HashMap<>();
/* 102 */               meta.put("colName", rsmd.getColumnName(i));
/* 103 */               meta.put("colTypeName", rsmd.getColumnTypeName(i));
/* 104 */               meta.put("colType", Integer.valueOf(rsmd.getColumnType(i)));
/* 105 */               meta.put("colSize", Integer.valueOf(rsmd.getColumnDisplaySize(i)));
/* 106 */               meta.put("precision", Integer.valueOf(rsmd.getPrecision(i)));
/* 107 */               meta.put("scale", Integer.valueOf(rsmd.getScale(i)));
/* 108 */               meta.put("nullable", Integer.valueOf(rsmd.isNullable(i)));
/* 109 */               meta.put("autoIncrement", Boolean.valueOf(rsmd.isAutoIncrement(i)));
/* 110 */               System.out.println(meta);
/* 111 */               result.add(meta);
/*     */             } 
/* 113 */             return result;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getTablePrimaryKeys(final String tableName) {
/* 121 */     JdbcTemplate tpl = getJdbcTemplate();
/* 122 */     return (List<String>)tpl.execute(new ConnectionCallback<List<String>>()
/*     */         {
/*     */           public List<String> doInConnection(Connection conn) throws SQLException, DataAccessException
/*     */           {
/* 126 */             List<String> result = new ArrayList<>();
/* 127 */             DatabaseMetaData dbmd = conn.getMetaData();
/* 128 */             ResultSet rs = dbmd.getPrimaryKeys(null, null, tableName);
/* 129 */             while (rs.next()) {
/* 130 */               result.add(rs.getString("COLUMN_NAME"));
/*     */             }
/*     */             if(result.size()==0){
                            result.add("id");
                        }
/* 133 */             return result;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, String> getTableColumnComments(final String tableName) {
/* 141 */     JdbcTemplate tpl = getJdbcTemplate();
/* 142 */     return (Map<String, String>)tpl.execute(new ConnectionCallback<Map<String, String>>()
/*     */         {
/*     */           public Map<String, String> doInConnection(Connection conn) throws SQLException, DataAccessException
/*     */           {
/* 146 */             DatabaseMetaData dbmd = conn.getMetaData();
/* 147 */             ResultSet rs = dbmd.getTables(null, "%", String.valueOf(tableName) + "%", new String[] { "TABLE" });
/* 148 */             Map<String, String> result = new HashMap<>();
/*     */             
/* 150 */             while (rs.next()) {
/* 151 */               String tableName = rs.getString("TABLE_NAME");
/* 152 */               if (tableName.equals(tableName)) {
/* 153 */                 ResultSet rs2 = dbmd.getColumns(null, "%", tableName, "%");
/* 154 */                 while (rs2.next()) {
/* 155 */                   result.put(rs2.getString("COLUMN_NAME"), rs2.getString("REMARKS"));
/*     */                 }
/*     */               } 
/*     */             } 
/* 159 */             return result;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static void reset() { instance = null; }
/*     */ 
/*     */   
/*     */   public static String getTableComment(String tableName) {
/* 170 */     if (!tableMap.containsKey(tableName)) {
/* 171 */       getTables();
/*     */     }
/* 173 */     TableDTO table = tableMap.get(tableName);
/* 174 */     if (table != null) {
/* 175 */       return table.getRemark();
/*     */     }
/* 177 */     return null;
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\DBHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */