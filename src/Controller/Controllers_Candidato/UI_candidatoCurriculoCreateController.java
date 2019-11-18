package Controller.Controllers_Candidato;

import DataBase.curriculoDAO;
import Model.Candidato;
import Model.Curriculo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UI_candidatoCurriculoCreateController implements Initializable {
    @FXML public Button btn_salvar;
    @FXML public TextField txt_nome;
    @FXML public TextField txt_cpf;
    @FXML public DatePicker txt_data;
    @FXML public TextArea txt_experiencia;
    @FXML public RadioButton rdBtn_sim;
    @FXML public RadioButton rdBtn_nao;
    @FXML public TextField txt_curso;
    @FXML public TextField txt_idCurso;
    @FXML public Label lb_curso;
    @FXML public Label lb_codCurso;
    @FXML public ComboBox cb_curso;
    private Candidato c;
    private Boolean editable;
    private Curriculo cur;
    private curriculoDAO DAO = new curriculoDAO();
    private UI_candidatoCurriculoController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void start(Candidato c, UI_candidatoCurriculoController controller, Boolean editable)
    {
        this.editable=editable;
        this.c=c;
        this.controller=controller;
        fill_comboBox();
        change();
        if(editable)
        {
            cur=DAO.read(c);
            txt_nome.setText(cur.getNome_candidato());
            txt_cpf.setText(cur.getCpf());
            txt_data.setValue(LocalDate.parse(cur.getNascimento(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txt_experiencia.setText(cur.getExperiencia());
            if(cur.getCurso())
            {
                cb_curso.setValue("Sim");
                txt_curso.setText(cur.getNome_curso());
                txt_idCurso.setText(String.valueOf(cur.getCod_curso()));
            }
            else
            {
                cb_curso.setValue("Não");
                txt_curso.setVisible(false);
                txt_idCurso.setVisible(false);
                lb_codCurso.setVisible(false);
                lb_curso.setVisible(false);
            }
        }
        else
        {
            txt_curso.setVisible(false);
            txt_idCurso.setVisible(false);
            lb_codCurso.setVisible(false);
            lb_curso.setVisible(false);
            txt_nome.setText(c.getNome());
            txt_cpf.setText(c.getIdentificador());
        }
    }
    public void fill_comboBox()
    {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Sim");
        tipos.add("Não");
        cb_curso.setItems(tipos);
    }
    public void change()
    {
        cb_curso.valueProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (s1.equals("Sim"))
                {
                    txt_curso.setText("");
                    txt_curso.setVisible(true);
                    txt_idCurso.setText("");
                    txt_idCurso.setVisible(true);
                    lb_codCurso.setVisible(true);
                    lb_curso.setVisible(true);
                }
                if (s1.equals("Não"))
                {
                    txt_curso.setText("None");
                    txt_curso.setVisible(false);
                    txt_idCurso.setText("0");
                    txt_idCurso.setVisible(false);
                    lb_codCurso.setVisible(false);
                    lb_curso.setVisible(false);
                }
            }
        });
    }

    @FXML
    public void Save(ActionEvent event)
    {
        if(!editable) {
            Curriculo cur = null;
            if (cb_curso.getValue().equals("Sim")) {
                cur = new Curriculo(c, txt_nome.getText(), txt_cpf.getText(), txt_data.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), txt_experiencia.getText(), true, txt_curso.getText(), Integer.parseInt(txt_idCurso.getText()));
            } else if (cb_curso.getValue().equals("Não")) {
                cur = new Curriculo(c, txt_nome.getText(), txt_cpf.getText(), txt_data.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), txt_experiencia.getText(), false);

            }
            DAO.create(cur);
            Stage close = (Stage) btn_salvar.getScene().getWindow();
            controller.reload();
            close.close();
        }
        else
        {
            Curriculo cur = null;
            if (cb_curso.getValue().equals("Sim")) {
                cur = new Curriculo(this.cur.getId(),c, txt_nome.getText(), txt_cpf.getText(), txt_data.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), txt_experiencia.getText(), true, txt_curso.getText(), Integer.parseInt(txt_idCurso.getText()));
            } else if (cb_curso.getValue().equals("Não")) {
                cur = new Curriculo(this.cur.getId(),c, txt_nome.getText(), txt_cpf.getText(), txt_data.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), txt_experiencia.getText(), false);

            }
            DAO.update(cur);
            Stage close = (Stage) btn_salvar.getScene().getWindow();
            controller.reload();
            close.close();
        }
    }
    @FXML
    public void Close(MouseEvent Event)
    {
        Stage close=(Stage)btn_salvar.getScene().getWindow();
        close.close();
    }

}