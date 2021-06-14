package com.betterjavacode.beacon23.service;

import com.betterjavacode.beacon23.domain.*;
import com.betterjavacode.beacon23.persistence.CveDataDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCveDataService
{
    @Mock
    private CveDataDao cveDataDao;

    @InjectMocks
    private CveDataService cveDataService;

    private CveData cveData;

    @Before
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);

        CveMetadata cveMetadata = new CveMetadata();
        cveMetadata.setCveId("CVE-2021-33742");
        cveMetadata.setAssigner("secure@microsoft.com");


        DescriptionData descriptionData = new DescriptionData();
        descriptionData.setLang("en");
        descriptionData.setValue("Windows MSHTML Platform Remote Code Execution Vulnerability");

        List<DescriptionData> descriptionDataList = new ArrayList<>();
        descriptionDataList.add(descriptionData);

        Description description = new Description();
        description.setDescriptionDataList(descriptionDataList);

        ReferenceData referenceData = new ReferenceData();
        referenceData.setName("https://portal.msrc.microsoft.com/en-US/security-guidance/advisory/CVE-2021-33741");
        referenceData.setUrl("https://portal.msrc.microsoft.com/en-US/security-guidance/advisory/CVE-2021-33741");
        referenceData.setRefsource("MISC");

        List<ReferenceData> referenceDataList = new ArrayList<>();
        referenceDataList.add(referenceData);

        Reference reference = new Reference();
        reference.setReferenceDataList(referenceDataList);

        Cve cve = new Cve();
        cve.setDataType("CVE");
        cve.setDataFormat("MITRE");
        cve.setDataVersion("4.0");
        cve.setCveMetadata(cveMetadata);
        cve.setReference(reference);
        cve.setDescription(description);

        List<CveItem> cveItems = new ArrayList<>();
        CveItem cveItem = new CveItem();
        cveItem.setCve(cve);
        cveItem.setPublishedDate("2021-06-08T23:15Z");
        cveItem.setLastModifiedDate("2021-06-08T23:15Z");
        cveItems.add(cveItem);

        cveData = new CveData();
        cveData.setCveDataType("CVE");
        cveData.setCveDataFormat("MITRE");
        cveData.setCveDataVersion("4.0");
        cveData.setNumberOfCves("1375");
        cveData.setCveItems(cveItems);

    }


    @Test
    public void testSave()
    {
        when(cveDataDao.save(cveData)).thenReturn(cveData);

        CveData createdCveData = cveDataService.save(cveData);

        assertNotNull(createdCveData);

        verify(cveDataDao, times(1)).save(cveData);
    }

}
