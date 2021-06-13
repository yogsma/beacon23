package com.betterjavacode.beacon23;

import com.betterjavacode.beacon23.domain.CveData;
import com.betterjavacode.beacon23.service.CveDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
public class Beacon23Application
{

	private static final Logger LOGGER = LoggerFactory.getLogger(Beacon23Application.class);

	public static void main (String[] args)
	{
		SpringApplication.run(Beacon23Application.class, args);
	}


	@Bean
	public CommandLineRunner runner (CveDataService cveDataService)
	{
		return args ->
		{
			ObjectMapper objectMapper = new ObjectMapper();
			TypeReference<CveData> cveDataTypeReference = new TypeReference<CveData>()
			{
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/nvdcve-1.1-modified" +
					".json");

			try
			{
				CveData cveData = objectMapper.readValue(inputStream, cveDataTypeReference);
				cveDataService.save(cveData);
			}
			catch (IOException e)
			{
				LOGGER.debug("Unable to save CVE Data: " + e.getMessage());
			}
		};
	}
}
