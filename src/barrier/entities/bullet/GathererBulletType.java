package barrier.entities.bullet;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.Effect;
import barrier.content.*;

/*
/ For those who don't know:
/ a bullet which absorbs energetic particles to become bigger and more powerful
/ to release a huge bullet after a large amount of time
*/
public class GathererBulletType extends PointBulletType {
  public Color colorFrom = Pal.lancerLaser;
  public Color colorTo = Pal.spore;
  public Effect particleEffect = BFx.gatherParticle;
  public float generalDelay = 10f; 
  public float effectDelay = 20f;
  public int effects = 20;
  
	public GathererBulletType(float damage) {
	   super(damage);
	   speed = 0f;
		 //to avoid potential bugs
		 lifetime = effectDelay * effects + generalDelay;
		 layer = Layer.bullet;
		 
		 collides = false;
		 absorbable = false;
		 hittable = false;
	}
	
	@Override
  public void draw(Bullet b) {
     Draw.color(colorFrom, colorTo, b.fout());
     Fill.circle(b.x, b.y, size);
     Draw.color(Color.white);
     Fill.circle(b.x, b.y, size / 2);
     Draw.reset();
  }
  
  @Override
	public void despawned(Bullet b) {
	   release(b);
	} 
	
	public void release(Bullet b) {
	   //soon
	}
}