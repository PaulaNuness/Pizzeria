package com.example.pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

/**Classe que controlla e esta vinculada com inicio.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Inicio_Controller {
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
    private TextField texto_usuario;

    @FXML
    private TextField texto_contraseña;

    @FXML
    private Button botton_salir;

    /**
     *quanto clico no botao entrar, se eu tenho aceso vai para a seguinte pantalla, se nao tenho aceso aparece Alertas
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Entrar(ActionEvent event) {
        try{
            if(texto_usuario.getText().equals("1")){
                FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("pantalla_usuario.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                Parent root = fxmlLoader.load();
                Pantalla_Usuario_Controller controlador = fxmlLoader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                stage.setScene(scene);
                stage.show();
            }
            if(texto_usuario.getText().equals("2")){
                FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("pantalla_admin.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
                Parent root = fxmlLoader.load();
                Pantalla_Admin_Controller controlador2 = fxmlLoader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
                stage.setScene(scene);
                stage.show();
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
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
     *
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Registrar(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("pantalla_registros.fxml"));//quando eu clicar en entrar, iremos para a ventana seguinte
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
