package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.AbstractEntity;

import java.io.Serializable;

public interface GenericService<ID extends Serializable, T extends AbstractEntity<ID>> {
}
