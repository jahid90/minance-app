package io.jahiduls.minance.repositories;

import io.jahiduls.minance.views.TermDepositIdsView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermDepositRepository extends JpaRepository<TermDepositIdsView, UUID> {
}
