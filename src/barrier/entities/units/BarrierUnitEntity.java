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
import mindustry.entities.bullet.*;
import barrier.content.*;
import barrier.content.BBulletTypes;

import static mindustry.Vars.*;

public class BarrierUnitEntity extends UnitEntity {
   public BulletType releaseBullet = BBulletTypes.repulsiveBulletSmall;
   public int releaseBullets = 36;
   public int releasesDuringKill = 15;
   public Sound releaseSound = Sounds.missile;
   
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
      for (int i = 0; i < releaseBullets; i++) {
         humiliate();
      }
      super.destroy();
   }
   
   @Override
   public void remove() {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate();
      }
      super.remove();
   }
   
   public void humiliate() {
      releaseBullet.create(this, team, x, y, rotation + Mathf.range(360f));
   }
}