/*     */ package com.pitaya.codegen.ui;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.generator.ApiModelClassGenerator;
import com.pitaya.codegen.generator.EntityClassGenerator;
import com.pitaya.codegen.generator.MapperClassGenerator;
import com.pitaya.codegen.generator.ServiceClassGenerator;
import com.pitaya.codegen.util.NameRuleUtil;
import com.pitaya.codegen.util.TemplateHelper;
import com.pitaya.codegen.util.UIUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
/*     */ public class CodeGenOptionDiag
/*     */   extends AbstractDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private JCheckBox ckTargetEntity = new JCheckBox("Entity");
/*  35 */   private JCheckBox ckTargetDao = new JCheckBox("Dao");
/*  36 */   private JCheckBox ckTargetService = new JCheckBox("Service");
/*     */   
/*  38 */   private JCheckBox ckTargetApiModel = new JCheckBox("ApiModel");
/*     */   
/*  40 */   private JTextField tfEntityName = new JTextField();
/*  41 */   private JTextField tfEntityPkg = new JTextField();
/*  42 */   private JTextField tfDaoPkg = new JTextField();
/*  43 */   private JTextField tfServicePkg = new JTextField();
/*  44 */   private JTextField tfApiModelPkg = new JTextField();
/*     */   
/*  46 */   private JProgressBar progressbar = new JProgressBar();
/*  47 */   private JLabel tipLabel = new JLabel();
/*  48 */   private Timer timer = null;
/*     */   
/*     */   private String tableName;
/*     */   
/*     */   public CodeGenOptionDiag() {
/*  53 */     setTitle("代码生成选项");
/*  54 */     setSize(500, 350);
/*  55 */     setResizable(false);
/*  56 */     initUI();
/*  57 */     this.timer = new Timer(100, this);
/*     */   }
/*     */   
/*     */   private void initUI() {
/*  61 */     Container contentPane = getContentPane();
/*     */     
/*  63 */     Box baseBox = Box.createVerticalBox();
/*  64 */     contentPane.add(baseBox);
/*     */     
/*  66 */     baseBox.setBorder(new EmptyBorder(10, 10, 10, 10));
/*     */     
/*  68 */     Box rangeBox = Box.createHorizontalBox();
/*  69 */     this.ckTargetEntity.setSelected(true);
/*  70 */     this.ckTargetDao.setSelected(true);
/*  71 */     this.ckTargetService.setSelected(true);
/*  72 */     this.ckTargetApiModel.setSelected(true);
/*     */     
/*  74 */     rangeBox.add(this.ckTargetEntity);
/*  75 */     rangeBox.add(this.ckTargetDao);
/*  76 */     rangeBox.add(this.ckTargetService);
/*  77 */     rangeBox.add(this.ckTargetApiModel);
/*     */     
/*  79 */     baseBox.add(UIUtil.createField("实体类名", this.tfEntityName));
/*  80 */     baseBox.add(Box.createVerticalStrut(2));
/*     */     
/*  82 */     baseBox.add(UIUtil.createField("Entity包名", this.tfEntityPkg));
/*  83 */     baseBox.add(Box.createVerticalStrut(2));
/*  84 */     baseBox.add(UIUtil.createField("Dao包名", this.tfDaoPkg));
/*     */     
/*  86 */     baseBox.add(Box.createVerticalStrut(2));
/*  87 */     baseBox.add(UIUtil.createField("Service包名", this.tfServicePkg));
/*     */     
/*  89 */     baseBox.add(Box.createVerticalStrut(2));
/*  90 */     baseBox.add(UIUtil.createField("ApiModel包名", this.tfApiModelPkg));
/*     */     
/*  92 */     baseBox.add(UIUtil.createField("生成范围", rangeBox));
/*  93 */     baseBox.add(Box.createVerticalStrut(2));
/*     */ 
/*     */     
/*  96 */     Box actionBox = Box.createHorizontalBox();
/*     */     
/*  98 */     JButton btnOk = new JButton("开始生成");
/*     */     
/* 100 */     btnOk.addActionListener(this);
/* 101 */     actionBox.add(btnOk);
/*     */     
/* 103 */     btnOk.setActionCommand("action.ok");
/*     */     
/* 105 */     this.progressbar.setOrientation(0);
/*     */     
/* 107 */     this.progressbar.setMinimum(0);
/*     */     
/* 109 */     this.progressbar.setMaximum(100);
/*     */     
/* 111 */     this.progressbar.setValue(0);
/*     */     
/* 113 */     this.progressbar.setStringPainted(true);
/* 114 */     actionBox.add(this.progressbar);
/* 115 */     actionBox.add(this.tipLabel);
/* 116 */     this.tipLabel.setPreferredSize(new Dimension(60, 25));
/* 117 */     actionBox.add(new JLabel("   "));
/* 118 */     baseBox.add(UIUtil.createField("", actionBox));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadOptions(String tableName) {
/* 123 */     this.tableName = tableName;
/* 124 */     this.tipLabel.setText("");
/* 125 */     this.progressbar.setValue(0);
/* 126 */     this.tfEntityName.setText(NameRuleUtil.getEntityClassName(tableName));
/*     */     
/* 128 */     Properties props = AppContext.getCurrentProjectProps();
/* 129 */     System.out.println(props);
/* 130 */     if (props.isEmpty()) {
/* 131 */       JOptionPane.showMessageDialog(null, "视图暂仅支持1个项目，必须选择或新建1个,否则生成的文件需要编辑相关包名");
/*     */       return;
/*     */     } 
/* 134 */     props.getProperty("project.pkg");
/*     */     
/* 136 */     Map<String, String> root = new HashMap<>();
/* 137 */     root.put("项目包名", props.getProperty("project.pkg"));
/* 138 */     root.put("模块名", "pm");
/*     */     
/* 140 */     String entityPkg = props.getProperty("entity.pkg.domain." + this.tableName);
/* 141 */     if (entityPkg == null) {
/* 142 */       String entityTpl = props.getProperty("project.entityPkgTpl");
/* 143 */       entityPkg = TemplateHelper.processStringTemplate(entityTpl, root);
/*     */     } 
/*     */     
/* 146 */     String daoPkg = props.getProperty("entity.pkg.dao." + this.tableName);
/* 147 */     if (daoPkg == null) {
/* 148 */       String daoPkgTpl = props.getProperty("project.daoPkgTpl");
/* 149 */       daoPkg = TemplateHelper.processStringTemplate(daoPkgTpl, root);
/*     */     } 
/*     */ 
/*     */     
/* 153 */     String servicePkg = props.getProperty("entity.pkg.service." + this.tableName);
/* 154 */     if (servicePkg == null) {
/* 155 */       String servicePkgTpl = props.getProperty("project.servicePkgTpl");
/* 156 */       servicePkg = TemplateHelper.processStringTemplate(servicePkgTpl, root);
/*     */     } 
/*     */     
/* 159 */     String apiModelPkg = props.getProperty("entity.pkg.apiModel." + this.tableName);
/* 160 */     if (apiModelPkg == null) {
/* 161 */       String apiModelPkgTpl = props.getProperty("project.apiModelPkgTpl", "");
/* 162 */       apiModelPkg = TemplateHelper.processStringTemplate(apiModelPkgTpl, root);
/*     */     } 
/*     */     
/* 165 */     this.tfEntityPkg.setText(entityPkg);
/* 166 */     this.tfDaoPkg.setText(daoPkg);
/* 167 */     this.tfServicePkg.setText(servicePkg);
/* 168 */     this.tfApiModelPkg.setText(apiModelPkg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 174 */     String cmd = e.getActionCommand();
/* 175 */     String entityName = this.tfEntityName.getText().trim();
/* 176 */     String entityPkg = this.tfEntityPkg.getText().trim();
/* 177 */     String daoPkg = this.tfDaoPkg.getText().trim();
/* 178 */     String servicePkg = this.tfServicePkg.getText().trim();
/* 179 */     String apiModelPkg = this.tfApiModelPkg.getText().trim();
/* 180 */     System.out.println(cmd);
/* 181 */     if (e.getSource() == this.timer) {
/* 182 */       int value = this.progressbar.getValue();
/*     */       
/* 184 */       if (value < 100) {
/*     */         
/* 186 */         this.progressbar.setValue(++value);
/*     */       }
/*     */       else {
/*     */         
/* 190 */         this.timer.stop();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if ("action.ok".equals(cmd)) {
/* 197 */       Properties props = new Properties();
/*     */       
/* 199 */       props.setProperty("entity.pkg.domain." + this.tableName, entityPkg);
/* 200 */       props.setProperty("entity.pkg.dao." + this.tableName, daoPkg);
/* 201 */       props.setProperty("entity.pkg.service." + this.tableName, servicePkg);
/* 202 */       props.setProperty("entity.pkg.apiModel." + this.tableName, apiModelPkg);
/*     */       
/* 204 */       AppContext.saveProjectProperties(props, AppContext.getCurrentProject());
/*     */       
/* 206 */       this.progressbar.setValue(0);
/* 207 */       this.timer.start();
/* 208 */       this.tipLabel.setText("Gernating ...");
/* 209 */       if (this.ckTargetEntity.isSelected()) {
/* 210 */         (new EntityClassGenerator()).generate(this.tableName, entityName, entityPkg);
/* 211 */         this.progressbar.setValue(100);
/* 212 */         this.tipLabel.setText("Entity OK!");
/*     */       } 
/* 214 */       if (this.ckTargetDao.isSelected()) {
/* 215 */         this.progressbar.setValue(0);
/* 216 */         (new MapperClassGenerator()).generate(this.tableName, entityName, entityPkg, daoPkg);
/* 217 */         this.progressbar.setValue(100);
/* 218 */         this.tipLabel.setText("DAO OK!");
/*     */       } 
/* 220 */       if (this.ckTargetService.isSelected()) {
/* 221 */         this.progressbar.setValue(0);
/* 222 */         (new ServiceClassGenerator()).generate(this.tableName, entityName, entityPkg, daoPkg, servicePkg);
/* 223 */         this.progressbar.setValue(100);
/* 224 */         this.tipLabel.setText("Service OK!");
/*     */       } 
/* 226 */       if (this.ckTargetApiModel.isSelected()) {
/* 227 */         this.progressbar.setValue(0);
/* 228 */         (new ApiModelClassGenerator()).generate(this.tableName, entityName, apiModelPkg);
/* 229 */         this.progressbar.setValue(100);
/* 230 */         this.tipLabel.setText("Service OK!");
/*     */       } 
/* 232 */       this.tipLabel.setText("All OK!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\CodeGenOptionDiag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */