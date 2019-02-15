package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

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
            int amount = Integer.parseInt(field.getText());
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
            int amount = Integer.parseInt(field.getText());
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

        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            btnExit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnDeposit.setDisable(false);



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

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane,accountNum,name,e_mail, balance, alert);
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
