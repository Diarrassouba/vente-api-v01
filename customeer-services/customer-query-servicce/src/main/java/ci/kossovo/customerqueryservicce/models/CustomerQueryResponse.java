package ci.kossovo.customerqueryservicce.models;

import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import java.util.List;

public record CustomerQueryResponse(List<CustomerDto> customers) {}
