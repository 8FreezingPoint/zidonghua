package uitls;

public class Text {
    private StringBuilder builder;

    public Text() {
        this.builder = new StringBuilder();
    }

    public Text append() {
        this.builder.append("\n");
        return this;
    }

    public Text append(String str) {
        this.builder.append(str).append("\n");
        return this;
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}
