package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Ref;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "ReferenceData")
@Table(name = "referencedata")
public class ReferenceData implements Serializable
{

    private static final long serialVersionUID = 3314482022653257638L;

    public ReferenceData()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("url")
    @Column(length = 500)
    private String url;

    @JsonProperty("name")
    @Column(length = 300)
    private String name;

    @JsonProperty("refsource")
    private String refsource;

    @ManyToOne
    @JoinColumn(name = "referenceid")
    private Reference reference;

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

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRefsource ()
    {
        return refsource;
    }

    public void setRefsource (String refsource)
    {
        this.refsource = refsource;
    }
}
