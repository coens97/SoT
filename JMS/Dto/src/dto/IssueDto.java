package dto;

public class IssueDto {
    private String game;
    private String issue;
    private String username;

    public IssueDto() {
    }

    public String getGame() {

        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return game + " - " + username;
    }
}
