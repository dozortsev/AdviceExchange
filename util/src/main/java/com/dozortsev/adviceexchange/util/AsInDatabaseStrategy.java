package com.dozortsev.adviceexchange.util;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;
import org.jooq.util.mysql.MySQLTableDefinition;

import static org.jooq.util.GeneratorStrategy.Mode.DEFAULT;

public class AsInDatabaseStrategy extends DefaultGeneratorStrategy {

    @Override public String getJavaClassName(Definition definition, Mode mode) {
        String name = super.getJavaClassName(definition, mode);

        return DEFAULT.equals(mode) && definition.getClass().equals(MySQLTableDefinition.class)
                ? "T".concat(name)
                : name;
    }
}