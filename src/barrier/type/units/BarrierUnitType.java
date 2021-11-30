package barrier.type.units;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.Color;
import arc.math.*;
import arc.math.Mathf;
import arc.math.Angles;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.UnitType;
import mindustry.entities.*;
import mindustry.graphics.*;

import static mindustry.Vars.*; 

public class BarrierUnitType extends UnitType{
  public Color shineColor = Pal.lancerLaser;
  public Color engColor = Pal.spore;
  public Color secondaryColor = Color.white;
  
  public int spikes = 4;
  
  public Bullet releaseBullet = BBulletTypes.smallRepulsiveBullet;
  public int releaseBullets = 36;
 
   public BarrierUnitType(String name) {
	  	super(name);
	  	constructor = UnitEntity::create;
	  	onTitleScreen = false;
	  	deathSound = Sounds.corexplode;
   }
   
   @Override
   public void draw(Unit unit) {
      super.draw(unit);
      drawAura(unit);
   }
   
   @Override
   public void drawEngine(Unit unit) {
      float offset = engineOffset / 2f + engineOffset / 2f * unit.elevation;
      Draw.color(engColor, secondaryColor, Mathf.absin(Time.time, 10, 1));
      Fill.circle(
        unit.x + Angles.trnsx(unit.rotation + 180, offset),
        unit.y + Angles.trnsy(unit.rotation + 180, offset),
        (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f)) * unit.elevation
      );
      Draw.color(secondaryColor);
      Fill.circle(
        unit.x + Angles.trnsx(unit.rotation + 180, offset - 1f),
        unit.y + Angles.trnsy(unit.rotation + 180, offset - 1f),
        (engineSize + Mathf.absin(Time.time, 2f, engineSize/ 4f)) * unit.elevation / 2f
      );
      Draw.reset();
   }
   
   public void drawAura(Unit unit) {
      Draw.color(shineColor, secondaryColor, Mathf.absin(Time.time, 10, 1));
      Draw.z(Layer.effect);
      float size = hitSize + 4.5f;
      Lines.circle(unit.x, unit.y, size);
      for (int i = 0; i < spikes; i++) {
         float rot = (360 / spikes * i) + Mathf.sin(Time.time * 0.05f) * 45f + unit.rotation;
         Drawf.tri(unit.x + Angles.trnsx(rot, size), unit.y + Angles.trnsy(rot, size), 4, -4, rot);
      }
      Draw.z();
   }
   
   public void humiliate(Unit unit) {
      releaseBullet.create(unit, unit.team, unit.x, unit.y, unit.rotation + Mathf.range(360), Mathf.range(2.5));
   }
   
   @Override
   public void killed() {
      Events.on(Trigger.update.class, () -> {
         if (!(state.isPaused())) {
            if (Mathf.chanceDelta(0.18f)) {
               releaseSound.at(this);
               humiliate(this);
            }
         }
      });
   }
   
   @Override
   public void destroy() {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate(this);
      }
      deathSound.at(x, y);
   }
   
   @Override
   public void remove() {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate(this);
      }
   }
}