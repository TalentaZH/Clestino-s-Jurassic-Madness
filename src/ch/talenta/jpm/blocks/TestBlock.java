package ch.talenta.jpm.blocks;

import ch.talenta.jpm.utils.Utility.RegisterVanillaBlock;
import ch.talenta.jpm.utils.Utility.VanillaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

//@RegisterVanillaBlock(block = VanillaBlocks.cake_slice1)
public class TestBlock extends Block{
	
	public static TestBlock instance =  null;

	public TestBlock () {
		super(Material.IRON);
		//super(true);
		setUnlocalizedName("testblock");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	

}
