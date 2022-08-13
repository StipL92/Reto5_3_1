
package controlador;


import modelo.Conexion;
import vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.swing.JOptionPane;

import javax.swing.JTable;


public class Controller implements ActionListener{
    DefaultTableModel tabla = new DefaultTableModel();
    Object[] registros= null;
    private Vista view;
   

    public Controller() {
        this.view = new Vista();
        
    }
    
   
    
    //Iniciar vista
    public void inicio (){
        this.view.setVisible(true);
        this.view.setTitle("Reto 5 Ciclo II Misión TIC UTP Grupo 16");
        this.view.setLocationRelativeTo(null);
        this.view.setResizable(false);
        this.view.btnInforme1.addActionListener(this);
        this.view.btnInforme2.addActionListener(this);
        this.view.btnInforme3.addActionListener(this);
        
    }
    
    //Metodo 1 Consulta lider
    
    public void ConsultarLider(){
        DefaultTableModel tabla = new DefaultTableModel();
        registros = new Object[4];
        
   
        try {
            Conexion con1 = new Conexion();
            Connection con = con1.conexion();
            
            String sql = "select ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia from Lider order by Ciudad_Residencia ASC";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            String [] titulo ={"ID_Lider", "Nombre", "Primer_Apellido", "Ciudad_Residencia"};
            tabla.setColumnIdentifiers(titulo);
            view.tablaResultados.setModel(tabla);
       
                while (rs.next()){
                    registros[0] = rs.getString("ID_Lider");
                    registros[1] = rs.getString("Nombre");
                    registros[2] = rs.getString("Primer_Apellido");
                    registros[3] = rs.getString("Ciudad_Residencia");
                    tabla.addRow(registros);
                }
               
                //tablaResultados.setModel(modelo);
                System.out.println("La consulta se realizo corectamente");
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta"); 
        } finally{
            
        }
        
    }
            
    
    //Metodo 2 Consulta Casas Campestres
    public void ConsultaCasasCampestres(){
        DefaultTableModel tabla = new DefaultTableModel();
        registros = new Object[4];
        
   
        try {
            Conexion con1 = new Conexion();
            Connection con = con1.conexion();
            
            String sql = "select\n" +
                        "ID_Proyecto,\n" +
                        "Constructora,\n" +
                        "Numero_Habitaciones,\n" +
                        "Ciudad\n" +
                        "From Proyecto\n" +
                        "where Clasificacion ='Casa Campestre' and\n" +
                        "Ciudad in('Santa Marta','Cartagena', 'Barranquilla')";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            String [] titulo ={"ID_Proyecto", "Constructora", "Numero_Habitaciones", "Ciudad"};
            tabla.setColumnIdentifiers(titulo);
            view.tablaResultados.setModel(tabla);
       
                while (rs.next()){
                    registros[0] = rs.getString("ID_Proyecto");
                    registros[1] = rs.getString("Constructora");
                    registros[2] = rs.getString("Numero_Habitaciones");
                    registros[3] = rs.getString("Ciudad");
                    tabla.addRow(registros);
                }
               
                //tablaResultados.setModel(modelo);
                System.out.println("La consulta se realizo corectamente");
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta"); 
        } 
    }
    
    
    
    //Metodo 3 Consulta Compras en Homcenter
    
    public void informeProyectos (){
        DefaultTableModel tabla = new DefaultTableModel();
        registros = new Object[3];
        
   
        try {
            Conexion con1 = new Conexion();
            Connection con = con1.conexion();
            
            String sql = "select\n" +
"ID_Compra,\n" +
"Constructora,\n" +
"Banco_Vinculado\n" +
"From Compra c\n" +
"join Proyecto p on c.ID_Proyecto = p.ID_Proyecto\n" +
"where  c.Proveedor='Homecenter' and p.Ciudad='Salento'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            String [] titulo ={"ID_Compra", "Constructora", "Banco_Vinculado"};
            tabla.setColumnIdentifiers(titulo);
            view.tablaResultados.setModel(tabla);
       
                while (rs.next()){
                    registros[0] = rs.getString("ID_Compra");
                    registros[1] = rs.getString("Constructora");
                    registros[2] = rs.getString("Banco_Vinculado");

                    tabla.addRow(registros);
                }
               
                //tablaResultados.setModel(modelo);
                System.out.println("La consulta se realizo corectamente");
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta"); 
        } finally{
            
        }
        
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnInforme1){
            //String[] titulo = new String[] {"ID_Lider", "Nombre", "Primer_Apellido", "Ciudad_Residencia"};
            //view.modelo.setColumnIdentifiers(titulo);
            ConsultarLider();
        }
        if (e.getSource() == view.btnInforme2){
            ConsultaCasasCampestres();
        }
        if (e.getSource() == view.btnInforme3){
            informeProyectos();
        }
    }
       
}
