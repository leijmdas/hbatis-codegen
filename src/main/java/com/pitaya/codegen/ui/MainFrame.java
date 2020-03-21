/*     */ package com.pitaya.codegen.ui;
/*     */ 
/*     */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.util.UIFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
/*     */ public class MainFrame
/*     */   extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   protected JToolBar tBar = new JToolBar();
/*     */   
/*  34 */   protected JMenuBar tMenuBar = new JMenuBar();
/*     */   
/*  36 */   protected JPanel workspacePanel = new JPanel();
/*     */   
/*  38 */   protected NavTreePanel treePanel = new NavTreePanel();
/*     */   
/*     */   public MainFrame(int width, int height, String title) {
/*  41 */     setSize(width, height);
/*  42 */     setTitle(title);
/*  43 */     setLocationRelativeTo(null);
/*  44 */     setDefaultCloseOperation(3);
/*     */     
/*  46 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  50 */     setLayout(new BorderLayout());
/*  51 */     createMenuBar();
/*     */     
/*  53 */     this.treePanel.setPreferredSize(new Dimension(300, 300));
/*     */     
/*  55 */     this.workspacePanel.setLayout(new BorderLayout());
/*     */     
/*  57 */     JSplitPane sPanel = new JSplitPane(1, true, this.treePanel, this.workspacePanel);
/*  58 */     sPanel.setDividerSize(3);
/*     */     
/*  60 */     getContentPane().add(sPanel, "Center");
/*     */   }
/*     */ 
/*     */   
/*     */   private void createMenuBar() {
/*  65 */     JMenu mMain = new JMenu("文件");
/*  66 */     JMenuItem newPropMenu = new JMenuItem("新建项目配置");
/*  67 */     JMenuItem openPropMenu = new JMenuItem("打开项目配置");
/*  68 */     JMenuItem exitMenu = new JMenuItem("退出");
/*  69 */     mMain.add(newPropMenu);
/*  70 */     mMain.add(openPropMenu);
/*  71 */     mMain.add(exitMenu);
/*     */ 
/*     */     
/*  74 */     JMenuItem dsMenuItem = new JMenuItem("数据源配置");
/*  75 */     JMenuItem projMenuItem = new JMenuItem("项目代码生成配置");
/*     */     
/*  77 */     dsMenuItem.setActionCommand("action.setting.datasource");
/*  78 */     newPropMenu.setActionCommand("action.project.new");
/*  79 */     openPropMenu.setActionCommand("action.project.open");
/*  80 */     exitMenu.setActionCommand("action.exit");
/*  81 */     projMenuItem.setActionCommand("action.project.config");
/*     */     
/*  83 */     newPropMenu.addActionListener(this);
/*  84 */     openPropMenu.addActionListener(this);
/*  85 */     dsMenuItem.addActionListener(this);
/*  86 */     projMenuItem.addActionListener(this);
/*  87 */     exitMenu.addActionListener(this);
/*     */     
/*  89 */     JMenu mCfg = new JMenu("配置");
/*  90 */     mCfg.add(dsMenuItem);
/*  91 */     mCfg.add(projMenuItem);
/*     */     
/*  93 */     this.tMenuBar.add(mMain);
/*  94 */     this.tMenuBar.add(mCfg);
/*  95 */     this.tMenuBar.add(new JMenu("帮助"));
/*  96 */     setJMenuBar(this.tMenuBar);
/*     */     
/*  98 */     JToolBar bBar = new JToolBar();
/*  99 */     JButton btnFlush = new JButton("刷新");
/* 100 */     btnFlush.setActionCommand("action.flush");
/* 101 */     btnFlush.addActionListener(this);
/* 102 */     bBar.add(btnFlush);
/*     */     
/* 104 */     add(bBar, "North");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public JPanel getWorkspacePanel() { return this.workspacePanel; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 115 */     String actionCmd = e.getActionCommand();
/* 116 */     if ("action.setting.datasource".equals(actionCmd)) {
/* 117 */       DatasourceSettingDiag diag = (DatasourceSettingDiag)UIFactory.instanceDiag(DatasourceSettingDiag.class);
/*     */       
/* 119 */       diag.loadConfig();
/* 120 */       diag.setVisible(true);
/*     */     }
/* 122 */     else if ("action.project.new".equals(actionCmd)) {
/*     */       
/* 124 */       ProjectGlobalSettingDiag diag = (ProjectGlobalSettingDiag)UIFactory.instanceDiag(ProjectGlobalSettingDiag.class);
/* 125 */       diag.setCreateFlag(true);
/* 126 */       diag.loadConfig();
/* 127 */       diag.setVisible(true);
/* 128 */     } else if ("action.project.config".equals(actionCmd)) {
/*     */       
/* 130 */       ProjectGlobalSettingDiag diag = (ProjectGlobalSettingDiag)UIFactory.instanceDiag(ProjectGlobalSettingDiag.class);
/* 131 */       diag.setCreateFlag(false);
/* 132 */       diag.loadConfig();
/* 133 */       diag.setVisible(true);
/* 134 */     } else if ("action.project.open".equals(actionCmd)) {
/* 135 */       JFileChooser jfc = new JFileChooser(new File("."));
/* 136 */       jfc.setFileSelectionMode(0);
/* 137 */       jfc.showDialog(new JLabel(), "选择.hbc");
/* 138 */       File file = jfc.getSelectedFile();
/* 139 */       if (file == null) {
/*     */         return;
/*     */       }
/* 142 */       if (!file.isFile() && !file.getName().endsWith(".hbc")) {
/* 143 */         JOptionPane.showMessageDialog(null, "非项目文件");
/*     */       }
/* 145 */       AppContext.loadProjectProperties(file);
/* 146 */       refreshProject(AppContext.getCurrentProjectProps().getProperty("project.name"));
/* 147 */     } else if ("action.flush".equals(actionCmd)) {
/*     */       try {
/* 149 */         AppContext.flush();
/* 150 */         this.treePanel.loadTables();
/* 151 */       } catch (Exception ex) {
/* 152 */         JOptionPane.showMessageDialog(null, "加载库表异常:" + ex.getMessage());
/*     */       } 
/* 154 */     } else if ("action.exit".equals(actionCmd)) {
/* 155 */       setVisible(false);
/* 156 */       System.exit(-1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshProject(String projName) {
/* 162 */     AppContext.setCurrentProject(projName);
/* 163 */     this.treePanel.loadTables();
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\MainFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */