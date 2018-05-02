package gdx.Humberto;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import gdx.menu.*;
import gdx.common.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HumbertoTest implements Screen, InputProcessor {
    /*=========================================================================================================================================================
        This scratch is based on making turns betweeen the player and enemies.
     =========================================================================================================================================================*/
    Button btnMenu;
    GameMenu gamMenu;
    Texture txButtonP, txButtonT;
    SpriteBatch batch;
    public OrthographicCamera cam;
    Texture Tank1, Tank2, floor, back, gasmoney, Enemygasmoney, block, Lives, EnemyLives;
    float TankX, TankY, EnemyX, EnemyY, TankSize, BlockX, BlockY, BlockSize, camX, camY;
    float SpriteSpeed = 155f, EnemySpriteSpeed = 155f;
    double dSpeed = 0, dEnemySpeed, dGravity = 0.15;
    int nGas, nEnemyGas, nLives, nEnemyLives;
    boolean bTurn = true, bGravity = true;
    Random rand = new Random();
    float rotationSpeed;
    int frog = rand.nextInt(3) + 1;
    float SX = TankX;
    int SY = 699, SH = 25, SW = 25;
    double dSpeedX = 4, dSpeedY = 4;
    boolean Fire = false, InAir = false;

    public HumbertoTest(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        Gdx.input.setInputProcessor(this);
        Tank1 = new Texture("Tanks.png");
        Tank2 = new Texture("enemy.png");
        floor = new Texture("floor.jpg");
        back = new Texture("back.jpg");
        gasmoney = new Texture("green.png");
        Enemygasmoney = new Texture("green.png");
        block = new Texture("block.png");
        Lives = new Texture("red.png");
        EnemyLives = new Texture("red.png");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(w, h);
        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
        TankX = 100;
        TankY = 500;
        EnemyX = Gdx.graphics.getWidth() - 200;
        EnemyY = 500;
        TankSize = 100;
        BlockX = 500;
        BlockY = 300;
        BlockSize = 100;
        dSpeed = 0;
        dEnemySpeed = 0;
        nGas = 500;
        nEnemyGas = 500;
        nLives = 100;
        nEnemyLives = 100;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1); //White background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        btnMenu.draw(batch);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
        batch.end();
        if (bGravity == true) { //Gravity
            dSpeed *= dGravity;
            dEnemySpeed *= dGravity;
        } else if (bGravity == false) {
            TankY++;
        }
        if (TankY >= 100) {
            TankY -= dSpeed;
            EnemyY -= dEnemySpeed;
        }
        if (bTurn == true) {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && TankX > 0) { // turn
                TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    nGas -= 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && TankX < Gdx.graphics.getWidth()) {
                TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    nGas -= 1;
                }
            }
            camX = TankX;
            camY = TankY;
            if (nGas <= 400) {
                bTurn = false;
            }
        } else if (bTurn == false) {
            EnemySpriteSpeed = 155f;
            SpriteSpeed = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.A) && EnemyX > 100) {
                EnemyX -= Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
                if (EnemySpriteSpeed > 1) {
                    nEnemyGas--;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && EnemyX < Gdx.graphics.getWidth()) {
                EnemyX += Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
                if (EnemySpriteSpeed > 1) {
                    nEnemyGas--;
                }
            }
            camX = EnemyX;
            camY = EnemyY;
            if (nEnemyGas <= 400) {
                EnemySpriteSpeed = 0;
                bTurn = true;
                SpriteSpeed = 155f;
                nGas = 500;
                nLives -= 10;
                nEnemyGas = 500;
            }
        }
        if (TankX > EnemyX && TankX < EnemyX + TankSize//Hitdect
                && +TankY + TankSize > EnemyY && TankY < EnemyY + TankSize) {
            TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
            EnemyX -= Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
        } else if (TankX < EnemyX && TankX > EnemyX - TankSize //Hitdect
                && +TankY + TankSize > EnemyY && TankY < EnemyY + TankSize) {
            TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
            EnemyX += Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
        }
        dEnemySpeed = 30;
        dSpeed = 30;
        EnemySpriteSpeed = 0;
        if (nLives <= 0) {
            TankX = 100;
            TankY = 500;
            EnemyX = Gdx.graphics.getWidth() - 200;
            EnemyY = 500;
            nLives = 100;
        }
    }

    /*package com.terrian.game;

   import com.badlogic.gdx.ApplicationAdapter;
   import com.badlogic.gdx.Gdx;
   import com.badlogic.gdx.graphics.GL20;
   import com.badlogic.gdx.graphics.Texture;
   import com.badlogic.gdx.graphics.g2d.SpriteBatch;
   import com.badlogic.gdx.Gdx;
   import com.badlogic.gdx.Input;
   import com.badlogic.gdx.InputProcessor;
   import com.badlogic.gdx.Screen;
   import com.badlogic.gdx.audio.Music;
   import com.badlogic.gdx.graphics.Cursor;
   import com.badlogic.gdx.graphics.GL20;
   import com.badlogic.gdx.graphics.Pixmap;
   import com.badlogic.gdx.graphics.Texture;
   import com.badlogic.gdx.graphics.g2d.Animation;
   import com.badlogic.gdx.graphics.g2d.BitmapFont;
   import com.badlogic.gdx.graphics.g2d.Sprite;
   import com.badlogic.gdx.graphics.g2d.SpriteBatch;
   import com.badlogic.gdx.graphics.g2d.TextureRegion;
   import com.badlogic.gdx.scenes.scene2d.Actor;
   import com.badlogic.gdx.scenes.scene2d.Stage;
   import com.badlogic.gdx.graphics.OrthographicCamera;
   import com.badlogic.gdx.math.MathUtils;
   import java.util.Random;
   import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
   import java.util.ArrayList;
  
   import java.util.Timer;
   import java.util.TimerTask;

       public class Terrian extends ApplicationAdapter {

           SpriteBatch batch;
           public OrthographicCamera cam;
           Texture Tank1, Tank2, floor, back, gasmoney, Enemygasmoney, block, Lives, EnemyLives;
           float TankX, TankY, EnemyX, EnemyY, TankSize, BlockX, BlockY, BlockSize, camX, camY;
           float SpriteSpeed = 155f, EnemySpriteSpeed = 155f;
           double dSpeed = 0, dEnemySpeed, dGravity = 0.15;
           int nGas, nEnemyGas, nLives, nEnemyLives;
           boolean bTurn = true, bGravity = true;
           Random rand = new Random();
           float rotationSpeed;
           int frog = rand.nextInt(3) + 1;
           float SX = TankX;
           int SY = 699, SH = 25, SW = 25;
           double dSpeedX = 4, dSpeedY = 4;
           boolean Fire = false;
           boolean InAir = false;

           public void create() {
               batch = new SpriteBatch();
               Tank1 = new Texture("Tanks.png");
               Tank2 = new Texture("enemy.png");
               floor = new Texture("floor.jpg");
               back = new Texture("back.jpg");
               gasmoney = new Texture("green.png");
               Enemygasmoney = new Texture("green.png");
               block = new Texture("block.png");
               Lives = new Texture("red.png");
               EnemyLives = new Texture("red.png");

               TankX = 100;
               TankY = 500;
               EnemyX = Gdx.graphics.getWidth() - 200;
               EnemyY = 500;
               TankSize = 100;
               BlockX = 500;
               BlockY = 300;
               BlockSize = 100;
               dSpeed = 0;
               dEnemySpeed = 0;
               nGas = 500;
               nEnemyGas = 500;
               nLives = 100;
               nEnemyLives = 100;
           }

           public void render() {
               batch.begin();
               float w = Gdx.graphics.getWidth();
               float h = Gdx.graphics.getHeight();
               cam = new OrthographicCamera(w, h);
               cam.position.set(camX, camY, 0);
               cam.update();
               batch.setProjectionMatrix(cam.combined);
               cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
               float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
               float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
               cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
               cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
               if (bGravity == true) { //Gravity
                   dSpeed *= dGravity;
                   dEnemySpeed *= dGravity;
               } else if (bGravity == false) {
                   TankY++;
               }
               if (TankY >= 100) {
                   TankY -= dSpeed;
                   EnemyY -= dEnemySpeed;
               }
               batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
               batch.draw(gasmoney, 10, Gdx.graphics.getHeight() - 50, nGas - 400, 10);
               batch.draw(Enemygasmoney, Gdx.graphics.getWidth() - nEnemyGas + 400 - 10, Gdx.graphics.getHeight() - 50, nEnemyGas - 400, 10);
               batch.draw(block, BlockX, BlockY, BlockSize, BlockSize);
               batch.draw(Tank1, TankX, TankY, TankSize, TankSize);
               batch.draw(Tank2, EnemyX, EnemyY, TankSize, TankSize);
               batch.draw(Lives, 10, Gdx.graphics.getHeight() - 25, nLives, 10);
               batch.draw(EnemyLives, Gdx.graphics.getWidth() - nEnemyLives - 10, Gdx.graphics.getHeight() - 25, nEnemyLives, 10);

               if (bTurn == true) {
                   if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && TankX > 0) { // turn
                       TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                       if (SpriteSpeed > 1) {
                           nGas -= 1;
                       }
                   }
                   if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && TankX < Gdx.graphics.getWidth()) {
                       TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                       if (SpriteSpeed > 1) {
                           nGas -= 1;
                       }
                   }
                   camX = TankX;
                   camY = TankY;
                   if (nGas <= 400) {
                       bTurn = false;
                   }
               } else if (bTurn == false) {
                   EnemySpriteSpeed = 155f;
                   SpriteSpeed = 0;
                   if (Gdx.input.isKeyPressed(Input.Keys.A) && EnemyX > 100) {
                       EnemyX -= Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
                       if (EnemySpriteSpeed > 1) {
                           nEnemyGas--;
                       }
                   }
                   if (Gdx.input.isKeyPressed(Input.Keys.D) && EnemyX < Gdx.graphics.getWidth()) {
                       EnemyX += Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
                       if (EnemySpriteSpeed > 1) {
                           nEnemyGas--;
                       }
                   }
                   camX = EnemyX;
                   camY = EnemyY;
                   if (nEnemyGas <= 400) {
                       EnemySpriteSpeed = 0;
                       bTurn = true;
                       SpriteSpeed = 155f;
                       nGas = 500;
                       nLives -= 10;
                       nEnemyGas = 500;
                   }
               }
               if (TankX > EnemyX && TankX < EnemyX + TankSize//Hitdect
                       && +TankY + TankSize > EnemyY && TankY < EnemyY + TankSize) {
                   TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                   EnemyX -= Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
               } else if (TankX < EnemyX && TankX > EnemyX - TankSize //Hitdect
                       && +TankY + TankSize > EnemyY && TankY < EnemyY + TankSize) {
                   TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                   EnemyX += Gdx.graphics.getDeltaTime() * EnemySpriteSpeed;
               }

          /* if (TankX > BlockX - 50 && TankX < BlockX + BlockSize - 20 //Hitdect
                   && + TankY < BlockY + BlockSize - 20) {
               TankY += 5;
           } */
    //if (TankY <= 100) {
         /*   dEnemySpeed = 30;
            dSpeed = 30;
            EnemySpriteSpeed = 0;
            if (nLives <= 0) {
                TankX = 100;
                TankY = 500;
                EnemyX = Gdx.graphics.getWidth() - 200;
                EnemyY = 500;
                nLives = 100;
            }
            batch.end();
        }

        public void resize ( int width, int height){
      /* cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();*/
     /*   }

        public void dispose () {
            batch.dispose();
            Tank1.dispose();
        }
    }
*/
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            if (isHit(screenX, screenY, btnMenu)) {
                System.out.println("Menu");
                gamMenu.updateState(0);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isHit(int nX, int nY, Sprite sprBtn) {
        if (nX > sprBtn.getX() && nX < sprBtn.getX() + sprBtn.getWidth() && nY > sprBtn.getY() && nY < sprBtn.getY() + sprBtn.getHeight()) {
            return true;
        } else {
            return false;
        }
    }
}
