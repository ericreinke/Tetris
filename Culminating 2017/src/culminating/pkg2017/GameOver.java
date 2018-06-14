/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating.pkg2017;

import static culminating.pkg2017.Play.play;
import java.awt.Font;
import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author ereinke7771
 */
public class GameOver extends BasicGameState{
public static Image background;
public TrueTypeFont font;
public TrueTypeFont font2;
public TrueTypeFont font3;
    GameOver(int i) throws SlickException {
        background=new Image("Images\\background.png");
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
         Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
         
    // load font from a .ttf file
    try {
        InputStream inputStream = ResourceLoader.getResourceAsStream("Images\\Ghostmea.ttf");
         
        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        awtFont2 = awtFont2.deriveFont(50f); // set font size
        font2 = new TrueTypeFont(awtFont2, false);
        Font awtFont3 = awtFont2.deriveFont(18f);
         // set font size
        font3 = new TrueTypeFont(awtFont3, false);
        
             
    } catch (Exception e) {
        e.printStackTrace();
    }   

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
background.draw(0,0);
font2.drawString(150, 100, "GAME OVER", Color.white);
    font3.drawString(275, 160, "YOU SUCK", Color.white);    
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }
    
}
