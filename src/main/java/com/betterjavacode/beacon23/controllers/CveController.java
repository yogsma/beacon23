package com.betterjavacode.beacon23.controllers;

import com.betterjavacode.beacon23.domain.projections.CveDTO;
import com.betterjavacode.beacon23.service.CveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/beacon23/vulnerabilities")
public class CveController
{

    @Autowired
    private CveService cveService;

    @GetMapping("/list")
    public List<CveDTO> getAllCveItems(@RequestParam(required = false, name="fromDate") String fromDate, @RequestParam(required = false, name=
            "toDate") String toDate)
    {
        List<CveDTO> cveDTOList = cveService.getCveItems(fromDate, toDate);

        if(cveDTOList == null || cveDTOList.isEmpty())
        {
            return new ArrayList<>();
        }
        else
        {
            return cveDTOList;
        }
    }

    @GetMapping
    public ResponseEntity<CveDTO> getCveItemById(@RequestParam("cveId") String cveId)
    {
        CveDTO cveDTO = cveService.getCveItemByCveId(cveId);

        if(cveDTO != null)
        {
            return new ResponseEntity<>(cveDTO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
