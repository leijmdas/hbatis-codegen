/*    */ package com.pitaya.codegen.generator;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.model.DaoEntity;
import com.pitaya.codegen.model.DomainEntity;
import com.pitaya.codegen.model.ServiceEntity;
import com.pitaya.codegen.model.ServiceImplEntity;
import com.pitaya.codegen.util.NameRuleUtil;
import com.pitaya.codegen.util.TemplateHelper;
import freemarker.template.Template;

import javax.swing.*;
import java.io.File;

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
/*    */ public class ServiceClassGenerator
/*    */ {
/*    */   public static ServiceEntity getServiceEntity(DaoEntity daoEntity, String servicePkg) {
/* 21 */     DomainEntity domainEntity = daoEntity.getDomainEntity();
/* 22 */     ServiceEntity serviceEntity = new ServiceEntity();
/* 23 */     serviceEntity.setPkgName(servicePkg);
/* 24 */     serviceEntity.setClassName(NameRuleUtil.getServiceClassName(daoEntity.getDomainEntity().getClassName()));
/* 25 */     serviceEntity.setDomainEntity(daoEntity.getDomainEntity());
/* 26 */     serviceEntity.addImport(domainEntity.getFullname());
/* 27 */     serviceEntity.addImport("java.util.List");
/* 28 */     return serviceEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void generate(String tableName, String entityName, String entityPkg, String daoPkg, String servicePkg) {
/* 33 */     Template tpl = TemplateHelper.getInstance().getTemplate("service.java.tpl");
/*    */     
/* 35 */     DomainEntity domainEntity = EntityClassGenerator.getDomainEntity(tableName, entityName, entityPkg);
/*    */     
/* 37 */     DaoEntity daoEntity = MapperClassGenerator.getDaoEntity(domainEntity, daoPkg);
/*    */     
/* 39 */     ServiceEntity serviceEntity = getServiceEntity(daoEntity, servicePkg);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     File dstFile = AppContext.getGenerateTargetFile(serviceEntity.getPkgName(), String.valueOf(serviceEntity.getClassName()) + ".java");
/*    */     
/*    */     try {
/* 48 */       TemplateHelper.write2File(tpl, serviceEntity, dstFile);
/* 49 */     } catch (Exception e) {
/* 50 */       e.printStackTrace();
/* 51 */       JOptionPane.showMessageDialog(null, "生成Service异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */     
/* 54 */     ServiceImplEntity entity = new ServiceImplEntity();
/* 55 */     entity.setClassName(String.valueOf(serviceEntity.getClassName()) + "Impl");
/* 56 */     entity.setPkgName(String.valueOf(serviceEntity.getPkgName()) + ".impl");
/* 57 */     entity.setServiceEntity(serviceEntity);
/* 58 */     entity.setDomainEntity(domainEntity);
/* 59 */     entity.setDaoEntity(daoEntity);
/* 60 */     entity.addImport("java.util.List");
/* 61 */     entity.addImport("org.springframework.beans.factory.annotation.Autowired");
/* 62 */     entity.addImport("org.springframework.stereotype.Service");
/*    */     
/* 64 */     entity.addImport(String.valueOf(entityPkg) + "." + domainEntity.getClassName());
/* 65 */     entity.addImport(String.valueOf(daoEntity.getPkgName()) + "." + daoEntity.getClassName());
/* 66 */     entity.addImport(String.valueOf(serviceEntity.getPkgName()) + "." + serviceEntity.getClassName());
/* 67 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".orm.criteria.statement.SelectStatement");
/* 68 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".orm.criteria.statement.UpdateStatement");
/* 69 */     entity.addImport(String.valueOf(NameRuleUtil.BASE_PKGNAME) + ".orm.criteria.support.StatementBuilder");
/*    */     
/* 71 */     tpl = TemplateHelper.getInstance().getTemplate("service.impl.java.tpl");
/*    */     
/* 73 */     dstFile = AppContext.getGenerateTargetFile(entity.getPkgName(), String.valueOf(entity.getClassName()) + ".java");
/*    */     
/*    */     try {
/* 76 */       if (!dstFile.exists()) {
/* 77 */         dstFile.createNewFile();
/*    */       }
/* 79 */       TemplateHelper.write2File(tpl, entity, dstFile);
/* 80 */     } catch (Exception e) {
/* 81 */       e.printStackTrace();
/* 82 */       JOptionPane.showMessageDialog(null, "生成ServiceImpl异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\generator\ServiceClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */