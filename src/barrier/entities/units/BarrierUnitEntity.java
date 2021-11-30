package barrier.entities.units;

import arc.*;
import arc.audio.*;
import arc.math.*;
import arc.math.Mathf;
import mindustry.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.entities.bullet.*;
import barrier.content.*;
import barrier.content.BBulletTypes;

import static mindustry.Vars.*;

public class BarrierUnitEntity extends UnitEntity {
   public BulletType releaseBullet = BBulletTypes.repulsiveBulletSmall;
   public int releaseBullets = 36;
   public Sound releaseSound = Sounds.none;
   
   @Override
   public void kill() {
      Events.run(Trigger.update, () -> {
         if (!(state.isPaused())) {
            if (Mathf.chanceDelta(0.18f)) {
               releaseSound.at(x, y);
               humiliate();
            }
         }
      });
   }
   
   @Override
   public void destroy() {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate();
      }
   }
   
   @Override
   public void remove() {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate();
      }
   }
   
   public void humiliate() {
      releaseBullet.create(this, team, x, y, rotation + Mathf.range(360f));
   }
}