package com.amm.controller;

import com.amm.entity.OrgUserEntity;
import com.amm.entity.WorkerEntity;
import com.amm.exception.InvalidOperatorException;
import com.amm.service.BaseOrgService;
import com.amm.service.OrgUserService;
import com.amm.service.WorkerService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/7/24 18:30.
 * Explain:组织机构接口
 */

@RestController
@RequestMapping("api/workers")
public class WorkerController extends BaseController{

    @Autowired
    private WorkerService workerService;

    @Autowired
    private BaseOrgService baseOrgService;

    @Autowired
    private OrgUserService orgUserService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerEntity create(@RequestBody(required = true) WorkerEntity workerEntity) {

        Validate.notNull(workerEntity, "The workerEntity must not be null, create failure.");
        Validate.notNull(workerEntity.getUserName(), "The userName must not be null, create failure.");
        Validate.notNull(workerEntity.getPassword(), "The password must not be null, create failure.");

//         if(workerService.isValidUserName(workerEntity.getUserName())) {
//            throw new InvalidOperatorException("用户名无效，数据库中已存在！");
//        }

        WorkerEntity workerEntity1 = workerService.findByWorkerName(workerEntity.getUserName());

        if (workerEntity1!=null && workerEntity1.getActive()){
            throw new InvalidOperatorException("用户名无效，数据库中已存在");
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        String password = userDetails.getPassword();
        OrgUserEntity currentUser = orgUserService.findOrgUser(userName, password);
        Validate.notNull(currentUser, "The currentUser is null, no user login, create failure.");

        workerEntity.setOrgId(currentUser.getOrgId());
        workerEntity.setCreator(currentUser.getUserName());
        workerEntity.setCreateTime(new Date());

        WorkerEntity created = workerService.create(workerEntity);

        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkerEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        List<WorkerEntity> workerEntityList = workerService.findAllByActive(active);

        return workerEntityList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public WorkerEntity update(@PathVariable Integer id,
                                @RequestBody WorkerEntity worker) {

        Validate.notNull(id, "The id of worker must not be null, update failure.");
        Validate.notNull(worker, "The worker object must not be null, update failure.");

        worker.setId(id);

        WorkerEntity updated = workerService.update(worker);

        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WorkerEntity findOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return workerService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public WorkerEntity deleteOne(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        return workerService.delete(id);
    }
}
