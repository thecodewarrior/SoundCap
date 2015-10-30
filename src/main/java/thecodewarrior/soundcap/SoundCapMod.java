package thecodewarrior.soundcap;


import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import paulscode.sound.SoundSystemConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SoundCapMod.MODID, version = SoundCapMod.VERSION)
public class SoundCapMod
{
    public static final String MODID = "soundCap";
    public static final String VERSION = "1.0";
    
    Logger l;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	l = event.getModLog();
    	float mulNormal = 2, mulStream = 2;
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	mulNormal = config.getFloat("normal", "multipliers", mulNormal, 0.25f, 64,
    		"Multiplier for the number of normal channels, number of channels will be 28*this");
    	mulStream = config.getFloat("stream", "multipliers", mulStream, 0.25f, 64,
        	"Multiplier for the number of stream channels, number of channels will be 4*this");
    	config.save();
    	int normalChannels    = (int)Math.floor( 28*mulNormal   );
    	int streamingChannels = (int)Math.floor(  4*mulStream );
    	SoundSystemConfig.setNumberNormalChannels(normalChannels);
    	SoundSystemConfig.setNumberStreamingChannels(streamingChannels);
    	
    	l.info(String.format( "Normal channel multiplier is %.3f, streaming channel multiplier is %.3f", mulNormal, mulStream ));
    	l.info(String.format( "Set maximum normal channels to %d, and maximum streaming channels to %d", normalChannels, streamingChannels ));
    }
}
