package thecodingstache.tedxuthlarissa.Model;

public class Schedule {
    private String titleProgram;
    private String description;
    private String time;

    public Schedule(String titleProgram, String description, String time) {
        this.titleProgram = titleProgram;
        this.description = description;
        this.time = time;
    }
    public Schedule(){

    }

    public String getTitleProgram() {
        return titleProgram;
    }

    public void setTitleProgram(String titleProgram) {
        this.titleProgram = titleProgram;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

