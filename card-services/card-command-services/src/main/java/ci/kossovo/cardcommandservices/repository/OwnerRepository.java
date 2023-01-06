package ci.kossovo.cardcommandservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ci.kossovo.cardcommandservices.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String> {}
