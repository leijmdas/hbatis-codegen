/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;

import java.util.regex.Pattern;

/*    */

/*    */
/*    */ 
/*    */ public class NameRuleUtil
/*    */ {
/*  9 */   public static String BASE_PKGNAME = "org.mybatis.hbatis";
/*    */ 
/*    */   
/* 12 */   public static String getDaoClassName(String entityName) { return String.valueOf(entityName) + "Mapper"; }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public static String getServiceClassName(String entityName) { return String.valueOf(entityName) + "Service"; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public static String getControllerClassName(String entityName) { return String.valueOf(entityName) + "Controller"; }
/*    */ 
/*    */   
/*    */   public static String getSimpleTableName(String tableName) {
/* 24 */     String[] arr = tableName.split("\\_");
/* 25 */     StringBuilder sb = new StringBuilder(); byte b; int i; String[] arrayOfString;
/* 26 */     for (i = (arrayOfString = arr).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 27 */       sb.append(s.charAt(0)); b++; }
/*    */     
/* 29 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getEntityClassName(String tableName) {
/* 34 */     String[] arr = tableName.replaceFirst("^" + AppContext.getCurrentProjectProps().getProperty("project.tablePrefix", ""), "").split("_");
/* 35 */     StringBuilder sb = new StringBuilder(); byte b; int i; String[] arrayOfString;
/* 36 */     for (i = (arrayOfString = arr).length, b = 0; b < i; ) { String seg = arrayOfString[b];
/* 37 */       if (seg.length() >= 1)
/*    */       {
/*    */         
/* 40 */         sb.append(seg.substring(0, 1).toUpperCase()).append(seg.substring(1)); }  b++; }
/*    */     
/* 42 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getClassFieldName(String colName) {
/* 47 */     if (colName.toUpperCase().equals(colName)) {
/* 48 */       return colName.toLowerCase();
/*    */     }
/* 50 */     String[] arr = colName.split("_");
/* 51 */     StringBuilder sb = new StringBuilder();
/* 52 */     int index = 0; byte b; int i; String[] arrayOfString;
/* 53 */     for (i = (arrayOfString = arr).length, b = 0; b < i; ) { String seg = arrayOfString[b];
/* 54 */       if (index == 0) {
/* 55 */         sb.append(seg.substring(0, 1).toLowerCase()).append(seg.substring(1));
/*    */       } else {
/* 57 */         sb.append(seg.substring(0, 1).toUpperCase()).append(seg.substring(1));
/*    */       } 
/*    */       
/* 60 */       index++;
/*    */       b++; }
/*    */     
/* 63 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public static String getClassFieldMethodName(String fieldName) {
/* 67 */     if (fieldName != null && fieldName.length() > 1) {
/* 68 */       String tmp = fieldName.substring(0, 2);
/* 69 */       Pattern p = Pattern.compile("[A-Z]", 32);
/* 70 */       if (p.matcher(tmp).find()) {
/* 71 */         return fieldName;
/*    */       }
/*    */     } 
/* 74 */     String[] arr = fieldName.split("_");
/* 75 */     StringBuilder sb = new StringBuilder(); byte b; int i; String[] arrayOfString;
/* 76 */     for (i = (arrayOfString = arr).length, b = 0; b < i; ) { String seg = arrayOfString[b];
/* 77 */       sb.append(seg.substring(0, 1).toUpperCase()).append(seg.substring(1));
/*    */       b++; }
/*    */     
/* 80 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\NameRuleUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */