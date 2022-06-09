package com.cab.service;

import java.util.List;

import com.cab.model.CabType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exceptions.CabException;
import com.cab.model.Cab;
import com.cab.repository.CabDAO;

@Service
public class CabService implements CabServiceInterface {

    @Autowired
    private CabDAO cabDAO;

    @Override
    public Cab addCab(Cab cab) {
        CabType type = cab.getCabType();
        cab.setPerKmRate(type.getPrice());
        cab.setSittingCapcity(type.sittingCapacity());
        return cabDAO.save(cab);
    }

    @Override
    public Cab getCabById(Integer id) throws CabException {
        return cabDAO.findById(id).orElseThrow(() -> new CabException("Cab doesn't exist with id : " + id));
    }

    @Override
    public List<Cab> getAllCabs() {
        return cabDAO.findAll();
    }

    @Override
    public Cab updateCab(Integer id, Cab cab) throws CabException {
        Cab cab1 = cabDAO.findById(id).orElseThrow(() -> new CabException("Cab with id : " + id + " does not exit.."));
        cab1.setPerKmRate(cab.getPerKmRate());
        cab1.setAvailable(cab.getAvailable());
        cab1.setCabType(cab.getCabType());
        cab1.setSittingCapcity(cab.getSittingCapcity());
        cabDAO.save(cab1);
        return cab1;
    }

    @Override
    public List<Cab> getCabByType(String cabType) {
        return cabDAO.findAllByCabType(cabType);
    }

    @Override
    public Integer countCabByType(String cabType) {
        return cabDAO.findAllByCabType(cabType).size();
    }

    @Override
    public Boolean updateAvailiblityStatus(Integer id, Boolean status) {
        Cab cab1 = cabDAO.findById(id).orElseThrow(() -> new CabException("Cab with id : " + id + " does not exit.."));
        cab1.setAvailable(status);
        cabDAO.save(cab1);
        return cab1.getAvailable();
    }

    @Override
    public Cab deleteCabById(Integer id) throws CabException {
        Cab st = cabDAO.findById(id).orElseThrow(() -> new CabException("Cab with id : " + id + " does not exit.."));
        cabDAO.delete(st);
        return st;
    }

}
