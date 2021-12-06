package barrier.content;

import mindustry.ctype.*;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;
import barrier.entities.bullet.RepulseBulletType;

public class BBulletTypes implements ContentList{
    public static BulletType
    
    // Repulsive bullets
    repulsiveBulletSmall,
    
    // Release bullets
    energyConcentrationBullet;
    
    @Override
    public void load() {
       repulsiveBulletSmall = new RepulseBulletType(2.25f, 150f){{
           size = 6f;
           rang = 60f;
           repulseRange = 25f;
           statusTime = 150f;
           
           despawnEff = BFx.barrierUnitShockwaveSmall;
       }};
       
       energyConcentrationBullet = new BasicBulletType(2.25f, 150f){{
           size = 6f;
           rang = 60f;
           repulseRange = 25f;
           statusTime = 150f;
           
           despawnEff = BFx.barrierUnitShockwaveSmall;
       }};
       
    }
}