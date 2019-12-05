package com.eduardozanela;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.eduardozanela.dto.AwardsDTO;
import com.eduardozanela.dto.ResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class GoldenRaspberryIntegrationTest {
	
	private static final Integer AWARDS_MAX_INTERVAL =13;
	private static final Integer AWARDS_MIN_INTERVAL =1;
	private static final Integer AWARDS_ID = 1;
	private static final Integer AWARDS_ID_NOT_FOUND = 0;

	@Autowired
    private TestRestTemplate testRestTemplate;
	
    @LocalServerPort
    private int port;
    
    @Test
    public void retriveAwards() {
    	ResponseEntity<AwardsDTO[]> response = testRestTemplate.getForEntity(getUrl("/v1/awards"), AwardsDTO[].class);
    	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assert.assertTrue(response.getBody().length > 0);
    }
    
    @Test
    public void retriveAwardsById() {
    	ResponseEntity<AwardsDTO> response = testRestTemplate.getForEntity(getUrl("/v1/awards/{id}"), AwardsDTO.class, AWARDS_ID);
    	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assert.assertNotNull(response.getBody());
    }
    @Test
    public void retriveAwardsByIdNotFound() {
    	ResponseEntity<AwardsDTO> response = testRestTemplate.getForEntity(getUrl("/v1/awards/{id}"), AwardsDTO.class, AWARDS_ID_NOT_FOUND);
    	Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    public void testeMinInterval() {
    	ResponseEntity<ResponseDTO> response = testRestTemplate.getForEntity(getUrl("/v1/winners"), ResponseDTO.class);
    	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assert.assertEquals(AWARDS_MIN_INTERVAL, response.getBody().getMin().get(0).getInterval());
    }
    
    @Test
    public void testeMaxInterval() {
    	ResponseEntity<ResponseDTO> response = testRestTemplate.getForEntity(getUrl("/v1/winners"), ResponseDTO.class);
    	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assert.assertEquals(AWARDS_MAX_INTERVAL, response.getBody().getMax().get(0).getInterval());
    }
    
    private String getUrl(String path) {
        return String.format("http://localhost:%s%s", port, path);
    }
}
