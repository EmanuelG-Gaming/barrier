package barrier.entities.units;

import arc.*;
import arc.math.*;
import arc.math.Mathf;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.gen.*;
import mindustry.gen.UnitEntity;
import mindustry.game.*;
import mindustry.type.*;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.Effect;
import barrier.content.BFx;

public class SpeedyUnitEntity extends UnitEntity {
  public Item item = Items.blastCompound;
  public Effect hfx = BFx.getItem;
  public Effect hfxBh = BFx.getItemBh;
  
  @Override
  public void create(Team team) {
     float sx = x + Mathf.sin(Time.time * 0.05f) * 50f, sy = y + Mathf.cos(Time.time * 0.05f) * 50f;
     for (int i = 0; i < type.itemCapacity; i++) {
        Time.run(8f * i, () -> {
           Fx.itemTransfer.at(sx, sy, 4, item.color, this);
           hfx.at(sx, sy, 0, item);
           stack.amount += 1;
        });
     }
     
     hfxBh.at(sx, sy, 0, item);
     stack.item = item;
     this.apply(StatusEffects.unmoving, 8f * type.itemCapacity + 10f);
     super.create(team);
  }
  
  @Override
  public String toString() {
     return "SpeedyUnitEntity#" + id;
  }
}