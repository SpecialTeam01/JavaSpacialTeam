package model;

import java.util.Date;

public class Mission {
    private int missionId;
    private Astronaut astronaut;
    private Planet planet;
    private String missionName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;

    public Mission() {}

    public Mission(int missionId, Astronaut astronaut, Planet planet,
                   String missionName, Date startDate, Date endDate,
                   String status, String description) {
        this.missionId = missionId;
        this.astronaut = astronaut;
        this.planet = planet;
        this.missionName = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    public int getMissionId() { return missionId; }
    public void setMissionId(int missionId) { this.missionId = missionId; }

    public Astronaut getAstronaut() { return astronaut; }
    public void setAstronaut(Astronaut astronaut) { this.astronaut = astronaut; }

    public Planet getPlanet() { return planet; }
    public void setPlanet(Planet planet) { this.planet = planet; }

    public String getMissionName() { return missionName; }
    public void setMissionName(String missionName) { this.missionName = missionName; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Mission{" +
                "missionId=" + missionId +
                ", astronaut=" + astronaut +
                ", planet=" + planet +
                ", missionName='" + missionName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
