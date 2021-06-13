package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Description")
@Table(name = "description")
public class Description implements Serializable
{

    private static final long serialVersionUID = -8907076339230944672L;

    public Description()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("description_data")
    private List<DescriptionData> descriptionDataList;

    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public List<DescriptionData> getDescriptionDataList ()
    {
        return descriptionDataList;
    }

    public void setDescriptionDataList (List<DescriptionData> descriptionDataList)
    {
        this.descriptionDataList = descriptionDataList;
    }
}
