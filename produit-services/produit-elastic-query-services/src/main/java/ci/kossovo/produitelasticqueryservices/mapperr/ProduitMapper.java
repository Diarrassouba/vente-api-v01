package ci.kossovo.produitelasticqueryservices.mapperr;

import ci.kossovo.produitelasticqueryservices.data.entities.Produit;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
  @Mapping(source = "produit.codeProduit", target = "code")
  @Mapping(source = "produit.description", target = "desc")
  ProduitDto produitToDto(Produit produit);

  List<ProduitDto> produitsToDtos(List<Produit> produits);

  @InheritInverseConfiguration
  Produit dtoToProduit(ProduitDto dto);
}
