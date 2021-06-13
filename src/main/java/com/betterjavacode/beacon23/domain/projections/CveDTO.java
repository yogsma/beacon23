package com.betterjavacode.beacon23.domain.projections;

import java.util.Date;
import java.util.List;

public class CveDTO
{
    private String cveId;
    private String assigner;
    private String description;
    private List<String> urls;
    private Date publishedDate;

    public CveDTO(String cveId, String assigner, String description, List<String> urls,
                  Date publishedDate)
    {
        this.cveId = cveId;
        this.assigner = assigner;
        this.description = description;
        this.urls = urls;
        this.publishedDate = publishedDate;
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

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public List<String> getUrl ()
    {
        return urls;
    }

    public void setUrl (List<String> urls)
    {
        this.urls = urls;
    }

    public Date getPublishedDate ()
    {
        return publishedDate;
    }

    public void setPublishedDate (Date publishedDate)
    {
        this.publishedDate = publishedDate;
    }
}
