/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

import java.sql.JDBCType;
import java.util.HashMap;
import java.util.Map;

/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum JdbcType
/*    */ {
/* 25 */   ARRAY(
/*    */ 
/*    */ 
/*    */     
/* 29 */     2003),
/* 30 */   BIT(-7),
/* 31 */   TINYINT(-6),
/* 32 */   SMALLINT(5),
/* 33 */   INTEGER(4),
/* 34 */   BIGINT(-5),
/* 35 */   FLOAT(6),
/* 36 */   REAL(7),
/* 37 */   DOUBLE(8),
/* 38 */   NUMERIC(2),
/* 39 */   DECIMAL(3),
/* 40 */   CHAR(1),
/* 41 */   VARCHAR(12),
/* 42 */   LONGVARCHAR(-1),
/* 43 */   DATE(91),
/* 44 */   TIME(92),
/* 45 */   TIMESTAMP(93),
/* 46 */   BINARY(-2),
/* 47 */   VARBINARY(-3),
/* 48 */   LONGVARBINARY(-4),
/* 49 */   NULL(0),
/* 50 */   OTHER(1111),
/* 51 */   BLOB(2004),
/* 52 */   CLOB(2005),
/* 53 */   BOOLEAN(16),
/* 54 */   CURSOR(-10),
/* 55 */   UNDEFINED(-2147482648),
/* 56 */   NVARCHAR(-9),
/* 57 */   NCHAR(-15),
/* 58 */   NCLOB(2011),
/* 59 */   STRUCT(2002); public final int TYPE_CODE;
/*    */    private static Map<Integer, JdbcType> codeLookup;
    /* 71 */
    /*    */   static  {
/* 62 */     codeLookup = new HashMap<>(); byte b;
/*    */     int i;
/*    */     JdbcType[] arrayOfJdbcType;
/* 65 */     for (i = (arrayOfJdbcType = values()).length, b = 0; b < i; ) { JdbcType type = arrayOfJdbcType[b];
/* 66 */       codeLookup.put(Integer.valueOf(type.TYPE_CODE), type);
/*    */       b++; }
/*    */   
/*    */   }
/*    */    JdbcType(int code) { this.TYPE_CODE = code; }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public static JdbcType forCode(int code) { return codeLookup.get(Integer.valueOf(code)); }
            public static void main(String[] args){
                System.out.println(codeLookup );
                System.out.println(DefaultJdbcTypeRegistry.getDefaultJavaType(JdbcType.INTEGER));
            }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\JdbcType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */