package org.gjdd.moire.decorators.entities

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.item.Items
import org.gjdd.moire.decorators.DecoratorRegistries
import org.gjdd.moire.elements.*
import org.gjdd.moire.matrices.rotateLocalYDegrees

class EntityAddedDecoratorTest : ModInitializer {
    private val testEntityAddedDecorator = entityAddedDecorator<HostileEntity> {
        elementHolder {
            itemDisplayElement {
                onTick {
                    transform {
                        rotateLocalYDegrees(11.25f)
                    }

                    startInterpolation(1)
                }

                item = Items.TNT.defaultStack
                ignorePositionUpdates()
            }

            addVirtualPassengerTo(entity)
        }
    }

    override fun onInitialize() {
        DecoratorRegistries.ENTITY_ADDED.register(testEntityAddedDecorator)
    }
}