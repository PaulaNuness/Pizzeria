package com.example.pizzeria.clases;



import java.sql.*;
import java.util.*;


/**
 * essa clase foi criada para conectar com a base de datos
 */
public class ConexionBBDD {
    /**
     * varibles que vou usar para fazer a conexao
     */
    private static final String URL="jdbc:mysql://localhost:3306/pizzeria";
    private static final String USER="root";
    private static final String PASSWORD="toor";
    private static Connection a;


    /**
     * metodo que fara a conexao
     * @return false ou true
     * @throws ClassNotFoundException Se você está obtendo uma exceção ClassNotFoundException ao tentar carregar a classe do driver JDBC do MySQL
     * @throws SQLException errores SQL
     */
    public static boolean conectar() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            boolean conectado=false;
            a= DriverManager.getConnection(URL,USER,PASSWORD);
            conectado= true;
            return conectado;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *metodo que mostrará se esta conectado ou nao
     * @return false ou true
     */
    public static boolean estadoConexion() {
        boolean ok=false;
        try {
            if (a != null && a.isValid(0)) {
                ok= true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    /**
     *metodo que desconectara
     * @return false ou true
     */
    public static boolean desconectar(){
        boolean desconectado=false;
        try{
            a.close();
            desconectado=true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return desconectado;
    }

    /**
     * metodo que dira se o usuario nao existe(0), se o usuario existe(1) e se o usuario e a contraseña coinciden(2)
     * @param x parametro que vai dizer se existe o email na base de datos
     * @param y parametro que vai dizer se existe a contraseña na base de datos
     * @return retorna o que houver na variavel existe
     */

    public static int usuario_existe(String x, String y) {
        int existe = 0;//nao existe
        try{
            Statement s=a.createStatement();
            ResultSet consulta_uno=s.executeQuery("Select * from usuarios;");//busca tudo que ha na tabela usuarios

            while(consulta_uno.next()){//enquanto volver linhas para consultar
                if (consulta_uno.getString("email").equals(x)){//se o que esta na coluna email é igual a x(pasado por parametro)
                    existe=1;//Si el usuario existe
                    if (consulta_uno.getString("contrasena").equals(y)){//se o que esta na coluna contraseña é igual a y(pasado por parametros)
                        existe=2;//Si el usuario existe y la contraseña coincide
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }

    /**
     * metodo que inserta pedidos na base de datos
     * @param i id_usuario
     * @param f fecha
     * @param sa sabor
     * @param t tamanho
     * @param b bebida
     * @param sal salsa
     * @return true ou false
     */

    public static boolean insertar_pedido(int i,String f,String sa,String t,String b,String sal) {
        boolean ok=false;
        try {
            Connection a = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql="insert into pedidos values(?,?,?,?,?,?)";
            PreparedStatement pre = a.prepareStatement(sql);
            pre.setInt(1, i);
            pre.setString(2, f);
            pre.setString(3, sa);
            pre.setString(4, t);
            pre.setString(5, b);
            pre.setString(6, sal);
            pre.executeUpdate();
            ok=true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    /**
     * metodo para obtener o id_usurio
     * @param email pasamos como parametro o email
     * @return retorna  o numero do id_usuario
     * @throws SQLException
     */
    public static int obtenerIDUsuario(String email) throws SQLException {
        try{
            int identifica=0;
            int x=0;
            String y=" ";
            HashMap<String,Integer>lista=new HashMap<String,Integer>();
            Connection a = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement s= a.createStatement();
            String sql = "SELECT * FROM usuarios ";
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                x = resultado.getInt(1);
                y=resultado.getString(2);
                lista.put(y,x);
            }
            identifica=lista.get(email);

            return identifica;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * metodo para insertar usuarios na base de datos
     * @param id_usuario
     * @param email
     * @param contraseña
     * @return true ou false
     */
    public static boolean insertar_usuario(int id_usuario,String email,String contraseña) {
        boolean ok=false;
        try {
            Connection a = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql="insert into usuarios values(?,?,?)";
            PreparedStatement pre = a.prepareStatement(sql);
            pre.setInt(1,id_usuario);
            pre.setString(2, email);
            pre.setString(3, contraseña);
            pre.executeUpdate();
            ok=true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    /**
     * metodo para obtener o ultimo id_usuario da base de datos
     * @return o ultimo numero +1
     * @throws SQLException
     */
    public static int obtenerultimoIDUsuario() throws SQLException {
        try{
            int identifica=0;
            Connection a = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement s= a.createStatement();
            String sql = "SELECT * FROM usuarios ";
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                identifica = resultado.getInt(1)+1;
            }
            return identifica;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * @return
     * @throws SQLException
     */
    public static String obtener_Pedidos() throws SQLException {
        try{
            HashMap<Integer,String>lista= new HashMap<Integer,String>();
            StringBuilder stringBuilder = new StringBuilder();

            int x=0;
            String y=" ";
            Connection a = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement s= a.createStatement();
            String sql = "SELECT * FROM pedidos ";
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                x=resultado.getInt(1);
                y=resultado.getString(2)+"--"+resultado.getString(3)+"--"+resultado.getString(4)+"--"+resultado.getString(5)+"--"+resultado.getString(6);

                lista.put(x,y);
            }
            for (Integer chave : lista.keySet()) {
                String valor = lista.get(chave);
                System.out.println("Usuario : " + chave +", Pedido: " + valor);
                stringBuilder.append("Usuario : " +chave).append(": ").append("--> Pedido: " +valor).append("\n");
            }

            System.out.println("**************************************");
            int xx=0;
            String yyy=" ";
            String email=" ";
            HashMap<Integer,String>lista2=new HashMap<Integer,String>();
            Connection aa = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement ss= a.createStatement();
            String sqll = "SELECT * FROM usuarios ";
            ResultSet resultadoo = s.executeQuery(sqll);

            while (resultadoo.next()) {
                x = resultadoo.getInt(1);
                y=resultadoo.getString(2);
                lista2.put(x,y);
            }
            for (Integer chave2 : lista2.keySet()) {
                String valor2 = lista2.get(chave2);
                stringBuilder.append("ID_Usuario: "+chave2).append(": ").append("email:"+valor2).append("\n");
            }

            String resul = stringBuilder.toString();

            return resul;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}

