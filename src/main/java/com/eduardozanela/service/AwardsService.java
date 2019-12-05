package com.eduardozanela.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduardozanela.dto.AwardsDTO;
import com.eduardozanela.dto.AwardsWinnersDTO;
import com.eduardozanela.dto.ResponseDTO;
import com.eduardozanela.entity.AwardsEntity;
import com.eduardozanela.repository.AwardsRepository;

@Service
public class AwardsService {

    private final String YES = "yes";

    @Autowired
    private AwardsRepository repo;
    
    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<ResponseDTO> filterAwards(){
        List<AwardsEntity> awardsEntities = repo.findByWinner(YES);
        List<AwardsEntity> entityList = awardsEntities.stream().sorted(Comparator.comparingInt(AwardsEntity::getYear)).collect(Collectors.toList());

        Map<String, List<AwardsEntity>> group = new HashMap<>();
        for(AwardsEntity e : entityList) {
            for(String p : e.getProducers()) {
                if(group.containsKey(p)){
                    group.get(p).add(e);
                } else {
                    ArrayList<AwardsEntity> list = new ArrayList<>();
                    list.add(e);
                    group.put(p, list);
                }
            }
        }

        group = group.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return calculateDiffBetweenAwards(group);
    }

    private ResponseEntity<ResponseDTO> calculateDiffBetweenAwards(Map<String, List<AwardsEntity>> map){
        AwardsWinnersDTO max = new AwardsWinnersDTO();
        AwardsWinnersDTO min = new AwardsWinnersDTO();
        Integer maxYear = 0;
        Integer minYear = null;
        for(String key : map.keySet()) {
            List<AwardsEntity> awards = map.get(key);
            for (int i = 0; i < awards.size() - 1; i++) {
                if ((awards.get(i + 1).getYear() - awards.get(i).getYear()) > maxYear) {
                    maxYear = (awards.get(i + 1).getYear() - awards.get(i).getYear());
                    max = new AwardsWinnersDTO(key, maxYear, awards.get(i).getYear(), awards.get(i + 1).getYear());
                }
                if (minYear == null || (awards.get(i + 1).getYear() - awards.get(i).getYear()) < minYear) {
                    minYear = (awards.get(i + 1).getYear() - awards.get(i).getYear());
                    min = new AwardsWinnersDTO(key, minYear, awards.get(i).getYear(), awards.get(i + 1).getYear());
                }
            }
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.getMax().add(max);
        responseDTO.getMin().add(min);
        return ResponseEntity.ok().body(responseDTO);
    }

	public ResponseEntity<List<AwardsDTO>> getAllAwards() {
		List<AwardsDTO> winners = new ArrayList<>();
		repo.findAll().stream().forEach(a -> winners.add(mapper.map(a, AwardsDTO.class)));
		return ResponseEntity.ok().body(winners);
	}

	public ResponseEntity<AwardsDTO> findAwardsById(Integer id) {
		Optional<AwardsEntity> findById = repo.findById(id);
		if(findById.isPresent()) {
			return ResponseEntity.ok().body(mapper.map(findById.get(), AwardsDTO.class));
		}
		return ResponseEntity.notFound().build();
	}
}
