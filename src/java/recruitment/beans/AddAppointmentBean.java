/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.beans;

import common.bo.ReportDataBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import common.util.CommonUtil;
import common.util.JsfHelper;
import common.util.Messages;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import recruitment.converters.CandidateToSelectItemConverter;
import recruitment.entitites.Appointment;
import recruitment.entitites.Candidates;
import recruitment.services.AppointmentDbService;
import recruitment.services.RecruitmentDbService;

/**
 *
 * @author ebubekir.gunerhanal
 */
@ManagedBean(name = "addAppointmentBean")
@ViewScoped
public class AddAppointmentBean {

    RecruitmentDbService dbService;
    AppointmentDbService appointmentDbService;
    
    private final int EKLENECEK_SAAT = 2;
    
    private Appointment record;
    private Appointment filter;
    private List<Appointment> records;

    private List<SelectItem> candList;
    private Map<BigInteger, String> candMap;
    private ScheduleModel eventModel;
    private ScheduleEvent event;

    @PostConstruct
    private void init() {
        initFilter();
        initRecord();
        initCandList();
        initCandMap();
        initEventModel();
    }

    private void initRecord() {
        record = new Appointment();
        record.setRecordDate(CommonUtil.getCurrentTime());
        event = new DefaultScheduleEvent("", CommonUtil.getCurrentTime(), CommonUtil.getCurrentTime(), record);

        record.setAppUserId(JsfHelper.getUser().getUserId());
        record.setAppUserName(JsfHelper.getUser().getFullName());
    }
    
    public void initFilter() {
        filter = new Appointment();
        filter.setStartDate(CommonUtil.getCurrentTime());
    }

    public void initCandMap() {
        candMap = new HashMap();
        dbService = new RecruitmentDbService();
        List<Candidates> candList = dbService.findAll(Candidates.class);
        for (Candidates cand : candList) {
            candMap.put(cand.getId(), cand.getFullName());
        }
    }
    
    private void initCandList() {
        dbService = new RecruitmentDbService();
        candList = CommonUtil.convert(dbService.em.createNamedQuery("Candidates.findAll", Candidates.class).getResultList(), new CandidateToSelectItemConverter());
    }

     public void initEventModel() {
        eventModel = new DefaultScheduleModel();
        appointmentDbService = new AppointmentDbService();
        records = appointmentDbService.appStatusModelFetchFilter(filter);

        for (Appointment appointment : records) {
            appointment.setCandidateName(candMap.get(appointment.getCandidateId()));
            String aciklama = CommonUtil.blankWhenNull(appointment.getCandidateName());

            if (!CommonUtil.isEmpty(appointment.getStartDate()) && !CommonUtil.isEmpty(appointment.getFinishDate())) {

                DefaultScheduleEvent row = new DefaultScheduleEvent(aciklama, appointment.getStartDate(), appointment.getFinishDate(), appointment);
                
                row.setStyleClass("event" + appointment.getCandidateId().intValue() % 7 + "");
                eventModel.addEvent(row);
            }
        }
    }
    
    public void saveRecord() throws Exception {
        dbService = new RecruitmentDbService();

        if (CommonUtil.isEmpty(record.getId())) {
            record.setId(dbService.getSequenceBigIntValue("seq_appointment_id"));
            record.setRecordDate(CommonUtil.getCurrentTime());
        }
        record.setAppUserId(JsfHelper.getUser().getUserId());
        record.setLastUpdate(CommonUtil.getCurrentTime());
        dbService.edit(record);
        init();
        JsfHelper.addSuccessMessage(Messages.getSaveSuccessMsg());
    }

    public void selectRecord(Appointment row) {
        record = row;
    }

    public void removeRecord(Appointment row) {
        dbService = new RecruitmentDbService();
        record = row;
        dbService.remove(record);
        init();
        JsfHelper.addSuccessMessage(Messages.getString("Data deleted."));
    }

    public void onEventSelect(SelectEvent selectEvent) {
        this.event = ((ScheduleEvent) selectEvent.getObject());
        record = (Appointment) event.getData();
        record.setStartDate(event.getStartDate());
        record.setFinishDate(event.getEndDate());
        if (!CommonUtil.isEmpty(record.getAppUserId())) {
            RequestContext.getCurrentInstance().execute("PF('eventDialogWid').show();");
        }
        if (JsfHelper.isUserThisAction()) {
            RequestContext.getCurrentInstance().execute("PF('eventDialogWid').show();");
        }

    }

    public void onDateSelect(SelectEvent selectEvent) {

        Date start = (Date) selectEvent.getObject();
        Date end = new Date(start.getTime());
        start.setHours(9);
        end.setHours(start.getHours() + 1);
        initRecord();
        record.setStartDate(start);
        record.setFinishDate(end);
        Appointment appointment = (Appointment) event.getData();
        this.event = new DefaultScheduleEvent("", start, end, appointment);

    }

    public void onEventMove(ScheduleEntryMoveEvent event) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

    }

    public void startDateChange() {
        record.setFinishDate(CommonUtil.addHourToDate(record.getStartDate(), EKLENECEK_SAAT));
    }

    public Appointment getRecord() {
        return record;
    }

    public void setRecord(Appointment record) {
        this.record = record;
    }

    public List<Appointment> getRecords() {
        return records;
    }

    public void setRecords(List<Appointment> records) {
        this.records = records;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Appointment getFilter() {
        return filter;
    }

    public void setFilter(Appointment filter) {
        this.filter = filter;
    }

    public List<SelectItem> getCandList() {
        return candList;
    }

    public void setCandList(List<SelectItem> candList) {
        this.candList = candList;
    }

}
