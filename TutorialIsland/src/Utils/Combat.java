package Utils;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class Combat {
    Script script = null;

    public Combat(Script script) {
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

    private void openStats() throws InterruptedException {
        if(script.tabs.getOpen().equals("EQUIPMENT"));
        if(script.widgets.get(84, 1) != null && script.widgets.get(84, 1).isVisible()) {
            script.widgets.get(84, 4).interact();
        }
        else{
            script.npcs.closest("Combat Instructor").interact("Talk-to");
        }
    }

    public void execute()  throws InterruptedException {
        checkIfTooFar();
        switch (script.configs.get(281)) {
            case 370:
                script.npcs.closest("Combat Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1500,2000), false);
                break;
            case 390:
                script.getTabs().open(Tab.EQUIPMENT);
                script.sleep(script.random(700,900));
                break;
            case 400:
                script.widgets.get(387, 18).interact();
                script.sleep(script.random(700,900));
                break;
            case 405:
                script.inventory.interact("Wield", "Bronze dagger");
                script.sleep(script.random(700,900));
                break;
            case 410:
                if(script.widgets.get(84, 1) != null && script.widgets.get(84, 1).isVisible()) {
                    script.widgets.get(84, 4).interact();
                }
                else{
                    script.npcs.closest("Combat Instructor").interact("Talk-to");
                    waiting();
                }
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1500,2000), false);
                break;
            case 420:
                script.getTabs().open(Tab.EQUIPMENT);
                script.sleep(script.random(700,900));
                openStats();
                script.inventory.interact("Wield", "Bronze sword");
                script.sleep(script.random(700,900));
                script.inventory.interact("Wield", "Wooden shield");
                script.sleep(script.random(700,900));
                break;
            case 430:
                script.getTabs().open(Tab.ATTACK);
                script.keyboard.typeKey(' ', 0, script.random(700,1300), false);
                break;
            case 440:
                script.walking.walk(new Position(3107, 9511, 0));
                waiting();
                script.objects.closest("Gate").interact("Open");
                waiting();
                break;
            case 450:
                if(!script.myPlayer().isAnimating() || script.myPlayer().isMoving())
                    script.npcs.closest("Giant rat").interact("Attack");
                break;
            case 460:
                waiting();
                break;
            case 470:
                script.objects.closest("Gate").interact("Open");
                waiting();
                script.npcs.closest("Combat Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1200,1600), false);
                break;
            case 480:
                script.inventory.interact("Wield", "Bronze arrow");
                script.sleep(script.random(700,900));
                script.inventory.interact("Wield", "Shortbow");
                script.sleep(script.random(700,900));
                NPC rat = script.getNpcs().npcs.closest(new Filter<NPC>() {
                    @Override
                    public boolean match(NPC npc) {
                        return npc != null && npc.getName().equals("Giant rat") && !npc.isUnderAttack() && (npc.getInteracting() == null || npc.getInteracting().equals(script.myPlayer())) && npc.getAnimation() != 4653;
                    }
                });
                rat.interact("Attack");
                new ConditionalSleep(5000) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() && script.myPlayer().isMoving();
                    }
                }.sleep();
                break;
            case 490:
                if(!script.myPlayer().isAnimating()) {
                    script.inventory.interact("Wield", "Bronze arrow");
                    script.sleep(script.random(400, 600));
                    script.inventory.interact("Wield", "Shortbow");
                    script.sleep(script.random(400, 600));
                    rat = script.getNpcs().npcs.closest(new Filter<NPC>() {
                        @Override
                        public boolean match(NPC npc) {
                            return npc != null && npc.getName().equals("Giant rat") && !npc.isUnderAttack() && (npc.getInteracting() == null || npc.getInteracting().equals(script.myPlayer())) && npc.getAnimation() != 4653;
                        }
                    });
                    rat.interact("Attack");
                    new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return script.myPlayer().isAnimating() && script.myPlayer().isMoving();
                        }
                    }.sleep();
                }
                else {
                    waiting();
                }
                break;
            case 500:
                script.walking.walk(new Position(3111, 9525, 0));
                waiting();
                script.objects.closest("Ladder").interact("Climb-up");
                break;
        }

    }
}
