package com.amm.repository;

import com.amm.entity.TerminalEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/8/4 13:32.
 * Explain:
 */
public interface TerminalRepository extends PagingAndSortingRepository<TerminalEntity, Integer> {
    List<TerminalEntity> findAllByActive(Boolean active);
}
