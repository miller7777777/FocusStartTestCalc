package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main extends Application {

    private String prompt = "Enter expression...";
    private String argument;
    private String result;

    @Override
    public void start(Stage primaryStage) throws Exception{

        final TextField textField = new TextField(prompt);
        textField.setMinWidth(180);

        // Clear
        Button buttonClear = new Button("Clear");
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.clear();
            }
        });

        // Calculate
        Button buttonCalc = new Button("Calculate");

        // Click this button without losing focus of the other component
        buttonCalc.setFocusTraversable(false);

        buttonCalc.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.copy();
                argument = textField.getText();
                result = calculate(argument);
                textField.setText(result);
                System.out.println(argument);
            }
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setVgap(5);
        root.setHgap(5);

        root.getChildren().addAll(textField, buttonClear,
                buttonCalc);

        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("JavaFX TextField (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();


//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }

    private String calculate(String argument) {

        double result;
        String res;
        Parser myParser = new Parser();     //Создаем объект класса Parser
        try {
            result = myParser.evaluate(argument);    //объект Parser принимает выражение в виде строки, разбирает  и вычисляет его и возвращает результат
            double newResult = new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).doubleValue();//округляем результат
            res = String.valueOf(newResult);  //результат преобразуется в строку

        } catch (Exception e) {
            res = "Error"; //если выражение некорректно или результат невозможно вычислить, Parser выбрасывает исключение, в качестве результата возвращаем null
        }


        return res;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
