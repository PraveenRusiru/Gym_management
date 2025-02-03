package lk.ijse.gym_management.DAO;

import lk.ijse.gym_management.DAO.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOType {
        CLIENTCONTACT,CLIENT,EXERCISEINSHEDULE,MEMBERSHIP,NUTRATIONPROGRAMME,PAYMENT,SHEDULE,NOTEFORCLIENT,EXERCISE,INVENTORY,ORDERS,ORDERDETAILS,SUPPLIMENT;
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CLIENTCONTACT:return new ClientContactDaoImpl();
            case CLIENT:return new ClientDaoImpl();
            case EXERCISEINSHEDULE:return new ExerciseInSheduleDaoImpl();
            case MEMBERSHIP:return new MembershipDaoImpl();
            case NUTRATIONPROGRAMME:return new NutrationProgrammeDaoImpl();
            case PAYMENT:return new PaymentDaoImpl();
            case SHEDULE:return new SheduleDaoImpl();
            case NOTEFORCLIENT:return new NoteForClientDaoImpl();
             case EXERCISE:return new ExerciseDaoImpl();
             case INVENTORY:return new InventoryDaoImpl();
            case ORDERS:return new OrderDaoImpl();
            case ORDERDETAILS:return new OrderDetailDaoImpl();
            case SUPPLIMENT:return new SupplimentDaoImpl();
            default:return null;
        }
    }
}
