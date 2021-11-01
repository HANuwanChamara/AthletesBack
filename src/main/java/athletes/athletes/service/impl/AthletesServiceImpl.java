package athletes.athletes.service.impl;

import athletes.athletes.dao.AthletesDao;
import athletes.athletes.dto.AthletesDetails;
import athletes.athletes.entity.AthletesEntity;
import athletes.athletes.repository.AthletesRepository;
import athletes.athletes.service.AthletesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthletesServiceImpl implements AthletesService {
    @Autowired
    private AthletesRepository athletesRepository;

    @Autowired
    private AthletesDao athletesDao;

    @Override
    public Long save(AthletesEntity athletesEntity) {
        return athletesDao.save(athletesEntity);
    }

    @Override
    public List<AthletesDetails> findAllAthletesSearchData(String name, String country, String gender, String event) {
        return athletesDao.findAllAthletesSearchData(name, country, gender, event);
    }
}
