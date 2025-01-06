import java.util.Objects;

class WorkoutRoutine {
    private String name;
    private int duration;

    public WorkoutRoutine(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "WorkoutRoutine{name='" + name + "', duration=" + duration + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WorkoutRoutine routine = (WorkoutRoutine) obj;
        return duration == routine.duration && Objects.equals(name, routine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }
}
