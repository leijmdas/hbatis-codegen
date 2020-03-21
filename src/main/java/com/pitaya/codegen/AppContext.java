/*     */ package com.pitaya.codegen;
/*     */ 
/*     */

import com.pitaya.codegen.ui.MainFrame;
import com.pitaya.codegen.util.DBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
/*     */ public class AppContext
/*     */ {
/*     */   private static MainFrame app;
/*  19 */   private static Properties props = new Properties();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   private static Map<String, Properties> projPropertyMap = new HashMap<>();
/*     */   
/*  26 */   private static String currentProjectName = null;
/*     */   
/*  28 */   public static void setApp(MainFrame app) { AppContext.app = app; }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static MainFrame getApp() { return app; }
/*     */ 
/*     */   
/*     */   public static <T extends BasePanel> T activePanel(Class<T> panel) {
/*  36 */     app.getWorkspacePanel().removeAll();
/*  37 */     app.getWorkspacePanel().updateUI();
/*     */     try {
/*  39 */       BasePanel basePanel = (BasePanel)panel.newInstance();
/*  40 */       app.getWorkspacePanel().add(basePanel, "Center");
/*  41 */       app.getWorkspacePanel().updateUI();
/*  42 */       return (T)basePanel;
/*  43 */     } catch (Exception e) {
/*  44 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() throws IOException {
/*  52 */     File cf = new File("./codegen.properties");
/*  53 */     if (!cf.exists()) {
/*  54 */       cf.createNewFile();
/*     */     }
/*  56 */     props.load(new FileInputStream(cf));
/*     */     
/*  58 */     File hbc = new File("./默认.hbc");
/*  59 */     if (!cf.exists()) {
/*  60 */       cf.createNewFile();
/*     */     }
/*  62 */     Properties pProps = new Properties();
/*  63 */     pProps.load(new FileInputStream(hbc));
/*     */     
/*  65 */     String pname = pProps.getProperty("project.name", "默认");
/*  66 */     System.out.println(pProps);
/*  67 */     saveProjectProperties(pProps, pname);
/*     */   }
/*     */   
/*     */   public static void saveProperties(Properties props) {
/*  71 */     AppContext.props.putAll(props);
/*     */     try {
/*  73 */       FileOutputStream out = new FileOutputStream(new File("./codegen.properties"));
/*  74 */       AppContext.props.store(out, "配置文件");
/*  75 */       out.close();
/*  76 */     } catch (IOException e) {
/*  77 */       throw new RuntimeException("持久化配置异常:" + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   public static void saveProjectProperties(Properties props, String project) {
/*  81 */     setCurrentProject(project);
/*  82 */     Properties projProps = projPropertyMap.get(project);
/*  83 */     if (projProps == null) {
/*  84 */       projProps = new Properties();
/*  85 */       projPropertyMap.put(project, props);
/*     */     } 
/*  87 */     projProps.putAll(props);
/*     */     
/*     */     try {
/*  90 */       FileOutputStream out = new FileOutputStream(new File("./" + project + ".hbc"));
/*  91 */       projProps.store(out, "配置文件");
/*  92 */       out.close();
/*  93 */     } catch (IOException e) {
/*  94 */       throw new RuntimeException("持久化配置异常:" + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*  98 */   public static Properties getProps() { return props; }
/*     */ 
/*     */   
/*     */   public static File getGenerateTargetFile(String pkg, String fileName) {
/* 102 */     File dir = new File(getCurrentProjectProps().getProperty("project.destDir", "./generate"));
/* 103 */     String[] arr = pkg.split("\\."); byte b; int i; String[] arrayOfString;
/* 104 */     for (i = (arrayOfString = arr).length, b = 0; b < i; ) { String tmp = arrayOfString[b];
/* 105 */       dir = new File(dir, tmp);
/* 106 */       if (!dir.exists())
/* 107 */         dir.mkdir(); 
/*     */       b++; }
/*     */     
/* 110 */     return new File(dir, fileName);
/*     */   }
/*     */ 
/*     */   
/* 114 */   public static void flush() { DBHelper.reset(); }
/*     */ 
/*     */   
/*     */   public static Properties getCurrentProjectProps() {
/* 118 */     Properties projProps = projPropertyMap.get(getCurrentProject());
/* 119 */     if (projProps == null) {
/* 120 */       projProps = new Properties();
/*     */     }
/*     */     
/* 123 */     return projProps;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public static String getCurrentProject() { return currentProjectName; }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public static void setCurrentProject(String projName) { currentProjectName = projName; }
/*     */ 
/*     */   
/*     */   public static void loadProjectProperties(File file) {
/*     */     try {
/* 137 */       Properties props = getCurrentProjectProps();
/* 138 */       props.load(new FileInputStream(file));
/* 139 */       String projectName = props.getProperty("project.name");
/* 140 */       saveProjectProperties(props, projectName);
/* 141 */       setCurrentProject(projectName);
/* 142 */     } catch (Exception e) {
/* 143 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\AppContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */