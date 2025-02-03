package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.ClientContactBO;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.ClientContactDaoImpl;
import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.Entity.ClientContact;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientContactBoImpl implements ClientContactBO {
    ClientContactDaoImpl clientContactDao=(ClientContactDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENTCONTACT);
    @Override
    public String getNextId() throws SQLException {
        return clientContactDao.getNextId();
    }

    @Override
    public void setEntity(ClientContactDto dto) throws SQLException {
        ClientContact clientContact=new ClientContact(dto.getNic(),dto.getPhone(),dto.getEmail(),dto.getClient_id());
        clientContactDao.setEntity(clientContact);
    }

    @Override
    public ClientContactDto getEntity() throws SQLException {
        ClientContact clientContact=clientContactDao.getEntity();
        ClientContactDto clientContactDto=null;
        if(clientContact!=null){
            clientContactDto=new ClientContactDto(clientContact.getNic(),clientContact.getPhone(),clientContact.getEmail(),clientContact.getClient_id());
        }
        return clientContactDto;
    }

    @Override
    public ArrayList<ClientContactDto> getAll() throws SQLException {
        ArrayList<ClientContact> clientContacts=clientContactDao.getAll();
        ArrayList<ClientContactDto> clientContactDtos=new ArrayList<>();
        for (ClientContact clientContact:clientContacts){
            ClientContactDto clientContactDto=new ClientContactDto(clientContact.getNic(),clientContact.getPhone(),clientContact.getEmail(),clientContact.getClient_id());
            clientContactDtos.add(clientContactDto);
        }
        return clientContactDtos;
    }

    @Override
    public boolean update() throws SQLException {
       return clientContactDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
       return clientContactDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
       return clientContactDao.save();
    }

    @Override
    public String getClientId(String clientNic) throws SQLException {
        return clientContactDao.getClientId(clientNic);
    }
}
