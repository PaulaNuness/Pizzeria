package com.example.pizzeria.controles;

import com.example.pizzeria.clases.ConexionBBDD;
import com.example.pizzeria.clases.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**Classe que controlla e esta vinculada com pantalla_usuario.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Pantalla_Usuario_Controller  implements Initializable {
    /**
     *elementos que tenho criado na parte grafica,Tipo nome_da_varible
     */
    Integer[] numerossalsas={1,2,3};
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
    private CheckBox ckeck_box_agua;
    @FXML
    private ChoiceBox<Integer> choice_box_ketchup;
    @FXML
    private ChoiceBox<Integer> choice_box_mostaza;
    @FXML
    private ChoiceBox<Integer> choice_box_barbacoa;
    @FXML
    private Button botton_calcular;
    @FXML
    private Button botton_confirmar;
    @FXML
    private TextArea texto_con_precio;
    @FXML
    private TableView<Pedido> tabela_pedido;
    @FXML
    private TableColumn coluna_sabor;
    @FXML
    private TableColumn coluna_tamaño;
    @FXML
    private TableColumn coluna_bebida;
    @FXML
    private TableColumn coluna_salsa;
    @FXML
    private Button botton_ver_pedido;
    @FXML
    private Button botton_salir;
    @FXML
    private Button botton_borrar_pedido;
    private ObservableList<Pedido>pedidos;

    /**
     *metodo para calcular o pedido
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Calcular_Pedido(ActionEvent event) {
        try{
            double valor=0;
            if (radio_button_atun.isSelected() ||radio_button_chocolate.isSelected() || radio_button_cuatro_quesos.isSelected()){
                valor=valor+1;
            }
            if(radio_button_pequeña.isSelected()||radio_button_media.isSelected()||radio_button_grande.isSelected()){
                valor=valor+1;
            }
            if(ckeck_box_agua.isSelected()||check_box_cerveza.isSelected()||chek_box_cola.isSelected()){
                valor=valor+1;
            }
            if(choice_box_ketchup.getValue()==1){
                valor=valor+1;
            }
            if(choice_box_mostaza.getValue()==1){
                valor=valor+1;
            }
            if(choice_box_barbacoa.getValue()==1){
                valor=valor+1;
            }
            if(choice_box_ketchup.getValue()==2){
                valor=valor+2;
            }
            if(choice_box_mostaza.getValue()==2){
                valor=valor+2;
            }
            if(choice_box_barbacoa.getValue()==2){
                valor=valor+2;
            }
            if(choice_box_ketchup.getValue()==3){
                valor=valor+3;
            }
            if(choice_box_mostaza.getValue()==3){
                valor=valor+3;
            }
            if(choice_box_barbacoa.getValue()==3){
                valor=valor+3;
            }
            texto_con_precio.setText(String.valueOf(valor));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *metodo qu econfirma o pedido e inserta o pedido na base de datos, na tabela pedidos
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Comfirmar_Pedido(ActionEvent event) {
        try{
            int usu=ConexionBBDD.obtenerIDUsuario(Inicio_Controller.ident);
            // Obtém a data atual e transformo em um String
            LocalDate dataAtual = LocalDate.now();
            String dia= String.valueOf(dataAtual);
            String sabor= tabela_pedido.getItems().get(0).getSabor();
            String tamanho= tabela_pedido.getItems().get(0).getTamano();
            String bebi= tabela_pedido.getItems().get(0).getBebida();
            String molho= tabela_pedido.getItems().get(0).getSalsa();
            if(ConexionBBDD.insertar_pedido(usu,dia,sabor,tamanho,bebi,molho)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("Confirmado!!!");//com o titulo
                alert.setContentText("Añadido el pedido");
                alert.show();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *metodo para ver o que pedimos, mostrará na tabela
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Ver_Pedido(ActionEvent event) {
        try{
            String sabor="";
            String tamanho="";
            String bebida="";
            String salsa_ketchup ="0 salsa ketchup";
            String salsa_mostaza="0 salsa mostaza ";
            String salsa_barbacoa="0 salsa barbacoa ";
            if(!this.radio_button_chocolate.isSelected() && !this.radio_button_cuatro_quesos.isSelected() && !this.radio_button_atun.isSelected()){// si no eliges nada
                Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("ERROR!!!");//com o titulo
                alert.setContentText("Eliges una opcion de sabor");
                alert.show();
            }else {
                if (this.radio_button_atun.isSelected()) {
                    sabor = "Atun";
                }
                if (this.radio_button_cuatro_quesos.isSelected()) {
                    sabor = "Cuatro Quesos";
                }
                if (this.radio_button_chocolate.isSelected()) {
                    sabor = "Chocolate";
                }
                if (!this.radio_button_pequeña.isSelected() && !this.radio_button_media.isSelected() && !this.radio_button_grande.isSelected()) {//si no eliges el tamaño de la pizza
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                    alert.setTitle("ERROR!!!");//com o titulo
                    alert.setContentText("Eliges un tamaño de pizza");
                    alert.show();
                } else {
                    if (this.radio_button_pequeña.isSelected()) {
                        tamanho = "Pequena";
                    }
                    if (this.radio_button_media.isSelected()) {
                        tamanho = "Media";
                    }
                    if (this.radio_button_grande.isSelected()) {
                        tamanho = "Grande";
                    }
                    if (!this.check_box_cerveza.isSelected() && !this.chek_box_cola.isSelected() && !this.ckeck_box_agua.isSelected()) {//si no eliges una bebida
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                        alert.setTitle("ERROR!!!");//com o titulo
                        alert.setContentText("Eliges una bebida");
                        alert.show();
                    }else{
                        if (this.check_box_cerveza.isSelected()) {
                            bebida = "Cerveza";
                        }
                        if (this.chek_box_cola.isSelected()) {
                            bebida = "Cola";
                        }
                        if (this.ckeck_box_agua.isSelected()) {
                            bebida = "Agua";
                        }
                        if(this.check_box_cerveza.isSelected() &&this.chek_box_cola.isSelected() || this.check_box_cerveza.isSelected()&&this.ckeck_box_agua.isSelected() || this.chek_box_cola.isSelected() &&this.ckeck_box_agua.isSelected()){//si eliges más de una bebida
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                            alert.setTitle("ERROR!!!");//com o titulo
                            alert.setContentText("Eliges solo una bebida");
                            alert.show();
                        }
                    }
                }
                if (choice_box_ketchup.getValue()==1 ) {
                    salsa_ketchup = "1 salsa Ketcup ";
                }
                if (choice_box_ketchup.getValue()==2 ) {
                    salsa_ketchup = "2 salsa Ketcup ";
                }
                if (choice_box_ketchup.getValue()==3 ) {
                    salsa_ketchup = "3 salsa Ketcup ";
                }
                if (choice_box_mostaza.getValue()==1) {
                    salsa_mostaza = "1 salsa Mostaza ";
                }
                if (choice_box_mostaza.getValue()==2) {
                    salsa_mostaza = "2 salsa Mostaza ";
                }
                if (choice_box_mostaza.getValue()==3) {
                    salsa_mostaza = "3 salsa Mostaza ";
                }
                if (choice_box_barbacoa.getValue()==1 ) {
                    salsa_barbacoa = "1 salsa Barbacoa ";
                }
                if (choice_box_barbacoa.getValue()==2 ) {
                    salsa_barbacoa = "2 salsa Barbacoa ";
                }
                if (choice_box_barbacoa.getValue()==3 ) {
                    salsa_barbacoa = "3 salsa Barbacoa ";
                }
            }
            Pedido pe=new Pedido(sabor,tamanho,bebida,salsa_ketchup+salsa_mostaza+salsa_barbacoa);//crio um objeto de la clase Pedido
            this.pedidos.add(pe);//añado el objeto al ObservableList
            this.tabela_pedido.setItems(pedidos);//pongo dentro de la tabla o que tengo dentro de ObservableList
        }catch (Exception e) {
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

    /**
     * metodo para limpar a tabela
     * @param event
     */
    @FXML
    void Borrar_pedido(ActionEvent event) {
        try{
            pedidos.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *metodo para por os numeros nas caixas do choicebox e construir a tabela com as colunas
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            choice_box_ketchup.setValue(0);
            choice_box_mostaza.setValue(0);
            choice_box_barbacoa.setValue(0);
            choice_box_ketchup.getItems().addAll(numerossalsas);//añadir no choice_box os elemntos que tenhpo dentro do Array
            choice_box_mostaza.getItems().addAll(numerossalsas);
            choice_box_barbacoa.getItems().addAll(numerossalsas);

            pedidos= FXCollections.observableArrayList();
            this.coluna_sabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
            this.coluna_tamaño.setCellValueFactory(new PropertyValueFactory<>("tamano"));
            this.coluna_bebida.setCellValueFactory(new PropertyValueFactory<>("bebida"));
            this.coluna_salsa.setCellValueFactory(new PropertyValueFactory<>("salsa"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
