package il.ac.Shenkar.CostManager.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;


public class ReportDialog {

    private JFrame reportFrame;
    private JPanel panelMonthReport;
    private String[] categories;

    public ReportDialog() {
        reportFrame = new JFrame("Monthly Report");
        panelMonthReport = new JPanel();
//        panelMonthReport.add(new JLabel("Your Expense Breakdown"));
        categories = new String[]{"Food", "Fun", "Bills", "Medical", "Clothing"};
    }

    private double getCategorySum(int day, int month, int year) {
        double sum = Math.random();
        return sum;
    }

    public void displayReport() {
        reportFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        reportFrame.setSize(800, 600);
        reportFrame.setVisible(true);
        reportFrame.add(panelMonthReport);
        panelMonthReport.setLayout(new GridLayout(categories.length + 1, 1));
        panelMonthReport.add(new JLabel("Your Month Breakdown:"));
        double total = 0;
        LocalDate currentDate = LocalDate.now();
        displayReportByType(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());

        panelMonthReport.add(new JLabel("Total Spendings:" + total + " $"));
    }

    public void reportBySpesificDate(int day, int month, int year) {
        reportFrame.setSize(800, 600);
        reportFrame.setVisible(true);
        reportFrame.add(panelMonthReport);
        panelMonthReport.setLayout(new GridLayout(categories.length + 1, 1));
        panelMonthReport.add(new JLabel("Your Month Breakdown:"));
        displayReportByType(day, month, year);
    }

    private void displayReportByType(int day, int month, int year) {
        if (panelMonthReport != null) {
            String reportType = "For:" + day + " / " + month + " / " + year;
            panelMonthReport.add(new JLabel(reportType));
            double total = 0;
            for (int i = 0; i < categories.length; i++) {
                JPanel panelContainer = new JPanel();
                panelContainer.setLayout(new GridLayout(2, 1));
                panelContainer.add(new JLabel(categories[i]));
                double categorySum = getCategorySum(day, month, year);
                total = total + categorySum;
                String sumStrung = String.valueOf(categorySum);
                panelContainer.add(new JLabel(sumStrung + " $"));
                panelMonthReport.add(panelContainer);
            }

        }

    }
}
