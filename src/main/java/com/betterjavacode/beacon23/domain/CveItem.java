package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "CveItem")
@Table(name = "cveitem")
public class CveItem implements Serializable
{

    private static final long serialVersionUID = -6860658973157963849L;

    public CveItem()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cveid")
    @JsonProperty("cve")
    private Cve cve;

    @JsonProperty("publishedDate")
    private String publishedDate;

    @JsonProperty("lastModifiedDate")
    private String lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "cvedata_id")
    private CveData cveData;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public CveData getCveData ()
    {
        return cveData;
    }

    public void setCveData (CveData cveData)
    {
        this.cveData = cveData;
    }

    public Cve getCve ()
    {
        return cve;
    }

    public void setCve (Cve cve)
    {
        this.cve = cve;
    }

    public String getPublishedDate ()
    {
        return publishedDate;
    }

    public void setPublishedDate (String publishedDate)
    {
        this.publishedDate = publishedDate;
    }

    public String getLastModifiedDate ()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate (String lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }
}
