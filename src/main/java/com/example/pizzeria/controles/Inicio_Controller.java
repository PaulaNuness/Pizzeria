package com.example.pizzeria.controles;

import com.example.pizzeria.PizzeriaApplication;
import com.example.pizzeria.clases.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

/**Classe que controlla e esta vinculada com inicio.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Inicio_Controller  {
    /**
     *elementos que tenho criado na parte grafica,Tipo nome_da_varible
     */
    @FXML
    private Button botton_entrar;

    @FXML
    private Button botton_limpiar;

    @FXML
    private Button botton_registrar;

    @FXML
    private  TextField texto_usuario;

    @FXML
    private PasswordField texto_contraseña;

    @FXML
    private Button botton_salir;
    public static String ident;

    /**
     *quanto clico no botao entrar, se eu tenho aceso vai para a seguinte pantalla, se nao tenho aceso aparece Alertas
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Entrar(ActionEvent event)  {
        try{
            ConexionBBDD.conectar();//chamei o metodo que tenho na classe ConexionBBDD
            System.out.println("conectado?"+ConexionBBDD.estadoConexion());//quero mostrar no terminar o estado da conexao
            if(!texto_usuario.getText().isEmpty() && !texto_contraseña.getText().isEmpty() ){//se o texto_usuario  e o texto_contraseña nao estao vazios
                if (ConexionBBDD.conectar()) {//se hay conexao com mysql
                    if (ConexionBBDD.usuario_existe(texto_usuario.getText(), texto_contraseña.getText()) == 0) {
                        Alert alert =new Alert(Alert.AlertType.ERROR);//criar alert de error
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Usuario no registrado");
                        alert.setContentText("El usuario que ha introducido no existe.");
                        alert.showAndWait();
                    } else if (ConexionBBDD.usuario_existe(texto_usuario.getText(), texto_contraseña.getText()) == 1) {
                        Alert alert =new Alert(Alert.AlertType.ERROR);//criar alert de error
                        alert.setTitle("Contraseña Incorrecta");
                        alert.setContentText("La contraseña introducida es erronea.");
                        alert.showAndWait();
                    } else if (ConexionBBDD.usuario_existe(texto_usuario.getText(), texto_contraseña.getText()) == 2) {
                        if(texto_usuario.getText().equals("admin@hotmail.com")){
                            FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("pantalla_admin.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                            Parent root = fxmlLoader.load();
                            Pantalla_Admin_Controller controlador2 = fxmlLoader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                            stage.setScene(scene);
                            stage.show();
                        }else{
                            ident=texto_usuario.getText();
                            FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("pantalla_usuario.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                            Parent root = fxmlLoader.load();
                            Pantalla_Usuario_Controller controlador = fxmlLoader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }else {//se nao tiver conexao com mysql
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No conectado");
                    alert.setContentText("No hay conexion con el servidor.");
                    alert.showAndWait();
                }
            }else{//se texto usuario e texto contraseña estao vazios
                Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("ERROR!!!");//com o titulo
                alert.setContentText("Rellenar usuario y contraseña");
                alert.show();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * o metodo abaixo limpa os campos texto_usuario e texto_contraseña que digitamos
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Limpiar(ActionEvent event) {
        try{
            texto_usuario.clear();
            texto_contraseña.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *metodo que abre a ventana para o usuario se registrar
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Registrar(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("pantalla_registros.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
            Parent root = fxmlLoader.load();
            Pantalla_Registros_Controller controlador3 = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *quando clico no botao salir me pergunta se quero sair, se clico em ok sai e se clico em cancelar continuo onde estou
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Salir(ActionEvent event) {
        try{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);//criar alerta de confirmacao
            alert.setTitle("Cerrando...");//com o titulo
            alert.setContentText("Quieres Salir?");//contendo o texto(neste caso uma pergunta)
            Optional<ButtonType> result = alert.showAndWait();//crio um objeto e guardo nele o resultado da escolha do usuario, OK ou CANCELAR
            if(result.get()== ButtonType.OK){//se o get do objeto for OK
                Stage stagePrincipal=(Stage) botton_salir.getScene().getWindow();
                stagePrincipal.close();//fecha a ventana
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
