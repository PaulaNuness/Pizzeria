package com.example.pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.util.Optional;

/**Classe que controlla e esta vinculada com pantalla_admin.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Pantalla_Admin_Controller {

    /**
     *elementos que tenho criado na parte grafica,Tipo nome_da_varible
     */
    @FXML
    private DatePicker calendario;

    @FXML
    private Button botton_Salir;

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
                Stage stagePrincipal=(Stage) botton_Salir.getScene().getWindow();
                stagePrincipal.close();//fecha a ventana

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
