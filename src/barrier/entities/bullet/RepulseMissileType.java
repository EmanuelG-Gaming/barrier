package barrier.entities.bullet;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.Color;
import arc.math.*;
import mindustry.graphics.*;
import mindustry.graphics.Pal;
import mindustry.entities.bullet.*;
import mindustry.entities.Effect;
import mindustry.gen.*;
import barrier.entities.bullet.RepulseBulletType;
import barrier.content.BFx;

public class RepulseMissileType extends RepulseBulletType {
   
   public RepulseMissileType(float speed, float damage) {
	  	super(speed, damage);
		  trailColor = Pal.spore;
		  trailLength = 8;
		  trailChance = 0f;
		  weaveScale = 8f;
      weaveMag = 1f;
      homingRange = 350f;
      keepVelocity = false;
      hitEffect = BFx.pesticide;
 	 }
 	 
 	 @Override
 	 public void update(Bullet b) {
 	    super.update(b);
 	    if (b.time < lifetime) {
 	       b.vel.setLength(Interp.pow10Out.apply(b.fout()) * speed);
 	    }
 	 }
 	 
   @Override
   public void draw(Bullet b) {
      super.draw(b);
      Draw.color(trailColor);
      Draw.z(layer - 0.01f);
      int points = 4;
      for (int i = 0; i < points; i++) {
         float angle = i * 360f / points;
         Drawf.tri(b.x + Angles.trnsx(angle, size - 2f), b.y + Angles.trnsy(angle, size - 2f), 8, 16, angle + b.rotation());
      }
      Draw.z();
      Draw.color();
   }
   
 	 @Override
 	 public void repulse(Bullet b) {
 	    super.repulse(b);
 	    BFx.gatherCumulate.at(b.x, b.y, rang + 10f, new Color[]{Pal.spore, Pal.spore});
 	 }
}