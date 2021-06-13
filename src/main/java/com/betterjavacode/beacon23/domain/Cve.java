package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Cve")
@Table(name = "cve")
public class Cve implements Serializable
{
    private static final long serialVersionUID = 495911128880997323L;

    public Cve()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("data_type")
    private String dataType;

    @JsonProperty("data_format")
    private String dataFormat;

    @JsonProperty("data_version")
    private String dataVersion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cvemetadataid")
    @JsonProperty("CVE_data_meta")
    private CveMetadata cveMetadata;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referenceid")
    @JsonProperty("references")
    private Reference reference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descriptionid")
    @JsonProperty("description")
    private Description description;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public Reference getReference ()
    {
        return reference;
    }

    public void setReference (Reference reference)
    {
        this.reference = reference;
    }

    public String getDataType ()
    {
        return dataType;
    }

    public void setDataType (String dataType)
    {
        this.dataType = dataType;
    }

    public String getDataFormat ()
    {
        return dataFormat;
    }

    public void setDataFormat (String dataFormat)
    {
        this.dataFormat = dataFormat;
    }

    public String getDataVersion ()
    {
        return dataVersion;
    }

    public void setDataVersion (String dataVersion)
    {
        this.dataVersion = dataVersion;
    }

    public CveMetadata getCveMetadata ()
    {
        return cveMetadata;
    }

    public void setCveMetadata (CveMetadata cveMetadata)
    {
        this.cveMetadata = cveMetadata;
    }

    public Description getDescription ()
    {
        return description;
    }

    public void setDescription (Description description)
    {
        this.description = description;
    }

    public String toString()
    {
        return "";
    }
}
