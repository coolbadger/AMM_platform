package com.amm.service;

import com.amm.entity.MachTerminalEntity;
import com.amm.entity.client.MachTerminal;

import java.util.List;

/**
 * Created by csw on 2016/8/7 11:51.
 * Explain:
 */
public interface MachTerminalService {
    MachTerminalEntity create(MachTerminalEntity machTerminalEntity);

    List<MachTerminal> findAll(Boolean isBind);

    MachTerminalEntity findByMachId(Integer machId);

    MachTerminalEntity updateToUnBind(Integer id, MachTerminalEntity machTerminalEntity);
}
