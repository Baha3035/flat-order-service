package kg.megacom.FlatOrdering.HouseFlatApp.dao;

import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {
//    long countAllByCodeAndStartDateAfter(Long user_id, Date date);
    long countAllByCodeIdAndSuccess(Long code_id, boolean success);
}
