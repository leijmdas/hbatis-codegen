/*    */ package com.pitaya.codegen.action;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.ui.DatasourceSettingDiag;
import com.pitaya.codegen.util.DBHelper;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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
/*    */ public class DataSourceSettingActionListener
/*    */   implements ActionListener
/*    */ {
/*    */   DatasourceSettingDiag diag;
/*    */   
/* 20 */   public DataSourceSettingActionListener(DatasourceSettingDiag diag) { this.diag = diag; }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 24 */     String cmd = e.getActionCommand();
/* 25 */     String host = this.diag.getTfHost().getText().trim();
/* 26 */     String username = this.diag.getTfUsername().getText().trim();
/* 27 */     String password = this.diag.getTfPassword().getText().trim();
/* 28 */     String dbInstance = this.diag.getTfDbInstance().getText().trim();
/* 29 */     String port = this.diag.getTfPort().getText().trim();
/* 30 */     if (host.equals("")) {
/* 31 */       JOptionPane.showMessageDialog((Component)this.diag, "主机地址不能为空");
/*    */       return;
/*    */     } 
/* 34 */     if (username.equals("")) {
/* 35 */       JOptionPane.showMessageDialog((Component)this.diag, "用户名不能为空");
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     if (dbInstance.equals("")) {
/* 40 */       JOptionPane.showMessageDialog((Component)this.diag, "数据库实例名不能为空");
/*    */       return;
/*    */     } 
/* 43 */     if (port.equals("")) {
/* 44 */       JOptionPane.showMessageDialog((Component)this.diag, "端口不能为空");
/*    */       return;
/*    */     } 
/* 47 */     if ("action.test".equals(cmd)) {
/* 48 */       DataSource ds = DBHelper.createDataSource(host, username, password, dbInstance, Integer.valueOf(Integer.parseInt(port)));
/* 49 */       Connection conn = null;
/*    */       try {
/* 51 */         conn = ds.getConnection();
/* 52 */         JOptionPane.showMessageDialog((Component)this.diag, "连接成功");
/* 53 */       } catch (SQLException e1) {
/* 54 */         JOptionPane.showMessageDialog((Component)this.diag, "连接异常:" + e1.getMessage());
/*    */       } finally {
/*    */         try {
/* 57 */           conn.close();
/* 58 */         } catch (SQLException sQLException) {}
/*    */       }
/*    */     
/*    */     }
/* 62 */     else if ("action.ok".equals(cmd)) {
/* 63 */       Properties props = new Properties();
/* 64 */       props.put("db.host", host);
/* 65 */       props.put("db.port", port);
/* 66 */       props.put("db.username", username);
/* 67 */       props.put("db.password", password);
/* 68 */       props.put("db.instanceName", dbInstance);
/*    */       
/* 70 */       AppContext.saveProperties(props);
/* 71 */       this.diag.setVisible(false);
/* 72 */       JOptionPane.showMessageDialog(null, "保存成功");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\action\DataSourceSettingActionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */