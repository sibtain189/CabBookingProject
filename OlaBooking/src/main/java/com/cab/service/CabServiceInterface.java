package com.cab.service;

import java.util.List;

import com.cab.exceptions.CabException;
import com.cab.model.Cab;

public interface CabServiceInterface {
	
    Cab addCab(Cab cab);

    Cab getCabById(Integer id) throws CabException;

    List<Cab> getAllCabs();

    Cab updateCab(Integer id, Cab cab) throws CabException;

    List<Cab> getCabByType(String cabType);

    Integer countCabByType(String cabType);

    Boolean updateAvailiblityStatus(Integer id, Boolean status);

    Cab deleteCabById(Integer id) throws CabException;
}
