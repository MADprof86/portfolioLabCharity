package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> getAllInstitutions(){
        return institutionRepository.findAll();
    }

    public Institution findById(Long id){
        Optional<Institution> institution = institutionRepository.findById(id);
        return institution.orElseGet(Institution::new);
    }
    public Institution save(Institution institution){

        return institutionRepository.save(institution);
    }

    public void delete(Institution institution){
        if(institutionRepository.findById(institution.getId()).isPresent()){
            institutionRepository.delete(institution);
        }
        else return;
    }

}
