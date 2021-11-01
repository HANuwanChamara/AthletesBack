package athletes.athletes.service;

import athletes.athletes.dto.AthletesDetails;
import athletes.athletes.entity.AthletesEntity;

import java.util.List;

public interface AthletesService {
    Long save(AthletesEntity athletesEntity);

    List<AthletesDetails> findAllAthletesSearchData(String name, String country, String gender, String event);
}
