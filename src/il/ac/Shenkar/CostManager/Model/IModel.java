package il.ac.Shenkar.CostManager.Model;

import java.util.Date;
import java.util.List;

public interface IModel {
    void createCostsTableIfNotExists() throws DAOException;

    void addCost(Cost cost) throws DAOException;

    List<Cost> getCostsByDate(Date date) throws DAOException;

    List<Cost> getCosts() throws DAOException;
}
