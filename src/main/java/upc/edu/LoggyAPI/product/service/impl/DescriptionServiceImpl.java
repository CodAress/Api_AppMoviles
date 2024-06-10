package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Description;
import upc.edu.LoggyAPI.product.repository.DescriptionRepository;
import upc.edu.LoggyAPI.product.service.DescriptionService;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public Description createDescription(Description description) {
        validateDescription(description);
        existDescriptionByTitle(description);
        return descriptionRepository.save(description);
    }

    @Override
    public Description updateDescription(Long description_id, Description description) {
        existDescriptionById(description_id);
        validateDescription(description);
        existDescriptionByTitle(description);
        Description descriptionToUpdate = descriptionRepository.findById(description_id).get();
        descriptionToUpdate.setTitle(description.getTitle());
        descriptionToUpdate.setText(description.getText());
        return descriptionRepository.save(descriptionToUpdate);
    }

    @Override
    public boolean deleteDescription(Long id) {
        existDescriptionById(id);
        descriptionRepository.deleteById(id);
        return true;
    }

    @Override
    public Description getDescriptionById(Long id) {
        existDescriptionById(id);
        return descriptionRepository.findById(id).get();
    }

    @Override
    public List<Description> getAllDescriptions() {
        List<Description> descriptions = descriptionRepository.findAll();
        if (descriptions.isEmpty()) {
            throw new IllegalArgumentException("Unregistered descriptions");
        }
        return descriptions;
    }

    private void validateDescription(Description description){
        if(description.getTitle() == null || description.getTitle().isEmpty()){
            throw new IllegalArgumentException("Description title is required");
        }
        if(description.getText() == null || description.getText().isEmpty()){
            throw new IllegalArgumentException("Description text is required");
        }
    }

    private void existDescriptionByTitle(Description description){
        if(descriptionRepository.existsByTitleIgnoreCase(description.getTitle())){
            throw new IllegalArgumentException(String.format("Description with title %s already exists", description.getTitle()));
        }
    }

    private void existDescriptionById(Long id){
        if(!descriptionRepository.existsById(id)){
            throw new IllegalArgumentException(String.format("Description with id %s does not exist", id));
        }
    }
}
