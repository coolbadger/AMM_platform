package com.amm.controller;

import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.client.MachTerminal;
import com.amm.service.RefMachTerminalService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨思名 on 2017/3/12.
 */
@RestController
@RequestMapping("api/RefMachTerminal")
public class RefMachTerminalController {

    @Autowired
    private RefMachTerminalService refMachTerminalService;

    @Autowired
    private MachTerminalController machTerminalController;


    @RequestMapping(method = RequestMethod.GET)
    public List<RefMachTerminalEntity> getAll(Boolean isBind){
        List<MachTerminal> machTerminal=machTerminalController.getAll(true);
        List<RefMachTerminalEntity> listRef= refMachTerminalService.findAll();
        List<RefMachTerminalEntity> tempList=new ArrayList<RefMachTerminalEntity>();
        for(int i=0;i<machTerminal.size();i++){
            Integer refId=machTerminal.get(i).getRefMachTerminalId();
            for(int a=0;a<listRef.size();a++){
                Integer ID=listRef.get(a).getId();
                if(refId==ID){
                    tempList.add(listRef.get(a));
                }
            }
        }

        return tempList;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public RefMachTerminalEntity updateMachWidth(@PathVariable Integer id, @RequestBody RefMachTerminalEntity refMachTerminalEntity){

        Validate.notNull(id, "The id of orgUser must not be null, update failure.");
        Validate.notNull(refMachTerminalEntity, "The orgUser object must not be null, update failure.");
        RefMachTerminalEntity refMachTerminal=refMachTerminalService.findById(id);
        refMachTerminalEntity.setCallNo(refMachTerminal.getCallNo());
        refMachTerminalEntity.setMachState(refMachTerminal.getMachState());
        refMachTerminalEntity.setId(refMachTerminal.getId());
        refMachTerminalEntity.setTerminalCode(refMachTerminal.getTerminalCode());
        refMachTerminalEntity.setWorkingType(refMachTerminal.getWorkingType());
        refMachTerminalEntity.setDrivingArea(refMachTerminal.getDrivingArea());
        refMachTerminalEntity.setMachCode(refMachTerminal.getMachCode());
        refMachTerminalEntity.setMachId(refMachTerminal.getMachId());
        refMachTerminalEntity.setMachName(refMachTerminal.getMachName());
        refMachTerminalEntity.setTerminalName(refMachTerminal.getTerminalName());
        refMachTerminalEntity.setWorkArea(refMachTerminal.getWorkArea());
        refMachTerminalEntity.setTerminalState(refMachTerminal.getTerminalState());
        refMachTerminalEntity.setWorkTime(refMachTerminal.getWorkTime());
        RefMachTerminalEntity updated = refMachTerminalService.updateRefMachTerminal(refMachTerminalEntity);
        return updated;

    }


/*

    */
/*删除*//*

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        Validate.notNull(id, "The id of machine must not be null, update failure.");
        refMachTerminalService.delete(id);
    }
*/



}
