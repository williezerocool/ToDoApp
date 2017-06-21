package corbos.todo;

import java.time.LocalDate;

public class ToDo {

    private String name;
    private boolean complete;
    private LocalDate createDate = LocalDate.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setIsComplete(boolean isComplete) {
        this.complete = isComplete;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate date) {
        this.createDate = date;
    }

}