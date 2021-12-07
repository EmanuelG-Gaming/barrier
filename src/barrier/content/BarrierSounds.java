package barrier.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

// something I've stolen from ER also
public class BarrierSounds {
  	public static Sound	gigablast = new Sound();
  	
  	protected static Sound loadSound(String fileName) {
	     String name = "sounds/" + fileName;
		   String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

		   Sound sound = new Sound();

	     AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
		   desc.errored = Throwable::printStackTrace;

	     return sound;
  	}
  	
  	public static void load() {
	     if (Vars.headless) return;
	     // Flar Bos sound!!!!!!1111!!
		   gigablast = loadSound("gigablast");
  	}
}