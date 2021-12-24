package barrier.type.units;

import arc.*;
import arc.math.*;
import arc.math.Mathf;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.game.Team;
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
  }
  
  @Override
  public void create(Team team) {
      Unit unit = super.create(team);
      float sx = unit.x + Mathf.sin(Time.time * 0.05f) * radius, sy = unit.y + Mathf.cos(Time.time * 0.05f) * radius;
      for (int i = 0; i < type.itemCapacity; i++) {
         Time.run(8f * i, () -> {
            if (unit.dead == false) {
               Fx.itemTransfer.at(sx, sy, 4, item.color, unit);
               hfx.at(sx, sy, 0, item);
               unit.stack.amount += 1;
            }
         });
      }
     
      hfxBh.at(sx, sy, 0, item);
      unit.stack.item = item;
      unit.apply(StatusEffects.unmoving, 8f * unit.type.itemCapacity + 10f);
     
      return unit;
  }
}