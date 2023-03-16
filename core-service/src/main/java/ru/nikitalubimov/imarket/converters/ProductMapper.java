package ru.nikitalubimov.iMarket.converters;

import org.mapstruct.Mapper;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.api.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public Product toEntity (ProductDto productDto);

    public ProductDto toDto (Product product);

}
