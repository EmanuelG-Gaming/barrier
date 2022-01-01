package barrier.content;

import arc.*;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.content.Items;
import mindustry.world.*;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.*;
import barrier.content.*;
import barrier.content.BFx;
import barrier.content.BBulletTypes;
import barrier.world.blocks.defense.BarrierBlockType;
import barrier.world.blocks.defense.turrets.BarrierTurret;

import static mindustry.type.ItemStack.with;

public class BBlockTypes implements ContentList {
  public static Block
  
  // Defense
  barrier, barrierLarge, 
  
  // Turrets
  failure;

  @Override
  public void load() {
    barrier = new BarrierBlockType("barrier"){{
       size = 1;
       hideDetails = false;
       requirements(Category.defense, BuildVisibility.editorOnly, with(Items.copper, 6, Items.graphite, 6, Items.thorium, 6, Items.surgeAlloy, 6, BItems.indignalum, 6, BItems.repudialite, 3));
    }};
    
    barrierLarge = new BarrierBlockType("barrierLarge"){{
       size = 2;
       hideDetails = false;
       requirements(Category.defense, BuildVisibility.editorOnly, with(Items.copper, 24, Items.graphite, 24, Items.thorium, 24, Items.surgeAlloy, 24, BItems.indignalum, 24, BItems.repudialite, 12));
    }};
    
    failure = new BarrierTurret("failure"){{
       size = 3;
       health = 1000 * size * size;
       
       shots = 3;
       range = 180f;
       reloadTime = 60f;
       inaccuracy = 8f;
       recoilAmount = 7f;
       shootSound = Sounds.release;
       
       rotateSpeed = 10f;
       
       ammo(Items.surgeAlloy, BBulletTypes.repulsiveBulletSmall);
       requirements(Category.turret, BuildVisibility.editorOnly, with(Items.titanium, 1100, Items.graphite, 1200, Items.silicon, 2500, Items.plastanium, 850, Items.surgeAlloy, 600, BItems.repudialite, 50));
    }};
  }
}