/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author John
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenu
     */
    public FrmMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jmiCrear = new javax.swing.JMenuItem();
        jmiEliminar = new javax.swing.JMenuItem();
        jmiRoles = new javax.swing.JMenuItem();
        btncaja = new javax.swing.JMenuItem();
        btnFactura = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenu();
        MenusCliente = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuProveedores = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuProductos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuCompras = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        menuPedidos = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jmiFacturaR = new javax.swing.JMenuItem();
        jmiComprasR = new javax.swing.JMenuItem();
        jmiClientesR = new javax.swing.JMenuItem();
        jmiProveedoresR = new javax.swing.JMenuItem();
        jmiPedidosR = new javax.swing.JMenuItem();
        btnProductosR = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar2.setBackground(new java.awt.Color(255, 255, 255));

        jMenu4.setText("Usuarios");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jmiCrear.setText("Crear");
        jmiCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCrearActionPerformed(evt);
            }
        });
        jMenu4.add(jmiCrear);

        jmiEliminar.setText("Eliminar");
        jMenu4.add(jmiEliminar);

        jmiRoles.setText("Roles");
        jmiRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRolesActionPerformed(evt);
            }
        });
        jMenu4.add(jmiRoles);

        btncaja.setText("Caja");
        btncaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncajaActionPerformed(evt);
            }
        });
        jMenu4.add(btncaja);

        btnFactura.setText("Factura");
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });
        jMenu4.add(btnFactura);

        jMenuBar2.add(jMenu4);

        menuCliente.setText("Clientes");
        menuCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        MenusCliente.setText("MenuCliente");
        menuCliente.add(MenusCliente);

        jMenuBar2.add(menuCliente);

        jMenu6.setText("Proveedores");
        jMenu6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        menuProveedores.setText("MenuProveedores");
        menuProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProveedoresActionPerformed(evt);
            }
        });
        jMenu6.add(menuProveedores);

        jMenuBar2.add(jMenu6);

        jMenu2.setText("Productos");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        menuProductos.setText("MenuProductos");
        jMenu2.add(menuProductos);

        jMenuBar2.add(jMenu2);

        jMenu3.setText("Compras");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        menuCompras.setText("MenuCompras");
        jMenu3.add(menuCompras);

        jMenuBar2.add(jMenu3);

        jMenu8.setText("Pedidos");
        jMenu8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        menuPedidos.setText("MenuPedidos");
        menuPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPedidosActionPerformed(evt);
            }
        });
        jMenu8.add(menuPedidos);

        jMenuBar2.add(jMenu8);

        jMenu7.setText("Reportes");
        jMenu7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jmiFacturaR.setText("Facturas");
        jMenu7.add(jmiFacturaR);

        jmiComprasR.setText("Compras");
        jmiComprasR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiComprasRActionPerformed(evt);
            }
        });
        jMenu7.add(jmiComprasR);

        jmiClientesR.setText("Clientes");
        jmiClientesR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClientesRActionPerformed(evt);
            }
        });
        jMenu7.add(jmiClientesR);

        jmiProveedoresR.setText("Proveedores");
        jMenu7.add(jmiProveedoresR);

        jmiPedidosR.setText("Pedidos");
        jMenu7.add(jmiPedidosR);

        btnProductosR.setText("Productos");
        btnProductosR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosRActionPerformed(evt);
            }
        });
        jMenu7.add(btnProductosR);

        jMenuBar2.add(jMenu7);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiCrearActionPerformed

    private void jmiRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiRolesActionPerformed

    private void menuProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuProveedoresActionPerformed

    private void menuPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuPedidosActionPerformed

    private void jmiClientesRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClientesRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiClientesRActionPerformed

    private void jmiComprasRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiComprasRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiComprasRActionPerformed

    private void btncajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncajaActionPerformed

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnProductosRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProductosRActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem MenusCliente;
    public javax.swing.JMenuItem btnFactura;
    public javax.swing.JMenuItem btnProductosR;
    public javax.swing.JMenuItem btncaja;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    public javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar2;
    public javax.swing.JMenuItem jmiClientesR;
    public javax.swing.JMenuItem jmiComprasR;
    public javax.swing.JMenuItem jmiCrear;
    public javax.swing.JMenuItem jmiEliminar;
    public javax.swing.JMenuItem jmiFacturaR;
    public javax.swing.JMenuItem jmiPedidosR;
    public javax.swing.JMenuItem jmiProveedoresR;
    public javax.swing.JMenuItem jmiRoles;
    public javax.swing.JMenu menuCliente;
    public javax.swing.JMenuItem menuCompras;
    public javax.swing.JMenuItem menuPedidos;
    public javax.swing.JMenuItem menuProductos;
    public javax.swing.JMenuItem menuProveedores;
    // End of variables declaration//GEN-END:variables
}
