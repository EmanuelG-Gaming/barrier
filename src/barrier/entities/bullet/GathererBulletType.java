package barrier.entities.bullet;

import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.Angles;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.content.Fx;
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
  
  public Color colorFrom = Pal.lancerLaser;
  public Color colorTo = Pal.spore;
  
  public Effect particleEffect = BFx.gatherParticle;
  public Effect cumulativeEffect = BFx.gatherCumulate;
  public float generalDelay = 10f; 
  public float effectDelay = 20f;
  public int effects = 20;
  public float shake = 8f;
  
  public Sound releaseSound = BarrierSounds.gigablast;
  public float releasePitchMin = 0.7f;
  public float releasePitchMax = 1.2f;
  
  public BulletType releaseBullet = null;
  
	public GathererBulletType() {
	   super();
	   speed = 8f;
		 lifetime = generalDelay;
		 layer = Layer.bullet;
		 trailEffect = Fx.none;
		 
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
	   for (int i = 0; i < effects; i++) {
	      Time.run(i * effectDelay, () -> {
	         particleEffect.at(b.x + Angles.trnsx(Mathf.range(360f), sizeTo * 1.25f), b.y + Angles.trnsy(Mathf.range(360f), sizeTo * 1.25f), 0, Tmp.c1.set(colorFrom).lerp(colorTo, Mathf.absin(Time.time, 8, 1)), b);
	      });
	   }
	   
	   float rx = b.x + Angles.trnsx(Mathf.range(360f), 4f), ry = b.y + Angles.trnsy(Mathf.range(360f), 4f);
	   if (releaseBullet != null) {
	      releaseBullet.create(b, rx, ry, b.rotation(), 1f);
	      releaseBullet.hitEffect.at(rx, ry);
	   }
	   cumulativeEffect.at(b.x, b.y, sizeTo, new Color[]{colorFrom, colorTo});
	   Effect.shake(shake, shake, b);
	   releaseSound.at(b.x, b.y, Mathf.random(releasePitchMin, releasePitchMax));
	}
}