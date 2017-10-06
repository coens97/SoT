package receiver;

public class TableRow {
    private String text;
    private String id;

    public TableRow(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return text;
    }
}