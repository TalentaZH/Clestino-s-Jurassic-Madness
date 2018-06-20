package ch.talenta.jpm;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = JPM.MODID, name = JPM.NAME, version = JPM.VERSION)
public class JPM {

	public static final String MODID = "jpm";
	public static final String NAME = "Celestino's Jurassic Madness";
	public static final String VERSION = "0.1a";
	
	public static final String RESOURCE_PREFIX = MODID.toLowerCase() + ":"; 

	private static Logger logger;
	
	@Instance(MODID)
	public static JPM main;
	
	//@SidedProxy(clientSide = "ch.talenta.jpm.sides.ClientProxy", serverSide = "ch.talenta.sides.jpm.ServerProxy")
	//public static Proxy proxy;

	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {	
		logger = event.getModLog();
		Minecraft.getMinecraft();
		//proxy.preInit(event);
	}

	@EventHandler
	public void init (FMLInitializationEvent event) {
		//proxy.init(event);
	}
	



}
