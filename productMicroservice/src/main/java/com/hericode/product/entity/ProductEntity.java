package com.hericode.product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
	
	@Id
	private String id;
	private String productName;
	private String productDescription;
	private Double unitPrice;

}
