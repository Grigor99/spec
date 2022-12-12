package com.example.specification.repositories.enumaration;

import com.example.specification.repositories.enumaration.SearchOperation;

public record SearchCriteria(String key,Object value,SearchOperation searchOperation){
}