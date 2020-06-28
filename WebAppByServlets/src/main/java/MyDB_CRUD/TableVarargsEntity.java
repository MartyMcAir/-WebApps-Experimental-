package MyDB_CRUD;

import java.lang.annotation.Target;

public class TableVarargsEntity {
    private final String[] args;

    public TableVarargsEntity(String... args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
