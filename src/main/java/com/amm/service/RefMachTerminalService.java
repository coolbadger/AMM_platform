package com.amm.service;

import com.amm.entity.RefMachTerminalEntity;
import com.amm.repository.RefMachTerminalRepository;

import java.util.List;

/**
 * Created by csw on 2016/8/6 19:50.
 * Explain:
 */
public interface RefMachTerminalService {
    RefMachTerminalEntity findOne(Integer refId);
    List<RefMachTerminalEntity> findAll();
    RefMachTerminalEntity findById(Integer Id);
    RefMachTerminalEntity updateRefMachTerminal(RefMachTerminalEntity refMachTerminal);

}
