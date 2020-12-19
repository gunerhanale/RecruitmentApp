/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.beans;

import common.constant.Globals;
import common.util.CommonUtil;
import common.util.JsfHelper;
import common.util.Messages;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recruitment.entitites.AppUser;
import recruitment.services.RecruitmentDbService;

/**
 *
 * @author ebubekir.gunerhanal
 */
@ManagedBean(name = "signUpBean")
@ViewScoped
public class SignUpBean {

    private RecruitmentDbService dbService;
    private AppUser record;

    @PostConstruct
    private void init() {
        initRecord();
    }

    private void initRecord() {
        record = new AppUser();
    }

    public void saveRecord() throws Exception {
        dbService = new RecruitmentDbService();
        record.setUserId(dbService.getSequenceBigIntValue("seq_user_id"));
        record.setLastLoginDate(CommonUtil.getCurrentTime());
        dbService.edit(record);
        
//        JsfHelper.addSuccessMessage(Messages.getSaveSuccessMsg());
//        JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/index.xhtml?faces-redirect=true");
        JsfHelper.getHttpSession().setAttribute("user", record);
        JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/public/home.xhtml?faces-redirect=true");
    }

    public AppUser getRecord() {
        return record;
    }

    public void setRecord(AppUser record) {
        this.record = record;
    }

}
