package edu.utah.blulab.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SnomedMappedModifier")
public class SnomedMappedModifierEntity {
    private int id;
    private String term;
    private String snomedPreferredLabel;
    private String snomedCui;
    private String hl7FamilyMemberRoleCode;
    private String hl7InternalId;
    private String lex;
    private String type;
    private String regex;
    private String direction;

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
    @Column(name = "TERM")
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Basic
    @Column(name = "SNOMED_PREFERRED_LABEL")
    public String getSnomedPreferredLabel() {
        return snomedPreferredLabel;
    }

    public void setSnomedPreferredLabel(String snomedPreferredLabel) {
        this.snomedPreferredLabel = snomedPreferredLabel;
    }

    @Basic
    @Column(name = "SNOMED_CUI")
    public String getSnomedCui() {
        return snomedCui;
    }

    public void setSnomedCui(String snomedCui) {
        this.snomedCui = snomedCui;
    }

    @Basic
    @Column(name = "HL7_FamilyMemberRoleCode")
    public String getHl7FamilyMemberRoleCode() {
        return hl7FamilyMemberRoleCode;
    }

    public void setHl7FamilyMemberRoleCode(String hl7FamilyMemberRoleCode) {
        this.hl7FamilyMemberRoleCode = hl7FamilyMemberRoleCode;
    }

    @Basic
    @Column(name = "HL7_Internal_ID")
    public String getHl7InternalId() {
        return hl7InternalId;
    }

    public void setHl7InternalId(String hl7InternalId) {
        this.hl7InternalId = hl7InternalId;
    }

    @Basic
    @Column(name = "Lex")
    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnomedMappedModifierEntity that = (SnomedMappedModifierEntity) o;
        return id == that.id &&
                Objects.equals(term, that.term) &&
                Objects.equals(snomedPreferredLabel, that.snomedPreferredLabel) &&
                Objects.equals(snomedCui, that.snomedCui) &&
                Objects.equals(hl7FamilyMemberRoleCode, that.hl7FamilyMemberRoleCode) &&
                Objects.equals(hl7InternalId, that.hl7InternalId) &&
                Objects.equals(lex, that.lex) &&
                Objects.equals(type, that.type) &&
                Objects.equals(regex, that.regex) &&
                Objects.equals(direction, that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, term, snomedPreferredLabel, snomedCui, hl7FamilyMemberRoleCode, hl7InternalId, lex, type, regex, direction);
    }
}
