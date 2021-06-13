package com.betterjavacode.beacon23.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Reference")
@Table(name = "reference")
public class Reference implements Serializable
{


    private static final long serialVersionUID = -3780804636754002058L;

    public Reference()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("reference_data")
    private List<ReferenceData> referenceDataList;

    public List<ReferenceData> getReferenceDataList ()
    {
        return referenceDataList;
    }

    public void setReferenceDataList (List<ReferenceData> referenceDataList)
    {
        this.referenceDataList = referenceDataList;
    }
}
