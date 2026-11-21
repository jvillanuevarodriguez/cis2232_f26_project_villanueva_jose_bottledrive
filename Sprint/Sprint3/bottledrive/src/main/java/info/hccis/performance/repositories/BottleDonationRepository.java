package info.hccis.performance.repositories;

import info.hccis.performance.entity.BottleDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BottleDonationRepository
        extends JpaRepository<BottleDonation, Integer> {
}