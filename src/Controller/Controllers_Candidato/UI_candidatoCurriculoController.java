package Controller.Controllers_Candidato;

import DataBase.curriculoDAO;
import Model.Candidato;
import Model.Curriculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI_candidatoCurriculoController implements Initializable {
    //Panel principal da view
    @FXML public AnchorPane pn_curriculo;
    //Botões de ação
    @FXML public Button btn_criar;
    @FXML public Button btn_editar;
    //Label de titulo
    @FXML public Label txt_nome;
    @FXML public Label txt_cpf;
    @FXML public Label txt_nascimento;
    @FXML public Label txt_experiencia;
    @FXML public Label txt_curso;
    @FXML public Label label_curso;
    //Variaveis normais
    private Curriculo curriculo;
    private Candidato c;
    private curriculoDAO DAO = new curriculoDAO();

    @Override//Inicia a view
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //Incia algumas coisas
    public void start(Candidato c,Double h , Double w)
    {
        this.c=c;
        pn_curriculo.setPrefHeight(h);
        pn_curriculo.setPrefWidth(w);
        if (currilo_exist())
        {
            btn_criar.setVisible(false);
            txt_nome.setText(curriculo.getNome_candidato());
            txt_cpf.setText(curriculo.getCpf());
            txt_experiencia.setText(curriculo.getExperiencia());
            txt_nascimento.setText(curriculo.getNascimento());
            txt_curso.setText(curriculo.getNome_curso());
            if (!curriculo.getCurso())
            {
                txt_curso.setVisible(false);
                label_curso.setVisible(false);
            }
            else
            {
                txt_curso.setVisible(true);
                txt_curso.setText(curriculo.getNome_curso());
                label_curso.setVisible(true);
            }
        }
        else
        {
            btn_editar.setVisible(false);
        }
    }
    //Recarrega os dados do curriculo
    public void reload()
    {

        if (currilo_exist())
        {
            btn_criar.setVisible(false);
            txt_nome.setText(curriculo.getNome_candidato());
            txt_cpf.setText(curriculo.getCpf());
            txt_experiencia.setText(curriculo.getExperiencia());
            txt_nascimento.setText(curriculo.getNascimento());
            txt_curso.setText(curriculo.getNome_curso());
            if (!curriculo.getCurso())
            {
                txt_curso.setVisible(false);
                label_curso.setVisible(false);
            }
            else
            {
                txt_curso.setVisible(true);
                txt_curso.setText(curriculo.getNome_curso());
                label_curso.setVisible(true);
            }
            btn_editar.setVisible(true);
        }
        else
        {
            btn_editar.setVisible(false);
        }
    }
    //Verifica se o curriculo existe ou não
    public boolean currilo_exist()
    {
        curriculo = DAO.read(c);
        if(curriculo ==null)
        {
            return false;
        }
        return  true;
    }

    //Evento para criar um novo curriculo
    @FXML
    public void create(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_curriculoCreate.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidatoCurriculoCreateController controller = loader.getController();
        controller.start(c,this,false);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    //Evento para editar o curriculo ja existente
    @FXML
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/View_Candidato/UI_candidato_curriculoCreate.fxml"));
        Parent root = null;
        root = loader.load();
        UI_candidatoCurriculoCreateController controller = loader.getController();
        controller.start(c,this,true);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
