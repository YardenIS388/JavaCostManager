package il.ac.Shenkar;
import il.ac.Shenkar.CostManager.Model.Cost;
import il.ac.Shenkar.CostManager.Model.DAOException;
import il.ac.Shenkar.CostManager.Model.DerbyDatabaseDAOModel;
import il.ac.Shenkar.CostManager.Model.IModel;
import il.ac.Shenkar.CostManager.View.MainApp;

import java.math.BigDecimal;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            int intValue = 40;
            BigDecimal bigDecimalValue = new BigDecimal(intValue);
            Date currentDate  = new Date();
            IModel dao = DerbyDatabaseDAOModel.getInstance();
            dao.createCostsTableIfNotExists();
            var cost1 = new Cost(1,"test",bigDecimalValue,"string2","string3",currentDate);
            dao.addCost(cost1);
            System.out.println(dao.getCosts());

        } catch (DAOException ex) {
            throw new RuntimeException(ex);
        }

       // new SimpleApp().go(args);
        System.out.println("SimpleApp finished");
        System.out.println("start");
       MainApp mainGui = new MainApp();
       mainGui.start();
    }
}
