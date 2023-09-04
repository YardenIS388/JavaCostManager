package il.ac.Shenkar.CostManager.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class MainApp {
    private JFrame frame;
    private JTextField tfId;
    private JTextField tfPrice;
    private JTextField tfName;
    private JLabel textTitle;
    private JLabel lblId, lblPrice, lblName;
    private JPanel panelId, panelPrice, panelName, panelBtAddProduct;
    private JButton btAddProduct;
    private JButton btThisMonthReport;
    private JButton btReportByMonthYear;
    private JTextField tfMessage;
    private JPanel panelAddCost;
    private JPanel panelWrapper;
    private JPanel panelTop;
    private JComboBox inputCurrency;
    private JComboBox inputCCategories;
    private JComboBox inputDay;
    private JComboBox inputMonth;
    private JComboBox inputYear;

    private String[] currencyOptions;
    private String[] categoryOptions;

    private JPanel panelReports;
    private JLabel labelReportsTitle;
    private JPanel panelThisMonth;
    private JLabel labelThisMonth;
    private JPanel panelMonthYear;
    private JLabel labelMonthYear;
    private JLabel thisMonthAmount;

    public MainApp() {
        frame = new JFrame("Cost Manager");
        panelWrapper = new JPanel();
        tfId = new JTextField(10);
        tfPrice = new JTextField(10);
        tfName = new JTextField(10);
        btAddProduct = new JButton("Add Cost");
        tfMessage = new JTextField(10);
        panelAddCost = new JPanel();
        lblId = new JLabel("Category:");
        lblPrice = new JLabel("Price:");
        lblName = new JLabel("Description:");
        panelId = new JPanel();
        panelPrice = new JPanel();
        panelName = new JPanel();
        panelBtAddProduct = new JPanel();
        textTitle = new JLabel("Cost Manager App");
        panelTop = new JPanel();
        currencyOptions = new String[]{"USD", "NIS", "EUR"};
        inputCurrency = new JComboBox(currencyOptions);
        categoryOptions = new String[]{"Food", "Fun", "Bills","Medical", "Clothing"};
        inputCCategories = new JComboBox(categoryOptions);

         panelReports = new JPanel();
         labelReportsTitle = new JLabel("Your Reports:");
        panelThisMonth = new JPanel();
         labelThisMonth = new JLabel("This Month");
         panelMonthYear = new JPanel();
        labelMonthYear = new JLabel("By Date");

        thisMonthAmount= new JLabel("3000$");

        btThisMonthReport = new JButton("View Report");
        btReportByMonthYear = new JButton("View Report");

        inputDay = new JComboBox(new String[]{"day","1","2","3","4","5", "6", "7", "8", "9", "10", "11","12", "13", "14", "15", "16", "17", "18"});
        inputMonth = new JComboBox(new String[]{"month","1","2","3","4","5", "6", "7", "8", "9", "10", "11","12"});
        inputYear = new JComboBox(new String[]{"year","1999","2000","2001","2002"});

    }

    public void start() {
        //handling the ui events
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        btAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String category = (String) inputCCategories.getSelectedItem();
                    String currency = (String) inputCurrency.getSelectedItem();
                    double price = Double.parseDouble(tfPrice.getText());
                    String description = tfName.getText();
                    //TODO: handle the data in the inputs to send to ViewModel
                    System.out.println(price + "\n" +  currency + "\n" +category + "\n" + description);
                    setMessage("Cost Updated Successfully");



                } catch(Exception ex) {
                    setMessage("problem with parsing one or more of the fields. adding product failed.");
                    System.out.println(ex);
                }
            }



        });

        btThisMonthReport.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    System.out.println(e);
                    ReportDialog report = new ReportDialog();
                    report.displayReport();
                }catch(Exception ex){
                    System.out.println(ex);
                    setMessage("Sorry, something went wrong with the report");
                }
            }
        });

        btReportByMonthYear.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    System.out.println(e);
                    ReportDialog report = new ReportDialog();
                    report.reportBySpesificDate(1,1,1);

                }catch(Exception ex){
                    System.out.println(ex);
                    setMessage("Sorry, something went wrong with the report");
                }
            }
        });

        /* * Frame Settings */
        frame.setLayout(new BorderLayout());
        frame.add(tfMessage, BorderLayout.SOUTH);
        frame.add(panelWrapper, BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setVisible(true);

        /* * Wrapper Panel Settings */
        panelWrapper.setLayout(new GridLayout(5,1));
        panelWrapper.add(panelTop);
        panelWrapper.add(panelAddCost);
        panelWrapper.setBorder(new EmptyBorder(5, 15, 5, 15));
        panelWrapper.setBackground(Color.lightGray);
        panelWrapper.add(panelReports);

        /* * Top Panel Settings */
        panelTop.setLayout(new FlowLayout());
        panelTop.add(textTitle);
        panelTop.setBorder(new EmptyBorder(25, 5, 5, 5));
        panelTop.setBackground(Color.lightGray);
        textTitle.setFont(new Font("Ariel",Font.BOLD, 24));

        /* * Add Cost  Panel Settings */
        panelAddCost.setLayout(new FlowLayout());
        panelAddCost.setBackground(Color.white);
        panelAddCost.add(new JLabel("Add New Cost"));
        panelAddCost.add(panelId);
        panelAddCost.add(panelPrice);
        panelAddCost.add(panelName);
        panelAddCost.add(panelBtAddProduct);
        panelAddCost.setBorder(new RoundedBorder(10));

        panelAddCost.add(inputCurrency);

        panelId.setLayout(new FlowLayout());
        panelId.add(lblId);
        panelId.add(inputCCategories);
        panelId.setBackground(Color.white);

        panelPrice.setLayout(new FlowLayout());
        panelPrice.add(lblPrice);
        panelPrice.add(inputCurrency);
        panelPrice.add(tfPrice);
        panelPrice.setBackground(Color.white);

        panelName.setLayout(new FlowLayout());
        panelName.add(lblName);
        panelName.add(tfName);
        panelName.setBackground(Color.white);

        panelBtAddProduct.setLayout(new FlowLayout());
        panelBtAddProduct.add(btAddProduct);

        panelBtAddProduct.setBackground(Color.white);

        /* Dashbord View*/
        panelReports.setLayout(new GridLayout(3, 1));
        panelReports.add(labelReportsTitle);
        labelReportsTitle.setFont(new Font("Ariel",Font.BOLD, 18));
        panelReports.add(panelThisMonth);

        panelThisMonth.add(labelThisMonth);
        panelReports.add(panelMonthYear);
        panelMonthYear.add( labelMonthYear);
        panelThisMonth.add(new JLabel(getCurrentDate().getMonth() + " / " + getCurrentDate().getYear()));
        panelThisMonth.add(thisMonthAmount);
        panelThisMonth.add(btThisMonthReport);
        panelThisMonth.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMonthYear.add(inputDay);
        panelMonthYear.add(inputMonth);
        panelMonthYear.add(inputYear);
        panelMonthYear.add(btReportByMonthYear);


    }

    private LocalDate getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        return currentDate;
    }

    public void setMessage(String text) {
        tfMessage.setText(text);
    }


    public void reset() {
        tfId.setText("");
        tfName.setText("");
        tfPrice.setText("");
    }



    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}
