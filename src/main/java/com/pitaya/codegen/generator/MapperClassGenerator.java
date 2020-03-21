/*    */ package com.pitaya.codegen.generator;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.model.DaoEntity;
import com.pitaya.codegen.model.DomainEntity;
import com.pitaya.codegen.util.NameRuleUtil;
import com.pitaya.codegen.util.TemplateHelper;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;

import javax.swing.*;
import java.io.File;
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
/*    */ public class MapperClassGenerator
/*    */ {
/*    */   public static DaoEntity getDaoEntity(DomainEntity domainEntity, String daoPkg) {
/* 21 */     DaoEntity daoEntity = new DaoEntity();
/* 22 */     daoEntity.setPkgName(daoPkg);
/* 23 */     daoEntity.setClassName(NameRuleUtil.getDaoClassName(domainEntity.getClassName()));
/* 24 */     daoEntity.setDomainEntity(domainEntity);
/* 25 */     daoEntity.addImport("java.util.List");
/* 26 */     daoEntity.addImport("org.apache.ibatis.annotations.Param");
/* 27 */     daoEntity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".orm.mapper.HbatisMapper");
/* 28 */     daoEntity.addImport(String.valueOf(domainEntity.getPkgName()) + "." + domainEntity.getClassName());
/*    */     
/* 30 */     return daoEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(String tableName, String entityName, String entityPkg, String daoPkg) {
/* 35 */     Template tpl = TemplateHelper.getInstance().getTemplate("dao.java.tpl");
/*    */     
/* 37 */     DomainEntity domainEntity = EntityClassGenerator.getDomainEntity(tableName, entityName, entityPkg);
/*    */     
/* 39 */     DaoEntity daoEntity = getDaoEntity(domainEntity, daoPkg);
/*    */ 
/*    */ 
/*    */     
/* 43 */     DaoEntity entity = daoEntity;
/* 44 */     File dstFile = AppContext.getGenerateTargetFile(entity.getPkgName(), String.valueOf(entity.getClassName()) + ".java");
/*    */     
/*    */     try {
/* 47 */       TemplateHelper.write2File(tpl, entity, dstFile);
/* 48 */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       JOptionPane.showMessageDialog(null, "生成DAO异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */ 
/*    */     
/* 54 */     tpl = TemplateHelper.getInstance().getTemplate("dao.xml.tpl");
/* 55 */     dstFile = AppContext.getGenerateTargetFile("mapper", String.valueOf(daoEntity.getClassName()) + ".xml");
/*    */     
/* 57 */     Map<String, Object> root = new HashMap<>();
/* 58 */     root.put("namespace", daoEntity.getFullname());
/* 59 */     root.put("domainEntity", domainEntity);
/*    */ 
/*    */     
/* 62 */     BeansWrapper beansWrapper = new BeansWrapper();
/*    */ 
/*    */     
/* 65 */     beansWrapper.setExposeFields(true);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 71 */       root.put("NameRuleUtil", beansWrapper.getStaticModels().get("com.pitaya.codegen.util.NameRuleUtil"));
/* 72 */       TemplateHelper.write2File(tpl, root, dstFile);
/* 73 */     } catch (Exception e) {
/* 74 */       e.printStackTrace();
/* 75 */       JOptionPane.showMessageDialog(null, "生成DAO文件异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\generator\MapperClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */