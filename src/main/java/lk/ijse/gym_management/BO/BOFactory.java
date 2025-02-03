package lk.ijse.gym_management.BO;

import lk.ijse.gym_management.BO.Impl.*;
import lk.ijse.gym_management.DTO.ClientDto;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType {
        CLIENT,CLIENTCONTACT,EXERCISEINSHEDULE,MEMBERSHIP,NUTRATION,PAYMENT,SHEDULE,NOTEFORCLIENT,EXERCISE,INVENTORY,ORDERS,ORDERDETAILS,SUPPLIMENT;
    }
    public  SuperBO getBOType(BOType type) {
        switch (type) {
            case CLIENT:return new ClientBoImpl();
            case CLIENTCONTACT:return new ClientContactBoImpl();
            case EXERCISEINSHEDULE:return new ExerciseInSheduleBoImpl();
            case MEMBERSHIP:return new MembershipBoImpl();
            case NUTRATION:return new NutrationBoImpl();
            case PAYMENT:return new PaymentBoImpl();
            case SHEDULE:return new SheduleBoImpl();
            case NOTEFORCLIENT:return new NoteForClientBOImpl();
            case EXERCISE:return new ExerciseBoImpl();
            case SUPPLIMENT:return new SupplimentBOImpl();
            case ORDERS:return new OrderBOImpl();
            case ORDERDETAILS:return new OrderDetailBOImpl();
            case INVENTORY:return new InventoryBOImpl();
            default:return null;
        }
    }

}
