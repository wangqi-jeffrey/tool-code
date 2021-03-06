/*
 * MainPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.wangqi.convert;

import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author  __USER__
 */
public class MainPanel extends javax.swing.JFrame {

	/** Creates new form MainPanel */
	public MainPanel() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("\u6e05\u7a7a");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u8f6c\u6362");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		jLabel1.setText("\u57fa\u672c\u6570\u636e\u7c7b\u578b\u53ca\u5176\u5305\u88c5\u7c7b\uff0cString Date");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		366,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jScrollPane2,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		379,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton2)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jButton1)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		550,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton2)
												.addComponent(jButton1)
												.addComponent(jLabel1))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														633, Short.MAX_VALUE)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														633, Short.MAX_VALUE))
								.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//JOptionPane.showMessageDialog(this, jTextArea1.getText());
		String area1 = jTextArea1.getText();
		if (area1 != null && !"".equals(area1)) {
			area1 = area1.replaceAll("/\\*.*\\*/", "");//替换多行注释
			area1 = area1.replaceAll("//(.*)", "");//替换单行注释
			area1 = area1.replaceAll("private", "");
			area1 = area1.replaceAll("byte", "");
			area1 = area1.replaceAll("short", "");
			area1 = area1.replaceAll("char", "");
			area1 = area1.replaceAll("int", "");
			area1 = area1.replaceAll("long", "");
			area1 = area1.replaceAll("float", "");
			area1 = area1.replaceAll("double", "");
			area1 = area1.replaceAll("boolean", "");
			area1 = area1.replaceAll("String", "");
			area1 = area1.replaceAll("Date", "");
			area1 = area1.replaceAll("Byte", "");
			area1 = area1.replaceAll("Boolean", "");
			area1 = area1.replaceAll("Short", "");
			area1 = area1.replaceAll("Character", "");
			area1 = area1.replaceAll("Integer", "");
			area1 = area1.replaceAll("Long", "");
			area1 = area1.replaceAll("Float", "");
			area1 = area1.replaceAll("Double", "");
			area1 = area1.replaceAll("BigDecimal", "");
			area1 = area1.replaceAll("\\s*", "");
			String[] arr = area1.split(";");
			StringBuffer sb = new StringBuffer();
			sb.append("{\r\n");
			for (int i = 0; i < arr.length; i++) {
				if (i != arr.length - 1)
					sb.append("    \"" + arr[i] + "\":\"\",\r\n");
				else
					sb.append("    \"" + arr[i] + "\":\"\"\r\n");
			}
			sb.append("}");
			jTextArea2.setFont(new Font("宋体", Font.BOLD, 18));
			jTextArea2.setText(sb.toString());
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		jTextArea1.setText("");
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainPanel().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	// End of variables declaration//GEN-END:variables

}