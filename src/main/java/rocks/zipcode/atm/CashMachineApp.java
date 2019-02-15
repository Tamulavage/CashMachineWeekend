package rocks.zipcode.atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextField amountField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);
        field.setMaxWidth( 250);
        field.setText("Account Number");
        field.setDisable(true);
        amountField.setMaxWidth( 250);
        amountField.setText("$$$$");
        amountField.setDisable(true);


        TextField accountNum = new TextField();
        TextField name = new TextField();
        TextField e_mail = new TextField();
        TextField balance = new TextField();
        TextField alert = new TextField();
        alert.setVisible(false);  // default to false - only show if alert is needed

     //   TextArea areaInfo = new TextArea();


        Button btnSubmit = new Button("Login with account ID");





        Button btnDeposit = new Button("Deposit");
        btnDeposit.setDisable(true);

        btnDeposit.setOnAction(e -> {
            float amount = Float.parseFloat(amountField.getText());
            cashMachine.deposit(amount);


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            accountNum.setText("Account Number : "+accountDataDisplay.getId());
            name.setText("name : "+accountDataDisplay.getName());
            e_mail.setText("e_mail : "+accountDataDisplay.getEmail());
            balance.setText("balance : "+accountDataDisplay.getBalance());
            if(!accountDataDisplay.getAlert().isEmpty()) {
                alert.setText("Alert : " + accountDataDisplay.getAlert());
                alert.setVisible(true);
            }
            else {
                alert.setVisible(false);
            }
     //       areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setDisable(true);

        btnWithdraw.setOnAction(e -> {
            float amount = Float.parseFloat(amountField.getText());
            cashMachine.withdraw(amount);


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            accountNum.setText("Account Number : "+accountDataDisplay.getId());
            name.setText("name : "+accountDataDisplay.getName());
            e_mail.setText("e_mail : "+accountDataDisplay.getEmail());
            balance.setText("balance : "+accountDataDisplay.getBalance());
            if(!accountDataDisplay.getAlert().isEmpty()) {
                alert.setText("Alert : " + accountDataDisplay.getAlert());
                alert.setVisible(true);
            }
            else {
                alert.setVisible(false);
            }

       //     areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Logout");
        btnExit.setDisable(true);

        btnExit.setOnAction(e -> {
            cashMachine.exit();
            


          //  areaInfo.setText(cashMachine.toString());
        });


        MenuItem menuItem1 = new MenuItem("1000");
        MenuItem menuItem2 = new MenuItem("2000");
        MenuItem menuItem3 = new MenuItem("3000");

        MenuButton menuButton =new MenuButton("Account Login", null, menuItem1, menuItem2, menuItem3);


       // btnSubmit.setOnAction(e -> {
       //     int id = Integer.parseInt(field.getText());

        menuItem2.setOnAction(e -> {

            cashMachine.login(2000);
            field.setText("2000");
            btnExit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnDeposit.setDisable(false);
            amountField.setDisable(false);


            AccountData accountDataDisplay =  cashMachine.getAccountData();
            accountNum.setText("Account Number : "+accountDataDisplay.getId());
            name.setText("name : "+accountDataDisplay.getName());
            e_mail.setText("e_mail : "+accountDataDisplay.getEmail());
            balance.setText("balance : "+accountDataDisplay.getBalance());
            if(!accountDataDisplay.getAlert().isEmpty()) {
                alert.setText("Alert : " + accountDataDisplay.getAlert());
                alert.setVisible(true);
            }
            else {
                alert.setVisible(false);
            }

        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(menuButton, field, amountField, flowpane,accountNum,name,e_mail, balance, alert);
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

}
