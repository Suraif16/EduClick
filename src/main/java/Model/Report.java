package Model;

import DAO.ReportDAO;

public class Report {
    private int count;
    private String reportFlag;
    private String reportID;
    private String contentID;
    private String type;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Report(String contentID, String type) {
        this.contentID = contentID;
        this.type = type;
    }
    public void Report(Report report){
    ReportDAO reportDAO = new ReportDAO();
        reportDAO.insert(this);
    }
    public Report(Report report){
        this.contentID = report.getContentID();
        this.type = report.getType();
    }
}
