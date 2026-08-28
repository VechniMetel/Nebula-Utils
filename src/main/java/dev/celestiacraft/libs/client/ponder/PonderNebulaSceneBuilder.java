package dev.celestiacraft.libs.client.ponder;

import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.foundation.PonderSceneBuilder;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class PonderNebulaSceneBuilder extends PonderSceneBuilder implements INebulaSceneBuilder {
	private final PonderNebulaWorldInstructions world;

	public PonderNebulaSceneBuilder(SceneBuilder builder) {
		super(builder.getScene());
		world = new PonderNebulaWorldInstructions();
	}

	@Override
	public @NotNull PonderNebulaWorldInstructions world() {
		return world;
	}

	public class PonderNebulaWorldInstructions extends PonderSceneBuilder.PonderWorldInstructions implements INebulaWorldInstructions {
		@Override
		public void removeEntity(ElementLink<EntityElement> link) {
			addInstruction((ponder) -> {
				EntityElement resolve = ponder.resolve(link);
				if (resolve != null) {
					resolve.ifPresent(Entity::discard);
				}
			});
		}
	}
}