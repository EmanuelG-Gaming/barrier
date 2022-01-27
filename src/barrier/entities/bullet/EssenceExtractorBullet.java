package barrier.entities.bullet;

import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

//something that (really) doesn't make other enemy bullets go well
public class EssenceExtractorBullet extends BulletType {
   public float radius = 16f;
   public float dmgMaxThreshold = 1200f;
   public Effect extractEffect = Bfx.bulletExtraction;
   
   public EssenceExtractorBullet(float speed, float damage) {
      super(speed, damage);
      layer = Layer.bullet;
      homingPower = 0.1f;
      collidesGround = true;
      despawnHit = true;
      shrinkY = 0.2f;
   }
   
   @Override
   public void draw(Bullet b) {
      super.draw(b);
      Draw.color(Color.white);
      Fill.circle(b.x, b.y, radius);
   }
   
   /*
    * original code
    * let hfx = new Effect(120, e => {
    *   if (!(e.data[2] instanceof Color)) return;
    *   if (e.data[3] == Core.atlas.find("error")) return;
    *   if (e.data[4] == Core.atlas.find("error")) return;
    *   Draw.z(Layer.bullet);
    *   Draw.mixcol(e.data[2].cpy().lerp(Color.black, e.fin()), 1);
    *   Tmp.v1.set(e.x, e.y).trns(e.rotation + 90, 10 * e.fin());
    *   Draw.rect(e.data[3], e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.data[0] * e.fout(), e.data[1] * e.fout(), e.rotation);
    *   Draw.mixcol();
    *   Draw.color(Color.white, Color.black, e.fin());
    *   Draw.rect(e.data[4], e.x, e.y, e.data[0] * e.fout(), e.data[1] * e.fout(), e.rotation);
    *   Draw.z();
    * });
    * let radius = 100;
    * Groups.bullet.intersect(92 * 8 - radius, 50 * 8 - radius, radius * 2, radius * 2).each(b => {
    *   if (b != null && b.team != Team.sharded && b.type.absorbable && !(b instanceof LightningBulletType) && b.damage <= 300) {
    *     b.absorb();
    *     hfx.at(b.x, b.y, b.rotation() - 90, [b.type.width, b.type.height, b.type.backColor.cpy(), "bullet-back", "bullet"]);
    *   }
    * });
   */
   
   @Override
   public void update(Bullet b) {
     super.update(b);
     Groups.bullet.intersect(b.x - radius, b.y - radius, radius * 2, radius * 2).each(bul -> {
       if (bul != null) {
         if (bul.team != b.team && bul.type.absorbable && !(bul instanceof LightningBulletType) && !(bul instanceof LaserBulletType) && bul.damage <= dmgMaxThreshold) {
             bul.absorb();
             extractEffect.at(bul.x, bul.y, bul.rotation(), bul);
         }
       }
     });
   }
   
   //what
   @Override
   public void hit(Bullet b, float x, float y) {
       super.hit(b, x, y);
   }
}