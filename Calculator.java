import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    //Deklarasi variabel ukuran Frame
    int boardWidth = 360;
    int boardHeight = 540;

    //Deklarasi variabel Warna
    Color customLightGrey = new Color(212, 212, 210);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(225, 149, 0);

    //Set buttonValue
    String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    //Deklarasi variabel Contain & Components
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    //A+B, A-B, A*B, A/B
    String A = "0";
    String operator = null;
    String B = null;

    Calculator(){

        //Set variable frame
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight); //boardWidth, boardHeight
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Set variable displayLabel
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        //Set variable displayPanel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        //Set variable buttonPanel
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        //Set function buttonPanel
        for(int i = 0; i < buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            //function setting warna top & right symbols button
            if (Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLightGrey);
                button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            }
            else {
                button.setBackground(customDarkGrey);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                         if(buttonValue == "=") {
                            if(A != null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(operator == "+"){
                                    displayLabel.setText(removeZeroDecinmal(numA+numB));
                                }
                                else if(operator == "-"){
                                    displayLabel.setText(removeZeroDecinmal(numA-numB));
                                }
                                else if(operator == "×"){
                                    displayLabel.setText(removeZeroDecinmal(numA*numB));
                                }
                                else if(operator == "÷"){
                                    displayLabel.setText(removeZeroDecinmal(numA/numB));
                                }
                                clearAll();
                            }
                         }
                         else if("+-×÷".contains(buttonValue)) {
                            if(operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                         }
                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if (buttonValue == "+/-") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecinmal(numDisplay));
                        }
                        else if (buttonValue == "%") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecinmal(numDisplay));
                        }
                    }
                    else{ //digit or .
                        if (buttonValue == "."){
                            if (!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        else if ("0123456789".contains(buttonValue)){
                            if (displayLabel.getText()=="0"){
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
            frame.setVisible(true);
        }
        
    }

    void clearAll(){
       A = "0";
       operator = null;
       B = null;
       
    }

    String removeZeroDecinmal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer .toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}           