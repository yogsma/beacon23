package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "CveData")
@Table(name = "cvedata")
public class CveData implements Serializable
{
    private static final long serialVersionUID = -8725872556819668807L;

    public CveData()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("CVE_data_type")
    private String cveDataType;

    @JsonProperty("CVE_data_format")
    private String cveDataFormat;

    @JsonProperty("CVE_data_version")
    private String cveDataVersion;

    @JsonProperty("CVE_data_numberOfCVEs")
    private String numberOfCves;

    @JsonProperty("CVE_data_timestamp")
    private String cveDataTimestamp;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("CVE_Items")
    private List<CveItem> cveItems;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getCveDataType ()
    {
        return cveDataType;
    }

    public void setCveDataType (String cveDataType)
    {
        this.cveDataType = cveDataType;
    }

    public String getCveDataFormat ()
    {
        return cveDataFormat;
    }

    public void setCveDataFormat (String cveDataFormat)
    {
        this.cveDataFormat = cveDataFormat;
    }

    public String getCveDataVersion ()
    {
        return cveDataVersion;
    }

    public void setCveDataVersion (String cveDataVersion)
    {
        this.cveDataVersion = cveDataVersion;
    }

    public String getNumberOfCves ()
    {
        return numberOfCves;
    }

    public void setNumberOfCves (String numberOfCves)
    {
        this.numberOfCves = numberOfCves;
    }

    public String getCveDataTimestamp ()
    {
        return cveDataTimestamp;
    }

    public void setCveDataTimestamp (String cveDataTimestamp)
    {
        this.cveDataTimestamp = cveDataTimestamp;
    }

    public List<CveItem> getCveItems ()
    {
        return cveItems;
    }

    public void setCveItems (List<CveItem> cveItems)
    {
        this.cveItems = cveItems;
    }
}
