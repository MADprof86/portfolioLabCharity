package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

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


}
