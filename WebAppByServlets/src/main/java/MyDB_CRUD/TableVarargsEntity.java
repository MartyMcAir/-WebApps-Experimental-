package MyDB_CRUD;

public class TableVarargsEntity {
    private final String[] args;

    public TableVarargsEntity(String... args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
