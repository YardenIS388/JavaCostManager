package il.ac.Shenkar.CostManager.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddCategoryDialog {
    private String category;
    private JFrame frameAddCategory;

    private JPanel panelAddCategory;
    private JLabel labelTitle;
    private JTextField tfCategory;
    private JLabel labelCategoryTf;
    private JButton btAddCategory;
    private JPanel panelContainer;

    public AddCategoryDialog(){
        frameAddCategory = new JFrame();
        labelTitle = new JLabel("Add New Cost Category");
        tfCategory = new JTextField();
        labelCategoryTf = new JLabel("Category Name: ");
        btAddCategory = new JButton("Add");
        panelAddCategory = new JPanel();
        panelContainer = new JPanel();

    }

    public void displayAddCategory(){
        System.out.println("display");
        frameAddCategory.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        btAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    category = tfCategory.getText();
                    System.out.println(category);
                    tfCategory.setText("");
                    panelAddCategory.add(new JLabel("Category added Successfully "));

                }catch(Exception ex){
                    System.out.println(ex);
                    panelAddCategory.add(new JLabel("Problem adding new category "));
                }
            }
        });
        frameAddCategory.setSize(800, 600);
        frameAddCategory.setVisible(true);
        frameAddCategory.add(panelAddCategory);
        panelAddCategory.add(labelTitle);
        labelTitle.setFont(new Font("Ariel",Font.BOLD, 18));
        panelAddCategory.add(panelContainer);
        panelContainer.setLayout(new GridLayout(3,1));
        panelContainer.add(labelCategoryTf);
        panelContainer.add(tfCategory);
        tfCategory.setSize(new Dimension(20, 20));
        panelContainer.add(btAddCategory);

    }

}
