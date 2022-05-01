public class Assignment {
    private String EmployeeID;
    private String ProjectID;
    private String StartDate;

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    private String EndDate;


    public Assignment(String employeeID, String projectID, String startDate, String endDate) {
        EmployeeID = employeeID;
        ProjectID = projectID;
        StartDate = startDate;
        EndDate = endDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "EmployeeID='" + EmployeeID + '\'' +
                ", ProjectID='" + ProjectID + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                '}';
    }

}
