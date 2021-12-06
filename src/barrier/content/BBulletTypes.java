package barrier.content;

import arc.graphics.Color;
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
       
       energyConcentrationBullet = new BasicBulletType(4.5f, 1900f){{
           backColor = Pal.spore;
           frontColor = Color.white;
           width = 34f;
           height = 50f;
       }};
       
    }
}