package barrier.entities.units;

import arc.*;
import arc.audio.*;
import arc.math.*;
import arc.math.Mathf;
import mindustry.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import barrier.content.*;
import barrier.content.BBulletTypes;

public class BarrierUnitEntity extends UnitEntity {
   public Bullet releaseBullet = BBulletTypes.repulsiveBulletSmall;
   public int releaseBullets = 36;
   public Sound releaseSound = Sounds.none;
   
   @Override
   public void killed(Unit unit) {
      Events.run(Trigger.update, () -> {
         if (!(state.isPaused())) {
            if (Mathf.chanceDelta(0.18f)) {
               releaseSound.at(unit.x, unit.y);
               humiliate(unit);
            }
         }
      });
   }
   
   @Override
   public void destroy(Unit unit) {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate(unit);
      }
      deathSound.at(unit.x, unit.y);
   }
   
   @Override
   public void remove(Unit unit) {
      for (int i = 0; i < releaseBullets; i++) {
         humiliate(unit);
      }
   }
   
   public void humiliate(Unit unit) {
      releaseBullet.create(unit, unit.team, unit.x, unit.y, unit.rotation + Mathf.range(360f));
   }
}