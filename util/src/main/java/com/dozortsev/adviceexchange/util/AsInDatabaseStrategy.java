package com.dozortsev.adviceexchange.util;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;
import org.jooq.util.mysql.MySQLTableDefinition;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jooq.util.GeneratorStrategy.Mode.DEFAULT;
import static org.jooq.util.GeneratorStrategy.Mode.POJO;

public class AsInDatabaseStrategy extends DefaultGeneratorStrategy {

    private static final List<String> userActivities = asList("answer", "question", "comment");

    @Override public String getJavaClassExtends(Definition definition, Mode mode) {
        if (POJO.equals(mode)) {
            if (userActivities.contains(definition.getName())) {
                return "UserActivity";
            }
        }
        return Object.class.getName();
    }

    @Override public String getJavaClassName(Definition definition, Mode mode) {
        String name = super.getJavaClassName(definition, mode);

        return DEFAULT.equals(mode) && definition.getClass().equals(MySQLTableDefinition.class)
                ? "T".concat(name)
                : name;
    }
}