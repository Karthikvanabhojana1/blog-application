package com.karthik.blog.payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	private Integer categoryId;
	@Size(min = 4)
	@NotBlank(message = "Category Title must not be Empty !!")
	private String categoryTitle;
	@NotBlank(message = "Category Details must not be Empty !!")
	@Size(min = 4, max = 30)
	private String categoryDetails;
}
