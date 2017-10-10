package sender;

public class TableRow {
    private String text;
    private String response;


    public TableRow(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return text  + " : "  + (response == null ? "...": response);
    }
}
