package com.betterjavacode.beacon23.service;

import com.betterjavacode.beacon23.domain.*;
import com.betterjavacode.beacon23.domain.projections.CveDTO;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCveService
{
    @Mock
    private CveDataDao cveDataDao;

    @InjectMocks
    private CveService cveService;

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

        List<CveData> cveDataList = new ArrayList<>();
        cveDataList.add(cveData);

        when(cveDataDao.findAll()).thenReturn(cveDataList);
    }

    @Test
    public void testGetAllCveItemsWithNoDateRange()
    {
        List<CveDTO> cveDTOList = cveService.getCveItems(null, null);

        assertEquals(1, cveDTOList.size());
    }

    @Test
    public void testGetAllCveItemsWithFromDateNull()
    {
        List<CveDTO> cveDTOList = cveService.getCveItems(null, "2021-06-09T23:15Z");

        assertEquals(1, cveDTOList.size());
    }

    @Test
    public void testGetAllCveItemsWithToDateNull()
    {
        List<CveDTO> cveDTOList = cveService.getCveItems("2021-06-01T23:15Z", null);

        assertEquals(1, cveDTOList.size());
    }

    @Test
    public void testGetAllCveItemsWithValidDateRange()
    {
        List<CveDTO> cveDTOList = cveService.getCveItems("2021-06-01T23:15Z", "2021-06-09T23:15Z");

        assertEquals(1, cveDTOList.size());
    }

    @Test
    public void testGetAllCveItemsWithValidDateRangeButNoCveItems()
    {
        List<CveDTO> cveDTOList = cveService.getCveItems("2021-06-01T23:15Z", "2021-06-03T23:15Z");

        assertEquals(0, cveDTOList.size());
    }

    @Test
    public void testGetCveItemByCveId()
    {
        CveDTO cveDTOWithCveId = cveService.getCveItemByCveId("CVE-2021-33742");

        assertNotNull(cveDTOWithCveId);
        assertEquals("Windows MSHTML Platform Remote Code Execution Vulnerability", cveDTOWithCveId.getDescription());
    }

    @Test
    public void testGetCveItemByNonExistentCveId()
    {
        CveDTO cveDTO = cveService.getCveItemByCveId("random-id");

        assertNull(cveDTO);
    }

}
