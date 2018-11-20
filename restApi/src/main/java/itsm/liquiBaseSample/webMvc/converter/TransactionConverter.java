package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.webMvc.dto.TransactionDto;
import itsm.liquiBaseSample.webMvc.repositories.PatientsRepository;
import itsm.liquiBaseSample.webMvc.repositories.ProductsRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements CustomDtoConverter<Transaction, TransactionDto> {

    private ProductsRepository productsRepository;
    private PatientsRepository patientsRepository;

    public TransactionConverter(ProductsRepository productsRepository, PatientsRepository patientsRepository) {
        this.productsRepository = productsRepository;
        this.patientsRepository = patientsRepository;
    }

    @Override
    public Transaction convertFromDto(TransactionDto dto) {
        if(dto != null) {
            Transaction entity = new Transaction();
            Patient patient = patientsRepository.findById(dto.getIdPatient()).orElse(null);
            Product product = productsRepository.findById(dto.getIdProduct()).orElse(null);
            entity.setId(dto.getId());
            entity.setPatient(patient);
            entity.setProduct(product);
            entity.setCreatedDate(dto.getCreatedDate());
            return entity;
        }
        else return null;
    }

    @Override
    public TransactionDto convertToDto(Transaction entity) {
        if(entity != null)
            return new TransactionDto(
                entity.getId(),
                entity.getPatient() == null ? null : entity.getPatient().getId(),
                entity.getProduct() == null ? null : entity.getProduct().getId(),
                entity.getCreatedDate());
        else
            return null;
    }
}
