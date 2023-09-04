package il.ac.Shenkar.CostManager.Model;

import java.util.Date;
import java.util.List;

public interface IModel {
    void addCost(Cost cost) throws DAOException;

    List<Cost> getCostsByDate(Date date);

    List<Cost> getCosts();
}
