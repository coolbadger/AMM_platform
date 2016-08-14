package com.amm.service;

import com.amm.entity.TerminalEntity;

import java.util.List;

/**
 * Created by csw on 2016/8/4 13:28.
 * Explain:
 */
public interface TerminalService {
    TerminalEntity create(TerminalEntity terminalEntity);

    List<TerminalEntity> findAllByActive(Boolean active, Boolean isBind);

    TerminalEntity update(TerminalEntity terminal);

    TerminalEntity findOne(Integer id);

    TerminalEntity delete(Integer id);

    TerminalEntity findTerminalCode(String terminalCode);
}
