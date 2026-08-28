package dev.celestiacraft.libs.client.ponder;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.TextElementBuilder;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.element.InputWindowElement;
import net.createmod.ponder.foundation.instruction.ShowInputInstruction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/**
 * Create-free Ponder scene builder facade. Implementations:
 * <ul>
 * <li>{@link PonderNebulaSceneBuilder} - Ponder only, no Create required</li>
 * <li>{@link CreateNebulaSceneBuilder} - extends Create's {@code CreateSceneBuilder}, exposes Create-specific helpers too</li>
 * </ul>
 */
public interface INebulaSceneBuilder extends SceneBuilder {
	Object OBJECT = new Object();

	@Override
	@NotNull INebulaWorldInstructions world();

	default void showStructure() {
		showStructure(getScene().getBasePlateSize() << 1);
	}

	default void showStructure(int height) {
		PonderScene scene = getScene();
		BlockPos start = new BlockPos(scene.getBasePlateOffsetX(), 0, scene.getBasePlateOffsetZ());
		BlockPos size = new BlockPos(scene.getBasePlateSize() - 1, height, scene.getBasePlateSize() - 1);
		Selection selection = scene.getSceneBuildingUtil().select().cuboid(start, size);
		encapsulateBounds(size);
		world().showSection(selection, Direction.UP);
	}

	default void encapsulateBounds(BlockPos size) {
		addInstruction((ponder) -> {
			PonderLevel sceneWorld = ponder.getWorld();
			sceneWorld.getBounds().encapsulate(size);
		});
	}

	default TextElementBuilder text(int duration, String text) {
		return overlay().showText(duration)
				.text(text);
	}

	default TextElementBuilder text(int duration, String text, Vec3 position) {
		return overlay().showText(duration)
				.text(text)
				.pointAt(position);
	}

	default TextElementBuilder sharedText(int duration, ResourceLocation location) {
		return overlay().showText(duration)
				.sharedText(location);
	}

	default TextElementBuilder sharedText(int duration, ResourceLocation location, Vec3 position) {
		return overlay().showText(duration)
				.sharedText(location)
				.pointAt(position)
				.colored(PonderPalette.BLUE);
	}

	default InputWindowElement showControls(int duration, Vec3 pos, Pointing pointing) {
		InputWindowElement element = new InputWindowElement(pos, pointing);
		addInstruction(new ShowInputInstruction(element, duration));
		return element;
	}

	static void init5x5(SceneBuilder builder, SceneBuildingUtil util) {
		builder.configureBasePlate(0, 0, 5);
		builder.scaleSceneView(0.9f);
		builder.world().showSection(util.select().layer(0), Direction.UP);
	}

	static void init7x7(SceneBuilder builder, SceneBuildingUtil util) {
		builder.configureBasePlate(0, 0, 7);
		builder.scaleSceneView(0.75f);
		builder.world().showSection(util.select().layer(0), Direction.UP);
	}

	static void init9x9(SceneBuilder builder, SceneBuildingUtil util) {
		builder.configureBasePlate(0, 0, 9);
		builder.scaleSceneView(0.6f);
		builder.world().showSection(util.select().layer(0), Direction.UP);
	}

	static void rotateAround(SceneBuilder builder, int duration, int angle) {
		float times = 360.0f / angle;

		for (int i = 0; i < times; i++) {
			rotate(builder, (int) (duration / times), angle);
		}
	}

	static void rotate(SceneBuilder builder, int time, int angle) {
		builder.rotateCameraY(angle);
		builder.idle(time);
	}
}