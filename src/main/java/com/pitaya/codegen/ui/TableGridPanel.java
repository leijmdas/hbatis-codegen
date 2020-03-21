/*     */ package com.pitaya.codegen.ui;
/*     */ 
/*     */

import com.pitaya.codegen.BasePanel;
import com.pitaya.codegen.model.TableDTO;
import com.pitaya.codegen.util.DBHelper;
import com.pitaya.codegen.util.DefaultJdbcTypeRegistry;
import com.pitaya.codegen.util.JdbcType;
import com.pitaya.codegen.util.UIFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableGridPanel
/*     */   extends BasePanel
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private JTable table = null;
/*  32 */   private JButton btnGen = new JButton("生成代码");
/*     */   private String tableName;
/*     */   private String tableRemark;
/*     */   
/*     */   public TableGridPanel() {
/*  37 */     setLayout(new BorderLayout());
/*  38 */     JToolBar tbar = new JToolBar();
/*     */     
/*  40 */     this.btnGen.setActionCommand("action.codegen");
/*  41 */     tbar.add(this.btnGen);
/*  42 */     add(tbar, "North");
/*  43 */     this.btnGen.addActionListener(this);
/*     */     
/*  45 */     String[] tableHeads = { "序号", "字段名", "字段类型", "JAVA类型", "可否为空", "长度", "备注" };
/*     */     
/*  47 */     this.table = new JTable();
/*  48 */     this.table.setRowHeight(22);
/*     */     
/*  50 */     DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
/*  51 */     dtm.setColumnIdentifiers((Object[])tableHeads);
/*     */ 
/*     */     
/*  54 */     this.table.setPreferredScrollableViewportSize(new Dimension(500, 80));
/*  55 */     JScrollPane s = new JScrollPane(this.table);
/*  56 */     add(s, "Center");
/*     */     
/*  58 */     TableColumn firsetColumn = this.table.getColumnModel().getColumn(0);
/*  59 */     firsetColumn.setPreferredWidth(30);
/*  60 */     firsetColumn.setMaxWidth(30);
/*  61 */     firsetColumn.setMinWidth(30);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTableMetas(TableDTO tableDTO) {
/*  66 */     this.tableName = tableDTO.getName();
/*  67 */     this.tableRemark = tableDTO.getRemark();
/*  68 */     DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
/*  69 */     List<Map<String, Object>> columns = DBHelper.getTableColumns(this.tableName);
/*     */     
/*  71 */     Map<String, String> columnCommentMap = DBHelper.getTableColumnComments(this.tableName);
/*  72 */     int rowNum = 1;
/*  73 */     for (Map<String, Object> column : columns) {
/*  74 */       String colName = column.get("colName").toString();
/*  75 */       if (column.get("autoIncrement") != null && Boolean.parseBoolean(column.get("autoIncrement").toString())) {
/*  76 */         colName = String.valueOf(colName) + "(自增)";
/*     */       }
/*     */       
/*  79 */       Integer colType = Integer.valueOf(Integer.parseInt(column.get("colType").toString()));
/*     */       
/*  81 */       JdbcType jdbcType = JdbcType.forCode(colType.intValue());
/*     */       
/*  83 */       Class<?> propType = DefaultJdbcTypeRegistry.getDefaultJavaType(jdbcType);
/*     */ 
/*     */       
/*  86 */       dtm.addRow(new Object[] {
/*  87 */             Integer.valueOf(rowNum), 
/*  88 */             colName, 
/*  89 */             column.get("colTypeName"), 
/*  90 */             propType, 
/*  91 */             (Integer.parseInt(column.get("nullable").toString()) > 0) ? "是" : "否", 
/*  92 */             column.get("colSize"), 
/*  93 */             columnCommentMap.get(column.get("colName").toString())
/*     */           });
/*  95 */       rowNum++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 102 */     String cmd = e.getActionCommand();
/* 103 */     if ("action.codegen".equals(cmd)) {
/* 104 */       CodeGenOptionDiag diag = (CodeGenOptionDiag)UIFactory.instanceDiag(CodeGenOptionDiag.class);
/* 105 */       diag.loadOptions(this.tableName);
/* 106 */       diag.setVisible(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\TableGridPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */