package com.amm.service;

import com.amm.entity.WorkerEntity;

import java.util.List;

/**
 * Created by csw on 2016/8/4 12:47.
 * Explain:
 */
public interface WorkerService {
    WorkerEntity create(WorkerEntity workerEntity);

    List<WorkerEntity> findAllByActive(Boolean active);

    WorkerEntity update(WorkerEntity worker);

    WorkerEntity findOne(Integer id);

    WorkerEntity delete(Integer id);

    WorkerEntity findByUserName(String userName);

    WorkerEntity findByUserNameAndPassword(String userName, String password);

    boolean isValidUserName(String userName);

    WorkerEntity findByWorkerName(String workerName);
}
