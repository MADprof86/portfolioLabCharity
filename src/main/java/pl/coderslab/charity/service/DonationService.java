package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }
    public Donation findById(Long id){
        Optional<Donation> donation = donationRepository.findById(id);

            return donation.orElseGet(Donation::new );


    }

    public Donation save(Donation donation) {
        return donationRepository.save(donation);
    }

    public void deleteById(Long id) {
        donationRepository.deleteById(id);
    }
    public void delete(Donation donation){
        if(donationRepository.findById(donation.getId()).isPresent()){
            donationRepository.delete(donation);
        }
        else return;
    }

    public int countByUser(User user) {
        return donationRepository.countByUser(user);
   }

    public List<Donation> findAllByUser(User user) {
        return  donationRepository.findAllByUser(user);
    }


    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Long getDonationsCount() {
        return donationRepository.count();
    }

    public Long getDonationsCountQuantity() {
        return donationRepository.getDonationsCountQuantity();
    }

    public Long countDonationsFromLast30Days() {
        LocalDate date = LocalDate.now().minusDays(30);
        return donationRepository.countDonationsFromLast30Days(date);
    }

    public HashMap<Institution, Long> getDonationsByInstitutionsMap() {
            List<Object[]> institutionsList = donationRepository.countDonationByInstitutions();
            HashMap<Institution,Long> hashMap = new HashMap<>();
            for(Object[] result : institutionsList){

                Institution institution = (Institution) result[0];
                Long numberOfDonations = (Long) result[1];
                hashMap.put(institution,numberOfDonations);

            }
            return hashMap;
    }
}
