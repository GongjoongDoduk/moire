package org.gjdd.moire.decorators

import org.gjdd.moire.decorators.entities.EntityAddedDecorator
import java.util.concurrent.CopyOnWriteArraySet

/**
 * A registry that manages decorators.
 *
 * @param T The type of decorators.
 */
public class DecoratorRegistry<T : Decorator<*>> {
    private val decorators = CopyOnWriteArraySet<T>()

    /**
     * Registers a decorator.
     *
     * @param decorator The decorator to register.
     * @return `true` if the decorator was not already registered.
     */
    public fun register(decorator: T): Boolean = decorators.add(decorator)

    /**
     * Retrieves all registered decorators.
     *
     * @return A set of registered decorators.
     */
    public fun getDecorators(): Set<T> = decorators
}

/**
 * Provides predefined decorator registries.
 */
public object DecoratorRegistries {

    /**
     * Registry for [EntityAddedDecorator] instances.
     */
    @JvmField
    public val ENTITY_ADDED: DecoratorRegistry<EntityAddedDecorator<*>> = DecoratorRegistry()
}