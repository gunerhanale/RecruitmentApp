package recruitment.beans;

import common.constant.Globals;
import common.util.JsfHelper;
import common.util.Messages;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recruitment.entitites.AppUser;
import recruitment.services.AppUserDbService;

/**
 *
 * @author ebubekir.gunerhanal
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

    private AppUserDbService appUserDbService;
    private AppUser record;

    @PostConstruct
    public void init() {
        initKullanici();
    }

    public void initKullanici() {
        record = new AppUser();
    }

    public void login() throws IOException {

        appUserDbService = new AppUserDbService();
        AppUser findedUser = appUserDbService.fetchUSer(record.getUserName());
        if (findedUser == null) {
            JsfHelper.addErrorMessage(Messages.getString("User not found, please before sign up!"));
            return;
        }

        if (!record.getPassword().equals(findedUser.getPassword())) {
            if (!Globals.DEBUG) {
                JsfHelper.addErrorMessage(Messages.getString("password is wrong"));
                return;
            }
        }
        JsfHelper.getHttpSession().setAttribute("user", findedUser);
        JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/public/home.xhtml?faces-redirect=true");
    }

    public void signUp() throws IOException {
        JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/public/signUp.xhtml");
    }

    public void logout() throws IOException {
        JsfHelper.getHttpSession().setAttribute("user", null);
        JsfHelper.getHttpSession().invalidate();
        JsfHelper.getExternalContext().redirect("/" + Globals.APP_NAME + "/index.xhtml?faces-redirect=true");
    }

    public AppUser getRecord() {
        return record;
    }

    public void setRecord(AppUser record) {
        this.record = record;
    }

}
