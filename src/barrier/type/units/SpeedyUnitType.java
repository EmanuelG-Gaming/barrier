package barrier.type.units;

import arc.*;
import arc.math.*;
import arc.math.Mathf;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.type.*;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.Effect;
import barrier.content.BFx;
import barrier.type.units.BarrierUnitType;

public class SpeedyUnitType extends BarrierUnitType {
  public Item item = Items.blastCompound;
  public Effect hfx = BFx.getItem;
  public Effect hfxBh = BFx.getItemBh;
  public float radius = 50f;
  public boolean isGathering = false;
  
  public SpeedyUnitType(String name) {
	  	super(name);
	  	// this unit should always use engine trails
	  	useEngineTrail = true;
  }
  
  @Override
  public Unit create(Team team) {
      Unit unit = super.create(team);
      float sx = Mathf.sin(Time.time * 0.05f) * radius, sy = Mathf.cos(Time.time * 0.05f) * radius;
      for (int i = 0; i < itemCapacity; i++) {
         Time.run(10f * i, () -> {
             Fx.itemTransfer.at(unit.x + sx, unit.y + sy, 4, item.color, unit);
             hfx.at(unit.x + sx, unit.y + sy, 0, item);
             hfxBh.at(unit.x + sx, unit.y + sy, 0, item);
             unit.stack.amount++;
         });
      }
     
      unit.stack.item = item;
      unit.apply(StatusEffects.unmoving, 10f * unit.type.itemCapacity + 10f);
     
      return unit;
  }
}