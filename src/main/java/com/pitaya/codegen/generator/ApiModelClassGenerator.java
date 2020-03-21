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
/*     */ public class ApiModelClassGenerator
/*     */   implements BeanGenerator
/*     */ {
/*     */   public static DomainEntity getDomainEntity(String tableName, String entityName, String entityPkg) {
/*  27 */     DomainEntity entity = new DomainEntity();
/*  28 */     entity.setTableName(tableName);
/*  29 */     entity.setClassName(entityName);
/*  30 */     entity.setPkgName(entityPkg);
/*  31 */     entity.setTableRemark(DBHelper.getTableComment(tableName));
/*  32 */     entity.setSimpleTableName(NameRuleUtil.getSimpleTableName(tableName));
/*     */ 
/*     */     
/*  35 */     List<Map<String, Object>> columns = DBHelper.getTableColumns(tableName);
/*     */     
/*  37 */     List<String> primaryKeys = DBHelper.getTablePrimaryKeys(tableName);
/*     */     
/*  39 */     Map<String, String> commentMap = DBHelper.getTableColumnComments(tableName);
/*     */     
/*  41 */     for (Map<String, Object> col : columns) {
/*  42 */       EntityField f = new EntityField();
/*  43 */       f.setColName(col.get("colName").toString());
/*  44 */       f.setName(NameRuleUtil.getClassFieldName(f.getColName()));
/*  45 */       f.setNullable(Boolean.valueOf((Integer.parseInt(col.get("nullable").toString()) > 0)));
/*  46 */       f.setLength(Integer.valueOf(Integer.parseInt(col.get("colSize").toString())));
/*  47 */       f.setAutoIncrement(Boolean.valueOf(Boolean.parseBoolean(col.get("autoIncrement").toString())));
/*  48 */       f.setPrimaryKey(Boolean.valueOf(false));
/*  49 */       f.setComment(commentMap.get(f.getColName()));
/*  50 */       f.setComment((f.getComment() == null) ? "" : f.getComment());
/*  51 */       for (String key : primaryKeys) {
/*  52 */         if (key.equals(f.getColName())) {
/*  53 */           f.setPrimaryKey(Boolean.valueOf(true));
/*  54 */           f.setNullable(Boolean.valueOf(true));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  59 */       Integer colType = Integer.valueOf(Integer.parseInt(col.get("colType").toString()));
/*     */       
/*  61 */       JdbcType jdbcType = JdbcType.forCode(colType.intValue());
/*  62 */       f.setColTypeName(jdbcType.toString());
/*     */       
/*  64 */       Class<?> propType = DefaultJdbcTypeRegistry.getDefaultJavaType(jdbcType);
/*  65 */       if (propType == null) {
/*  66 */         throw new RuntimeException("未知类型:" + f.getColName() + "," + colType);
/*     */       }
/*  68 */       if (propType.getName().contains(".")) {
/*  69 */         entity.addImport(propType.getName());
/*     */       }
/*  71 */       f.setPropType(propType.getSimpleName());
/*  72 */       entity.getFields().add(f);
/*     */       
/*  74 */       if (f.getPrimaryKey().booleanValue()) {
/*  75 */         entity.getPrimaryKeys().add(f);
/*     */       }
/*     */     }
//                if(entity.getPrimaryKeys().size()==0){
//                    EntityField f = new EntityField();
//                    /*  43 */       f.setColName("id");
//                    /*  44 */       f.setName(NameRuleUtil.getClassFieldName(f.getColName()));
//                    /*  45 */       f.setNullable(Boolean.valueOf(false));
//                    /*  46 */       f.setLength(Integer.valueOf(11));
//                    /*  47 */       f.setAutoIncrement(Boolean.valueOf(true));
//                    /*  48 */       f.setPrimaryKey(Boolean.valueOf(false));
//                    /*  49 */       f.setComment(commentMap.get(f.getColName()));
//                    /*  50 */       f.setComment((f.getComment() == null) ? "" : f.getComment());
//
//                    entity.getPrimaryKeys().add(f);
//                }
/*  78 */     if (entity.getPrimaryKeys().size() > 1) {
/*  79 */       entity.setPkClassName(String.valueOf(entity.getClassName()) + ".PK");
/*  80 */     } else if (entity.getPrimaryKeys().size() == 1) {
/*  81 */       entity.setPkClassName(((EntityField)entity.getPrimaryKeys().get(0)).getPropType());
/*     */     } 
/*  83 */     return entity;
/*     */   }
/*     */   
/*     */   public void generate(String tableName, String entityName, String entityPkg) {
/*  87 */     Template tpl = TemplateHelper.getInstance().getTemplate("apimodel.java.tpl");
/*  88 */     DomainEntity entity = getDomainEntity(tableName, entityName, entityPkg);
/*     */     
/*  90 */     StringWriter sw = new StringWriter();
/*     */     
/*     */     try {
/*  93 */       tpl.process(entity, sw);
/*  94 */     } catch (TemplateException e) {
/*     */       
/*  96 */       e.printStackTrace();
/*  97 */     } catch (IOException e) {
/*     */       
/*  99 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 102 */     System.out.println(sw.toString());
/*     */     
/* 104 */     File dstFile = AppContext.getGenerateTargetFile(entity.getPkgName(), String.valueOf(entity.getClassName()) + "DTO.java");
/*     */     
/*     */     try {
/* 107 */       TemplateHelper.write2File(tpl, entity, dstFile);
/* 108 */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */       JOptionPane.showMessageDialog(null, "生成ApiModel异常[tableName:" + tableName + "]:" + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\generator\ApiModelClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */