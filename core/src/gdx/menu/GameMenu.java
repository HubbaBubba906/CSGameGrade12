package gdx.menu;

import com.badlogic.gdx.Game;
import gdx.common.*;
import gdx.menu.Screens.*;
import gdx.Humberto.*;

public class GameMenu extends Game {

    ScrMenu scrMenu;
    HumbertoTest HumbertoTest;
    ScrScratchMenu ScrScratch;
    ScrMGGScratch ScrScratch1;
    ScrScratchT ScrScratch2;
    ScrScratchShoot ScrScratch3;
    ScrScratchShooting2 ScrScratch4;
    ScrScratchShootV ScrScratch5;
    int nScreen; // 0 for menu, 1 for Scratchs

    public void updateState(int _nScreen) {
        nScreen = _nScreen;
        if (nScreen == 0) {
            setScreen(scrMenu);
        } else if (nScreen == 1) {
            setScreen(ScrScratch);
        } //Scratches are going to start at 10 and go farther, main game assests are going to be under 10.
        else if (nScreen == 10) {
            setScreen(ScrScratch1);
        } //Scratch screen for terrain
        else if (nScreen == 11) {
            setScreen(ScrScratch2);
        } //Scratch for shooting
        else if (nScreen == 12) {
            setScreen(ScrScratch3);
        } //Scratch for shooting again
        else if (nScreen == 13) {
            setScreen(ScrScratch4);
        } //Scratch for shooting with vectors and gravity
        else if (nScreen == 14) {
            setScreen(ScrScratch5);
        }
        //This is a screen for humberto within our single repositiory to see if this works with push and pull
        else if (nScreen == 20){
            setScreen(HumbertoTest);
        }
    }

    @Override
    public void create() {
        nScreen = 0;
        // notice that "this" is passed to each screen. Each screen now has access to methods within the "game" master program
        scrMenu = new ScrMenu(this);
        ScrScratch = new ScrScratchMenu(this);
        ScrScratch1 = new ScrMGGScratch(this);
        ScrScratch2 = new ScrScratchT(this);
        ScrScratch3 = new ScrScratchShoot(this);
        ScrScratch4 = new ScrScratchShooting2(this);
        ScrScratch5 = new ScrScratchShootV(this);
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
