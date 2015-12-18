package io.github.benas.jpopulator.randomizers.withbackreferences;

import io.github.benas.jpopulator.api.BackreferenceRandomizer;
import io.github.benas.jpopulator.api.Randomizer;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;


/**
 * Randomizer for Backreferences to resolve (simple) circle relations.
 *
 * @param  <T> The related circle type.
 *
 * @author mertinat
 * @since  17.12.2015
 */
public class BackreferenceSimpleRandomizer<T> implements BackreferenceRandomizer<T> {
    private Randomizer<T> innerRandomizer;
    private String backreferenceFieldName;

    public BackreferenceSimpleRandomizer(final String backreferenceFieldName) {
        this(backreferenceFieldName, null);
    }

    public BackreferenceSimpleRandomizer(final String backreferenceFieldName,
        final Randomizer<T> innerRandomizer) {
        this.backreferenceFieldName = backreferenceFieldName;
        this.innerRandomizer = innerRandomizer;
    }

    @Override
    public T getRandomValue() {
        return (innerRandomizer != null) ? innerRandomizer.getRandomValue() : null;
    }

    @Override
    public String getBackreferenceFieldName() {
        return backreferenceFieldName;
    }

    @Override
    public boolean hasInnerRandomizer() {
        return innerRandomizer != null;
    }

    @Override
    public void setBackreference(final T object, final Object backreference)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (innerRandomizer instanceof BackreferenceRandomizer) {
            ((BackreferenceRandomizer) innerRandomizer).setBackreference(object, backreference);
        } else {
            PropertyUtils.setProperty(object, getBackreferenceFieldName(), backreference);
        }
    }
}