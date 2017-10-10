package receiver;

import dto.IssueDto;

public class TableRow {
    private IssueDto issue;
    private String id;

    public TableRow(IssueDto issue, String id) {
        this.issue = issue;
        this.id = id;
    }

    public IssueDto getIssue() {
        return issue;
    }

    public void setIssue(IssueDto issue) {
        this.issue = issue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return issue.toString();
    }
}