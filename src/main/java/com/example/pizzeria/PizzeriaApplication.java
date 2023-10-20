package com.example.pizzeria;

import com.example.pizzeria.clases.ConexionBBDD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

/**
 *@author Paula Nunes
 * @since 2023/10/05
 */
public class PizzeriaApplication extends Application {
    /**
     *
     * @param stage --O método "start" recebe um parâmetro chamado "stage" do tipo "Stage". O "Stage" é um tipo de classe em JavaFX usado para representar uma janela de aplicativo em uma interface gráfica JavaFX
     * @throws IOException  Ela é geralmente usada quando ocorrem erros durante a leitura ou escrita de dados para arquivos, sockets de rede ou outros tipos de operações de entrada e saída.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);//nao tenho o menu acima
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args Ele permite que você passe argumentos da linha de comando para o programa quando você o executa
     */
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        launch();
    }
}