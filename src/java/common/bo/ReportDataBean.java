package common.bo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ReportDataBean implements Cloneable, Comparable {

    private String rowName;
    private Map rowValues;
    private Map<String, BigDecimal> rowValuesNumeric;
    private Map<String, Boolean> rowValuesBoolean;
    private Integer maxIndex = 0;

    public ReportDataBean() {

    }

    public ReportDataBean(String rowName) {
        this.rowName = rowName;
        this.rowValues = new HashMap();
        this.rowValuesNumeric = new HashMap<String, BigDecimal>();
        this.rowValuesBoolean = new HashMap<String, Boolean>();
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public Integer getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(Integer maxIndex) {
        this.maxIndex = maxIndex;
    }

    public Map getRowValues() {
        return rowValues;
    }

    public void setRowValues(Map rowValues) {
        this.rowValues = rowValues;
    }

    public Map<String, BigDecimal> getRowValuesNumeric() {
        return rowValuesNumeric;
    }

    public void setRowValuesNumeric(Map<String, BigDecimal> rowValuesNumeric) {
        this.rowValuesNumeric = rowValuesNumeric;
    }

    public ReportDataBean clone() {
        try {
            return (ReportDataBean) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Boolean> getRowValuesBoolean() {
        return rowValuesBoolean;
    }

    public void setRowValuesBoolean(Map<String, Boolean> rowValuesBoolean) {
        this.rowValuesBoolean = rowValuesBoolean;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ReportDataBean)) {
            return false;
        }
        ReportDataBean other = (ReportDataBean) obj;
        if (this.rowName != null && this.rowName.equals(other.getRowName())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object arg0) {
        return 0;
    }

}
