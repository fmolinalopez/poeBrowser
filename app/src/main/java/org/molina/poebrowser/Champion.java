package org.molina.poebrowser;

import java.io.Serializable;

/**
 * Created by Vikin on 15/03/2018.
 */

public class Champion implements Serializable {

    private static final long serialVersionUID = 5222401203184130144L;

    private String rank;
    private boolean dead;
    private boolean online;
    private String name;
    private String level;
    private String clase;
    private String experience;
    private int challenges;

    public Champion(String rank, boolean dead, boolean online, String name, String level, String clase, String experience, int challenges) {
        this.rank = rank;
        this.dead = dead;
        this.online = online;
        this.name = name;
        this.level = level;
        this.clase = clase;
        this.experience = experience;
        this.challenges = challenges;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRank() {
        return rank;
    }

    public boolean getDead() {
        return dead;
    }

    public boolean getOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getClase() {
        return clase;
    }

    public String getExperience() {
        return experience;
    }

    public int getChallenges() {
        return challenges;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "rank='" + rank + '\'' +
                ", dead='" + dead + '\'' +
                ", online='" + online + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", clase='" + clase + '\'' +
                ", experience='" + experience + '\'' +
                ", challenges=" + challenges +
                '}';
    }
}
