package common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import recruitment.entitites.AppUser;

public class JsfHelper {
    
    public static AppUser getUser() {
        return (AppUser) getHttpSession().getAttribute("user");
    }
    
    public static boolean isUserThisAction() {
        AppUser user = getUser();
        if (user == null) {
            return false;
        }
        return true;
    }

    public static HttpSession getHttpSession() {
        return getHttpSession(false);
    }

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static HttpSession getHttpSession(boolean val) {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(val);
    }

   
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                msg, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, "");
        FacesContext.getCurrentInstance().addMessage("messages", facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                msg, "");
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get(key);
    }

    public static Map getRequest() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap();
    }

    public static Object getObjectFromRequestParameter(
            String requestParameterName,
            javax.faces.convert.Converter converter, UIComponent component) {
        String theId = getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(),
                component, theId);
    }


    public static InputStream getResource(String path) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getResourceAsStream(path);
    }

    public static Map<String, String> pageParamToMap(String pageParam) {
        Map<String, String> retValue = new HashMap<String, String>();
        if (pageParam == null) {
            return retValue;
        }
        String[] pageParams = pageParam.split(";");

        for (String param : pageParams) {
            String[] params = param.split("=");
            if (params != null && params.length > 1) {
                retValue.put(params[0], params[1]);
            }
        }
        return retValue;
    }
}
