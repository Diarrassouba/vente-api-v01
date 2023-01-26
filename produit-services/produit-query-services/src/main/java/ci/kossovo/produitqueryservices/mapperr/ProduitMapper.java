package ci.kossovo.produitqueryservices.mapperr;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
  @Mapping(source = "produit.codeProduit", target = "code")
  @Mapping(source = "produit.description", target = "desc")
  ProduitDto produitToDto(ProduitEntity produit);

  List<ProduitDto> produitsToDtos(List<ProduitEntity> produits);

  @InheritInverseConfiguration
  ProduitEntity dtoToProduit(ProduitDto dto);
}
