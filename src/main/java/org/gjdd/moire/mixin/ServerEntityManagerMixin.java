package org.gjdd.moire.mixin;

import eu.pb4.polymer.virtualentity.api.attachment.EntityAttachment;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.world.entity.EntityLike;
import org.gjdd.moire.decorators.DecoratorRegistries;
import org.gjdd.moire.decorators.entities.EntityAddedDecorator;
import org.gjdd.moire.decorators.entities.EntityAddedDecoratorContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ServerEntityManager.class)
public abstract class ServerEntityManagerMixin<T extends EntityLike> {
    @SuppressWarnings("unchecked")
    @Inject(method = "addEntity(Lnet/minecraft/world/entity/EntityLike;Z)Z", at = @At(value = "RETURN"))
    private void moire$injectAddEntity(T entityLike, boolean existing, CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValue() || !(entityLike instanceof Entity entity)) {
            return;
        }

        var context = new EntityAddedDecoratorContext<>(entity);
        for (var decorator : DecoratorRegistries.ENTITY_ADDED.getDecorators()) {
            if (decorator.getEntityClass().isInstance(entity)) {
                var holder = ((EntityAddedDecorator<Entity>) decorator).decorate(context);
                EntityAttachment.ofTicking(holder, entity);
            }
        }
    }
}
