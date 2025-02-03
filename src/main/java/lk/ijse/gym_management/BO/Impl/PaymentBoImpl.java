package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.PaymentBO;
import lk.ijse.gym_management.Controller.AddNewMembershipController;
import lk.ijse.gym_management.Controller.MembershipController;
import lk.ijse.gym_management.Controller.MembershipRowController;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.*;
import lk.ijse.gym_management.DB.DBConnection;
import lk.ijse.gym_management.DTO.PaymentDto;
import lk.ijse.gym_management.Entity.ExercisesInShedule;
import lk.ijse.gym_management.Entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBO {
    PaymentDaoImpl paymentDao=(PaymentDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    ClientContactDaoImpl clientContactDao=(ClientContactDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENTCONTACT);
    ClientDaoImpl clientDao=(ClientDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENT);
    MembershipDaoImpl membershipDao=(MembershipDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBERSHIP);
    SheduleDaoImpl sheduleDao=(SheduleDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SHEDULE);
    ExerciseInSheduleDaoImpl exerciseInSheduleDao=(ExerciseInSheduleDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISEINSHEDULE);
    NutrationProgrammeDaoImpl nutrationProgrammeDao=(NutrationProgrammeDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.NUTRATIONPROGRAMME);
    NoteForClientDaoImpl noteForClientDao=(NoteForClientDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.NOTEFORCLIENT);

    @Override
    public String getNextId() throws SQLException {
        return paymentDao.getNextId();
    }

    @Override
    public void setEntity(PaymentDto dto) throws SQLException {
        paymentDao.setEntity(new Payment(dto.getPaymentId(),dto.getMemberId(),dto.getSheduleId(),dto.getAmount(),dto.getDate(),dto.getStatus()));
    }

    @Override
    public PaymentDto getEntity() throws SQLException {
        Payment payment = paymentDao.getEntity();
        PaymentDto paymentDto=null;
        if(payment!=null){
            paymentDto=new PaymentDto(payment.getPaymentId(), payment.getMemberId(), payment.getSheduleId(), payment.getAmount(), payment.getDate(), payment.getStatus());
        }
        return paymentDto;
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ArrayList<Payment> payments = paymentDao.getAll();
        ArrayList<PaymentDto> dtos = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentDto paymentDto=new PaymentDto(payment.getPaymentId(), payment.getMemberId(), payment.getSheduleId(), payment.getAmount(), payment.getDate(), payment.getStatus());
            dtos.add(paymentDto);
        }
        return dtos;
    }

    @Override
    public boolean update() throws SQLException {
      return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
     return    paymentDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
        Connection connection=null;
        if(MembershipRowController.isFromMembershipRowController){
            try {
                connection=DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
                if(membershipDao.update()){
                    if(paymentDao.save()){
                        connection.commit();
                        return true;
                    }
                }
                connection.rollback();
                System.out.println("paymen saved Error");
                return false;
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                connection.rollback();
                return false;
            }finally {
                connection.setAutoCommit(true);
            }

        } else if (AddNewMembershipController.isFromAddNewMembership) {
            try {
                connection=DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
                if(membershipDao.save()){
                    System.out.println("Membership Saved");
                    if(paymentDao.save()){
                        System.out.println("Payment Saved");
                        connection.commit();
                        makeEverythingNll();
                        return true;
                    }
                }
                connection.rollback();
                System.out.println("payment saved Error");
                return false;
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                connection.rollback();
                return false;
            }finally {
                connection.setAutoCommit(true);
            }
        } else{
            boolean isMembershipSaved=false;
            boolean isSheduleSvaed=false;
            try{
                connection= DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
                if(clientDao.save()){
                    if (clientContactDao.save()){
                        //System.out.println("Client id "+noteForClientDao.getEntity().getClientId());
                        if(membershipDao.getEntity()!=null){
                            if(membershipDao.save()){
                                isMembershipSaved=true;
                            }
                        }
                        if(sheduleDao.getEntity()!=null) {
                            if(noteForClientDao.save()){
                                if (sheduleDao.save()) {
                                    if (exerciseInSheduleDao.save()) {
                                        if (nutrationProgrammeDao.save()) {
                                            isSheduleSvaed = true;
                                        }
                                    }
                                }
                            }
                        }

                        if(paymentDao.save()){
                            connection.commit();
                            System.out.println("Payment Done !!");
                            return true;
                        }

                    }

                }
                connection.rollback();
                System.out.println("There is a problem in Transaction");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                connection.rollback();
                return false;
            }finally {
                makeEverythingNll();
                connection.setAutoCommit(true);
            }
        }


   //return paymentDao.save();
    }
    private void makeEverythingNll() throws SQLException {
         paymentDao.setEntity(null);
         clientContactDao.setEntity(null);
         clientDao.setEntity(null);
         membershipDao.setEntity(null);
         sheduleDao.setEntity(null);
         exerciseInSheduleDao.setEntity(null);
         nutrationProgrammeDao.setEntity(null);
         noteForClientDao.setEntity(null);


    }
}
