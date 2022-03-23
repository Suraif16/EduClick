package Model;

import DAO.ReportInsertDAO;

public class Report {
    private int count;
    private String reportFlag;
    private String reportID;
    private String contentID;
    private String type;
    private String UserID;
    private String AnswerID;
    private String NF_postID;
    private String EpostID;


    public int getCount() {
        return count;
    }
    public void setCount(Integer count) {
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

    public String getUserID() {return UserID;}
    public void setUserID(String userID) { UserID = userID;}

    public String getAnswerID() {return AnswerID;}
    public void setAnswerID(String answerID) {AnswerID = answerID;}

    public String getNF_postID() {return NF_postID;}
    public void setNF_postID(String NF_postID) {this.NF_postID = NF_postID;}

    public String getEpostID() { return EpostID; }
    public void setEpostID(String epostID) {EpostID = epostID;}

    public Report(String contentID, String type) {
        this.contentID = contentID;
        this.type = type;
    }

    public Report() { }

    public Report selectdao(){
        ReportInsertDAO reportInsertDAO = new ReportInsertDAO();
        return reportInsertDAO.select(this);
    }
    public Report updatedao(String ID,Integer count){
        ReportInsertDAO reportInsertDAO = new ReportInsertDAO();
        return reportInsertDAO.update(ID,count);
    }

    public String reportinsert(String contentID, String type){
        ReportInsertDAO reportInsertDAO = new ReportInsertDAO();
        return reportInsertDAO.insert(contentID,type);
    }



}
