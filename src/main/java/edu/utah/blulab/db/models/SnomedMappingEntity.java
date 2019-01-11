package edu.utah.blulab.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "snomed_mapping")
public class SnomedMappingEntity {
    private int id;
    private String cui;
    private String preferredLabel;
    private String synonym;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUI")
    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    @Basic
    @Column(name = "Preferred_Label")
    public String getPreferredLabel() {
        return preferredLabel;
    }

    public void setPreferredLabel(String preferredLabel) {
        this.preferredLabel = preferredLabel;
    }

    @Basic
    @Column(name = "Synonym")
    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnomedMappingEntity that = (SnomedMappingEntity) o;
        return id == that.id &&
                Objects.equals(cui, that.cui) &&
                Objects.equals(preferredLabel, that.preferredLabel) &&
                Objects.equals(synonym, that.synonym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cui, preferredLabel, synonym);
    }
}
