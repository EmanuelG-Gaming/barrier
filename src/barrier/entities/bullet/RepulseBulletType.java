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
import mindustry.entities.*;
import mindustry.entities.Units;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import barrier.content.*;
import barrier.content.BStatusEffects;

public class RepulseBulletType extends BulletType {
	public float repulseRange = 50f;
	public float range = 120f;
	public float size = 16f;
	public StatusEffect knockbackStatus = BStatusEffects.severed;
	public float statusTime = 70f;
	private static final Vec2 v1 = new Vec2();
	public Effect despawnEff = BFx.barrierUnitShockwave;

	public RepulseBulletType(float speed, float damage) {
		super(speed, damage);
		layer = Layer.bullet;
		trailColor = Pal.lancerLaser;
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
  @Override
	public void despawned(Bullet b) {
	  Units.nearbyEnemies(b.owner.team, b.x, b.y, range, enemy -> {
      if (enemy != null && enemy.within(b.x, b.y, range) && enemy.isValid()) {
        v1.set(unit).sub(enemy).nor().scl(repulseRange * 80f);
        v1.setAngle(b.angleTo(enemy));
        enemy.impulse(v1);
         
        enemy.apply(statusTime, time);
      }
    });
    despawnEff.at(b.x, b.y);
	}
}