
package ch.talenta.jpm.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

import ch.talenta.jpm.JPM;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = JPM.MODID)
public class Utility {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface RegisterBlockWithMesh {
		public String ressourceLocation();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface RegisterVanillaBlock {
		public VanillaBlocks block();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface RegisterVanillaItem {
		public VanillaItems item();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface RegisterItemWithMesh {
		public String ressourceLocation();
		public String[] variants();
	}


	@SubscribeEvent
	public static void registerBlocks (RegistryEvent.Register<Block> event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, InvocationTargetException {
		Reflections reflections = new Reflections("ch.talenta.jpm");
		Set<Class<?>> blocks = reflections.getTypesAnnotatedWith(RegisterBlockWithMesh.class);
		
		for (Class<?> c : blocks) {
			Block b = (Block)c.getConstructors()[0].newInstance();
			b.getClass().getField("instance").set(null, b);
			
			String loc = JPM.RESOURCE_PREFIX + c.getAnnotation(RegisterItemWithMesh.class).ressourceLocation();
			b.setRegistryName(new ResourceLocation(JPM.MODID, loc));
			event.getRegistry().register(b);
			
			ItemBlock i = new ItemBlock(b);
			
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(loc, "inventory"));

		}
		
		blocks = reflections.getTypesAnnotatedWith(RegisterVanillaBlock.class);
		
		for (Class<?> c : blocks) {
			Block b = (Block)c.getConstructors()[0].newInstance();
			b.getClass().getField("instance").set(null, b);
			
			String loc = JPM.RESOURCE_PREFIX + c.getAnnotation(RegisterVanillaBlock.class).block().name();
			b.setRegistryName(new ResourceLocation(JPM.MODID, loc));
			event.getRegistry().register(b);
			
			ItemBlock i = new ItemBlock(b);
			
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(loc, "inventory"));

		}
	}


	@SubscribeEvent
	public static void registerItems (RegistryEvent.Register<Item> event) throws InstantiationException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchFieldException {
		
		Reflections reflections = new Reflections("ch.talenta.jpm");
		Set<Class<?>> blocks = reflections.getTypesAnnotatedWith(RegisterItemWithMesh.class);
		for (Class<?> c : blocks) {
			Item b = (Item)c.getConstructors()[0].newInstance();
			b.getClass().getField("instance").set(null, b);

			String loc = JPM.RESOURCE_PREFIX + c.getAnnotation(RegisterItemWithMesh.class).ressourceLocation();
			b.setRegistryName(new ResourceLocation(JPM.MODID, loc));
			event.getRegistry().register(b);

			String[] variants = c.getAnnotation(RegisterItemWithMesh.class).variants();
			ModelResourceLocation[] models = new ModelResourceLocation[variants.length];
			for (int k = 0; k < variants.length; k++)
				models[k] = new ModelResourceLocation(loc, variants[k]);

			ModelLoader.registerItemVariants(b, models);
				
			for (int k = 0; k < models.length; k++)
				ModelLoader.setCustomModelResourceLocation(b, 0, models[k]);
		}
		
		blocks = reflections.getTypesAnnotatedWith(RegisterVanillaItem.class);
		for (Class<?> c : blocks) {
			Item b = (Item)c.getConstructors()[0].newInstance();
			b.getClass().getField("instance").set(null, b);

			String loc = JPM.RESOURCE_PREFIX + c.getAnnotation(RegisterVanillaItem.class).item().name();
			b.setRegistryName(new ResourceLocation(JPM.MODID, loc));
			event.getRegistry().register(b);

			ModelResourceLocation mrl = new ModelResourceLocation(loc, "inventory");
			ModelLoader.registerItemVariants(b, mrl);
			ModelLoader.setCustomModelResourceLocation(b, 0, mrl);
	
		}
	}
	
	
	public enum VanillaBlocks{
		acacia_bark,
		acacia_door_bottom,
		acacia_door_bottom_rh,
		acacia_door_top,
		acacia_door_top_rh,
		acacia_fence_gate_closed,
		acacia_fence_gate_open,
		acacia_fence_inventory,
		acacia_fence_post,
		acacia_fence_side,
		acacia_inner_stairs,
		acacia_leaves,
		acacia_log,
		acacia_outer_stairs,
		acacia_planks,
		acacia_sapling,
		acacia_stairs,
		acacia_wall_gate_closed,
		acacia_wall_gate_open,
		activator_rail_active_flat,
		activator_rail_active_raised_ne,
		activator_rail_active_raised_sw,
		activator_rail_flat,
		activator_rail_raised_ne,
		activator_rail_raised_sw,
		allium,
		andesite,
		andesite_smooth,
		anvil,
		anvil_slightly_damaged,
		anvil_undamaged,
		anvil_very_damaged,
		beacon,
		bedrock,
		bedrock_mirrored,
		beetroots_stage0,
		beetroots_stage1,
		beetroots_stage2,
		beetroots_stage3,
		birch_bark,
		birch_door_bottom,
		birch_door_bottom_rh,
		birch_door_top,
		birch_door_top_rh,
		birch_fence_gate_closed,
		birch_fence_gate_open,
		birch_fence_inventory,
		birch_fence_post,
		birch_fence_side,
		birch_inner_stairs,
		birch_leaves,
		birch_log,
		birch_outer_stairs,
		birch_planks,
		birch_sapling,
		birch_stairs,
		birch_wall_gate_closed,
		birch_wall_gate_open,
		black_concrete,
		black_concrete_powder,
		black_glazed_terracotta,
		black_stained_glass_pane_noside,
		black_stained_glass_pane_noside_alt,
		black_stained_glass_pane_post,
		black_stained_glass_pane_side,
		black_stained_glass_pane_side_alt,
		black_wool,
		block,
		blue_concrete,
		blue_concrete_powder,
		blue_glazed_terracotta,
		blue_stained_glass_pane_noside,
		blue_stained_glass_pane_noside_alt,
		blue_stained_glass_pane_post,
		blue_stained_glass_pane_side,
		blue_stained_glass_pane_side_alt,
		blue_wool,
		bone_block,
		bookshelf,
		brewing_stand,
		brewing_stand_bottle0,
		brewing_stand_bottle1,
		brewing_stand_bottle2,
		brewing_stand_empty0,
		brewing_stand_empty1,
		brewing_stand_empty2,
		brick,
		brick_inner_stairs,
		brick_outer_stairs,
		brick_stairs,
		brown_concrete,
		brown_concrete_powder,
		brown_glazed_terracotta,
		brown_mushroom,
		brown_mushroom_block_c,
		brown_mushroom_block_cap_all,
		brown_mushroom_block_e,
		brown_mushroom_block_inside_all,
		brown_mushroom_block_n,
		brown_mushroom_block_ne,
		brown_mushroom_block_nw,
		brown_mushroom_block_s,
		brown_mushroom_block_se,
		brown_mushroom_block_stem,
		brown_mushroom_block_stem_all,
		brown_mushroom_block_sw,
		brown_mushroom_block_w,
		brown_stained_glass_pane_noside,
		brown_stained_glass_pane_noside_alt,
		brown_stained_glass_pane_post,
		brown_stained_glass_pane_side,
		brown_stained_glass_pane_side_alt,
		brown_wool,
		button,
		button_inventory,
		button_pressed,
		cactus,
		cake_slice1,
		cake_slice2,
		cake_slice3,
		cake_slice4,
		cake_slice5,
		cake_slice6,
		cake_uneaten,
		carpet,
		carpet_black,
		carpet_blue,
		carpet_brown,
		carpet_cyan,
		carpet_gray,
		carpet_green,
		carpet_light_blue,
		carpet_lime,
		carpet_magenta,
		carpet_orange,
		carpet_pink,
		carpet_purple,
		carpet_red,
		carpet_silver,
		carpet_white,
		carpet_yellow,
		carrots_stage0,
		carrots_stage1,
		carrots_stage2,
		carrots_stage3,
		cauldron_empty,
		cauldron_level1,
		cauldron_level2,
		cauldron_level3,
		chain_command_block,
		chain_command_block_conditional,
		chorus_flower,
		chorus_flower_dead,
		chorus_plant,
		chorus_plant_noside,
		chorus_plant_noside1,
		chorus_plant_noside2,
		chorus_plant_noside3,
		chorus_plant_side,
		clay,
		coal_block,
		coal_ore,
		coarse_dirt,
		cobblestone,
		cobblestone_wall_inventory,
		cobblestone_wall_mossy_inventory,
		cobblestone_wall_post,
		cobblestone_wall_side,
		cocoa_age0_s,
		cocoa_age1_s,
		cocoa_age2_s,
		command_block,
		command_block_conditional,
		comparator_lit,
		comparator_lit_subtract,
		comparator_unlit,
		comparator_unlit_subtract,
		crafting_table,
		crop,
		cross,
		cube,
		cube_all,
		cube_bottom_top,
		cube_column,
		cube_directional,
		cube_mirrored,
		cube_mirrored_all,
		cube_top,
		cyan_concrete,
		cyan_concrete_powder,
		cyan_glazed_terracotta,
		cyan_stained_glass_pane_noside,
		cyan_stained_glass_pane_noside_alt,
		cyan_stained_glass_pane_post,
		cyan_stained_glass_pane_side,
		cyan_stained_glass_pane_side_alt,
		cyan_wool,
		daisy,
		dandelion,
		dark_oak_bark,
		dark_oak_door_bottom,
		dark_oak_door_bottom_rh,
		dark_oak_door_top,
		dark_oak_door_top_rh,
		dark_oak_fence_gate_closed,
		dark_oak_fence_gate_open,
		dark_oak_fence_inventory,
		dark_oak_fence_post,
		dark_oak_fence_side,
		dark_oak_inner_stairs,
		dark_oak_leaves,
		dark_oak_log,
		dark_oak_outer_stairs,
		dark_oak_planks,
		dark_oak_sapling,
		dark_oak_stairs,
		dark_oak_wall_gate_closed,
		dark_oak_wall_gate_open,
		daylight_detector,
		daylight_detector_inverted,
		dead_bush,
		detector_rail_flat,
		detector_rail_powered_flat,
		detector_rail_powered_raised_ne,
		detector_rail_powered_raised_sw,
		detector_rail_raised_ne,
		detector_rail_raised_sw,
		diamond_block,
		diamond_ore,
		diorite,
		diorite_smooth,
		dirt,
		dispenser,
		dispenser_vertical,
		door_bottom,
		door_bottom_rh,
		door_top,
		door_top_rh,
		double_fern_bottom,
		double_fern_top,
		double_grass_bottom,
		double_grass_top,
		double_paeonia_bottom,
		double_paeonia_top,
		double_rose_bottom,
		double_rose_top,
		double_stone,
		double_stone_top,
		double_sunflower_bottom,
		double_sunflower_top,
		double_syringa_bottom,
		double_syringa_top,
		dragon_egg,
		dropper,
		dropper_vertical,
		emerald_block,
		emerald_ore,
		enchanting_table_base,
		end_bricks,
		end_portal_frame_empty,
		end_portal_frame_filled,
		end_rod,
		end_stone,
		farmland,
		farmland_dry,
		farmland_moist,
		fence_gate_closed,
		fence_gate_open,
		fence_inventory,
		fence_post,
		fence_side,
		fern,
		fire_floor,
		fire_floor0,
		fire_floor1,
		fire_side,
		fire_side0,
		fire_side1,
		fire_side_alt,
		fire_side_alt0,
		fire_side_alt1,
		fire_up,
		fire_up0,
		fire_up1,
		fire_up_alt,
		fire_up_alt0,
		fire_up_alt1,
		flower_pot,
		flower_pot_acacia,
		flower_pot_allium,
		flower_pot_birch,
		flower_pot_cactus,
		flower_pot_cross,
		flower_pot_daisy,
		flower_pot_dandelion,
		flower_pot_dark_oak,
		flower_pot_dead_bush,
		flower_pot_fern,
		flower_pot_houstonia,
		flower_pot_jungle,
		flower_pot_mushroom_brown,
		flower_pot_mushroom_red,
		flower_pot_oak,
		flower_pot_orchid,
		flower_pot_rose,
		flower_pot_spruce,
		flower_pot_tulip_orange,
		flower_pot_tulip_pink,
		flower_pot_tulip_red,
		flower_pot_tulip_white,
		frosted_ice_0,
		frosted_ice_1,
		frosted_ice_2,
		frosted_ice_3,
		furnace,
		glass,
		glass_black,
		glass_blue,
		glass_brown,
		glass_cyan,
		glass_gray,
		glass_green,
		glass_light_blue,
		glass_lime,
		glass_magenta,
		glass_orange,
		glass_pane_noside,
		glass_pane_noside_alt,
		glass_pane_post,
		glass_pane_side,
		glass_pane_side_alt,
		glass_pink,
		glass_purple,
		glass_red,
		glass_silver,
		glass_white,
		glass_yellow,
		glazed_terracotta,
		glowstone,
		golden_rail_active_flat,
		golden_rail_active_raised_ne,
		golden_rail_active_raised_sw,
		golden_rail_flat,
		golden_rail_raised_ne,
		golden_rail_raised_sw,
		gold_block,
		gold_ore,
		granite,
		granite_smooth,
		grass,
		grass_normal,
		grass_path,
		grass_snowed,
		gravel,
		gray_concrete,
		gray_concrete_powder,
		gray_glazed_terracotta,
		gray_stained_glass_pane_noside,
		gray_stained_glass_pane_noside_alt,
		gray_stained_glass_pane_post,
		gray_stained_glass_pane_side,
		gray_stained_glass_pane_side_alt,
		gray_wool,
		green_concrete,
		green_concrete_powder,
		green_glazed_terracotta,
		green_stained_glass_pane_noside,
		green_stained_glass_pane_noside_alt,
		green_stained_glass_pane_post,
		green_stained_glass_pane_side,
		green_stained_glass_pane_side_alt,
		green_wool,
		half_slab,
		half_slab_acacia,
		half_slab_birch,
		half_slab_brick,
		half_slab_cobblestone,
		half_slab_dark_oak,
		half_slab_jungle,
		half_slab_nether_brick,
		half_slab_oak,
		half_slab_purpur,
		half_slab_quartz,
		half_slab_red_sandstone,
		half_slab_sandstone,
		half_slab_spruce,
		half_slab_stone,
		half_slab_stone_brick,
		hardened_clay,
		hardened_clay_black,
		hardened_clay_blue,
		hardened_clay_brown,
		hardened_clay_cyan,
		hardened_clay_gray,
		hardened_clay_green,
		hardened_clay_light_blue,
		hardened_clay_lime,
		hardened_clay_magenta,
		hardened_clay_orange,
		hardened_clay_pink,
		hardened_clay_purple,
		hardened_clay_red,
		hardened_clay_silver,
		hardened_clay_white,
		hardened_clay_yellow,
		hay,
		heavy_pressure_plate_down,
		heavy_pressure_plate_up,
		hopper_down,
		hopper_side,
		houstonia,
		ice,
		inner_stairs,
		iron_bars_cap,
		iron_bars_cap_alt,
		iron_bars_post,
		iron_bars_post_ends,
		iron_bars_side,
		iron_bars_side_alt,
		iron_block,
		iron_door_bottom,
		iron_door_bottom_rh,
		iron_door_top,
		iron_door_top_rh,
		iron_ore,
		iron_trapdoor_bottom,
		iron_trapdoor_open,
		iron_trapdoor_top,
		item_frame,
		item_frame_map,
		jukebox,
		jungle_bark,
		jungle_door_bottom,
		jungle_door_bottom_rh,
		jungle_door_top,
		jungle_door_top_rh,
		jungle_fence_gate_closed,
		jungle_fence_gate_open,
		jungle_fence_inventory,
		jungle_fence_post,
		jungle_fence_side,
		jungle_inner_stairs,
		jungle_leaves,
		jungle_log,
		jungle_outer_stairs,
		jungle_planks,
		jungle_sapling,
		jungle_stairs,
		jungle_wall_gate_closed,
		jungle_wall_gate_open,
		ladder,
		lapis_block,
		lapis_ore,
		leaves,
		lever,
		lever_off,
		light_blue_concrete,
		light_blue_concrete_powder,
		light_blue_glazed_terracotta,
		light_blue_stained_glass_pane_noside,
		light_blue_stained_glass_pane_noside_alt,
		light_blue_stained_glass_pane_post,
		light_blue_stained_glass_pane_side,
		light_blue_stained_glass_pane_side_alt,
		light_blue_wool,
		light_pressure_plate_down,
		light_pressure_plate_up,
		lime_concrete,
		lime_concrete_powder,
		lime_glazed_terracotta,
		lime_stained_glass_pane_noside,
		lime_stained_glass_pane_noside_alt,
		lime_stained_glass_pane_post,
		lime_stained_glass_pane_side,
		lime_stained_glass_pane_side_alt,
		lime_wool,
		lit_furnace,
		lit_pumpkin,
		lit_redstone_lamp,
		lit_redstone_ore,
		lit_redstone_torch,
		lit_redstone_torch_wall,
		magenta_concrete,
		magenta_concrete_powder,
		magenta_glazed_terracotta,
		magenta_stained_glass_pane_noside,
		magenta_stained_glass_pane_noside_alt,
		magenta_stained_glass_pane_post,
		magenta_stained_glass_pane_side,
		magenta_stained_glass_pane_side_alt,
		magenta_wool,
		magma,
		melon,
		melon_stem_fruit,
		melon_stem_growth0,
		melon_stem_growth1,
		melon_stem_growth2,
		melon_stem_growth3,
		melon_stem_growth4,
		melon_stem_growth5,
		melon_stem_growth6,
		melon_stem_growth7,
		mob_spawner_cage,
		mossy_cobblestone,
		mossy_cobblestone_wall_post,
		mossy_cobblestone_wall_side,
		mycelium,
		netherrack,
		nether_brick,
		nether_brick_fence_inventory,
		nether_brick_fence_post,
		nether_brick_fence_side,
		nether_brick_inner_stairs,
		nether_brick_outer_stairs,
		nether_brick_stairs,
		nether_wart_block,
		nether_wart_stage0,
		nether_wart_stage1,
		nether_wart_stage2,
		normal_rail_curved,
		normal_rail_flat,
		normal_rail_raised_ne,
		normal_rail_raised_sw,
		normal_torch,
		normal_torch_wall,
		noteblock,
		oak_bark,
		oak_fence_gate_closed,
		oak_fence_gate_open,
		oak_fence_inventory,
		oak_fence_post,
		oak_fence_side,
		oak_inner_stairs,
		oak_leaves,
		oak_log,
		oak_outer_stairs,
		oak_planks,
		oak_sapling,
		oak_stairs,
		oak_wall_gate_closed,
		oak_wall_gate_open,
		observer,
		observer_powered,
		obsidian,
		orange_concrete,
		orange_concrete_powder,
		orange_glazed_terracotta,
		orange_stained_glass_pane_noside,
		orange_stained_glass_pane_noside_alt,
		orange_stained_glass_pane_post,
		orange_stained_glass_pane_side,
		orange_stained_glass_pane_side_alt,
		orange_tulip,
		orange_wool,
		orchid,
		orientable,
		orientable_vertical,
		outer_stairs,
		packed_ice,
		pane_noside,
		pane_noside_alt,
		pane_post,
		pane_side,
		pane_side_alt,
		pink_concrete,
		pink_concrete_powder,
		pink_glazed_terracotta,
		pink_stained_glass_pane_noside,
		pink_stained_glass_pane_noside_alt,
		pink_stained_glass_pane_post,
		pink_stained_glass_pane_side,
		pink_stained_glass_pane_side_alt,
		pink_tulip,
		pink_wool,
		piston,
		piston_extended,
		piston_extended_normal,
		piston_head,
		piston_head_normal,
		piston_head_short,
		piston_head_short_normal,
		piston_head_short_sticky,
		piston_head_sticky,
		piston_inventory_normal,
		piston_inventory_sticky,
		piston_normal,
		podzol,
		poppy,
		portal_ew,
		portal_ns,
		potatoes_stage0,
		potatoes_stage1,
		potatoes_stage2,
		potatoes_stage3,
		pressure_plate_down,
		pressure_plate_up,
		prismarine_bricks,
		prismarine_dark,
		prismarine_rough,
		pumpkin,
		pumpkin_stem_fruit,
		pumpkin_stem_growth0,
		pumpkin_stem_growth1,
		pumpkin_stem_growth2,
		pumpkin_stem_growth3,
		pumpkin_stem_growth4,
		pumpkin_stem_growth5,
		pumpkin_stem_growth6,
		pumpkin_stem_growth7,
		purple_concrete,
		purple_concrete_powder,
		purple_glazed_terracotta,
		purple_stained_glass_pane_noside,
		purple_stained_glass_pane_noside_alt,
		purple_stained_glass_pane_post,
		purple_stained_glass_pane_side,
		purple_stained_glass_pane_side_alt,
		purple_wool,
		purpur_block,
		purpur_inner_stairs,
		purpur_outer_stairs,
		purpur_pillar_top,
		purpur_stairs,
		quartz_chiseled,
		quartz_inner_stairs,
		quartz_lines,
		quartz_normal,
		quartz_ore,
		quartz_outer_stairs,
		quartz_stairs,
		quartz_top,
		rail_curved,
		rail_flat,
		rail_raised_ne,
		rail_raised_sw,
		redstone_block,
		redstone_dot,
		redstone_ore,
		redstone_side,
		redstone_side0,
		redstone_side1,
		redstone_side_alt,
		redstone_side_alt0,
		redstone_side_alt1,
		redstone_up,
		red_concrete,
		red_concrete_powder,
		red_glazed_terracotta,
		red_mushroom,
		red_mushroom_block_c,
		red_mushroom_block_cap_all,
		red_mushroom_block_e,
		red_mushroom_block_inside_all,
		red_mushroom_block_n,
		red_mushroom_block_ne,
		red_mushroom_block_nw,
		red_mushroom_block_s,
		red_mushroom_block_se,
		red_mushroom_block_stem,
		red_mushroom_block_stem_all,
		red_mushroom_block_sw,
		red_mushroom_block_w,
		red_nether_brick,
		red_sand,
		red_sandstone_all,
		red_sandstone_chiseled,
		red_sandstone_inner_stairs,
		red_sandstone_normal,
		red_sandstone_outer_stairs,
		red_sandstone_smooth,
		red_sandstone_stairs,
		red_stained_glass_pane_noside,
		red_stained_glass_pane_noside_alt,
		red_stained_glass_pane_post,
		red_stained_glass_pane_side,
		red_stained_glass_pane_side_alt,
		red_tulip,
		red_wool,
		reeds,
		repeater_1tick,
		repeater_2tick,
		repeater_3tick,
		repeater_4tick,
		repeater_locked_1tick,
		repeater_locked_2tick,
		repeater_locked_3tick,
		repeater_locked_4tick,
		repeater_on_1tick,
		repeater_on_2tick,
		repeater_on_3tick,
		repeater_on_4tick,
		repeater_on_locked_1tick,
		repeater_on_locked_2tick,
		repeater_on_locked_3tick,
		repeater_on_locked_4tick,
		repeating_command_block,
		repeating_command_block_conditional,
		sand,
		sandstone_all,
		sandstone_chiseled,
		sandstone_inner_stairs,
		sandstone_normal,
		sandstone_outer_stairs,
		sandstone_smooth,
		sandstone_stairs,
		sea_lantern,
		silver_concrete,
		silver_concrete_powder,
		silver_glazed_terracotta,
		silver_stained_glass_pane_noside,
		silver_stained_glass_pane_noside_alt,
		silver_stained_glass_pane_post,
		silver_stained_glass_pane_side,
		silver_stained_glass_pane_side_alt,
		silver_wool,
		slime,
		snow,
		snow_height10,
		snow_height12,
		snow_height14,
		snow_height2,
		snow_height4,
		snow_height6,
		snow_height8,
		soul_sand,
		sponge,
		sponge_wet,
		spruce_bark,
		spruce_door_bottom,
		spruce_door_bottom_rh,
		spruce_door_top,
		spruce_door_top_rh,
		spruce_fence_gate_closed,
		spruce_fence_gate_open,
		spruce_fence_inventory,
		spruce_fence_post,
		spruce_fence_side,
		spruce_inner_stairs,
		spruce_leaves,
		spruce_log,
		spruce_outer_stairs,
		spruce_planks,
		spruce_sapling,
		spruce_stairs,
		spruce_wall_gate_closed,
		spruce_wall_gate_open,
		stairs,
		stem_fruit,
		stem_growth0,
		stem_growth1,
		stem_growth2,
		stem_growth3,
		stem_growth4,
		stem_growth5,
		stem_growth6,
		stem_growth7,
		sticky_piston,
		stone,
		stonebrick_chiseled,
		stonebrick_cracked,
		stonebrick_mossy,
		stonebrick_normal,
		stone_brick_inner_stairs,
		stone_brick_outer_stairs,
		stone_brick_stairs,
		stone_button,
		stone_button_inventory,
		stone_button_pressed,
		stone_inner_stairs,
		stone_mirrored,
		stone_outer_stairs,
		stone_pressure_plate_down,
		stone_pressure_plate_up,
		stone_stairs,
		structure_block,
		structure_block_corner,
		structure_block_data,
		structure_block_load,
		structure_block_save,
		tall_grass,
		thin_block,
		tinted_cross,
		tnt,
		torch,
		torch_wall,
		trapdoor_bottom,
		trapdoor_open,
		trapdoor_top,
		tripwire_attached_n,
		tripwire_attached_ne,
		tripwire_attached_ns,
		tripwire_attached_nse,
		tripwire_attached_nsew,
		tripwire_hook,
		tripwire_hook_attached,
		tripwire_hook_attached_powered,
		tripwire_hook_powered,
		tripwire_n,
		tripwire_ne,
		tripwire_ns,
		tripwire_nse,
		tripwire_nsew,
		unlit_redstone_lamp,
		unlit_redstone_torch,
		unlit_redstone_torch_wall,
		upper_slab,
		upper_slab_acacia,
		upper_slab_birch,
		upper_slab_brick,
		upper_slab_cobblestone,
		upper_slab_dark_oak,
		upper_slab_jungle,
		upper_slab_nether_brick,
		upper_slab_oak,
		upper_slab_purpur,
		upper_slab_quartz,
		upper_slab_red_sandstone,
		upper_slab_sandstone,
		upper_slab_spruce,
		upper_slab_stone,
		upper_slab_stone_brick,
		vine_1,
		vine_1u,
		vine_2,
		vine_2u,
		vine_2u_opposite,
		vine_2_opposite,
		vine_3,
		vine_3u,
		vine_4,
		vine_4u,
		vine_u,
		wall_gate_closed,
		wall_gate_open,
		wall_inventory,
		wall_post,
		wall_side,
		waterlily,
		web,
		wheat_stage0,
		wheat_stage1,
		wheat_stage2,
		wheat_stage3,
		wheat_stage4,
		wheat_stage5,
		wheat_stage6,
		wheat_stage7,
		white_concrete,
		white_concrete_powder,
		white_glazed_terracotta,
		white_stained_glass_pane_noside,
		white_stained_glass_pane_noside_alt,
		white_stained_glass_pane_post,
		white_stained_glass_pane_side,
		white_stained_glass_pane_side_alt,
		white_tulip,
		white_wool,
		wooden_button,
		wooden_button_inventory,
		wooden_button_pressed,
		wooden_door_bottom,
		wooden_door_bottom_rh,
		wooden_door_top,
		wooden_door_top_rh,
		wooden_pressure_plate_down,
		wooden_pressure_plate_up,
		wooden_trapdoor_bottom,
		wooden_trapdoor_open,
		wooden_trapdoor_top,
		yellow_concrete,
		yellow_concrete_powder,
		yellow_glazed_terracotta,
		yellow_stained_glass_pane_noside,
		yellow_stained_glass_pane_noside_alt,
		yellow_stained_glass_pane_post,
		yellow_stained_glass_pane_side,
		yellow_stained_glass_pane_side_alt,
		yellow_wool;

	}
	
	public enum VanillaItems{
		acacia_boat,
		acacia_door,
		acacia_fence,
		acacia_fence_gate,
		acacia_leaves,
		acacia_log,
		acacia_planks,
		acacia_sapling,
		acacia_slab,
		acacia_stairs,
		activator_rail,
		allium,
		andesite,
		andesite_smooth,
		anvil_intact,
		anvil_slightly_damaged,
		anvil_very_damaged,
		apple,
		armor_stand,
		arrow,
		baked_potato,
		banner,
		barrier,
		beacon,
		bed,
		bedrock,
		beef,
		beetroot,
		beetroot_seeds,
		beetroot_soup,
		birch_boat,
		birch_door,
		birch_fence,
		birch_fence_gate,
		birch_leaves,
		birch_log,
		birch_planks,
		birch_sapling,
		birch_slab,
		birch_stairs,
		black_carpet,
		black_concrete,
		black_concrete_powder,
		black_glazed_terracotta,
		black_shulker_box,
		black_stained_glass,
		black_stained_glass_pane,
		black_stained_hardened_clay,
		black_wool,
		blaze_powder,
		blaze_rod,
		blue_carpet,
		blue_concrete,
		blue_concrete_powder,
		blue_glazed_terracotta,
		blue_orchid,
		blue_shulker_box,
		blue_stained_glass,
		blue_stained_glass_pane,
		blue_stained_hardened_clay,
		blue_wool,
		bone,
		bone_block,
		book,
		bookshelf,
		bottle_drinkable,
		bottle_lingering,
		bottle_splash,
		bow,
		bowl,
		bow_pulling_0,
		bow_pulling_1,
		bow_pulling_2,
		bread,
		brewing_stand,
		brick,
		brick_block,
		brick_slab,
		brick_stairs,
		broken_elytra,
		brown_carpet,
		brown_concrete,
		brown_concrete_powder,
		brown_glazed_terracotta,
		brown_mushroom,
		brown_mushroom_block,
		brown_shulker_box,
		brown_stained_glass,
		brown_stained_glass_pane,
		brown_stained_hardened_clay,
		brown_wool,
		bucket,
		cactus,
		cake,
		carrot,
		carrot_on_a_stick,
		cauldron,
		chainmail_boots,
		chainmail_chestplate,
		chainmail_helmet,
		chainmail_leggings,
		chain_command_block,
		charcoal,
		chest,
		chest_minecart,
		chicken,
		chiseled_brick_monster_egg,
		chiseled_quartz_block,
		chiseled_red_sandstone,
		chiseled_sandstone,
		chiseled_stonebrick,
		chorus_flower,
		chorus_fruit,
		chorus_fruit_popped,
		chorus_plant,
		clay,
		clay_ball,
		clock,
		clock_01,
		clock_02,
		clock_03,
		clock_04,
		clock_05,
		clock_06,
		clock_07,
		clock_08,
		clock_09,
		clock_10,
		clock_11,
		clock_12,
		clock_13,
		clock_14,
		clock_15,
		clock_16,
		clock_17,
		clock_18,
		clock_19,
		clock_20,
		clock_21,
		clock_22,
		clock_23,
		clock_24,
		clock_25,
		clock_26,
		clock_27,
		clock_28,
		clock_29,
		clock_30,
		clock_31,
		clock_32,
		clock_33,
		clock_34,
		clock_35,
		clock_36,
		clock_37,
		clock_38,
		clock_39,
		clock_40,
		clock_41,
		clock_42,
		clock_43,
		clock_44,
		clock_45,
		clock_46,
		clock_47,
		clock_48,
		clock_49,
		clock_50,
		clock_51,
		clock_52,
		clock_53,
		clock_54,
		clock_55,
		clock_56,
		clock_57,
		clock_58,
		clock_59,
		clock_60,
		clock_61,
		clock_62,
		clock_63,
		clownfish,
		coal,
		coal_block,
		coal_ore,
		coarse_dirt,
		cobblestone,
		cobblestone_monster_egg,
		cobblestone_slab,
		cobblestone_wall,
		cod,
		command_block,
		command_block_minecart,
		comparator,
		compass,
		compass_00,
		compass_01,
		compass_02,
		compass_03,
		compass_04,
		compass_05,
		compass_06,
		compass_07,
		compass_08,
		compass_09,
		compass_10,
		compass_11,
		compass_12,
		compass_13,
		compass_14,
		compass_15,
		compass_17,
		compass_18,
		compass_19,
		compass_20,
		compass_21,
		compass_22,
		compass_23,
		compass_24,
		compass_25,
		compass_26,
		compass_27,
		compass_28,
		compass_29,
		compass_30,
		compass_31,
		cooked_beef,
		cooked_chicken,
		cooked_cod,
		cooked_mutton,
		cooked_porkchop,
		cooked_rabbit,
		cooked_salmon,
		cookie,
		cracked_brick_monster_egg,
		cracked_stonebrick,
		crafting_table,
		cyan_carpet,
		cyan_concrete,
		cyan_concrete_powder,
		cyan_glazed_terracotta,
		cyan_shulker_box,
		cyan_stained_glass,
		cyan_stained_glass_pane,
		cyan_stained_hardened_clay,
		cyan_wool,
		dandelion,
		dark_oak_boat,
		dark_oak_door,
		dark_oak_fence,
		dark_oak_fence_gate,
		dark_oak_leaves,
		dark_oak_log,
		dark_oak_planks,
		dark_oak_sapling,
		dark_oak_slab,
		dark_oak_stairs,
		dark_prismarine,
		daylight_detector,
		dead_bush,
		detector_rail,
		diamond,
		diamond_axe,
		diamond_block,
		diamond_boots,
		diamond_chestplate,
		diamond_helmet,
		diamond_hoe,
		diamond_horse_armor,
		diamond_leggings,
		diamond_ore,
		diamond_pickaxe,
		diamond_shovel,
		diamond_sword,
		diorite,
		diorite_smooth,
		dirt,
		dispenser,
		double_fern,
		double_grass,
		double_rose,
		dragon_breath,
		dragon_egg,
		dropper,
		dye_black,
		dye_blue,
		dye_brown,
		dye_cyan,
		dye_gray,
		dye_green,
		dye_light_blue,
		dye_lime,
		dye_magenta,
		dye_orange,
		dye_pink,
		dye_purple,
		dye_red,
		dye_silver,
		dye_white,
		dye_yellow,
		egg,
		elytra,
		emerald,
		emerald_block,
		emerald_ore,
		enchanted_book,
		enchanting_table,
		ender_chest,
		ender_eye,
		ender_pearl,
		end_bricks,
		end_crystal,
		end_portal_frame,
		end_rod,
		end_stone,
		experience_bottle,
		farmland,
		feather,
		fermented_spider_eye,
		fern,
		filled_map,
		fireworks,
		firework_charge,
		fire_charge,
		fishing_rod,
		fishing_rod_cast,
		flint,
		flint_and_steel,
		flower_pot,
		furnace,
		furnace_minecart,
		generated,
		ghast_tear,
		glass,
		glass_bottle,
		glass_pane,
		glowstone,
		glowstone_dust,
		golden_apple,
		golden_axe,
		golden_boots,
		golden_carrot,
		golden_chestplate,
		golden_helmet,
		golden_hoe,
		golden_horse_armor,
		golden_leggings,
		golden_pickaxe,
		golden_rail,
		golden_shovel,
		golden_sword,
		gold_block,
		gold_ingot,
		gold_nugget,
		gold_ore,
		granite,
		granite_smooth,
		grass,
		grass_path,
		gravel,
		gray_carpet,
		gray_concrete,
		gray_concrete_powder,
		gray_glazed_terracotta,
		gray_shulker_box,
		gray_stained_glass,
		gray_stained_glass_pane,
		gray_stained_hardened_clay,
		gray_wool,
		green_carpet,
		green_concrete,
		green_concrete_powder,
		green_glazed_terracotta,
		green_shulker_box,
		green_stained_glass,
		green_stained_glass_pane,
		green_stained_hardened_clay,
		green_wool,
		gunpowder,
		handheld,
		handheld_rod,
		hardened_clay,
		hay_block,
		heavy_weighted_pressure_plate,
		hopper,
		hopper_minecart,
		houstonia,
		ice,
		iron_axe,
		iron_bars,
		iron_block,
		iron_boots,
		iron_chestplate,
		iron_door,
		iron_helmet,
		iron_hoe,
		iron_horse_armor,
		iron_ingot,
		iron_leggings,
		iron_nugget,
		iron_ore,
		iron_pickaxe,
		iron_shovel,
		iron_sword,
		iron_trapdoor,
		item_frame,
		jukebox,
		jungle_boat,
		jungle_door,
		jungle_fence,
		jungle_fence_gate,
		jungle_leaves,
		jungle_log,
		jungle_planks,
		jungle_sapling,
		jungle_slab,
		jungle_stairs,
		knowledge_book,
		ladder,
		lapis_block,
		lapis_ore,
		lava_bucket,
		lead,
		leather,
		leather_boots,
		leather_chestplate,
		leather_helmet,
		leather_leggings,
		lever,
		light_blue_carpet,
		light_blue_concrete,
		light_blue_concrete_powder,
		light_blue_glazed_terracotta,
		light_blue_shulker_box,
		light_blue_stained_glass,
		light_blue_stained_glass_pane,
		light_blue_stained_hardened_clay,
		light_blue_wool,
		light_weighted_pressure_plate,
		lime_carpet,
		lime_concrete,
		lime_concrete_powder,
		lime_glazed_terracotta,
		lime_shulker_box,
		lime_stained_glass,
		lime_stained_glass_pane,
		lime_stained_hardened_clay,
		lime_wool,
		lit_pumpkin,
		magenta_carpet,
		magenta_concrete,
		magenta_concrete_powder,
		magenta_glazed_terracotta,
		magenta_shulker_box,
		magenta_stained_glass,
		magenta_stained_glass_pane,
		magenta_stained_hardened_clay,
		magenta_wool,
		magma,
		magma_cream,
		map,
		melon,
		melon_block,
		melon_seeds,
		milk_bucket,
		minecart,
		mob_spawner,
		mossy_brick_monster_egg,
		mossy_cobblestone,
		mossy_cobblestone_wall,
		mossy_stonebrick,
		mushroom_stew,
		mutton,
		mycelium,
		name_tag,
		netherbrick,
		netherrack,
		nether_brick,
		nether_brick_fence,
		nether_brick_slab,
		nether_brick_stairs,
		nether_star,
		nether_wart,
		nether_wart_block,
		noteblock,
		oak_boat,
		oak_door,
		oak_fence,
		oak_fence_gate,
		oak_leaves,
		oak_log,
		oak_planks,
		oak_sapling,
		oak_slab,
		oak_stairs,
		observer,
		obsidian,
		orange_carpet,
		orange_concrete,
		orange_concrete_powder,
		orange_glazed_terracotta,
		orange_shulker_box,
		orange_stained_glass,
		orange_stained_glass_pane,
		orange_stained_hardened_clay,
		orange_tulip,
		orange_wool,
		oxeye_daisy,
		packed_ice,
		paeonia,
		painting,
		paper,
		pink_carpet,
		pink_concrete,
		pink_concrete_powder,
		pink_glazed_terracotta,
		pink_shulker_box,
		pink_stained_glass,
		pink_stained_glass_pane,
		pink_stained_hardened_clay,
		pink_tulip,
		pink_wool,
		piston,
		podzol,
		poisonous_potato,
		poppy,
		porkchop,
		potato,
		prismarine,
		prismarine_bricks,
		prismarine_crystals,
		prismarine_shard,
		pufferfish,
		pumpkin,
		pumpkin_pie,
		pumpkin_seeds,
		purple_carpet,
		purple_concrete,
		purple_concrete_powder,
		purple_glazed_terracotta,
		purple_shulker_box,
		purple_stained_glass,
		purple_stained_glass_pane,
		purple_stained_hardened_clay,
		purple_wool,
		purpur_block,
		purpur_pillar,
		purpur_slab,
		purpur_stairs,
		quartz,
		quartz_block,
		quartz_column,
		quartz_ore,
		quartz_slab,
		quartz_stairs,
		rabbit,
		rabbit_foot,
		rabbit_hide,
		rabbit_stew,
		rail,
		record_11,
		record_13,
		record_blocks,
		record_cat,
		record_chirp,
		record_far,
		record_mall,
		record_mellohi,
		record_stal,
		record_strad,
		record_wait,
		record_ward,
		redstone,
		redstone_block,
		redstone_lamp,
		redstone_ore,
		redstone_torch,
		red_carpet,
		red_concrete,
		red_concrete_powder,
		red_glazed_terracotta,
		red_mushroom,
		red_mushroom_block,
		red_nether_brick,
		red_sand,
		red_sandstone,
		red_sandstone_slab,
		red_sandstone_stairs,
		red_shulker_box,
		red_stained_glass,
		red_stained_glass_pane,
		red_stained_hardened_clay,
		red_tulip,
		red_wool,
		reeds,
		repeater,
		repeating_command_block,
		rotten_flesh,
		saddle,
		salmon,
		sand,
		sandstone,
		sandstone_slab,
		sandstone_stairs,
		sea_lantern,
		shears,
		shield,
		shield_blocking,
		shulker_box,
		shulker_shell,
		sign,
		silver_carpet,
		silver_concrete,
		silver_concrete_powder,
		silver_glazed_terracotta,
		silver_shulker_box,
		silver_stained_glass,
		silver_stained_glass_pane,
		silver_stained_hardened_clay,
		silver_wool,
		skull,
		skull_char,
		skull_creeper,
		skull_dragon,
		skull_skeleton,
		skull_wither,
		skull_zombie,
		slime,
		slime_ball,
		smooth_red_sandstone,
		smooth_sandstone,
		snow,
		snowball,
		snow_layer,
		soul_sand,
		spawn_egg,
		speckled_melon,
		spectral_arrow,
		spider_eye,
		sponge,
		sponge_wet,
		spruce_boat,
		spruce_door,
		spruce_fence,
		spruce_fence_gate,
		spruce_leaves,
		spruce_log,
		spruce_planks,
		spruce_sapling,
		spruce_slab,
		spruce_stairs,
		stick,
		sticky_piston,
		stone,
		stonebrick,
		stone_axe,
		stone_brick_monster_egg,
		stone_brick_slab,
		stone_brick_stairs,
		stone_button,
		stone_hoe,
		stone_monster_egg,
		stone_pickaxe,
		stone_pressure_plate,
		stone_shovel,
		stone_slab,
		stone_stairs,
		stone_sword,
		string,
		structure_block,
		structure_void,
		sugar,
		sunflower,
		syringa,
		tall_grass,
		tipped_arrow,
		tnt,
		tnt_minecart,
		torch,
		totem,
		trapdoor,
		trapped_chest,
		tripwire_hook,
		vine,
		waterlily,
		water_bucket,
		web,
		wheat,
		wheat_seeds,
		white_carpet,
		white_concrete,
		white_concrete_powder,
		white_glazed_terracotta,
		white_shulker_box,
		white_stained_glass,
		white_stained_glass_pane,
		white_stained_hardened_clay,
		white_tulip,
		white_wool,
		wooden_axe,
		wooden_button,
		wooden_hoe,
		wooden_pickaxe,
		wooden_pressure_plate,
		wooden_shovel,
		wooden_sword,
		writable_book,
		written_book,
		yellow_carpet,
		yellow_concrete,
		yellow_concrete_powder,
		yellow_glazed_terracotta,
		yellow_shulker_box,
		yellow_stained_glass,
		yellow_stained_glass_pane,
		yellow_stained_hardened_clay,
		yellow_wool
	}


}
