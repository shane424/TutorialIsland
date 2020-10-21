package Utils;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.MagicSpell;
import org.osbot.rs07.api.ui.Spells;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;


public class Magic {
    Script script = null;

    public Magic(Script script) {
        this.script = script;
    }

    public void checkIfTooFar() throws InterruptedException {
        if(script.widgets.isVisible(162)){
            script.widgets.get(162,33).interact();
            script.sleep(script.random(400,700));
        }
    }

    public void waiting() throws InterruptedException {
        while (script.myPlayer().isAnimating() || script.myPlayer().isMoving()) {
            script.sleep(script.random(100, 400));
        }
    }


    public void execute() throws InterruptedException {
        checkIfTooFar();
        switch (script.configs.get(281)) {
            case 620:
                script.walking.walk(new Area(3140, 3088, 3143, 3085).getRandomPosition());
                waiting();
                script.npcs.closest("Magic Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1300,1700), false);
                break;
            case 630:
                if(!script.getTabs().getOpen().equals("MAGIC"))
                    script.getTabs().open(Tab.MAGIC);
                break;
            case 640:
                script.npcs.closest("Magic Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1300,1700), false);
                break;
            case 650:
                script.walking.walk(new Area(3138, 3091, 3140, 3091).getRandomPosition());
                waiting();
                Entity chicken;
                chicken = script.getNpcs().npcs.closest(new Filter<NPC>() {
                    @Override
                    public boolean match(NPC npc) {
                        return npc != null && npc.getName().equals("Chicken") && !npc.isUnderAttack() && (npc.getInteracting() == null || npc.getInteracting().equals(script.myPlayer())) && npc.getAnimation() != 4653;
                    }
                });
                script.magic.castSpellOnEntity(Spells.NormalSpells.WIND_STRIKE, chicken);
                new ConditionalSleep(5000) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() && script.myPlayer().isMoving();
                    }
                }.sleep();
                waiting();
                break;
            case 670:
                script.npcs.closest("Magic Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1300,1700), false);
                if(script.widgets.isVisible(219)){
                    script.widgets.get(219,0,1).interact("Continue");
                }
                script.keyboard.typeKey(' ', 0, script.random(1900,2100), false);
                break;
        }
    }
}
