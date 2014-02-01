package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class GenericServiceImpl<ID extends Serializable, T extends AbstractEntity<ID>> implements GenericService<ID, T> {

}
