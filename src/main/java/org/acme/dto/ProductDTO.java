package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class ProductDTO {
    private long id;

    private char[] name;

    private char[] description;

    private char[] category;

    private char[] model;

    private long price;
}
