/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.*;

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
/*    */ public class TemplateHelper
/*    */ {
/*    */   public static final String TPL_DIR = "/template";
/* 26 */   private static TemplateHelper INSTANCE = null;
/*    */   
/*    */   public static TemplateHelper getInstance() {
/* 29 */     if (INSTANCE == null) {
/* 30 */       INSTANCE = new TemplateHelper();
/*    */     }
/* 32 */     return INSTANCE;
/*    */   }
/*    */   
/* 35 */   Configuration cfg = new Configuration();
/*    */ 
/*    */ 
/*    */   
/*    */   private TemplateHelper() {
/* 40 */     this.cfg.setTemplateLoader((TemplateLoader)new ClassTemplateLoader(TemplateHelper.class, "/template"));
/* 41 */     this.cfg.setObjectWrapper((ObjectWrapper)new DefaultObjectWrapper());
/*    */ 
/*    */     
/* 44 */     this.cfg.setDefaultEncoding("UTF-8");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Template getTemplate(String templateName) {
/*    */     try {
/* 51 */       return this.cfg.getTemplate(templateName, "utf-8");
/* 52 */     } catch (IOException e) {
/* 53 */       throw new RuntimeException("获取模板异常[template:" + templateName + "]", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String process(String templateName, Object rootMap) {
/* 59 */     Template tpl = getTemplate(templateName);
/* 60 */     return process(tpl, rootMap);
/*    */   }
/*    */   
/*    */   public String process(Template tpl, Object rootMap) {
/* 64 */     StringWriter sw = new StringWriter();
/*    */     try {
/* 66 */       tpl.process(rootMap, sw);
/*    */     }
/* 68 */     catch (TemplateException e) {
/* 69 */       throw new RuntimeException("模板数数据异常:" + e.getMessage(), (Throwable)e);
/* 70 */     } catch (IOException e) {
/* 71 */       throw new RuntimeException(e);
/*    */     } 
/* 73 */     return sw.toString();
/*    */   }
/*    */   public static void write2File(Template tpl, Object entity, File dstFile) throws TemplateException, IOException {
/* 76 */     OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(dstFile), "utf-8");
/* 77 */     tpl.process(entity, osw);
/*    */   }
/*    */   public static String processStringTemplate(String tplStr, Object root) {
/* 80 */     Configuration cfg = new Configuration();
/* 81 */     StringTemplateLoader stringLoader = new StringTemplateLoader();
/* 82 */     stringLoader.putTemplate("myTemplate", tplStr);
/*    */     try {
/* 84 */       cfg.setTemplateLoader((TemplateLoader)stringLoader);
/* 85 */       Template template = cfg.getTemplate("myTemplate", "utf-8");
/*    */       
/* 87 */       StringWriter writer = new StringWriter();
/* 88 */       template.process(root, writer);
/* 89 */       return writer.toString();
/* 90 */     } catch (Exception e) {
/* 91 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 97 */     Template tpl = getInstance().getTemplate("/notice/ItemHandler_Undertaker_Reviewed_Notice_Self.ftl");
/* 98 */     System.out.println(tpl.getName());
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\TemplateHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */