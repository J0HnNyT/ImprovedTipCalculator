/*
Programmer Name: Johnny Taylor
Assignment Start: 3/17/2018 10:00am
Assignment Completion: 3/17/2018 12:32am
Total Hours for Assignment: 2.5
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class TipCalculatorController {
    
    private static final NumberFormat currency = 
       NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = 
       NumberFormat.getPercentInstance();
    
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    
    @FXML
    private Label tipPercentageLabel;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField tipTextField;
    @FXML
    private TextField totalTextField;
    @FXML
    private Slider tipPercentageSlider;
    @FXML
    private TextField peopleInPartyTextField;
    @FXML
    private TextField totalPerPersonTextField;
    
    //? clear button action event.
    @FXML
    void coinBoxButtonPressed(ActionEvent event) {
        amountTextField.clear();
        peopleInPartyTextField.clear();
        tipTextField.clear();
        totalTextField.clear();
        totalPerPersonTextField.clear();
        
    }
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try{
            
            //get User input for number of people in the party.
            BigDecimal peopleInParty = new BigDecimal(
               peopleInPartyTextField.getText());
            
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            //divide the number of people in the party by the total after tip.
            BigDecimal perPersonTotal = total.divide(peopleInParty);
          
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
            //set the locale format of the per person total for the text field. 
            totalPerPersonTextField.setText(currency.format(perPersonTotal));
            
           }
        catch(NumberFormatException e){
            //prompts the User to input the party number if not typed in.
            peopleInPartyTextField.setText("Enter Number of People");
            peopleInPartyTextField.selectAll();
            
            amountTextField.setText("Enter Amount");
            amountTextField.selectAll();
            peopleInPartyTextField.requestFocus();
            
        }
    }//End Event Handler.
    
    public void initialize() {
        currency.setRoundingMode(RoundingMode.CEILING);
        
        tipPercentageSlider.valueProperty().addListener(
           new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov,
               Number oldValue, Number newValue)
                {
                    tipPercentage 
                   = BigDecimal.valueOf(newValue.intValue()/ 100.0);
                tipPercentageLabel.setText(percent.format(tipPercentage));
                }
            }
        );
    }    
}
