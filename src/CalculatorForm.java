import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm {
    private JTextField displayField;
    private JPanel CalculatorView;
    private JButton CancelEntry;
    private JButton Clear;
    private JButton Division;
    private JButton Seven;
    private JButton Eight;
    private JButton Nine;
    private JButton Multiplication;
    private JButton Four;
    private JButton Five;
    private JButton Six;
    private JButton Subtraction;
    private JButton One;
    private JButton Two;
    private JButton Three;
    private JButton Addition;
    private JButton Minus_Plus;
    private JButton Zero;
    private JButton Dot;

    enum CalcOP {NONE, ADD, SUB, MULTIPLY, DIVIDE};
    private boolean isDigitEnterMode = false;
    private String displayString = "";
    private double result = 0;
    private CalcOP lastOP = CalcOP.NONE;
    public CalculatorForm() {

        Zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("0");
            }
        });
        One.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("1");
            }
        });
        Two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("2");
            }
        });
        Three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("3");
            }
        });
        Four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("4");
            }
        });
        Five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("5");
            }
        });
        Six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("6");
            }
        });
        Seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("7");
            }
        });
        Eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("8");
            }
        });
        Nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("9");
            }
        });
        Dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit(".");
            }
        });
        CancelEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDigitEnterMode=false;
                displayField.setText("0");

            }
        });
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result=0;
                lastOP=CalcOP.NONE;
                isDigitEnterMode=false;
                displayField.setText("0.0");
            }
        });

        Multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                evalLastOP(CalcOP.MULTIPLY);

            }
        });
        Subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.SUB);
            }
        });
        Addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.ADD);
            }
        });
        Equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                evalLastOP(CalcOP.NONE);

            }
        });
        Minus_Plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=displayField.getText();
                if(text=="0.0"){
                    return;
                }
                char fist_c=text.charAt(0);
                if(fist_c=='-'){
                    displayField.setText(text.substring(1));
                }
                else{
                    displayField.setText("-"+ text);
                }
            }
        });
        Division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.DIVIDE);
            }
        });
    }
    private void enterDigit(String digit)
    {
        if (!isDigitEnterMode) {
            if (digit == ".")
                displayString = "0.";
            else
                displayString = digit;
            isDigitEnterMode = true;
        }
        else {
// Only floating-point number
// can start with 0
            if (displayString == "0" && digit != ".")
                return;
            displayString += digit;
        }
        displayField.setText(displayString);
    }
    private void evalLastOP(CalcOP currOP)
    {
        double value = Double.parseDouble(displayField.getText());
// Note that we evaluate last Operator, not current

        try{
            switch(lastOP){
                case ADD:
                    result += value;
                    break;
                case SUB:
                    result -= value;
                    break;
                case DIVIDE:
                    result /= value;
                    break;
                case MULTIPLY:
                    result *= value;
                    break;
                default:
                    result=value;
                    break;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        displayField.setText(Double.toString(result));
        isDigitEnterMode = false;
        lastOP = currOP;
    }
    public void testClick(String button) throws Exception
    {
        switch (button)
        {
            case "+": Addition.doClick(); break;
            case "-": Subtraction.doClick(); break;
            case "*": Multiplication.doClick(); break;
            case "/": Division.doClick(); break;
            case ".": Dot.doClick(); break;
            case "=": Equal.doClick(); break;
            case "±": Minus_Plus.doClick(); break;
            case "CE": CancelEntry.doClick(); break;
            case "CLEAR": Clear.doClick(); break;
            case "0": Zero.doClick(); break;
            case "1": One.doClick(); break;
            case "2": Two.doClick(); break;
            case "3": Three.doClick(); break;
            case "4": Four.doClick(); break;
            case "5": Five.doClick(); break;
            case "6": Six.doClick(); break;
            case "7": Seven.doClick(); break;
            case "8": Eight.doClick(); break;
            case "9": Nine.doClick(); break;
            default:
                throw new Exception("Error! No button " + button);
        }
    }
    public void showWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new CalculatorForm().CalculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public double getResult() {
        return result;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculatorForm");
        frame.setContentPane(new CalculatorForm().CalculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton Equal;
}
