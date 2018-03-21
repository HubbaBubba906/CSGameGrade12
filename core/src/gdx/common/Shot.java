package gdx.common;

import gdx.menu.Screens.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
//
public class Shot extends Sprite {
    public Shot(int nW, int nH, int nX, int nY, String sFileName) {
        super(new Texture(Gdx.files.internal(sFileName)));  //For example "Menu.jpg"
        setPosition(nX, nY);
        setFlip(false, true);
        setSize(nW, nH);
    }
}