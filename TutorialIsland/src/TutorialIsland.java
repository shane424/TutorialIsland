import Utils.*;

import org.rspeer.script.Script;

import java.awt.*;
import java.util.concurrent.TimeUnit;


@ScriptManifest(name = "Tutorial Island Completer", author = "Bear", version = 1.0, info = "", logo = "")
public class TutorialIsland extends Script {
    Script script = this;
    private long startTime;
    private long timeRan;
    Area tutorialIsland = new Area(3060, 3134, 3152, 3060);
    @Override
    public void onStart() {
        //Code here will execute before the loop is
        startTime = System.currentTimeMillis();
        timeRan = 0;
        if(!tutorialIsland.contains(myPlayer())){
            stop();
        }
    }

    public void checkIfTooFar() throws InterruptedException {
        if(widgets.isVisible(162)){
            widgets.get(162,33).interact();
            sleep(random(400,700));
        }
    }

    @Override
    public void onExit() {
        //Code here will execute after the script ends


    }

    public void waiting() throws InterruptedException {
        while (script.myPlayer().isAnimating() || script.myPlayer().isMoving()) {
            sleep(random(100, 400));
        }
    }

//    public void spaceBar() throws InterruptedException {
//        keyboard.pressKey(32);
//        sleep(random(1000,1500));
//        keyboard.releaseKey(32);
//    }

    @Override
    public int onLoop() throws InterruptedException {
        if(!tutorialIsland.contains(myPlayer())){
            stop();
        }
        //script.log("Current config " + script.configs.get(281));
        if(configs.get(281) >= 20 && configs.get(281) <= 110)
            new SurvivalExpert(script).execute();
        if(configs.get(281) >= 130 && configs.get(281) <= 200)
            new Chef(script).execute();
        if(configs.get(281) >= 260 && configs.get(281) <= 360)
            new Mining(script).execute();
        if(configs.get(281) >= 370 && configs.get(281) <= 500)
            new Combat(script).execute();
        if(configs.get(281) >= 620 && configs.get(281) <= 670)
            new Magic(script).execute();
        checkIfTooFar();
        switch(configs.get(281)) {
            case 0:
                new CreateChar(script).execute();
                npcs.closest("RuneScape Guide").interact("Talk-to");
                waiting();
                //push space 6 times, hit 3 for experienced, space 3 more times, settings
                keyboard.typeKey(' ', 0, random(800,1000), false);
                break;
            case 3:
                getTabs().open(Tab.SETTINGS);
                break;
            case 7:
                //space 2 times
                keyboard.typeKey(' ', 0, random(800,1000), false);
                break;
            case 10:
                objects.closest("Door").interact("Open");
                waiting();
                break;
            case 20:
                new SurvivalExpert(script).execute();
                break;
            case 120:
                walking.walk(new Area(3090, 3092, 3091, 3091));
                waiting();
                objects.closest("Gate").interact("Open");
                waiting();
                break;
            case 210:
                walking.walk(new Area(3087, 3127, 3085, 3126));
                waiting();
                objects.closest("Door").interact("Open");
                waiting();
                break;
            case 220:
                npcs.closest("Quest Guide").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(1400,1700), false);
                break;
            case 230:
                getTabs().open(Tab.QUEST);
                sleep(random(500,700));
                break;
            case 240:
                npcs.closest("Quest Guide").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(1400,1600), false);
                break;
            case 250:
                objects.closest("Ladder").interact("Climb-down");
                break;
            //[281: 520] Close bank interface, use poll booth (id: 26815) - Skip 1, 2, 3 times,
            case 510:
                walking.walk(new Area(3121, 3122, 3123, 3121).getRandomPosition());
                waiting();
                npcs.closest("Banker").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(800,1000), false);
                if(script.widgets.isVisible(219)){
                    script.widgets.get(219,0,1).interact("Continue");
                }
                break;
            case 520:
                if(widgets.isVisible(12)) {
                    script.widgets.get(12, 3, 11).interact("Close");
                    sleep(random(500, 900));
                    objects.closest("Poll booth").interact("Use");
                    waiting();
                    keyboard.typeKey(' ', 0, random(1200, 1500), false);
                }
                else{
//                    npcs.closest("Banker").interact("Talk-to");
//                    waiting();
//                    keyboard.typeKey(' ', 0, random(1000,1300), false);
//                    keyboard.pressKey(49);
                    objects.closest("Poll booth").interact("Use");
                    waiting();
                    keyboard.typeKey(' ', 0, random(1200, 1500), false);
                }
                break;
            case 525:
                if(script.widgets.isVisible(310)) {
                    script.widgets.get(310, 2, 11).interact("Close");
                    sleep(random(500, 900));
                    walking.walk(new Position(3124, 3124, 0));
                    waiting();
                    objects.closest("Door").interact("Open");
                }
                else{
                    objects.closest("Poll booth").interact("Use");
                    waiting();
                    keyboard.typeKey(' ', 0, random(1200,1500), false);
                }
                break;
            case 530:
                npcs.closest("Financial Advisor").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(2000,2300), false);
                break;
            case 540:
                walking.walk(new Position(3129,3124,0));
                sleep(random(400,700));
                waiting();
                if(objects.closest("Door").hasAction("Open"))
                    objects.closest("Door").interact("Open");
                break;
            case 550:
                walking.walk(new Area(3129, 3108, 3130, 3106).getRandomPosition());
                waiting();
                npcs.closest("Brother Brace").interact("Talk-to");
                sleep(random(400,700));
                waiting();
                keyboard.typeKey(' ', 0, random(1800,2000), false);
                break;
            case 560:
                if(!getTabs().getOpen().equals("PRAYER"))
                    getTabs().open(Tab.PRAYER);
                break;
            case 570:
                npcs.closest("Brother Brace").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(1500,1700), false);
                break;
            case 580:
                if(!getTabs().getOpen().equals("FRIENDS"))
                    getTabs().open(Tab.FRIENDS);
                break;
            case 590:
                if(!getTabs().getOpen().equals("IGNORES"))
                    getTabs().open(Tab.IGNORES);
                break;
            case 600:
                npcs.closest("Brother Brace").interact("Talk-to");
                waiting();
                keyboard.typeKey(' ', 0, random(2000,2300), false);
                break;
            case 610:
                walking.walk(new Area(3122, 3103, 3123, 3103));
                waiting();
                objects.closest("Door").interact("Open");
                break;
        }

        return random(100,300); //The amount of time in milliseconds before the loop starts over
    }


    @Override
    public void onPaint(Graphics2D g) {
        //This is where you will put your code for paint(s)
        timeRan = System.currentTimeMillis() - startTime;
        g.drawString(formatTime(timeRan), 50, 330);
    }

    public final String formatTime(final long ms){
        long s = ms / 1000, m = s / 60, h = m / 60, d = h / 24;
        s %= 60; m %= 60; h %= 24;

        return d > 0 ? String.format("%02d:%02d:%02d:%02d", d, h, m, s) :
                h > 0 ? String.format("%02d:%02d:%02d", h, m, s) :
                        String.format("%02d:%02d", m, s);
    }

    private String ft(long duration)
    {
        String res = "";
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                .toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                .toMinutes(duration));
        if (days == 0) {
            res = (hours + ":" + minutes + ":" + seconds);
        } else {
            res = (days + ":" + hours + ":" + minutes + ":" + seconds);
        }
        return res;
    }
}