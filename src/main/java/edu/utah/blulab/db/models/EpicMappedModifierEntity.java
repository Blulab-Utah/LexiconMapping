package edu.utah.blulab.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EpicMappedModifier")
public class EpicMappedModifierEntity {
    private int id;
    private String epicLabel;
    private String epicCode;
    private String standardCode;
    private String standardLabel;
    private String standardCodeSystem;
    private String epicCodeSystem;
    private String type;
    private String regex;
    private String direction;
    private String lex;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Epic_label")
    public String getEpicLabel() {
        return epicLabel;
    }

    public void setEpicLabel(String epicLabel) {
        this.epicLabel = epicLabel;
    }

    @Basic
    @Column(name = "Epic_code")
    public String getEpicCode() {
        return epicCode;
    }

    public void setEpicCode(String epicCode) {
        this.epicCode = epicCode;
    }

    @Basic
    @Column(name = "Standard_code")
    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    @Basic
    @Column(name = "Standard_label")
    public String getStandardLabel() {
        return standardLabel;
    }

    public void setStandardLabel(String standardLabel) {
        this.standardLabel = standardLabel;
    }

    @Basic
    @Column(name = "Standard_code_system")
    public String getStandardCodeSystem() {
        return standardCodeSystem;
    }

    public void setStandardCodeSystem(String standardCodeSystem) {
        this.standardCodeSystem = standardCodeSystem;
    }

    @Basic
    @Column(name = "Epic_code_system")
    public String getEpicCodeSystem() {
        return epicCodeSystem;
    }

    public void setEpicCodeSystem(String epicCodeSystem) {
        this.epicCodeSystem = epicCodeSystem;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Regex")
    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Basic
    @Column(name = "Direction")
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "Lex")
    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpicMappedModifierEntity that = (EpicMappedModifierEntity) o;
        return id == that.id &&
                Objects.equals(epicLabel, that.epicLabel) &&
                Objects.equals(epicCode, that.epicCode) &&
                Objects.equals(standardCode, that.standardCode) &&
                Objects.equals(standardLabel, that.standardLabel) &&
                Objects.equals(standardCodeSystem, that.standardCodeSystem) &&
                Objects.equals(epicCodeSystem, that.epicCodeSystem) &&
                Objects.equals(type, that.type) &&
                Objects.equals(regex, that.regex) &&
                Objects.equals(direction, that.direction) &&
                Objects.equals(lex, that.lex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, epicLabel, epicCode, standardCode, standardLabel, standardCodeSystem, epicCodeSystem, type, regex, direction, lex);
    }
}
