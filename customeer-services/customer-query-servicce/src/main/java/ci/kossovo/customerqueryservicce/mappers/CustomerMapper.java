package ci.kossovo.customerqueryservicce.mappers;


import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ci.kossovo.customerqueryservicce.data.documents.Adresse;
import ci.kossovo.customerqueryservicce.data.documents.Customer;
import ci.kossovo.ventecoreapi.dtos.customer.AdresseDto;
import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  @Mapping(source = "customer.adresse.tel", target = "tel")
  @Mapping(source = "customer.adresse.ville", target = "ville")
  @Mapping(source = "customer.adresse.quartier", target = "quartier")
  @Mapping(source = "customer.id", target = "customerId")
  CustomerDto customerToCustomerDto(Customer customer);

  List<CustomerDto> customerListToCustomerDtosList(List<Customer> customers);

  @InheritInverseConfiguration
  Customer customerDtCustomer(CustomerDto customerDto);

 @Mapping(source = "adresse.id", target = "adresseId")
  AdresseDto adresseToAdresseDto(Adresse adresse);

  @InheritInverseConfiguration
  Adresse adresseDtoToAdresse(AdresseDto adresseDto);
}
