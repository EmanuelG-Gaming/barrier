package barrier.entities.units;

import arc.*;
import arc.audio.*;
import arc.math.*;
import arc.math.Mathf;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.content.*;
import mindustry.content.Fx;
import mindustry.type.*;
import mindustry.type.UnitType;
import mindustry.entities.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import barrier.content.*;
import barrier.content.BBulletTypes;
import barrier.content.BUnitTypes;

import static mindustry.Vars.*;

public class BarrierUnitEntity extends UnitEntity {
   public BulletType releaseBullet = BBulletTypes.repulsiveBulletSmall;
   public int releaseBullets = 16;
   public int releasesDuringKill = 15;
   public float speedScl = 3.2f;
   public Sound releaseSound = Sounds.missile;
   public Effect destroyShockwave = Fx.spawnShockwave;
   
   private static UnitType toastUnit = BUnitTypes.barrierUnit;
   
   @Override
   public void kill() {
     for (int i = 0; i < releasesDuringKill; i++) {
        Time.run(9f * i, () -> {
           if (!(state.isPaused())) {
              if (Mathf.chanceDelta(0.45f)) {
                 releaseSound.at(x, y);
                 humiliate();
              }
           }
        });
     }
     super.kill();
   }
   
   @Override
   public void destroy() {
      destroyShockwave.at(x, y, 70);
      for (int i = 0; i < releaseBullets; i++) {
         humiliateSpeed();
      }
      ui.hudfrag.showToast(toastUnit.region, Core.bundle.format("barrier.isDead", toastUnit.localizedName));
      super.destroy();
   }
   
   @Override
   public void remove() {
      super.remove();
   }
   
   public void humiliate() {
      releaseBullet.create(this, team, x, y, rotation + Mathf.range(360f));
   }
   
   /*like humiliate() but the bullets have random speeds*/
   public void humiliateSpeed() {
      releaseBullet.create(this, team, x, y, rotation + Mathf.range(360f), Mathf.range(speedScl));
   }
}