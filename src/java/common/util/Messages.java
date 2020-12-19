package common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class Messages {
    private final static ResourceBundle bundle =  ResourceBundle.getBundle("/Bundle", new Locale("tr"));

    public static String getSaveSuccessMsg(){
    	return getString("Saved successfully!");
    }
    public static String getKayitSecilmediMsg(){
    	return getString("ISLEM ICIN EN AZ BIR KAYIT SECMELISINIZ!");
    }
    public static String getDeleteSuccessMsg(){
    	return getString("SILME ISLEMI BASARILI");
    }
    public static String getSaveErrorMsg(){
    	return getString("KAYDETME ISLEMI BASARISIZ!");
    }
    public static String getSaveErrorNullMsg(){
    	return getString("ILGILI ALANLARI DOLDURUNUZ!");
    }
    public static String getUpdateSuccessMsg(){
    	return getString("GUNCELLEME_ISLEMI_BASARILI");
    } 
    public static String getString(String key){
    	try{
    		String val = bundle.getString(key);
    		if(CommonUtil.isEmpty(val))
    			val = key;
    		return val;
    	}catch(Exception e){
    		return key;
    	}
        
    }
    public static String getString(String key, Object... params  ) {
        try {
            return MessageFormat.format(bundle.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }    
}