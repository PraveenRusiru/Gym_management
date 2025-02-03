package lk.ijse.gym_management.BO.Impl;

import lk.ijse.gym_management.BO.MembershipBo;
import lk.ijse.gym_management.DAO.DAOFactory;
import lk.ijse.gym_management.DAO.Impl.MembershipDaoImpl;
import lk.ijse.gym_management.DTO.MembershipDto;
import lk.ijse.gym_management.Entity.Membership;

import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipBoImpl implements MembershipBo {
   MembershipDaoImpl membershipDao =(MembershipDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBERSHIP);
    @Override
    public String getNextId() throws SQLException {
        return membershipDao.getNextId();
    }

    @Override
    public void setEntity(MembershipDto dto) throws SQLException {
            membershipDao.setEntity(new Membership(dto.getMemberId(),dto.getClientId(),dto.getStartDate(),dto.getEndDate(), dto.getMembershipType()));
    }

    @Override
    public MembershipDto getEntity() throws SQLException {
        Membership membership = membershipDao.getEntity();
        MembershipDto membershipDto = null;
        if (membership != null) {
            membershipDto=new MembershipDto(membership.getMemberId(),membership.getClientId(),membership.getStartDate(),membership.getEndDate(),membership.getMembershipType());
        }
        return membershipDto;
    }

    @Override
    public ArrayList<MembershipDto> getAll() throws SQLException {
        ArrayList<Membership> memberships = membershipDao.getAll();
        ArrayList<MembershipDto> membershipDtos = new ArrayList<>();
        for (Membership membership : memberships) {
            MembershipDto membershipDto=new MembershipDto(membership.getMemberId(),membership.getClientId(),membership.getStartDate(),membership.getEndDate(),membership.getMembershipType());
            membershipDtos.add(membershipDto);
        }
        return membershipDtos;
    }

    @Override
    public boolean update() throws SQLException {
      return   membershipDao.update();
    }

    @Override
    public boolean delete(String id) throws SQLException {
      return   membershipDao.delete(id);
    }

    @Override
    public boolean save() throws SQLException {
       return membershipDao.save();
    }
}
