package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DonationRepository  extends JpaRepository<Donation,Long> {
    Integer countByUser(User user);

    List<Donation> findAllByUser(User user);


    @Query("SELECT sum(d.quantity) from Donation d")
    Long getDonationsCountQuantity();

    @Query("SELECT count(d) FROM Donation d WHERE d.pickUpDate >= :date")
    Long countDonationsFromLast30Days(LocalDate date);

    @Query("SELECT d.institution, COUNT(d) FROM Donation d GROUP BY d.institution")
    List<Object[]> countDonationByInstitutions();

}
