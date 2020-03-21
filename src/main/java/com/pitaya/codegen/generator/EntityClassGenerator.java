/*     */ package com.pitaya.codegen.generator;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.model.DomainEntity;
import com.pitaya.codegen.model.EntityField;
import com.pitaya.codegen.util.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

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
/*     */ public class EntityClassGenerator
/*     */   implements BeanGenerator
/*     */ {
/*     */   public static DomainEntity getDomainEntity(String tableName, String entityName, String entityPkg) {
/*  27 */     DomainEntity entity = new DomainEntity();
/*  28 */     entity.setTableName(tableName);
/*  29 */     entity.setClassName(entityName);
/*  30 */     entity.setPkgName(entityPkg);
/*  31 */     entity.setSimpleTableName(NameRuleUtil.getSimpleTableName(tableName));
/*  32 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".core.type.JdbcType");
/*  33 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".core.annotation.*");
/*  34 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".core.*");
/*     */     
/*  36 */     List<Map<String, Object>> columns = DBHelper.getTableColumns(tableName);
/*     */     
/*  38 */     List<String> primaryKeys = DBHelper.getTablePrimaryKeys(tableName);
/*     */     
/*  40 */     Map<String, String> commentMap = DBHelper.getTableColumnComments(tableName);
/*     */     
/*  42 */     for (Map<String, Object> col : columns) {
/*  43 */       EntityField f = new EntityField();
/*  44 */       f.setColName(col.get("colName").toString());
/*  45 */       f.setName(NameRuleUtil.getClassFieldName(f.getColName()));
/*  46 */       f.setNullable(Boolean.valueOf((Integer.parseInt(col.get("nullable").toString()) > 0)));
/*  47 */       f.setLength(Integer.valueOf(Integer.parseInt(col.get("colSize").toString())));
/*  48 */       f.setAutoIncrement(Boolean.valueOf(Boolean.parseBoolean(col.get("autoIncrement").toString())));
/*  49 */       f.setPrimaryKey(Boolean.valueOf(false));
/*  50 */       f.setComment(commentMap.get(f.getColName()));
/*  51 */       f.setComment((f.getComment() == null) ? "" : f.getComment());
/*  52 */       for (String key : primaryKeys) {
/*  53 */         if (key.equals(f.getColName())) {
/*  54 */           f.setPrimaryKey(Boolean.valueOf(true));
/*  55 */           f.setNullable(Boolean.valueOf(true));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  60 */       Integer colType = Integer.valueOf(Integer.parseInt(col.get("colType").toString()));
/*     */       
/*  62 */       JdbcType jdbcType = JdbcType.forCode(colType.intValue());
/*  63 */       f.setColTypeName(jdbcType.toString());
/*     */       
/*  65 */       Class<?> propType = DefaultJdbcTypeRegistry.getDefaultJavaType(jdbcType);
/*  66 */       if (propType == null) {
/*  67 */         throw new RuntimeException("未知类型:" + f.getColName() + "," + colType);
/*     */       }
/*  69 */       if (propType.getName().contains(".")) {
/*  70 */         entity.addImport(propType.getName());
/*     */       }
/*  72 */       f.setPropType(propType.getSimpleName());
/*  73 */       entity.getFields().add(f);
/*     */       
/*  75 */       if (f.getPrimaryKey().booleanValue()) {
/*  76 */         entity.getPrimaryKeys().add(f);
/*     */       }
/*     */     } 
/*  79 */     if (entity.getPrimaryKeys().size() > 1) {
/*  80 */       entity.setPkClassName(String.valueOf(entity.getClassName()) + ".PK");
/*  81 */     } else if (entity.getPrimaryKeys().size() == 1) {
/*  82 */       entity.setPkClassName(((EntityField)entity.getPrimaryKeys().get(0)).getPropType());
/*     */     } 
/*  84 */     return entity;
/*     */   }
/*     */   
/*     */   public void generate(String tableName, String entityName, String entityPkg) {
/*  88 */     Template tpl = TemplateHelper.getInstance().getTemplate("entity.java.tpl");
/*  89 */     DomainEntity entity = getDomainEntity(tableName, entityName, entityPkg);
/*  90 */     entity.setTableRemark(DBHelper.getTableComment(entity.getTableName()));
/*  91 */     StringWriter sw = new StringWriter();
/*     */     
/*     */     try {
/*  94 */       tpl.process(entity, sw);
/*  95 */     } catch (TemplateException e) {
/*     */       
/*  97 */       e.printStackTrace();
/*  98 */     } catch (IOException e) {
/*     */       
/* 100 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 103 */     System.out.println(sw.toString());
/*     */     
/* 105 */     File dstFile = AppContext.getGenerateTargetFile(entity.getPkgName(), String.valueOf(entity.getClassName()) + ".java");
/*     */     
/*     */     try {
/* 108 */       TemplateHelper.write2File(tpl, entity, dstFile);
/* 109 */     } catch (Exception e) {
/* 110 */       e.printStackTrace();
/* 111 */       JOptionPane.showMessageDialog(null, "生成Entity异常[tableName:" + tableName + "]:" + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\generator\EntityClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */