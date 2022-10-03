package Vista;

import Modelo.*;
import Controlador.*;
import Vista.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class JFrame extends javax.swing.JFrame {

    public JFrame() {
        //Instanciamos una clase vista de cuenta para ir guardando las cuentas
        Lista <Cuenta> lista = new Lista();
        
        //Insertamos 5 cuentas en la lista
        lista.insertar(new Cuenta(2020, 10, 3, 3000, "Sergio"));
        lista.insertar(new Cuenta(2018, 4, 5, 45000, "Pedro"));
        lista.insertar(new Cuenta(2021, 7, 5, 8500, "Juan"));
        lista.insertar(new Cuenta(2020, 9, 7, 16000, "Jose"));
        lista.insertar(new Cuenta(2022, 2, 1, 7000, "Ruben"));
        
        lista.setActual(lista.getInicio());
        
        //Creamos una cuanta saber en cual estamos
        Cuenta cuentaActual = (Cuenta) lista.getActual().getDato();
        
        initComponents();
        
        numCuenta.setText(""+cuentaActual.getNumCuenta());
        diaF.setText("" + cuentaActual.getFecha().get(Calendar.DATE));
        mesF.setText("" + cuentaActual.getFecha().get(Calendar.MONTH));
        anioF.setText("" + cuentaActual.getFecha().get(Calendar.YEAR));
        saldoCuenta.setText(""+cuentaActual.getSaldo());
        propCuenta.setText(""+cuentaActual.getPropietario());
        
        //Ponemos que no se vean estos botones para que solo se vean cuando quiera crear una cuenta
        botonAceptar.setVisible(false);
        botonCancelar.setVisible(false);
        
        //Accion que hace el boton anterior
        botonAnterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                //Si la lista anterior no es nula
                if(lista.getActual().getAnterior() != null){ 
                    //Instanciamos un objeto de la clase cuenta y lo igualamos a la cuenta que iba antes
                    Cuenta C = (Cuenta) lista.getActual().getAnterior().getDato();
                    //Actualizamos la lista a una anterior
                    lista.setActual(lista.getActual().getAnterior());
                    
                    //Mostramos los datos de la cuenta anterior
                    numCuenta.setText("" + C.getNumCuenta());
                    diaF.setText("" + C.getFecha().get(Calendar.DATE));
                    mesF.setText("" + C.getFecha().get(Calendar.MONTH));
                    anioF.setText("" + C.getFecha().get(Calendar.YEAR));
                    saldoCuenta.setText("" + C.getSaldo());
                    propCuenta.setText("" + C.getPropietario());
                }

            }
        });
        
        //Accion del boton siguiente
        //Hace lo mismo que el boton anterior pero en vez de para atras, para adelante
        botonSiguiente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                //Si la lista siguiente no es nula
                if(lista.getActual().getSiguiente() != null){
                    //Instanciamos un objeto de la clase cuenta y lo igualamos a la cuenta que va despues
                    Cuenta C = (Cuenta) lista.getActual().getSiguiente().getDato();
                    //Actualizamos la lista a una siguiente
                    lista.setActual(lista.getActual().getSiguiente());
                    
                    //Mostramos los datos de la cuenta siguinete
                    numCuenta.setText("" + C.getNumCuenta());
                    diaF.setText(""+C.getFecha().get(Calendar.DATE));
                    mesF.setText(""+C.getFecha().get(Calendar.MONTH));
                    anioF.setText(""+C.getFecha().get(Calendar.YEAR));
                    saldoCuenta.setText("" + C.getSaldo());
                    propCuenta.setText("" + C.getPropietario());
                }

            }
        });
        
        //Acciones del boton para crear las cuentas
        botonCrear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Modificamos los datos de las etiquetas para que se muestre por pantalla
                GregorianCalendar fechaActual = new GregorianCalendar(new Locale("es", "ES"));
                numCuenta.setText("Autonumerico");
                numCuenta.setBackground(Color.GRAY);
                diaF.setText("dd");
                mesF.setText("mm");
                anioF.setText("yyyy");
                saldoCuenta.setText("");
                propCuenta.setText("");
                
                //Dejamos que se pueda modificar el saldo y el propietario
                propCuenta.setEditable(true);
                saldoCuenta.setEditable(true);
                
                //Ponemos que sean visibles o no los botones
                botonCrear.setVisible(false);
                botonAnterior.setVisible(false);
                botonSiguiente.setVisible(false);
                botonAceptar.setVisible(true);
                botonCancelar.setVisible(true);
                
                //Acciones del boton aceptar dentro del boton crear
                botonAceptar.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         //Igualamos los datos que haya introducido coon la nueva cuenta
                        GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
                        String dia = diaF.getText();
                        String mes = mesF.getText();
                        String anio = anioF.getText();
                        String prop = propCuenta.getText();
                        float saldo = Float.parseFloat(saldoCuenta.getText());
                        //GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
                        
                        //Si no ha introducido fecha, ponemos la del sistema
                        if(dia.equalsIgnoreCase("dd") || dia.equalsIgnoreCase("") || mes.equalsIgnoreCase("mm") || mes.equalsIgnoreCase("")
                                || anio.equalsIgnoreCase("yyyy") || anio.equalsIgnoreCase("")){
                            lista.insertar(new Cuenta(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE),saldo, prop));
                        }
                        else //Si ha puesto fecha la introducimos con la clase GregorianCalendar
                        {
                            int dia1 = Integer.parseInt(dia);
                            int mes1 = Integer.parseInt(mes);
                            int anio1 = Integer.parseInt(anio);
                            lista.insertar(new Cuenta(anio1, mes1, dia1,saldo, prop));
                        }
                        
                        //No permitimos que pueda modificar nada
                        propCuenta.setEditable(false);
                        saldoCuenta.setEditable(false);
                        diaF.setEditable(false);
                        mesF.setEditable(false);
                        anioF.setEditable(false);
                        numCuenta.setBackground(null);
                        
                        //Ponemos visibles o no los botones
                        botonSiguiente.setVisible(true);
                        botonAnterior.setVisible(true);
                        botonAceptar.setVisible(false);
                        botonCancelar.setVisible(false);
                        botonCrear.setVisible(true);
                        
                        Cuenta C = (Cuenta) lista.getActual().getDato();
                        //Mostramos de nuevo los datos de la cuenta una vez que pulsamos el boton aceptar
                        refrescarCuenta(C);
                    }
                });
                
                //Acciones del boton cancelar dentro del boton crear
                botonCancelar.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                        //Si pulsa cancelar volvemos atras y no creamos inguna cuenta
                        //Ponemos visibles o no los botones
                        botonSiguiente.setVisible(true);
                        botonAnterior.setVisible(true);
                        botonAceptar.setVisible(false);
                        botonCancelar.setVisible(false);
                        botonCrear.setVisible(true);
                        
                        numCuenta.setBackground(null);
                        
                        Cuenta C = (Cuenta) lista.getActual().getDato();
                        //Mostramos de nuevo los datos de la cuenta una vez que pulsamos el boton cancelar
                        refrescarCuenta(C);
                    }
                });
            }
        });
    }
    
    public void refrescarCuenta(Cuenta cuenta){
        //Metodo para mostar los datos de una cuenta que le pasemos
        numCuenta.setText(""+cuenta.getNumCuenta());
        diaF.setText(""+cuenta.getFecha().get(Calendar.DATE));
        mesF.setText(""+cuenta.getFecha().get(Calendar.MONTH));
        anioF.setText(""+cuenta.getFecha().get(Calendar.YEAR));
        saldoCuenta.setText(""+cuenta.getSaldo());
        propCuenta.setText(""+cuenta.getPropietario());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        propCuenta = new javax.swing.JTextField();
        numCuenta = new javax.swing.JTextField();
        saldoCuenta = new javax.swing.JTextField();
        diaF = new javax.swing.JTextField();
        mesF = new javax.swing.JTextField();
        anioF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        botonAnterior = new javax.swing.JButton();
        botonSiguiente = new javax.swing.JButton();
        botonCrear = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Numero de Cuenta:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Saldo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Fecha:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Propietario:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        propCuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        propCuenta.setText("propietario");

        numCuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numCuenta.setText("numCuenta\n");
        numCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numCuentaActionPerformed(evt);
            }
        });

        saldoCuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saldoCuenta.setText("saldo");

        diaF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diaF.setText("dd");

        mesF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesF.setText("mm");

        anioF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anioF.setText("yyyy");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("/");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("/");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(diaF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anioF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(saldoCuenta)
                    .addComponent(propCuenta)
                    .addComponent(numCuenta))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(numCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(diaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(anioF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(mesF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(saldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(propCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        botonAnterior.setText("Anterior");
        botonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnteriorActionPerformed(evt);
            }
        });

        botonSiguiente.setText("Siguiente");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        botonCrear.setText("Crear Cuenta");
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });

        botonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonCancelar.setText("Cancelar");

        botonAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonAceptar.setText("Aceptar");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numCuentaActionPerformed

    }//GEN-LAST:event_numCuentaActionPerformed

    private void botonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAnteriorActionPerformed

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSiguienteActionPerformed

    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCrearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        Lista <Cuenta> list = new Lista();
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioF;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonAnterior;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JTextField diaF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField mesF;
    private javax.swing.JTextField numCuenta;
    private javax.swing.JTextField propCuenta;
    private javax.swing.JTextField saldoCuenta;
    // End of variables declaration//GEN-END:variables
}
