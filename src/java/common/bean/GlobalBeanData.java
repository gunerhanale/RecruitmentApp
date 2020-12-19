package common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;


public class GlobalBeanData {
    private static Map<String,List<SelectItem>> CACHE_SELECT_ITEMS = new HashMap<String, List<SelectItem>>();
    private static Map<String,Map> CACHE_MAPS = new HashMap<String, Map>();
    
    public static void setCacheItems(String key, List<SelectItem> items){
        CACHE_SELECT_ITEMS.put(key, items);
    }
    public static List<SelectItem> getCacheItems(String key){
        return CACHE_SELECT_ITEMS.get(key);
    }
    public static void clearCacheItems(String key){
        CACHE_SELECT_ITEMS.put(key, null);
    }
    public static void clearAllCacheItems(){
        CACHE_SELECT_ITEMS.clear();
    } 
    public static void setCacheMap(String key, Map map){
        CACHE_MAPS.put(key, map);
    }
    public static Map getCacheMap(String key){
        return CACHE_MAPS.get(key);
    }
    public static void clearCacheMap(String key){
        CACHE_MAPS.put(key, null);
    }
    public static void clearAllCacheMap(){
        CACHE_MAPS.clear();
    } 
    
}
