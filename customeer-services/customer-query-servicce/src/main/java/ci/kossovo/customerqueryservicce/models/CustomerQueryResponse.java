package ci.kossovo.customerqueryservicce.models;

import java.util.List;

import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;


public record CustomerQueryResponse(List<CustomerDto> customers) {

  
}
