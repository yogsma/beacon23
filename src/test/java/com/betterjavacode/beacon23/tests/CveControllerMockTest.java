package com.betterjavacode.beacon23.tests;

import com.betterjavacode.beacon23.domain.*;
import com.betterjavacode.beacon23.persistence.CveDataDao;
import com.betterjavacode.beacon23.service.CveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CveControllerMockTest
{
    @Autowired
    private CveService cveService;

    @MockBean
    private CveDataDao cveDataDao;

    @Test
    public void testGetCveItemById()
    {
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

        CveItem cveItem = new CveItem();
        cveItem.setCve(cve);
        cveItem.setPublishedDate("2021-06-08T23:15Z");
        cveItem.setLastModifiedDate("2021-06-08T23:15Z");

        List<CveItem> cveItems = new ArrayList<>();
        cveItems.add(cveItem);

        CveData cveData = new CveData();
        cveData.setCveDataType("CVE");
        cveData.setCveDataFormat("MITRE");
        cveData.setCveDataVersion("4.0");
        cveData.setNumberOfCves("1375");
        cveData.setCveItems(cveItems);

        List<CveData> cveDataList = new ArrayList<>();
        cveDataList.add(cveData);

        when(cveDataDao.findAll()).thenReturn(cveDataList);

        assertTrue(cveService.getCveItemByCveId("CVE-2021-33742").getDescription().contains("Windows MSHTML Platform Remote Code Execution Vulnerability"));

    }
}
