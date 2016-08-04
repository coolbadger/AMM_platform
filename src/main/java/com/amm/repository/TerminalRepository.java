package com.amm.repository;

import com.amm.entity.TerminalEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/8/4 13:32.
 * Explain:
 */
public interface TerminalRepository extends PagingAndSortingRepository<TerminalEntity, Integer> {
}
