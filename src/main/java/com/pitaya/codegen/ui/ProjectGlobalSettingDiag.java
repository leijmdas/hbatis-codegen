/*     */ package com.pitaya.codegen.ui;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.util.UIUtil;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProjectGlobalSettingDiag
/*     */   extends AbstractDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  26 */   private JTextField tfProjectName = new JTextField();
/*     */   
/*  28 */   private JTextField tfPackageName = new JTextField();
/*     */   
/*  30 */   private JTextField tfEntityPkgName = new JTextField();
/*     */   
/*  32 */   private JTextField tfDaoPkgName = new JTextField();
/*     */   
/*  34 */   private JTextField tfServicePkgName = new JTextField();
/*  35 */   private JTextField tfApiModelPkgName = new JTextField();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private JTextField tfTablePrefix = new JTextField();
/*     */   
/*  42 */   private JTextField tfDestPath = new JTextField();
/*     */   
/*     */   private boolean createFlag = true;
/*     */ 
/*     */   
/*     */   public ProjectGlobalSettingDiag() {
/*  48 */     setTitle("项目信息");
/*  49 */     setSize(600, 330);
/*  50 */     setResizable(false);
/*  51 */     initUI();
/*     */   }
/*     */ 
/*     */   
/*  55 */   public void setCreateFlag(boolean createFlag) { this.createFlag = createFlag; }
/*     */ 
/*     */   
/*     */   private void initUI() {
/*  59 */     Container contentPane = getContentPane();
/*     */     
/*  61 */     Box baseBox = Box.createVerticalBox();
/*  62 */     contentPane.add(baseBox);
/*     */     
/*  64 */     baseBox.setBorder(new EmptyBorder(10, 10, 10, 10));
/*     */ 
/*     */     
/*  67 */     baseBox.add(UIUtil.createField("项目名称", this.tfProjectName));
/*  68 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  70 */     baseBox.add(UIUtil.createField("项目包名", this.tfPackageName));
/*  71 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  73 */     baseBox.add(UIUtil.createField("Entity包名", this.tfEntityPkgName));
/*  74 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  76 */     baseBox.add(UIUtil.createField("Dao包名", this.tfDaoPkgName));
/*  77 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  79 */     baseBox.add(UIUtil.createField("Service包名", this.tfServicePkgName));
/*  80 */     baseBox.add(Box.createVerticalStrut(2));
/*  81 */     baseBox.add(UIUtil.createField("ApiModel包名", this.tfApiModelPkgName));
/*  82 */     baseBox.add(Box.createVerticalStrut(2));
/*  83 */     baseBox.add(UIUtil.createField("表前缀", this.tfTablePrefix));
/*     */     
/*  85 */     baseBox.add(Box.createVerticalStrut(2));
/*  86 */     baseBox.add(UIUtil.createField("生成目录", this.tfDestPath));
/*     */ 
/*     */ 
/*     */     
/*  90 */     Box actionBox = Box.createHorizontalBox();
/*     */     
/*  92 */     JButton btnOk = new JButton("确定");
/*     */     
/*  94 */     actionBox.add(btnOk);
/*     */     
/*  96 */     btnOk.setActionCommand("action.ok");
/*  97 */     btnOk.addActionListener(this);
/*     */     
/*  99 */     baseBox.add(UIUtil.createField("", actionBox));
/*     */   }
/*     */   public void loadConfig() {
/* 102 */     Properties props = new Properties();
/* 103 */     if (!this.createFlag) {
/* 104 */       props = AppContext.getCurrentProjectProps();
/*     */     }
/* 106 */     System.out.println("createFlag " + this.createFlag + " current project props:" + props);
/* 107 */     this.tfProjectName.setText(props.getProperty("project.name", "默认"));
/* 108 */     this.tfPackageName.setText(props.getProperty("project.pkg", "com."));
/* 109 */     this.tfTablePrefix.setText(props.getProperty("project.tablePrefix", "p_"));
/*     */     
/* 111 */     this.tfEntityPkgName.setText(props.getProperty("project.entityPkgTpl"));
/* 112 */     this.tfDaoPkgName.setText(props.getProperty("project.daoPkgTpl"));
/* 113 */     this.tfServicePkgName.setText(props.getProperty("project.servicePkgTpl"));
/* 114 */     this.tfApiModelPkgName.setText(props.getProperty("project.apiModelPkgTpl"));
/* 115 */     this.tfDestPath.setText(props.getProperty("project.destDir", ""));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 120 */     String cmd = e.getActionCommand();
/*     */     
/* 122 */     if ("action.ok".equals(cmd)) {
/* 123 */       Properties props = new Properties();
/* 124 */       props.put("project.name", this.tfProjectName.getText().trim());
/* 125 */       props.put("project.pkg", this.tfPackageName.getText().trim());
/* 126 */       props.put("project.tablePrefix", this.tfTablePrefix.getText().trim());
/* 127 */       props.put("project.entityPkgTpl", this.tfEntityPkgName.getText().trim());
/* 128 */       props.put("project.daoPkgTpl", this.tfDaoPkgName.getText().trim());
/* 129 */       props.put("project.servicePkgTpl", this.tfServicePkgName.getText().trim());
/* 130 */       props.put("project.apiModelPkgTpl", this.tfApiModelPkgName.getText().trim());
/* 131 */       props.put("project.destDir", this.tfDestPath.getText().trim());
/*     */       
/* 133 */       if (StringUtils.isEmpty(props.getProperty("project.name"))) {
/* 134 */         JOptionPane.showMessageDialog(null, "项目名称不能为空");
/*     */         return;
/*     */       } 
/* 137 */       AppContext.saveProjectProperties(props, props.getProperty("project.name"));
/* 138 */       setVisible(false);
/* 139 */       JOptionPane.showMessageDialog(null, "保存成功");
/*     */       
/* 141 */       AppContext.getApp().refreshProject(props.getProperty("project.name"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\ProjectGlobalSettingDiag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */