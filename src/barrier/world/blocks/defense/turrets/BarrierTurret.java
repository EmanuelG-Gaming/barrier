package barrier.world.blocks.defense.turrets;

import mindustry.world.blocks.defense.turrets.ItemTurret;

/*
 *Just a turret with a different base region,
 *for better recognition
*/
public class BarrierTurret extends ItemTurret {
   
   public BarrierTurret(String name) {
      super(name);
   }
   
   @Override
   public void load() {
      super.load();
      baseRegion = Core.atlas.find("barrier-barrierTurret-base-" + size);
   }
}