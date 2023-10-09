package com.example.pizzeria.controles;

import com.example.pizzeria.clases.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.SQLException;
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
    private Button botton_Salir;
    @FXML
    private TextArea texto_area_pedidos;
    @FXML
    private Button botton_Pedidos;

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

    /**
     *
     * @param event
     */
    @FXML
    void Ver_Pedidos(ActionEvent event) {
        try{
            texto_area_pedidos.setText(ConexionBBDD.obtener_Pedidos());
            ConexionBBDD.obtener_Pedidos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
