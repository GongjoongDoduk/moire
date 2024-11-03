package org.gjdd.moire.decorators

import eu.pb4.polymer.virtualentity.api.ElementHolder

public interface Decorator<T : DecoratorContext> {
    public fun decorate(context: T): ElementHolder
}

public interface DecoratorContext