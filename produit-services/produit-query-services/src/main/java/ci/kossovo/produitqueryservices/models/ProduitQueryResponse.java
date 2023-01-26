package ci.kossovo.produitqueryservices.models;

import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import java.util.List;

public record ProduitQueryResponse(List<ProduitDto> produitResponse) {}
