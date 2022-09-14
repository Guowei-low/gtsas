package com.gtassignment.sas.service.impl;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    ModelMapper modelMapper;
}
