package kg.megacom.FlatOrdering.HouseFlatApp.dao;

import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
}
