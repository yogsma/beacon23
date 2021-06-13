package com.betterjavacode.beacon23.persistence;

import com.betterjavacode.beacon23.domain.CveData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CveDataDao extends JpaRepository<CveData, Long>
{
}
