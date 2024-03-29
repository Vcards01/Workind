package Controller.Controllers_Empresa;

import DataBase.usuarioDAO;
import Model.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_empresaDadosController implements Initializable {

    //Panel principal de dadoss
    @FXML public AnchorPane panel_dados;
    //Label de titulos
    @FXML public Label txt_nome;
    @FXML public Label txt_cpf;
    //Variaveis normais
    @FXML private Empresa e;
    @FXML private usuarioDAO DAO = new usuarioDAO();

    @Override//Incia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //Incia algumas coisas
    public void start(Empresa e,Double h , Double w)
    {
        this.e= e;
        txt_nome.setText(e.getNome());
        txt_cpf.setText(e.getIdentificador());
        panel_dados.setPrefHeight(h);
        panel_dados.setPrefWidth(w);
    }
    //Recarrega os dados
    public void reload(Empresa e)
    {
        this.e= e;
        txt_nome.setText(e.getNome());
        txt_cpf.setText(e.getIdentificador());
    }

    //Ação de editar os dados
    @FXML
    public void editar_dados(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresa_dadosEditar.fxml"));
        Parent root = null;
        root = loader.load();
        UI_empresa_DadosEditarController controller = loader.getController();
        controller.start(e,this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    //ação de editar a senha
    @FXML
    public void editar_senha(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Empresa/UI_empresa_dadosEditarSenha.fxml"));
        Parent root = null;
        root = loader.load();
        UI_empresa_DadosEditarSenhaController controller= loader.getController();
        controller.start(e);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
