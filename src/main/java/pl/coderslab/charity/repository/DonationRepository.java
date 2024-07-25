package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

public interface DonationRepository  extends JpaRepository<Donation,Long> {
    Integer countByUser(User user);
}
