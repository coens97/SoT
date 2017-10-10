package receiver;

import dto.IssueDto;

import javax.jms.Destination;

public class TableRow {
    private IssueDto issue;
    private String id;
    private Destination reply;

    public Destination getReply() {
        return reply;
    }

    public void setReply(Destination reply) {
        this.reply = reply;
    }

    public TableRow(IssueDto issue, String id, Destination reply) {
        this.issue = issue;
        this.id = id;
        this.reply = reply;
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