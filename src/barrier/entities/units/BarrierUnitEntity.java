package barrier.entities.units;

import arc.*;
import arc.audio.*;
import arc.math.*;
import arc.math.Mathf;
import arc.math.Angles;
import arc.util.*;
import arc.util.Time;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.gen.UnitEntity;
import mindustry.content.*;
import mindustry.content.Fx;
import mindustry.content.Weathers;
import mindustry.type.*;
import mindustry.type.UnitType;
import mindustry.type.Weather;
import mindustry.entities.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.units.*;
import mindustry.graphics.Trail;
import barrier.content.*;
import barrier.content.BBulletTypes;
import barrier.content.BUnitTypes;

import static mindustry.Vars.*;

public class BarrierUnitEntity extends UnitEntity {
   public BulletType releaseBullet = BBulletTypes.repulsiveBulletSmall;
   public int releaseBullets = 16;
   public int releasesDuringKill = 8;
   public float speedScl = 3.2f;
   public Sound releaseSound = Sounds.missile;
   public Effect destroyShockwave = Fx.spawnShockwave;
   public Weather weather = Weathers.suspendParticles;
   public float weatherTime = 9600f;
   
   public UnitType destroyUnit = BUnitTypes.flyer;
   public int destroyUnits = 5;
   public float unitSpread = 45f;
   private static UnitType toastUnit = BUnitTypes.barrierUnit;
   
   public Trail trail = new Trail(7);
   
   @Override
   public void update() {
       super.update();
       // again offset
       float offset = type.engineOffset / 2f + type.engineOffset / 2f * elevation;
       trail.update(
           x + Angles.trnsx(rotation + 180, offset / 2f),
           y + Angles.trnsy(rotation + 180, offset / 2f)
       );
   }
   
   @Override
   public void kill() {
     for (int i = 0; i < releasesDuringKill; i++) {
        Time.run(9f * i + Mathf.range(0f, 3.5f), () -> {
           if (!(state.isPaused())) {
             releaseSound.at(x, y);
             humiliate();
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
      
      float windx = Mathf.range(1f) * world.width(), windy = Mathf.range(1f) * world.height();
      Call.createWeather(weather, 0.5f + Mathf.range(1.5f), weatherTime + Mathf.range(1600f), windx, windy);
      ui.hudfrag.showToast(Icon.warning, toastUnit.emoji() + " " + Core.bundle.format("barrier.isDead", toastUnit.localizedName));
      
      Time.run(60f, () -> {
         for (int i = 0; i < destroyUnits; i++) {
            float rx = x + Mathf.range(unitSpread), ry = y + Mathf.range(unitSpread);
            Fx.spawn.at(rx, ry);
            Sounds.respawn.at(rx, ry);
            Unit u = destroyUnit.create(team);
            u.set(rx, ry);
            Tmp.v1.set(u.x, u.y);
            float rot = u.angleTo(Tmp.v1);
            u.rotation = rot;
            Events.fire(new UnitCreateEvent(u, null, this));
            if (!net.client()) {
              u.add();
            }
         }
      });
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
   
   @Override
   public String toString() {
      return "BarrierUnitEntity#" + id;
   }
}