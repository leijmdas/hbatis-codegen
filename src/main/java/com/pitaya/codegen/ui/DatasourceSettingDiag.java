/*     */ package com.pitaya.codegen.ui;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.action.DataSourceSettingActionListener;
import com.pitaya.codegen.util.UIUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
/*     */ public class DatasourceSettingDiag
/*     */   extends AbstractDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   private DataSourceSettingActionListener actionListener = new DataSourceSettingActionListener(this);
/*     */   
/*  24 */   protected JTextField tfHost = new JTextField();
/*  25 */   protected JTextField tfUsername = new JTextField();
/*  26 */   protected JTextField tfPassword = new JTextField();
/*  27 */   protected JTextField tfDbInstance = new JTextField();
/*  28 */   protected JTextField tfPort = new JTextField();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DatasourceSettingDiag() {
/*  34 */     setTitle("数据源设置");
/*  35 */     setSize(400, 230);
/*  36 */     setResizable(false);
/*  37 */     initUI();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initUI() {
/*  42 */     Container contentPane = getContentPane();
/*     */     
/*  44 */     Box baseBox = Box.createVerticalBox();
/*  45 */     contentPane.add(baseBox);
/*     */ 
/*     */ 
/*     */     
/*  49 */     baseBox.setBorder(new EmptyBorder(10, 10, 10, 10));
/*  50 */     baseBox.add(UIUtil.createField("主机地址", this.tfHost));
/*  51 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  53 */     baseBox.add(UIUtil.createField("用户名", this.tfUsername));
/*  54 */     baseBox.add(Box.createVerticalStrut(2));
/*  55 */     baseBox.add(UIUtil.createField("密码", this.tfPassword));
/*  56 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  58 */     baseBox.add(UIUtil.createField("实例名", this.tfDbInstance));
/*     */     
/*  60 */     baseBox.add(Box.createVerticalStrut(2));
/*  61 */     baseBox.add(UIUtil.createField("端口", this.tfPort));
/*     */ 
/*     */     
/*  64 */     baseBox.add(Box.createVerticalStrut(5));
/*     */ 
/*     */     
/*  67 */     Box actionBox = Box.createHorizontalBox();
/*     */     
/*  69 */     JButton btnOk = new JButton("保存");
/*     */     
/*  71 */     JButton btnTest = new JButton("测试连接");
/*  72 */     actionBox.add(btnOk);
/*  73 */     actionBox.add(btnTest);
/*     */     
/*  75 */     btnOk.setActionCommand("action.ok");
/*  76 */     btnTest.setActionCommand("action.test");
/*     */     
/*  78 */     btnOk.addActionListener((ActionListener)this.actionListener);
/*  79 */     btnTest.addActionListener((ActionListener)this.actionListener);
/*     */     
/*  81 */     baseBox.add(UIUtil.createField("", actionBox));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public JTextField getTfHost() { return this.tfHost; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public JTextField getTfUsername() { return this.tfUsername; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public JTextField getTfPassword() { return this.tfPassword; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public JTextField getTfDbInstance() { return this.tfDbInstance; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public JTextField getTfPort() { return this.tfPort; }
/*     */ 
/*     */   
/*     */   public void loadConfig() {
/* 108 */     Properties props = AppContext.getProps();
/* 109 */     this.tfDbInstance.setText(props.getProperty("db.instanceName", ""));
/* 110 */     this.tfHost.setText(props.getProperty("db.host", "localhost"));
/* 111 */     this.tfUsername.setText(props.getProperty("db.username", "root"));
/* 112 */     this.tfPassword.setText(props.getProperty("db.password", ""));
/* 113 */     this.tfPort.setText(props.getProperty("db.port", "3306"));
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\DatasourceSettingDiag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */