package com.example.specification.repositories.specs;

import com.example.specification.repositories.specs.enumaration.SearchOperation;

public record SearchCriteria(String key,Object value,SearchOperation searchOperation){
}