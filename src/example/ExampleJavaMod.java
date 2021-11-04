package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import example.content;

public class ExampleJavaMod extends Mod {

    public ExampleJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            boolean show = true;
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Yes");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("error")/*ohno*/).padBottom(20f).row();
                dialog.cont.button("Hide this and never show it again", dialog::hide).size(100f, 350f);
                if (show) dialog.show();
            });
        });
    }

    private final ContentList[] barrierContent = {
      new BBlockTypes();
    }
    
    @Override
    public void loadContent() {
      Log.info("Loading some example content...");
      for (ContentList list : barrierContent) {
        list.load();
      }
    }
}
