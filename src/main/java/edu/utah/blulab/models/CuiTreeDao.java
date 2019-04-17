package edu.utah.blulab.models;

public class CuiTreeDao {

    private String cui;
    private String preferredLabel;
    private String synonym;
    private String conceptCode;

    public String getCui() {
        return this.cui;
    }

    public void setCui(final String cui) {
        this.cui = cui;
    }

    public String getPreferredLabel() {
        return this.preferredLabel;
    }

    public void setPreferredLabel(final String preferredLabel) {
        this.preferredLabel = preferredLabel;
    }

    public String getSynonym() {
        return this.synonym;
    }

    public void setSynonym(final String synonym) {
        this.synonym = synonym;
    }

    public String getConceptCode() {
        return this.conceptCode;
    }

    public void setConceptCode(final String conceptCode) {
        this.conceptCode = conceptCode;
    }
}
