package dev.celestiacraft.libs.client.ponder;

import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.WorldInstructions;

public interface INebulaWorldInstructions extends WorldInstructions {
	void removeEntity(ElementLink<EntityElement> link);
}