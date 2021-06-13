package com.betterjavacode.beacon23.domain;

import com.betterjavacode.beacon23.utils.JpaUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "DescriptionData")
@Table(name = "descriptiondata")
public class DescriptionData implements Serializable
{
    private static final long serialVersionUID = 8456373583292279719L;

    public DescriptionData()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("value")
    @Column(length = 300)
    private String value;

    @ManyToOne
    @JoinColumn(name = "descriptionid")
    private Description description;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public Description getDescription ()
    {
        return description;
    }

    public void setDescription (Description description)
    {
        this.description = description;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = JpaUtil.truncate(value, 300);
    }
}
