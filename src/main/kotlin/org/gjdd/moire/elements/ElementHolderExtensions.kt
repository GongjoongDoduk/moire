package org.gjdd.moire.elements

import eu.pb4.polymer.virtualentity.api.ElementHolder
import eu.pb4.polymer.virtualentity.api.VirtualEntityUtils
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment
import eu.pb4.polymer.virtualentity.api.attachment.EntityAttachment
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment
import eu.pb4.polymer.virtualentity.api.elements.*
import net.minecraft.entity.Entity
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import org.gjdd.moire.internal.ElementHolderExtensions

public val ElementHolder.age: Int get() = (this as ElementHolderExtensions).`moire$getAge`()

public inline fun elementHolder(block: ElementHolder.() -> Unit): ElementHolder = ElementHolder().apply(block)

public inline fun ElementHolder.blockDisplayElement(block: BlockDisplayElement.() -> Unit): BlockDisplayElement =
    addElement(org.gjdd.moire.elements.blockDisplayElement(block))

public inline fun ElementHolder.interactionElement(block: InteractionElement.() -> Unit): InteractionElement =
    addElement(org.gjdd.moire.elements.interactionElement(block))

public inline fun ElementHolder.itemDisplayElement(block: ItemDisplayElement.() -> Unit): ItemDisplayElement =
    addElement(org.gjdd.moire.elements.itemDisplayElement(block))

public inline fun ElementHolder.markerElement(block: MarkerElement.() -> Unit): MarkerElement =
    addElement(org.gjdd.moire.elements.markerElement(block))

public inline fun ElementHolder.mobAnchorElement(block: MobAnchorElement.() -> Unit): MobAnchorElement =
    addElement(org.gjdd.moire.elements.mobAnchorElement(block))

public inline fun ElementHolder.textDisplayElement(block: TextDisplayElement.() -> Unit): TextDisplayElement =
    addElement(org.gjdd.moire.elements.textDisplayElement(block))

public inline fun ElementHolder.onStartWatching(crossinline block: (ServerPlayNetworkHandler) -> Unit): Unit =
    (this as ElementHolderExtensions).`moire$addStartWatchingListener` { block(it) }

public inline fun ElementHolder.onSetAttachment(crossinline block: (HolderAttachment?) -> Unit): Unit =
    (this as ElementHolderExtensions).`moire$addSetAttachmentListener` { block(it) }

public inline fun ElementHolder.onTick(crossinline block: () -> Unit): Unit =
    (this as ElementHolderExtensions).`moire$addTickListener` { block() }

public fun ElementHolder.addVirtualPassengerTo(entity: Entity): Unit =
    VirtualEntityUtils.addVirtualPassenger(entity, *entityIds.toIntArray())

public fun ElementHolder.chunkAttachment(world: ServerWorld, pos: Vec3d): HolderAttachment =
    ChunkAttachment.of(this, world, pos)

public fun ElementHolder.chunkAttachmentTicking(world: ServerWorld, pos: Vec3d): HolderAttachment =
    ChunkAttachment.ofTicking(this, world, pos)

public fun ElementHolder.entityAttachment(entity: Entity): EntityAttachment =
    EntityAttachment.of(this, entity)

public fun ElementHolder.entityAttachmentTicking(entity: Entity): EntityAttachment =
    EntityAttachment.ofTicking(this, entity)