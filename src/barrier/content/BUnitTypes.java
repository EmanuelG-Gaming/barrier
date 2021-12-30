package barrier.content;

import arc.*;
import arc.util.*;
import arc.struct.*;
import mindustry.ai.types.*;
import mindustry.ctype.*;
import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.mod.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import barrier.type.units.*;
import barrier.content.*;
import barrier.entities.bullet.*;
import barrier.entities.units.BarrierUnitEntity;
import barrier.entities.units.SpeedyUnitEntity;

public class BUnitTypes implements ContentList{
    public static UnitType
    
    barrierUnit, flyer, savior;
    
    @Override
    public void load() {
        
        barrierUnit = new BarrierUnitType("barrierUnit"){{
            hideDetails = false;
            flying = true;
            health = 40000f;
            speed = 3.5f;
            accel = 0.08f;
            drag = 0.01f;
            range = 160f;
            engineOffset = 7f;
            defaultController = FlyingAI::new;
            constructor = BarrierUnitEntity::new;
            ammoType = new PowerAmmoType(1550);
            spikes = 5;
            
            region = Core.atlas.find("barrierUnit");
            
            // Summit wep
            weapons.add(
            new Weapon("summit"){{
                top = false;
                y = 0f;
                x = 0f;
                reload = 260f;
                recoil = 4f;
                shake = 2f;
                ejectEffect = Fx.none;
                shootSound = Sounds.release;
                bullet = new RepulseBulletType(3f, 600f){{
                   splashDamage = 750f;
                   splashDamageRadius = 110f;
                }};
            }},
            
            // Amalgate wep
            new Weapon("amalgate"){{
                top = false;
                y = 0f;
                x = 0f;
                reload = 160f;
                recoil = 0f;
                shake = 0f;
                ejectEffect = Fx.none;
                shootSound = Sounds.mineDeploy;
                bullet = new GathererBulletType(){{
                   releaseBullet = BBulletTypes.energyConcentrationBullet;
                }};
            }});
        }};
        
        flyer = new SpeedyUnitType("speedyBastard"){{
            hideDetails = false;
            hitSize = 4.75f;
            flying = true;
            health = 1500f;
            speed = 4.5f;
            accel = 0.07f;
            drag = 0.006f;
            range = 160f;
            engineOffset = 6.25f;
            defaultController = FlyingAI::new;
            constructor = SpeedyUnitEntity::new;
            ammoType = new PowerAmmoType(600);
            spikes = 3;
            
            region = Core.atlas.find("speedyBastard");
        }};
        
        finalBoss = new BarrierUnitType("finalBoss"){{
            hideDetails = false;
            hitSize = 28f;
            flying = true;
            lowAltitude = true;
            health = 120000f;
            defense = 15;
            speed = 3.5f;
            accel = 0.07f;
            drag = 0.006f;
            range = 250f;
            engineOffset = 17;
            engineSize = 4.5f; 
            defaultController = FlyingAI::new;
            constructor = BarrierUnitEntity::new;
            ammoType = new PowerAmmoType(16000);
            spikes = 8;
            useSecondarySpikes = true;
            spikeWidth = 7f;
            spikeHeight = 10f;
            shineColor = engColor;
            
            region = Core.atlas.find("finalBoss");
        }};
                
        savior = new BarrierUnitType("anticheatUnit"){{
            hideDetails = false;
            hitSize = 3.45f;
            flying = true;
            health = Integer.MAX_VALUE;
            speed = 2.5f;
            accel = 0.07f;
            drag = 0.006f;
            range = 190f;
            engineOffset = 3.5f;
            engineSize = 0.5f;
            defaultController = FlyingAI::new;
            constructor = SpeedyUnitEntity::new;
            ammoType = new PowerAmmoType(5000);
            spikes = 2;
            
            region = Core.atlas.find("anticheatUnit");
        }};
    };
}