package model;

import java.util.Date;

public class Astronaut {
    private int astronautId;
    private String name;
    private Date birthDate;
    private String nationality;
    private int missionsCompleted;
    private boolean active;

    public Astronaut() {
    }

    public Astronaut(int astronautId, String name, Date birthDate, String nationality, int missionsCompleted, boolean active) {
        this.astronautId = astronautId;
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.missionsCompleted = missionsCompleted;
        this.active = active;
    }

    public int getAstronautId() {
        return astronautId;
    }

    public void setAstronautId(int astronautId) {
        this.astronautId = astronautId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getMissionsCompleted() {
        return missionsCompleted;
    }

    public void setMissionsCompleted(int missionsCompleted) {
        this.missionsCompleted = missionsCompleted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "astronautId=" + astronautId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", missionsCompleted=" + missionsCompleted +
                ", active=" + active +
                '}';
    }
}
