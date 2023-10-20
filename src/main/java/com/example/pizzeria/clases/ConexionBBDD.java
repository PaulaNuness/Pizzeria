package com.example.pizzeria.clases;




import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;


/**
 * essa clase foi criada para conectar com a base de datos
 */
public class ConexionBBDD {
    private static Connection a;

    /**
     * metodo utiliza a clase R para ler o fichero pizzeria.properties e fazer a conexao com a base de datos
     * @return true o false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static boolean conectar() throws ClassNotFoundException, SQLException, IOException {
        boolean conect=false;
        Properties configuration = new Properties();
        configuration.load(R.getProperties("pizzeria.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        Class.forName("com.mysql.cj.jdbc.Driver");
        a = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
        conect=true;
        return conect;
    }

    /**
     * metodo desconecta a aplicaçao com a base de datos
     * @throws SQLException
     */
    public static void desconectar() throws SQLException {
        a.close();
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
                    MessageDigest dig = MessageDigest.getInstance("SHA-256");// Crie uma instância do MessageDigest com o algoritmo SHA-256
                    dig.reset();// Crie um novo hash para a senha digitada
                    dig.update(y.getBytes());
                    byte[] senhaDigitadaHash = dig.digest();
                    StringBuilder senhaDigitadaHex = new StringBuilder();// Converta o hash da senha digitada em hexadecimal
                    for (byte b : senhaDigitadaHash) {
                        senhaDigitadaHex.append(String.format("%02x", b));
                    }
                    if (consulta_uno.getString("contrasena").equals(senhaDigitadaHex.toString())){//se o que esta na coluna contraseña é igual a y(pasado por parametros)
                        existe=2;//Si el usuario existe y la contraseña coincide
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
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
            ConexionBBDD.conectar();
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            ConexionBBDD.conectar();
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
            ConexionBBDD.conectar();
            String sql="insert into usuarios values(?,?,?)";
            PreparedStatement pre = a.prepareStatement(sql);
            pre.setInt(1,id_usuario);
            pre.setString(2, email);
            pre.setString(3, contraseña);
            pre.executeUpdate();
            ok=true;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            ConexionBBDD.conectar();
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

    /**metodo obtenho os pedidos da tabla pedidos na base de datos
     * @return
     * @throws SQLException
     */
    public static String obtener_Pedidos() throws SQLException {
        try{
            HashMap<Integer,String>lista= new HashMap<Integer,String>();
            StringBuilder stringBuilder = new StringBuilder();

            int x=0;
            String y=" ";
            ConexionBBDD.conectar();
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
            ConexionBBDD.conectar();
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

    /**
     * metodo verifica se ja existe o email que passei por parametro
     * @param x
     * @return
     */
    public static boolean usuario_em_la_base_de_datos(String x) {
        boolean existe = false;//nao existe
        try {
            Statement s = a.createStatement();
            ResultSet consulta_uno = s.executeQuery("Select * from usuarios;");//busca tudo que ha na tabela usuarios

            while (consulta_uno.next()) {//enquanto volver linhas para consultar
                if (consulta_uno.getString("email").equals(x)) {//se o que esta na coluna email é igual a x(pasado por parametro)
                    existe = true;//Si el usuario existe
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return existe;
    }

    /**
     * metodo elimina da base de datos usuarios que ja tenham o mesmo email
     * @param email
     * @throws SQLException
     */
    public static void eliminar_usuario(String email) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE email = ?";

        PreparedStatement sentencia = a.prepareStatement(sql);
        sentencia.setString(1, email);
        sentencia.executeUpdate();
    }


}

