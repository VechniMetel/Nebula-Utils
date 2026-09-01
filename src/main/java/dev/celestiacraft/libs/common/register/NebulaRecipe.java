package dev.celestiacraft.libs.common.register;

import dev.celestiacraft.libs.NebulaLibs;
import dev.celestiacraft.libs.api.register.recipe.RecipeEntry;
import dev.celestiacraft.libs.common.recipe.anvil_craft.AnvilCraftRecipe;
import dev.celestiacraft.libs.common.recipe.anvil_craft.AnvilCraftSerializer;

public class NebulaRecipe {
	public static final RecipeEntry<AnvilCraftRecipe> ANVIL_CRAFT;

	static {
		ANVIL_CRAFT = NebulaLibs.REGISTRATE.recipe("anvil_craft", AnvilCraftSerializer::new)
				.register();
	}

	public static void register() {
		NebulaLibs.LOGGER.info("Nebula Libs RecipeTypes Registered!");
	}
}