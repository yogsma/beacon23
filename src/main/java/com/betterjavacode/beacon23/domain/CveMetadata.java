package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "CveMetadata")
@Table(name = "cvemetadata")
public class CveMetadata implements Serializable
{

    private static final long serialVersionUID = 2762664723950848391L;

    public CveMetadata()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("ID")
    private String cveId;

    @JsonProperty("ASSIGNER")
    private String assigner;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getCveId ()
    {
        return cveId;
    }

    public void setCveId (String cveId)
    {
        this.cveId = cveId;
    }

    public String getAssigner ()
    {
        return assigner;
    }

    public void setAssigner (String assigner)
    {
        this.assigner = assigner;
    }
}
