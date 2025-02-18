package pelicula;
import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class conexion extends javax.swing.JFrame {
    private static Connection conn;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String password="root";
    private static final String url="jdbc:mysql://localhost:3306/analisis";
    
    public Connection conectar(){
        conn=null;
        try{
            conn=(Connection) DriverManager.getConnection(url,user,password);
        }catch(SQLException e){
            
        JOptionPane.showMessageDialog(null, "Error! "+ e.toString());
    }
        return conn;
    }
        public void RellenaLaTablaConDatosMySQLPelicula(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        conexion conn = new conexion();
        Connection conexion = conn.conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Titulo");
        model.addColumn("Duracion");
        model.addColumn("Precio");
        model.addColumn("Actor");
        
        visor.setModel(model);
        String [] dato = new String[5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
        
        
        public void InsertarRegistroConProcedimientoAlmacenadoPelicula(JTextField idcodpelicula, JTextField titulo, JTextField duracion, JTextField precio, JTextField actor)
    {
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall(" CALL agregar_peli(?,?,?,?,?)");
            

            proc.setString(1, idcodpelicula.getText());
            proc.setString(2, titulo.getText());
            proc.setString(3, duracion.getText());
            proc.setString(4, precio.getText());
            proc.setString(5, actor.getText());
            proc.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha añadido una nueva pelicula!");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
        
        public void EliminaRegistroPelicula(String id)
    {
        String sql = "delete from pelis where idcodpelicula = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
        
        public void ActualizarPelicula(JTextField titulo, JTextField duracion, JTextField precio, JTextField autor, String id)
    {
        String sql = "update pelis set Titulo = '" + titulo.getText() +"', Duracion = '" + duracion.getText()+"', Precio = '" + precio.getText()+ "', Autor = '" + autor.getText() + "' where idcodpelicula = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "La pelicula se ha cambiado");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
        
        
        public void RellenaLaTablaConDatosMySQLCliente(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        conexion conn = new conexion();
        Connection conexion = conn.conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("idmembresia");
        model.addColumn("nombrecl");
        model.addColumn("dpi");
        model.addColumn("telefono");

        
        visor.setModel(model);
        String [] dato = new String[4];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
        public void RellenaLaTablaConDatosMySQLRentas(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        conexion conn = new conexion();
        Connection conexion = conn.conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("idrentas");
        model.addColumn("idmembresia");
        model.addColumn("idcodpelicula");
        model.addColumn("fechapres");
        model.addColumn("fecharetor");

        
        visor.setModel(model);
        String [] dato = new String[5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);

                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
        
        
        public void InsertarRegistroConProcedimientoAlmacenadoCliente(JTextField nombrecl, JTextField dpi, JTextField telefono)
    {
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall(" CALL agregar_cliente(?,?,?)");
            

            proc.setString(1, nombrecl.getText());
            proc.setString(2, dpi.getText());
            proc.setString(3, telefono.getText());
            proc.execute();
            JOptionPane.showMessageDialog(null, "Se ha añadido un nuevo cliente!");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
        public void EliminaRegistroCliente(String id)
    {
        String sql = "delete from clientes where idmembresia = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro eliminado!");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
        
        public void InsertarRegistroConProcedimientoAlmacenadoRentas(JTextField idmembre, JTextField idcodpel, JTextField fechapr,JTextField fecharet)
    {
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall(" CALL agregar_renta(?,?,?,?)");
            

            proc.setString(1, idmembre.getText());
            proc.setString(2, idcodpel.getText());
            proc.setString(3, fechapr.getText());
            proc.setString(4, fecharet.getText());
            proc.execute();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
        public void EliminaRegistroRentas(String id)
    {
        String sql = "delete from rentas where idrentas = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registro eliminado!");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
        
        public void ActualizarRentas(JTextField fecharetorno, String id)
    {
        String sql = "update rentas set fecharetor = '" + fecharetorno.getText() +"' where idrentas = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "La renta se ha cambiado");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
        
        
    

}

