package com.betterjavacode.beacon23.service;

import com.betterjavacode.beacon23.domain.CveData;
import com.betterjavacode.beacon23.persistence.CveDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CveDataService
{
    @Autowired
    private CveDataDao cveDataDao;

    public CveData save(CveData cveData)
    {
        CveData createdCveData = cveDataDao.save(cveData);

        return createdCveData;
    }
}
