package kg.megacom.FlatOrdering.HouseFlatApp.dao;

import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
