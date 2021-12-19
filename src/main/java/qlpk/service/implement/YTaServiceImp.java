package qlpk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qlpk.dto.UserDTO;
import qlpk.entity.BacSy;
import qlpk.entity.YTa;
import qlpk.modelUtil.YtaLuong;
import qlpk.repo.UserRepo;
import qlpk.repo.YTaRepo;
import qlpk.service.YTaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class YTaServiceImp implements YTaService{
    @Autowired
    private YTaRepo yTaRepo;
    @Autowired
    private UserRepo userRepo;
	public YTaServiceImp(YTaRepo yTaRepo, UserRepo userRepo) {
		this.yTaRepo = yTaRepo;
        this.userRepo = userRepo;
	}
    final long pageSize = 5;
    @Override
    public List<YTa> findAll() {
        return yTaRepo.findYTaByIsDelete(false);
    }

    @Override
    public void deleteYTa(int id) {
        YTa yTa = yTaRepo.getById(id);
        yTa.setDelete(true);
        updateYTa(yTa);
    }

    @Override
    public boolean saveYTa(YTa yTa, UserDTO userDTO) {
        yTa.setUser(userRepo.findByUserName(userDTO.getUserName(), false));
        yTaRepo.save(yTa);
        return true;
    }

    @Override
    public boolean updateYTa(YTa yTa) {
    	 yTaRepo.save(yTa);
         return true;
    }

    @Override
    public YTa searchYTaByCMT(String cmt) {
        return yTaRepo.findByCmt(cmt);
    }

    @Override
    public Optional<YTa> getById(int id) {
        return yTaRepo.findYTaByIdAndIsDelete(id, false);
    }

    @Override
    public YTa getByUsername(String username) {
        return yTaRepo.findYTaByUserAndIsDelete(userRepo.findByUserName(username, false), false);
        //return yTaRepo.findYTaByUser(userRepo.findByUserName(username));
    }
}
