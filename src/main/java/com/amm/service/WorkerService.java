package com.amm.service;

import com.amm.entity.WorkerEntity;

import java.util.List;

/**
 * Created by csw on 2016/8/4 12:47.
 * Explain:
 */
public interface WorkerService {
    WorkerEntity create(WorkerEntity workerEntity);

    List<WorkerEntity> findAll();

    WorkerEntity update(WorkerEntity worker);

    WorkerEntity findOne(Integer id);

    WorkerEntity delete(Integer id);
}
