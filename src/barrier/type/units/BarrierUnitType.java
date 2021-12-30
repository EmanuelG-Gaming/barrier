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
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.UnitType;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;
import barrier.entities.units.BarrierUnitEntity;

public class BarrierUnitType extends UnitType{
  public Color shineColor = Pal.lancerLaser;
  public Color engColor = Pal.spore;
  public Color secondaryColor = Color.white;
  
  public boolean useEngineTrail = false;
  
  public int spikes = 4;
  public boolean useSecondarySpikes = false;
  public float spikeWidth = -4f;
  public float spikeHeight = -4f;
  
   public BarrierUnitType(String name) {
	  	super(name);
	  	onTitleScreen = false;
	  	deathSound = Sounds.corexplode;
   }
   
   @Override
   public void draw(Unit unit) {
      super.draw(unit);
      Draw.z(Layer.effect);
      drawAura(unit);
      Draw.z();
   }
   
   @Override
   public void drawEngine(Unit unit) {
      if (!unit.isFlying()) return;
      float offset = engineOffset / 2f + engineOffset / 2f * unit.elevation;
      if (useEngineTrail) {
         if (unit instanceof BarrierUnitEntity d) { 
            d.trail.draw(
               Tmp.c1.set(engColor).lerp(secondaryColor, Mathf.absin(Time.time, 10, 1)),
               (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f) * unit.elevation) * trailScl
            );
         }
      } 
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
      float size = hitSize + 5f;
      Lines.circle(unit.x, unit.y, size);
      for (int i = 0; i < spikes; i++) {
         float rot = (360 / spikes * i) + Mathf.sin(Time.time * 0.05f) * 45f + unit.rotation;
         Drawf.tri(unit.x + Angles.trnsx(rot, size), unit.y + Angles.trnsy(rot, size), spikeWidth, spikeHeight, rot);
         if (useSecondarySpikes) {
           Drawf.tri(unit.x + Angles.trnsx(rot, size), unit.y + Angles.trnsy(rot, size), spikeWidth, -spikeHeight, rot);
         }
      }
   }
}