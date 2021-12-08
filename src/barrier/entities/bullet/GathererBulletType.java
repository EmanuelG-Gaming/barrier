package barrier.entities.bullet;

import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.Effect;
import mindustry.graphics.*;
import mindustry.gen.*;
import barrier.content.*;

/*
/ For those who don't know:
/ a bullet which absorbs energetic particles to become bigger and more powerful
/ to release a huge bullet after a large amount of time
*/
public class GathererBulletType extends PointBulletType {
  public float sizeFrom = 20f;
  public float sizeTo = 100f;
  public static final float particleSize = 5f;
  
  public Color colorFrom = Pal.lancerLaser;
  public Color colorTo = Pal.spore;
  
  public Effect particleEffect = BFx.gatherParticle;
  public Effect cumulativeEffect = BFx.gatherCumulate;
  public float generalDelay = 10f; 
  public float effectDelay = 20f;
  public int effects = 20;
  
  public Sound releaseSound = BarrierSounds.gigablast;
  
	public GathererBulletType() {
	   super();
	   speed = 0f;
		 //to avoid potential bugs
		 lifetime = effectDelay * effects + generalDelay;
		 layer = Layer.bullet;
		 
		 collides = false;
		 absorbable = false;
		 hittable = false;
	}
	
  @Override
	public void despawned(Bullet b) {
	   release(b);
	} 
	
	@Override
	public void hit(Bullet b) {
	   release(b);
	} 
	
	public void release(Bullet b) {
	   float progress = 0f;
	   
	   progress += Time.delta;
	   for (int i = 0; i < effects; i++) {
	      Time.run(i * effectDelay, () -> {
	         particleEffect.at(b.x + Mathf.range(50), b.y + Mathf.range(50), particleSize, Tmp.c1.set(colorFrom).lerp(colorTo, progress), b);
	      });
	   }
	   
	   cumulativeEffect.at(b.x, b.y, sizeTo, new Color[]{colorFrom, colorTo});
	   releaseSound.at(b.x, b.y);
	}
}