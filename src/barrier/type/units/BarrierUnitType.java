package barrier.type.units;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.Color;
import arc.math.*;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.math.geom.Angles;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.UnitType;
import mindustry.entities.*;
import mindustry.graphics.*;

public class BarrierUnitType extends UnitType{
  public Color shineColor = Pal.lancerLaser;
  public Color engColor = Pal.spore;
  public Color secondaryColor = Color.white;
  
  public int spikes = 4;
   public BarrierUnitType(String name) {
	  	super(name);
	  	constructor = UnitEntity::create;
	  	onTitleScreen = false;
   }
   
   @Override
   public void draw(Unit unit) {
      super.draw(unit);
      drawAura(unit);
   }
   
   @Override
   public void drawEngine(Unit unit) {
      if (unit instanceof UnitEntity u) {
        float offset = engineOffset / 2f + engineOffset / 2f * unit.elevation;
        Draw.color(engColor, secondaryColor, Mathf.absin(Time.time, 10, 1));
        Fill.circle(
          unit.x + Angles.trnsx(unit.rotation + 180, offset),
          unit.y + Angles.trnsy(unit.rotation + 180, offset),
          (u.engineScl + Mathf.absin(Time.time, 2f, u.engineScl / 4f)) * unit.elevation
        );
        Draw.color(secondaryColor);
        Fill.circle(
          unit.x + Angles.trnsx(unit.rotation + 180, offset),
          unit.y + Angles.trnsy(unit.rotation + 180, offset),
          (u.engineScl + Mathf.absin(Time.time, 2f, u.engineScl / 4f)) * unit.elevation / 2
        );
        Draw.reset();
      }
   }
   
   public void drawAura(Unit unit) {
      Draw.color(shineColor, secondaryColor, Mathf.absin(Time.time, 10, 1));
      Draw.z(Layer.effect);
      float size = hitSize + 3f;
      Lines.circle(unit.x, unit.y, size);
      for (int i = 0; i < spikes; i++) { //////////////////////////////////////////////////////////////
         float rot = (360 / spikes * i) + Mathf.sin(Time.time * 0.05) * 45 + unit.rotation;
         Drawf.tri(unit.x + Angles.trsnx(rot, size), unit.y + Angles.trnsy(rot, size), 4, -4, rot)
      }
      Draw.z();
   }
}