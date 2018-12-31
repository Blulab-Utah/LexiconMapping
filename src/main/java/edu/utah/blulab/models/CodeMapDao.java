package edu.utah.blulab.models;

import javax.persistence.*;
import java.util.Objects;

public class CodeMapDao {
    private int id;
    private String epicLabel;
    private String epicCode;
    private String standardCode;
    private String standardLabel;
    private String standardCodeSystem;
    private String epicCodeSystem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEpicLabel() {
        return epicLabel;
    }

    public void setEpicLabel(String epicLabel) {
        this.epicLabel = epicLabel;
    }

    public String getEpicCode() {
        return epicCode;
    }

    public void setEpicCode(String epicCode) {
        this.epicCode = epicCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardLabel() {
        return standardLabel;
    }

    public void setStandardLabel(String standardLabel) {
        this.standardLabel = standardLabel;
    }

    public String getStandardCodeSystem() {
        return standardCodeSystem;
    }

    public void setStandardCodeSystem(String standardCodeSystem) {
        this.standardCodeSystem = standardCodeSystem;
    }

    public String getEpicCodeSystem() {
        return epicCodeSystem;
    }

    public void setEpicCodeSystem(String epicCodeSystem) {
        this.epicCodeSystem = epicCodeSystem;
    }
}
