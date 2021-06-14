package com.betterjavacode.beacon23.service;

import com.betterjavacode.beacon23.domain.*;
import com.betterjavacode.beacon23.domain.projections.CveDTO;
import com.betterjavacode.beacon23.persistence.CveDataDao;
import com.betterjavacode.beacon23.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CveService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CveService.class);
    public static final String EMPTY_STRING = "";

    @Autowired
    public CveDataDao cveDataDao;

    public List<CveDTO> getCveItems(String from, String to)
    {
        LOGGER.debug("The date range values are from = {} and to = {}", from, to);
        List<CveData> cveDataList = cveDataDao.findAll();
        List<CveDTO> cveDTOList = new ArrayList<>();

        for(CveData cveData : cveDataList)
        {
            List<CveItem> cveList = cveData.getCveItems();
            for(CveItem cveItem: cveList)
            {
                Date fromDate;
                Date toDate;

                if(!isNullOrEmpty(from) && !isNullOrEmpty(to))
                {
                    fromDate = DateUtil.formatDate(from);
                    toDate = DateUtil.formatDate(to);

                    Date publishedDate = DateUtil.formatDate(cveItem.getPublishedDate());

                    if(publishedDate.after(toDate) || publishedDate.before(fromDate))
                    {
                        continue;
                    }
                }
                CveDTO cveDTO = convertCveItemToCveDTO(cveItem);
                cveDTOList.add(cveDTO);
            }
        }
        return cveDTOList;
    }

    private boolean isNullOrEmpty (String str)
    {
        return (str == null || str.isEmpty());
    }

    private String buildDescription (List<DescriptionData> descriptionDataList)
    {
        if(descriptionDataList == null || descriptionDataList.isEmpty())
        {
            return EMPTY_STRING;
        }
        else
        {
            return descriptionDataList.get(0).getValue();
        }
    }

    private List<String> buildReferenceUrls (List<ReferenceData> referenceDataList)
    {
        return referenceDataList.stream().map(it -> it.getUrl()).collect(Collectors.toList());
    }

    public CveDTO getCveItemByCveId(String cveId)
    {
        List<CveData> cveDataList = cveDataDao.findAll();
        CveDTO cveDTO = null;

        for(CveData cveData : cveDataList)
        {
            List<CveItem> cveItems = cveData.getCveItems();

            Optional<CveItem> optionalCveItem =
                    cveItems.stream().filter(ci -> ci.getCve().getCveMetadata().getCveId().equals(cveId)).findAny();
            CveItem cveItem = null;
            if(optionalCveItem.isPresent())
            {
                cveItem = optionalCveItem.get();
            }
            else
            {
                return cveDTO;
            }
            cveDTO = convertCveItemToCveDTO(cveItem);
        }

        return cveDTO;
    }

    public CveDTO getCveItemByCveId (long id)
    {
        CveData cveData = cveDataDao.getById(id);
        CveDTO cveDTO = null;

        for(CveItem cveItem : cveData.getCveItems())
        {
            cveDTO = convertCveItemToCveDTO(cveItem);
        }

        return cveDTO;
    }

    private CveDTO convertCveItemToCveDTO (CveItem cveItem)
    {
        Cve cve = cveItem.getCve();
        CveMetadata cveMetadata = cve.getCveMetadata();
        String id = cveMetadata.getCveId();
        String assigner = cveMetadata.getAssigner();
        Reference references = cve.getReference();
        List<ReferenceData> referenceDataList = references.getReferenceDataList();
        List<String> referenceUrls = buildReferenceUrls(referenceDataList);
        Description description = cve.getDescription();
        List<DescriptionData> descriptionDataList = description.getDescriptionDataList();
        String desc = buildDescription(descriptionDataList);
        Date publishedDate = DateUtil.formatDate(cveItem.getPublishedDate());

        return new CveDTO(id, assigner, desc, referenceUrls, publishedDate);
    }
}
