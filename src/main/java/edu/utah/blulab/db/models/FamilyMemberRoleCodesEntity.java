package edu.utah.blulab.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FamilyMemberRoleCodes")
public class FamilyMemberRoleCodesEntity {
    private int id;
    private String term;
    private String snomedPreferredLabel;
    private String snomedCui;
    private String hl7FamilyMemberRoleCode;
    private String hl7InternalId;

    @Id
    @Column(name = "ID")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyMemberRoleCodesEntity that = (FamilyMemberRoleCodesEntity) o;
        return id == that.id &&
                Objects.equals(term, that.term) &&
                Objects.equals(snomedPreferredLabel, that.snomedPreferredLabel) &&
                Objects.equals(snomedCui, that.snomedCui) &&
                Objects.equals(hl7FamilyMemberRoleCode, that.hl7FamilyMemberRoleCode) &&
                Objects.equals(hl7InternalId, that.hl7InternalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, term, snomedPreferredLabel, snomedCui, hl7FamilyMemberRoleCode, hl7InternalId);
    }
}
