package common.util;


import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import common.bo.ReportDataBean;
import common.constant.Globals;

/**
 *
 * @author ebubekir.gunerhanal
 */
public class CommonUtil {

    public static <E> List<ReportDataBean> toReportDataList(List<E> list) {
        List<ReportDataBean> reportDataList = new ArrayList<>();
        int i = 1;
        for (E e : list) {
            ReportDataBean row = new ReportDataBean();
            row.setRowName("" + (i++));
            row.setRowValues(toMap(e));
            reportDataList.add(row);
        }
        return reportDataList;
    }

    public static <E> Map toMap(E e) {
        Map map = new HashMap();
        try {
            Class<? extends Object> c = e.getClass();
            Method[] m = c.getDeclaredMethods();
            for (Method method : m) {
                if (method.getName().startsWith("get")) {
                    String name = method.getName();
                    name = name.substring(3, 4).toLowerCase() + name.substring(4, name.length());
                    map.put(name, method.invoke(e, new Object[0]));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public static String blankWhenNull(String str) {
        return isEmpty(str) ? "" : str;
    }

    public static <E> String toXml(E e) {
        try {
            JAXBContext context = JAXBContext.newInstance(e.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            m.marshal(e, stringWriter);
            String str = stringWriter.toString();
            return str;

        } catch (JAXBException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return "";
    }

    public static <E> E fromXml(String path, E e) {

        try {
            JAXBContext context = JAXBContext.newInstance(e.getClass());
            Unmarshaller u = context.createUnmarshaller();
            E ret = (E) u.unmarshal(e.getClass().getResource(path)); // ok

            return ret;
        } catch (JAXBException ex) {
            ex.printStackTrace();
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }

    public static String formatNumber(BigDecimal val) {
        if (val == null) {
            return "";
        }
        String number = "";
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("tr"));
        try {
            number = formatter.format(val);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static String formatNumber(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        while (str.contains(".") && str.endsWith("0")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        try {
            BigDecimal number = new BigDecimal(str);
            /*
			 * if (number.scale() < 2) { number = number.setScale(2); }
             */
            return number.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertISO88599ToISO88591(String str) {
        str = str.replace("ç", "c");
        str = str.replace("Ç", "C");
        str = str.replace("ğ", "g");
        str = str.replace("Ğ", "G");
        str = str.replace("ı", "i");
        str = str.replace("İ", "I");
        str = str.replace("ö", "o");
        str = str.replace("Ö", "O");
        str = str.replace("ş", "s");
        str = str.replace("Ş", "S");
        str = str.replace("ü", "u");
        str = str.replace("Ü", "U");
        return str;
    }

    public static long dayDiff(Date start, Date end) {
        return TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
    }

    public static int monthDiff(Date start, Date end) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);

        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

        return monthDiff;
    }

    public static Date addHourToDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    public static Date addMonthToDate(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public static Date addMinuteToDate(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static Date addDayToDate(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    public static String formatDate(Date date) {
        return formatDate(date, "dd/MM/yyyy");

    }

    public static String formatDateTime(Date date) {
        return formatDate(date, "dd/MM/yyyy  HH:mm");

    }

    public static String formatTime(Date date) {
        return formatDate(date, "HH:mm");
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        Format formatter = new SimpleDateFormat(pattern);

        return formatter.format(date);
    }

    public static String getOnlyNumerics(String str) {
        if (str == null) {
            return "";
        }
        str = str.replaceAll("[^0-9]", "");

        return str;
    }

    public static String toDbStr(String str) {
        if (str == null) {
            return "";
        }
        str = str.replaceAll("%", "");
        str = str.replaceAll("!", "");

        return str;
    }

    public static Date toDate(String dateStr, String pattern) {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDateWithoutTime(Date date, String pattern)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(sdf.format(date));

    }

    public static Timestamp getCurrentTime() {
        Timestamp ts = new Timestamp(new Date().getTime());

        return ts;
    }

    public static boolean isEmpty(Collection c) {
        if (c == null || c.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Long val) {
        if (val == null || val.intValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Integer val) {
        if (val == null || val.intValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Number val) {
        if (val == null || val.intValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(BigInteger val) {
        if (val == null || val.intValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(BigDecimal val) {
        if (val == null || val.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static <T, R> List<R> convert(Collection<T> in,
            Converter<T, R> converter) {
        List<R> result = new ArrayList<R>();

        for (T type : in) {
            R item = converter.convert(type);
            result.add(item);
        }

        return result;
    }

    public static <T, R> R convert(T in, Converter<T, R> converter) {

        R item = converter.convert(in);

        return item;
    }

    public static Boolean isEmptyList(Collection col) {
        return (col == null || col.isEmpty());
    }

    public static String generateUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne.toString();
    }

    /*
	 * public static void copyProperties(Object toObj, Object fromObj) {
	 * StringBuffer buff = new StringBuffer();
	 * 
	 * Field[] fields = fromObj.getClass().getDeclaredFields(); String
	 * getMethodName = "", setMethodName = ""; for (int i = 0; i <
	 * fields.length; i++) { getMethodName = "get" +
	 * StringUtils.capitalize(fields[i].getName()); setMethodName = "set" +
	 * StringUtils.capitalize(fields[i].getName());
	 * 
	 * try { Method getMethod = fromObj.getClass().getMethod(getMethodName);
	 * Object o = getMethod.invoke(fromObj); if (o != null) { Method setMethod =
	 * toObj.getClass().getMethod(setMethodName, o.getClass());
	 * setMethod.invoke(toObj, getMethod.invoke(fromObj)); } } catch (Exception
	 * e) { System.err.println("class:" + fields[i].getClass() + "
	 * getMethodName" + getMethodName); e.printStackTrace(); } }
	 * 
	 * }
     */
    public static String customFormat(String pattern, BigDecimal value) {
        if (pattern == null || value == null) {
            return null;
        }

        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
        // System.out.println(value + "  " + pattern + "  " + output);
    }

    public static String toFit(String str, int size) {
        if (str == null) {
            str = "";
        }
        str = str.trim();
        if (str.length() > size) {
            str = str.substring(str.length() - size);
        } else if (str.length() < size) {
            int len = str.length();
            for (int i = 0; i < (size - len); i++) {
                str = "0" + str;
            }
        }
        return str;
    }

    public static void selectAll(List<? extends Selectable> list,
            Boolean selected) {
        for (Selectable selectable : list) {
            selectable.setSelected(selected);
        }
    }

    public static <T> List<T> getSelectedList(List<? extends Selectable> list,
            Boolean selected) {
        List<T> result = new ArrayList<T>();
        for (Selectable selectable : list) {
            if (selected.equals(selectable.getSelected())) {
                result.add((T) selectable);
            }
        }
        return result;
    }

    public static String md5(String key) {

        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(key.getBytes(), 0, key.length());

            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }

    public static BigDecimal posnetStringToDecimal(String str) {
        while (str.contains(".") && str.startsWith("0")) {
            str = str.substring(1, str.length());
        }
        BigDecimal value = new BigDecimal(str);
        value = value.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return value;
    }

    public static int posnetStringToInt(String str) {
        while (str.contains(".") && str.startsWith("0")) {
            str = str.substring(1, str.length());
        }
        BigDecimal value = new BigDecimal(str);

        return value.intValue();
    }

    public static int toInteger(String val) {
        if (isEmpty(val)) {
            return 0;
        }
        try {
            return Integer.valueOf(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static BigInteger toBigInteger(String val) {
        if (isEmpty(val)) {
            return BigInteger.ZERO;
        }
        try {
            return new BigInteger(val);
        } catch (NumberFormatException e) {
            return BigInteger.ZERO;
        }
    }

    public static long toLong(String val) {
        if (isEmpty(val)) {
            return 0;
        }
        try {
            return Long.valueOf(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static short toShort(String val) {
        if (isEmpty(val)) {
            return 0;
        }
        try {
            return Short.valueOf(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int roundUp(int n) {
        return (n + 4) / 5 * 5;
    }

    public static String toReportString(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }

    public static List<String> getMonthList() {

        try {
            DateFormatSymbols symbols = new DateFormatSymbols(Globals.LOCALE);
            String[] monthNames = symbols.getMonths();
            List<String> list = new ArrayList<String>();
            for (String monthName : monthNames) {
                if (!isEmpty(monthName)) {
                    list.add(monthName);
                }
            }
            return list;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    public static String addQuate(String str) {
        if (str != null) {
            str = str.trim();
            return "'" + str + "'";
        } else {
            return "''";
        }

    }

    public static BigDecimal isNull(BigDecimal d1) {
        return (d1 == null ? BigDecimal.ZERO : d1);
    }

    public static Map<String, Object> getMediumModalOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", true);
        options.put("width", "800px");
        options.put("height", "800px");

        return options;

    }

    public static Map<String, Object> getLargeModalOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", true);
        options.put("width", "90%");
        options.put("height", "800px");

        return options;

    }

    public static void main(String[] str) {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, 2, 1);
        System.out.println("diff:::" + monthDiff(new Date(), cal.getTime()));

    }

}
