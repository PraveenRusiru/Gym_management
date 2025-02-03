package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.ClientBo;
import lk.ijse.gym_management.DAO.ClientDao;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DTO.ClientContactDto;
import lk.ijse.gym_management.DTO.ClientDto;
import lk.ijse.gym_management.Entity.Clients;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientBoImpl implements ClientBo {
    ClientDao clientDao= (ClientDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENT);
    @Override
    public String getNextId() throws SQLException {
        return clientDao.getNextId();
    }

    @Override
    public void setEntity(ClientDto dto) throws SQLException {
        Clients client = new Clients(dto.getClientId(),dto.getClientName(),dto.getAge(),dto.getGender(),dto.getGoal(),dto.getHeight(),dto.getWeight(),dto.getFat_per(),dto.getJoined_date());
        clientDao.setEntity(client);
    }

    @Override
    public ClientDto getEntity() throws SQLException {
        Clients client = clientDao.getEntity();
        ClientDto clientDto=null;
        if(client!=null){
            clientDto=new ClientDto(client.getClientId(), client.getClientName(), client.getAge(), client.getGender(), client.getGoal(), client.getHeight(), client.getWeight(), client.getFat_per(), client.getJoined_date());
        }
        return clientDto;
    }

    @Override
    public ArrayList<ClientDto> getAll() throws SQLException {
        ArrayList<Clients> clients = clientDao.getAll();
        ArrayList<ClientDto> clientDtos = new ArrayList<>();
        for (Clients client : clients) {
            ClientDto clientDto = new ClientDto(client.getClientId(), client.getClientName(), client.getAge(), client.getGender(), client.getGoal(), client.getHeight(), client.getWeight(), client.getFat_per(), client.getJoined_date());
            clientDtos.add(clientDto);
        }
        return clientDtos;
    }

    @Override
    public boolean update() throws SQLException {
       return clientDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
           return clientDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
            return clientDao.save();
    }
}
