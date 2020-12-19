package common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import common.constant.Globals;
import common.util.CommonUtil;
import common.util.JsfHelper;
import recruitment.converters.CountryPhoneCodeToSelectItemConverter;
import recruitment.entitites.PhoneCountryCode;
import recruitment.services.RecruitmentDbService;


/**
 *
 * @author ebubekir.gunerhanal
 */
@Named
@ManagedBean(name = "globalBean")
@ApplicationScoped
public class GlobalBean {

    private String dateTimePattern = Globals.DATE_TIME_FORMAT_SCREEN;
    private String datePattern = Globals.DATE_FORMAT_SCREEN;
    private String timePattern = Globals.TIME_FORMAT_SCREEN;
    
    private String version = Globals.VERSION;
    private String lang = Globals.LANGUAGE;
    private Locale locale = Globals.LOCALE;
    private String timeZone = Globals.TIME_ZONE;

    private List<SelectItem> evetHayirList;
    private List<SelectItem> printOptionList;
    
    private List<SelectItem> countyPhoneCodeList;
    private RecruitmentDbService dbService;
   
    @PostConstruct
    public void init() {

    }
    
    public void isUserAuthorized() throws Exception{
        if(!JsfHelper.isUserThisAction()){
            JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/error/yetki.xhtml");
        }
    }
    
    public void sessionTimeOutUpdate(){
        if(Globals.DEBUG){
            System.out.println("SESSION C::" + JsfHelper.getHttpSession().getCreationTime());
            System.out.println("SESSION M::" + JsfHelper.getHttpSession().getMaxInactiveInterval());
            System.out.println("SESSION L::" + JsfHelper.getHttpSession().getLastAccessedTime());            
        }
    }

    public List<SelectItem> getCountyPhoneCodeList() {
        final String key = "countyPhoneCodeList";
        countyPhoneCodeList = GlobalBeanData.getCacheItems(key);
        dbService = new RecruitmentDbService();
        if (CommonUtil.isEmpty(countyPhoneCodeList)) {
            List<PhoneCountryCode> records = dbService.findAll(PhoneCountryCode.class);
            countyPhoneCodeList = CommonUtil.convert(records, new CountryPhoneCodeToSelectItemConverter());
            GlobalBeanData.setCacheItems(key, countyPhoneCodeList);
        }
        return countyPhoneCodeList;
    }

    public void setCountyPhoneCodeList(List<SelectItem> countyPhoneCodeList) {
        this.countyPhoneCodeList = countyPhoneCodeList;
    }
    
    

    public List<SelectItem> getEvetHayirList() {
        
        final String key = "evetHayirList";
        evetHayirList = GlobalBeanData.getCacheItems(key);
        if (CommonUtil.isEmpty(evetHayirList)) {
            evetHayirList = new ArrayList<>();
            evetHayirList.add(new SelectItem(1, "Evet"));
            evetHayirList.add(new SelectItem(0, "Hayır"));
            GlobalBeanData.setCacheItems(key, evetHayirList);
        }

        return evetHayirList;        
    }

    public void setEvetHayirList(List<SelectItem> evetHayirList) {
        this.evetHayirList = evetHayirList;
    }

    public List<SelectItem> getPrintOptionList() {
        final String key = "printOptionList";
        printOptionList = GlobalBeanData.getCacheItems(key);
        if (CommonUtil.isEmpty(printOptionList)) {
            printOptionList = new ArrayList<>();
            printOptionList.add(new SelectItem("PDF", "PDF"));
            printOptionList.add(new SelectItem("DOCX", "Word"));
            printOptionList.add(new SelectItem("RTF", "Rtf"));
            printOptionList.add(new SelectItem("XLS", "Excel"));
            GlobalBeanData.setCacheItems(key, printOptionList);
        }

        return printOptionList;            
    }
    
    public void setPrintOptionList(List<SelectItem> printOptionList) {
        this.printOptionList = printOptionList;
    }
    public void emptyAction(){
        
    }
       
    public String getDateTimePattern() {
        return dateTimePattern;
    }

    public void setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
