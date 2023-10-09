package com.example.pizzeria.controles;

import com.example.pizzeria.clases.ConexionBBDD;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Classe que controlla e esta vinculada com pantalla_registros.fxml
 * @author Paula Nunes
 * @since 2023/10/05
 */
public class Pantalla_Registros_Controller implements Initializable {
    /**
     *elementos que tenho criado na parte grafica,Tipo nome_da_varible
     */
    @FXML
    private Button botton_Confirmar;
    @FXML
    private Button botton_Salir;
    @FXML
    private TextField texto_registrar_email;
    @FXML
    private TextField texto_registrar_contrasena;
    @FXML
    private ImageView pizza;
    @FXML
    private ImageView pizza2;
    @FXML
    private ImageView tomate;

    /**
     *metodo que inserta o novo ususario na base de datos
     * @param "ActionEvent", que é um parâmetro comum para manipuladores de eventos JavaFX. O parâmetro do evento representa o evento que acionou o método.
     */
    @FXML
    void Confirmar_Registro(ActionEvent event) {
        try{
            String em=texto_registrar_email.getText();
            int numero=ConexionBBDD.obtenerultimoIDUsuario();
            String cont=texto_registrar_contrasena.getText();
            Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$");
            Matcher mat = pat.matcher(em);
            if(mat.find()){
                if(ConexionBBDD.insertar_usuario(numero,em,cont)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                    alert.setTitle("Confirmado!!!");//com o titulo
                    alert.setContentText("Añadido los datos");
                    alert.show();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.INFORMATION);//criar alerta de informacao
                alert.setTitle("Formato incorrecto!!!");//com o titulo
                alert.setContentText("Email debe tener:\nuno o varios caracteres al inicio\ndespues uno @\ndespues uno o varios caracteres\ndespues seguido de un punto y al menos tres caracters");
                alert.show();
            }

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
                Stage stagePrincipal=(Stage) botton_Salir.getScene().getWindow();
                stagePrincipal.close();//fecha a ventana
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metodo para criar as animaciones
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* //translate de la primera imagen de pizza
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(pizza);
        translate.setDuration(Duration.millis(8000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(-540);
        translate.setAutoReverse(true);
        translate.play();
        //translate de la segunda imagen de pizza
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(pizza2);
        tr.setDuration(Duration.millis(8000));
        tr.setCycleCount(TranslateTransition.INDEFINITE);
        tr.setByY(540);
        tr.setAutoReverse(true);
        tr.play();*/
       //tomates
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(tomate);
        rotate.setDuration(Duration.millis(23000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();
        //scale
        /*ScaleTransition scale = new ScaleTransition();
        scale.setNode(tomate);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.3);
        scale.setByY(0.3);
        scale.setAutoReverse(true);
        scale.play();*/
    }
}
