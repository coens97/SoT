package sender;

public class TableRow {
    private String text;
    private int id;

    public TableRow(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return text;
    }
}
