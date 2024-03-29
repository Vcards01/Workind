package Controller.Controllers_Candidato;

import Model.Candidato;
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

public class UI_candidatoDadosController implements Initializable {

    //Painel principal dos dados
    @FXML public AnchorPane panel_dados;
    //Labem de titulos
    @FXML public Label txt_nome;
    @FXML public Label txt_email;
    @FXML public Label txt_cpf;
    //Variaveis normais
    @FXML private Candidato c;

    @Override//Inicia view
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //Incia algumas coisas
    public void start(Candidato c,Double h , Double w)
    {
        this.c= c;
        txt_nome.setText(c.getNome());
        txt_cpf.setText(c.getIdentificador());
        txt_email.setText(c.getEmail());
        panel_dados.setPrefHeight(h);
        panel_dados.setPrefWidth(w);
    }
    //Recarrega os dados
    public void reload(Candidato c)
    {
        this.c= c;
        txt_nome.setText(c.getNome());
        txt_cpf.setText(c.getIdentificador());
        txt_email.setText(c.getEmail());
    }
    //evento de edição de dados
    @FXML
    public void editar_dados(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_dadosEditar.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidato_DadosEditarController controller = loader.getController();
        controller.start(c,this);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    //Evento de edição de senha
    @FXML
    public void editar_senha(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_dadosEditarSenha.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidato_DadosEditarSenhaController controller= loader.getController();
        controller.start(c);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
