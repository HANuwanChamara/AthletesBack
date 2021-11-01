package athletes.athletes.dao.impl;

import athletes.athletes.dao.AthletesDao;
import athletes.athletes.dto.AthletesDetails;
import athletes.athletes.entity.AthletesEntity;
import athletes.athletes.entity.AthletesEventEntity;
import athletes.athletes.repository.AthletesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AthletesDaoImpl implements AthletesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AthletesRepository athletesRepository;

    @Override
    @Transactional
    public Long save(AthletesEntity athletesEntity) {
        for (AthletesEventEntity athletesEventEntity : athletesEntity.getEvents()) {
            athletesEventEntity.setAthletes(athletesEntity);
        }
        entityManager.persist(athletesEntity);
        entityManager.flush();
        return athletesRepository.findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<AthletesDetails> findAllAthletesSearchData(String name, String country, String gender, String event) {

        List<AthletesDetails> AthletesDtoList = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select a.first_name,a.last_name,a.gender,a.country,e.result,e.name from athletes a " +
                "left join athletes_event e on a.id=e.athletes_id where a.status=1");
        if (name != "") {
            sql.append(" and a.first_name like '%");
            sql.append(name);
            sql.append("%' ");
        }
        if (country != "") {
            sql.append(" and a.country='" + country);
            sql.append("' ");
        }
        if (gender != "") {
            sql.append(" and a.gender='" + gender);
            sql.append("' ");
        }
        if (event != "") {
            sql.append(" and e.name='" + event);
            sql.append("' ");
        }
        List<Object[]> object = entityManager.createNativeQuery(sql.toString()).getResultList();
        for (Object[] o : object) {

            AthletesDetails athletesDto = new AthletesDetails();
            athletesDto.setFirstName(getString(0, o));
            athletesDto.setLastName(getString(1, o));
            athletesDto.setGender(getString(2, o));
            athletesDto.setCountry(getString(3, o));
            athletesDto.setResult(getString(4, o));
            athletesDto.setEvent(getString(5, o));
            AthletesDtoList.add(athletesDto);

        }
        return AthletesDtoList;

    }

    private String getString(int i, Object[] o) {
        return o[i] != null ? o[i].toString() : "";
    }
}
