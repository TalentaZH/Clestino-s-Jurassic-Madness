package ch.talenta.jpm.items;

import ch.talenta.jpm.JPM;
import ch.talenta.jpm.utils.Utility.RegisterItemWithMesh;
import ch.talenta.jpm.utils.Utility.RegisterVanillaItem;
import ch.talenta.jpm.utils.Utility.VanillaItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//@RegisterVanillaItem(item = VanillaItems.emerald_ore)
public class TestItem extends Item{
	
	public static TestItem instance = null;
	
	public TestItem() {
		//super(true);
				setUnlocalizedName("testitem");
				setCreativeTab(CreativeTabs.MISC);
	}
	
}
