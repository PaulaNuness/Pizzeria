package com.example.pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**Classe que controlla e esta vinculada com pantalla_usuario.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Pantalla_Usuario_Controller implements Initializable {

    /**
     *elementos que tenho criado na parte grafica,Tipo nome_da_varible
     */
    @FXML
    private CheckBox ckeck_box_agua;

    @FXML
    private RadioButton radio_button_cuatro_quesos;

    @FXML
    private RadioButton radio_button_atun;

    @FXML
    private RadioButton radio_button_chocolate;

    @FXML
    private RadioButton radio_button_pequeña;

    @FXML
    private RadioButton radio_button_media;

    @FXML
    private RadioButton radio_button_grande;

    @FXML
    private CheckBox check_box_cerveza;

    @FXML
    private CheckBox chek_box_cola;

    @FXML
    private ChoiceBox<Integer> choice_box_ketchup;
    Integer [] cantidad_ketchup={1,2,3};

    @FXML
    private ChoiceBox<Integer> choice_box_mostaza;
    Integer [] cantidad_mostaza={1,2,3};

    @FXML
    private ChoiceBox<Integer> choice_box_barbacoa;
    Integer [] cantidad_barbacoa={1,2,3};

    @FXML
    private Button botton_calcular;

    @FXML
    private Button botton_confirmar;

    @FXML
    private TextArea texto_con_precio;

    @FXML
    private TableView<?> tabela_pedido;

    @FXML
    private TableColumn<?, ?> coluna_sabor;

    @FXML
    private TableColumn<?, ?> coluna_massa;

    @FXML
    private TableColumn<?, ?> coluna_tamaño;

    @FXML
    private TableColumn<?, ?> coluna_bebida;

    @FXML
    private TableColumn<?, ?> coluna_extra;

    @FXML
    private TableColumn<?, ?> coluna_salsa;

    @FXML
    private Button botton_ver_pedido;
    @FXML
    private Button botton_salir;

    /**
     *
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Calcular_Pedido(ActionEvent event) {

    }

    /**
     *
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Comfirmar_Pedido(ActionEvent event) {
        try{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
            alert.setTitle("Confirmado!!!");//com o titulo
            alert.setContentText("Pedido hecho");
            alert.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Ver_Pedido(ActionEvent event) {

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

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice_box_ketchup.getItems().addAll(cantidad_ketchup);//añadir no choice_box os elemntos que tenhpo dentro do Array
        choice_box_mostaza.getItems().addAll(cantidad_mostaza);
        choice_box_barbacoa.getItems().addAll(cantidad_barbacoa);


    }
}
