package kg.megacom.FlatOrdering.HouseFlatApp.dao;

import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code,Long> {
    Code findByUserIdAndCodeStatusNot(Long id, CodeStatus codeStatus);
}
