package athletes.athletes.repository;

import athletes.athletes.entity.AthletesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AthletesRepository extends JpaRepository<AthletesEntity, Long> {
    AthletesEntity findTopByOrderByIdDesc();
}
