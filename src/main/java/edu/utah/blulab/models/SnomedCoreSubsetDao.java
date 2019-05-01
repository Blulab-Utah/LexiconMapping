package edu.utah.blulab.models;

public class SnomedCoreSubsetDao {


    private String id;
    private String snomedCid;
    private String snomedFsn;
    private String snomedConceptStatus;
    private String umlsCui;
    private String occurence;
    private String Usage;
    private String firstInSubset;
    private String isRetiredFromSubset;
    private String lastInSubset;
    private String replacedBySnomedCid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnomedCid() {
        return snomedCid;
    }

    public void setSnomedCid(String snomedCid) {
        this.snomedCid = snomedCid;
    }

    public String getSnomedFsn() {
        return snomedFsn;
    }

    public void setSnomedFsn(String snomedFsn) {
        this.snomedFsn = snomedFsn;
    }

    public String getSnomedConceptStatus() {
        return snomedConceptStatus;
    }

    public void setSnomedConceptStatus(String snomedConceptStatus) {
        this.snomedConceptStatus = snomedConceptStatus;
    }

    public String getUmlsCui() {
        return umlsCui;
    }

    public void setUmlsCui(String umlsCui) {
        this.umlsCui = umlsCui;
    }

    public String getOccurence() {
        return occurence;
    }

    public void setOccurence(String occurence) {
        this.occurence = occurence;
    }

    public String getUsage() {
        return Usage;
    }

    public void setUsage(String usage) {
        Usage = usage;
    }

    public String getFirstInSubset() {
        return firstInSubset;
    }

    public void setFirstInSubset(String firstInSubset) {
        this.firstInSubset = firstInSubset;
    }

    public String getIsRetiredFromSubset() {
        return isRetiredFromSubset;
    }

    public void setIsRetiredFromSubset(String isRetiredFromSubset) {
        this.isRetiredFromSubset = isRetiredFromSubset;
    }

    public String getLastInSubset() {
        return lastInSubset;
    }

    public void setLastInSubset(String lastInSubset) {
        this.lastInSubset = lastInSubset;
    }

    public String getReplacedBySnomedCid() {
        return replacedBySnomedCid;
    }

    public void setReplacedBySnomedCid(String replacedBySnomedCid) {
        this.replacedBySnomedCid = replacedBySnomedCid;
    }
}
