package ci.kossovo.customerqueryservicce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ci.kossovo.customerqueryservicce.data.documents.Adresse;
import ci.kossovo.customerqueryservicce.data.documents.Customer;
import ci.kossovo.customerqueryservicce.data.repository.AdresseRepository;
import ci.kossovo.customerqueryservicce.data.repository.CustomerRepository;
import ci.kossovo.customerqueryservicce.exceptions.NotFindAdresseException;
import ci.kossovo.customerqueryservicce.mappers.CustomerMapper;
import ci.kossovo.ventecoreapi.dtos.customer.AdresseDto;
import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import ci.kossovo.ventecoreapi.exceptions.customers.NotFoundCustomerException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final AdresseRepository adresseRepository;
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public Adresse enregistrerAdresse(Adresse adresse) {
    // String adresseId = UUID.randomUUID().toString().split("-")[0];
    // adresse.setAdresseId(adresseId);
    return adresseRepository.save(adresse);
  }

  public Customer inscrire(Customer customer) {
    return customerRepository.save(customer);
  }

  public CustomerDto getByIdCustomer(String id) {
    Customer customer = customerRepository
      .findById(id)
      .orElseThrow(() ->
        new NotFoundCustomerException("Customer de " + id + " introuvable.")
      );
    return customerMapper.customerToCustomerDto(customer);
  }

  public AdresseDto getByIdAdresse(String id) {
    Adresse adresse = adresseRepository
      .findById(id)
      .orElseThrow(() ->
        new NotFindAdresseException("L'adresse " + id + "n'existe pas")
      );
    return customerMapper.adresseToAdresseDto(adresse);
  }

  public List<CustomerDto> findAllCustomer() {
    List<Customer> customers = customerRepository.findAll();

    return customerMapper.customerListToCustomerDtosList(customers);
  }
}
