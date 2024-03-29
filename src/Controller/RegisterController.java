package Controller;

import DataBase.usuarioDAO;
import Model.Candidato;
import Model.Empresa;
import Model.Usuario;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML public ImageView close;
    @FXML public ComboBox cb_tipo;
    @FXML public TextField txt_user;
    @FXML public TextField txt_id;
    @FXML public TextField txt_nome;
    @FXML public TextField txt_email;
    @FXML public PasswordField txt_senha;
    private usuarioDAO dao = new usuarioDAO();
    private ArrayList<Usuario> usuarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fill_comboBox();
        check_type();
        txt_email.setVisible(false);
        usuarios=dao.getUsuarios();
    }
    public void fill_comboBox()
    {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Candidato");
        tipos.add("Empresa");
        cb_tipo.setItems(tipos);
    }
    public void check_type()
    {
        cb_tipo.valueProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (s1.equals("Candidato"))
                {
                    txt_email.setText("");
                    txt_email.setVisible(true);
                }
                if (s1.equals("Empresa"))
                {
                    txt_email.setText("None");
                    txt_email.setVisible(false);
                }
            }
        });
    }
    @FXML
    public void register(ActionEvent actionEvent) {
        Boolean existes=false;
        Boolean numero=false;
        if(txt_id.getText().equals("")||txt_nome.getText().equals("")||txt_senha.getText().equals("")||txt_user.getText().equals("")||txt_email.getText().equals(""))
        {
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Preencha todos os campos");
            aviso.setHeaderText(null);
            aviso.setContentText("Para se registrar, preencha todos os campos.");
            aviso.show();
        }
        else
        {
            for (Usuario u:usuarios)
            {
                if(txt_id.getText().equals(u.getIdentificador()))
                {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Usuario já existe");
                    aviso.setHeaderText(null);
                    aviso.setContentText("Já existe um usuario com esse CPF/CNPJ");
                    aviso.show();
                    existes=true;
                    break;
                }
                else
                {
                    existes=false;
                }
            }
            if (existes==false)
            {
                try
                {
                    Double.parseDouble(txt_id.getText());
                    numero=true;
                }
                catch (Exception e)
                {
                    Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                    aviso.setTitle("Formato incorreto");
                    aviso.setHeaderText(null);
                    aviso.setContentText("CPF/CNPJ apanas numeros");
                    aviso.show();
                    numero=false;
                }
                if(numero)
                {
                    if(cb_tipo.getValue().equals("Candidato"))
                    {
                        Usuario u= new Candidato(txt_user.getText(),txt_senha.getText(),txt_nome.getText(),txt_id.getText(),txt_email.getText());
                        dao.create(u);
                    }
                    else if(cb_tipo.getValue().equals("Empresa"))
                    {
                        Usuario u = new Empresa(txt_user.getText(),txt_senha.getText(),txt_nome.getText(),txt_id.getText());
                        dao.create(u);
                    }
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                        Stage register = (Stage) close.getScene().getWindow();
                        register.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
    @FXML
    public void back(MouseEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Stage register = (Stage) close.getScene().getWindow();
            register.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Close(MouseEvent Event) {
        Platform.exit();
        System.exit(0);
    }
}
