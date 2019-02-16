package rocks.zipcode.atm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.soap.Text;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextField amountField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    TextField accountNum = new TextField();
    TextField name = new TextField();
    TextField e_mail = new TextField();
    TextField balance = new TextField();
    TextField alert = new TextField();

    Button btnDeposit = new Button("Deposit");
    Button btnWithdraw = new Button("Withdraw");
    Button btnExit = new Button("Logout");
    Button btnExitApp = new Button("Exit");

    Label alertLabel = new Label("Alert : ");

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 300);
        field.setMaxWidth( 250);
        field.setDisable(true);
        amountField.setMaxWidth( 250);
        amountField.setDisable(true);



        alert.setVisible(false);  // default to false - only show if alert is needed
        alertLabel.setVisible(false);



        btnDeposit.setDisable(true);


        btnExitApp.setOnAction(e -> {
            Platform.exit();

        });

        btnDeposit.setOnAction(e -> {
            float amount = Float.parseFloat(amountField.getText());
            cashMachine.deposit(amount);


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);
        });

        btnWithdraw.setDisable(true);

        btnWithdraw.setOnAction(e -> {
            float amount = Float.parseFloat(amountField.getText());
            cashMachine.withdraw(amount);


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);

        });

        btnExit.setDisable(true);

        btnExit.setOnAction(e -> {
            btnExit.setDisable(true);
            btnWithdraw.setDisable(true);
            btnDeposit.setDisable(true);
            amountField.setDisable(true);
            amountField.clear();
            field.clear();
            accountNum.clear();
            name.clear();
            e_mail.clear();
            balance.clear();
            alert.clear();

            cashMachine.exit();



        });


        MenuItem menuItem1 = new MenuItem("1000");
        MenuItem menuItem2 = new MenuItem("2000");
        MenuItem menuItem3 = new MenuItem("3000");
        MenuItem menuItemNew = new MenuItem("New Account");


        MenuButton menuButton =new MenuButton("Account Login", null, menuItem1,menuItem2,menuItem3,menuItemNew);

        menuItem1.setOnAction(e -> {

            cashMachine.login(1000);
            field.setText("1000");
            enableLoggedInButtons();

            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);

        });
        menuItem2.setOnAction(e -> {

            cashMachine.login(2000);
            field.setText("2000");
            enableLoggedInButtons();

            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);

        });
        menuItem3.setOnAction(e -> {

            cashMachine.login(3000);
            field.setText("3000");
            enableLoggedInButtons();


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);

        });

        menuItemNew.setOnAction(e -> {



            Stage newWindow = new Stage();
            newWindow.setTitle("New Account");
            newWindow.setScene(new Scene(createInternal()));

            newWindow.show();





        });


      // Prep Labels
      Label accountIDLabel = new Label("Account ID : ");
      Label amountFieldLabel = new Label("Amount : $");
      Label accountNameLabel = new Label("Name : ");
      Label balanceLabel = new Label("Balance : ");


      GridPane gridPane = new GridPane();

      gridPane.setAlignment(Pos.CENTER);
      // Buttons
      gridPane.add(btnDeposit,0,0);
      gridPane.add(btnWithdraw,1,0);
      gridPane.add(btnExit,2,6);
      gridPane.add(btnExitApp,3,6);
      // label fields
      gridPane.add(accountIDLabel,0,1);
      gridPane.add(amountFieldLabel,0,2);
      gridPane.add(accountNameLabel,0,3);
      gridPane.add(balanceLabel,0,4);
      gridPane.add(alertLabel,0,5);  // note: default set to hidden
      // Text fields
      gridPane.add(accountNum,1,1);
      gridPane.add(amountField,1,2);
      gridPane.add(name,1,3);
      gridPane.add(balance,1,4);
      gridPane.add(alert,1,5,2,1);// note: default set to hidden




      vbox.getChildren().addAll(menuButton, gridPane);


        return vbox;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void setLocalTextFields(AccountData accountDataDisplay){
        accountNum.setText(accountDataDisplay.getId()+"");
        name.setText(accountDataDisplay.getName());
        e_mail.setText(accountDataDisplay.getEmail());
        balance.setText(""+accountDataDisplay.getBalance());
        if(!accountDataDisplay.getAlert().isEmpty()) {
            alert.setText("Alert : " + accountDataDisplay.getAlert());
            alert.setVisible(true);
            alertLabel.setVisible(true);
        }
        else {
            alert.setVisible(false);
            alertLabel.setVisible(false);
        }
    }

    private GridPane createInternal() {
        GridPane internalBox = new GridPane();
        internalBox.setPrefSize(300, 200);

        internalBox.setPadding(new Insets(10,10,10,10));
        internalBox.setVgap(5);
        internalBox.setHgap(5);

        internalBox.setAlignment(Pos.CENTER);


        TextField accountDisplay = new TextField();
        String initAccountText= "Account";
        accountDisplay.setText(initAccountText);

        TextField accountName = new TextField();
        accountName.setText("Name");

        TextField email = new TextField();
        email.setText("e-mail");

        TextField initBalance = new TextField();
        initBalance.setText("initBalance");


        Button btnAdd = new Button("Add New Account");

        btnAdd.setOnAction(e -> {

            if(initAccountText.equals(accountDisplay.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No account id");
                alert.setContentText("No account to add");
                alert.showAndWait();

            }
            else{

            field.setText(accountDisplay.getText());
            String name =accountName.getText();
            String myEmail =email.getText();

            int accountId = Integer.parseInt(field.getText());

            cashMachine.setAccountData(accountId, name, myEmail, 0);

            AccountData accountDataDisplay =  cashMachine.getAccountData();
            setLocalTextFields(accountDataDisplay);

            enableLoggedInButtons();

        }

        });

        Label text1 = new Label("Account ID : ");
        Label text2 = new Label("Your name : ");
        Label text3 = new Label("Your e-mail : ");


        internalBox.add(text1,0,0);
        internalBox.add(accountDisplay, 1, 0);

        internalBox.add(text2,0,1);
        internalBox.add(accountName, 1, 1);

        internalBox.add(text3,0,2);
        internalBox.add(email, 1, 2);

        internalBox.add(btnAdd, 0, 3, 3, 1);


        return internalBox;
    }

    public void enableLoggedInButtons()
    {
        btnExit.setDisable(false);
        btnWithdraw.setDisable(false);
        btnDeposit.setDisable(false);
        amountField.setDisable(false);
    }



}
