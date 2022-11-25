package com.example.specification.repositories.specs;

import com.example.specification.repositories.specs.enumaration.SearchOperation;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
}
