/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import common.util.CommonUtil;
import common.util.JsfHelper;
import common.util.Messages;
import recruitment.entitites.Candidates;
import recruitment.services.RecruitmentDbService;

/**
 *
 * @author ebubekir.gunerhanal
 */
@ManagedBean(name = "addCandidateBean")
@ViewScoped
public class AddCandidateBean {

    RecruitmentDbService dbService;

    private Candidates record;
    private List<Candidates> records;

    @PostConstruct
    private void init() {
        initRecord();
        initRecords();

    }

    private void initRecord() {
        record = new Candidates();
    }

    private void initRecords() {
        dbService = new RecruitmentDbService();
        records = dbService.findAll(Candidates.class);
    }

    public void newRecord() {
        initRecord();
    }

    public void saveRecord() throws Exception {
        dbService = new RecruitmentDbService();

        if (CommonUtil.isEmpty(record.getId())) {
            record.setId(dbService.getSequenceBigIntValue("seq_candidate_id"));
        }
        record.setRecordTime(CommonUtil.getCurrentTime());
        dbService.edit(record);
        init();
        JsfHelper.addSuccessMessage(Messages.getSaveSuccessMsg());
    }

    public void selectRecord(Candidates row) {
        record = row;
    }

    public void removeRecord(Candidates row) {
        dbService = new RecruitmentDbService();
        record = row;
        dbService.remove(record);
        init();
        JsfHelper.addSuccessMessage(Messages.getString("Data deleted."));
    }

    public Candidates getRecord() {
        return record;
    }

    public void setRecord(Candidates record) {
        this.record = record;
    }

    public List<Candidates> getRecords() {
        return records;
    }

    public void setRecords(List<Candidates> records) {
        this.records = records;
    }

}
