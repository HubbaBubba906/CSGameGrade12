package gdx.menu;

import com.badlogic.gdx.Game;
import gdx.common.*;
import gdx.menu.Screens.*;
import gdx.Humberto.*;

public class GameMenu extends Game {

    ScrMenu scrMenu;
    HumbertoTest HumbertoTest;
    ScrScratchMenu scrScratch;
    ScrMove scrScratch1;
    ScrTurns scrScratch2;
    ScrExplodeScratch scrScratch3;
    ScrMovingShot scrScratch4;
    ScrVector scrScratch5;
    ScrUserInput scrScratch6;
    ScrPlay scrPlay;
    int nScreen; // 0 for menu, 1 for Scratchs

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        //Scratches are going to start at 10 and go farther, main game assests are going to be under 10.
        if (nScreen == 0) {
            setScreen(scrMenu);
        }
        else if (nScreen == 1) {
            setScreen(scrScratch);
        }
        else if (nScreen == 2) {
            setScreen(scrPlay);
        }
        else if (nScreen == 10) {
            setScreen(scrScratch1);
        } //Scratch screen for terrain
        else if (nScreen == 11) {
            setScreen(scrScratch2);
        } //Scratch for shooting
        else if (nScreen == 12) {
            setScreen(scrScratch3);
        } //Scratch for shooting again
        else if (nScreen == 13) {
            setScreen(scrScratch4);
        } //Scratch for shooting with vectors and gravity
        else if (nScreen == 14) {
            setScreen(scrScratch5);
            //Scratch for user input shooting
        } else if (nScreen == 15) {
            setScreen(scrScratch6);
        } //This is a screen for humberto within our single repositiory to see if this works with push and pull
        else if (nScreen == 20) {
            setScreen(HumbertoTest);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        // notice that "this" is passed to each screen. Each screen now has access to methods within the "game" master program
        scrMenu = new ScrMenu(this);
        scrPlay = new ScrPlay(this);
        scrScratch = new ScrScratchMenu(this);
        scrScratch1 = new ScrMove(this);
        scrScratch2 = new ScrTurns(this);
        scrScratch3 = new ScrExplodeScratch(this);
        scrScratch4 = new ScrMovingShot(this);
        scrScratch5 = new ScrVector(this);
        scrScratch6 = new ScrUserInput(this);
        HumbertoTest = new HumbertoTest(this);
        updateState(0);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
