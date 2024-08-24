package edu.gdlc_project.gdlc_pckgs.service.DocumentMaterial_Service;

import edu.gdlc_project.gdlc_pckgs.model.DocumentMaterial;
import edu.gdlc_project.gdlc_pckgs.repository.DocumentMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentMaterialServiceImp implements DocumentMaterialService {

    @Autowired
    private DocumentMaterialRepository documentMaterialRepository;

    @Override
    public DocumentMaterial saveMaterialDocument(DocumentMaterial documentMaterial) {
        return null;
    }

    @Override
    public List<DocumentMaterial> getAllMaterialsDocuments() {
        return documentMaterialRepository.findAll();
    }
}
