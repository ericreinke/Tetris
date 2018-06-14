/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating.pkg2017;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author ereinke7771
 */
public class HighScores extends BasicGameState{
Image screen;
private static StateBasedGame game;
public String mouse="";
    HighScores(int i) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        screen=new Image("Images\\blankhigh.png");
    }

    @Override
    public int getID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        game=sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        screen.draw(0,0);
          g.setColor(Color.white);
    g.drawString(mouse, 10, 50);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Input input = gc.getInput();
        int xpos = input.getMouseX();
int ypos = input.getMouseY();
    mouse = "x: " + xpos + " y: " + ypos; 
    
        if(input.isMouseButtonDown(0)&&input.getMouseX()>18&&input.getMouseX()<187&&input.getMouseY()>326&&input.getMouseY()<344){
            game.enterState(0,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
    }
    
}
