package barrier.entities.bullet;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.math.geom.Vec2;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.type.StatusEffect;
import mindustry.entities.*;
import mindustry.entities.Units;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import barrier.content.*;
import barrier.content.BStatusEffects;

public class RepulseBulletType extends BulletType {
	public float repulseRange = 50f;
	public float rang = 120f;
	public float repulseDamage = 0f;
	public float size = 10f;
	public StatusEffect knockbackStatus = BStatusEffects.severed;
	public float statusTime = 400f;
	public Effect despawnEff = BFx.barrierUnitShockwave;
	
	private static final Vec2 v1 = new Vec2();
  
	public RepulseBulletType(float speed, float damage) {
		super(speed, damage);
		layer = Layer.bullet;
		trailColor = Pal.lancerLaser;
		trailLength = 4;
		trailWidth = size;
		trailParam = size - 1.5f;
		trailChance = 1f;
	}
	
	@Override
  public void draw(Bullet b) {
    Draw.color(trailColor);
    Fill.circle(b.x, b.y, size);
    Draw.color(Color.white);
    Fill.circle(b.x, b.y, size / 2);
    Draw.reset();
  }
  
  /*this is a thing from Flare Boss*/
  public void repulse(Bullet b) {
    Units.nearbyEnemies(b.team, b.x, b.y, rang, enemy -> {
      if (enemy != null && enemy.within(b.x, b.y, rang) && enemy.isValid()) {
        enemy.apply(knockbackStatus, statusTime);
        v1.set(b).sub(enemy).nor().scl(repulseRange * 80f);
        v1.setAngle(b.angleTo(enemy));
        enemy.impulse(v1);
      }
    });
  }
  
  @Override
  public void hit(Bullet b) {
	   despawned(b);
	}
	
  @Override
	public void despawned(Bullet b) {
	  repulse(b);
    despawnEff.at(b.x, b.y);
	}
}