/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating.pkg2017;

//import javafx.animation.FadeTransition;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import static org.newdawn.slick.Color.black;
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
public class Culminating extends BasicGameState{
public String mouse="";
public boolean end;
private static StateBasedGame game;
    Image menu;
    //= new Image("Images\\Menu(1).jpeg");
    Culminating(int i) throws SlickException {
       menu= new Image("Images\\MainMenu.png");
        
    }

    @Override
    public int getID() {  
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
         game = sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        menu.draw(0,0);
        g.setColor(Color.white);
    g.drawString(mouse, 10, 50);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
 Input input = gc.getInput();
int xpos = input.getMouseX();
int ypos = input.getMouseY();
    mouse = "x: " + xpos + " y: " + ypos; 
    //SETTINGS
        if(input.isMouseButtonDown(0)&&input.getMouseX()>193&&input.getMouseX()<436&&input.getMouseY()>115&&input.getMouseY()<142){
            game.enterState(1,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
        if(input.isMouseButtonDown(0)&&input.getMouseX()>535&&input.getMouseX()<621&&input.getMouseY()>348&&input.getMouseY()<366){
         end=true;
        }
        if(end)gc.exit(); 
        //HIGHSCORES
         if(input.isMouseButtonDown(0)&&input.getMouseX()>143&&input.getMouseX()<485&&input.getMouseY()>185&&input.getMouseY()<214){
            game.enterState(2,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
         //PLAY
          if(input.isMouseButtonDown(0)&&input.getMouseX()>170&&input.getMouseX()<471&&input.getMouseY()>42&&input.getMouseY()<67){
            game.enterState(3,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
        
    }
    
 
    
}
