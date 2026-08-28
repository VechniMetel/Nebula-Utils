package dev.celestiacraft.libs.client.ponder;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class CreateNebulaSceneBuilder extends CreateSceneBuilder implements INebulaSceneBuilder {
	private final CreateNebulaWorldInstructions world;

	public CreateNebulaSceneBuilder(SceneBuilder builder) {
		super(builder);
		world = new CreateNebulaWorldInstructions();
	}

	@Override
	public @NotNull CreateNebulaWorldInstructions world() {
		return world;
	}

	public class CreateNebulaWorldInstructions extends CreateSceneBuilder.WorldInstructions implements INebulaWorldInstructions {
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