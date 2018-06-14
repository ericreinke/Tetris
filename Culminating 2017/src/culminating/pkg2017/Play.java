/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.



 */
package culminating.pkg2017;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Font;
import java.io.InputStream;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author ereinke7771
 */
public class Play  extends BasicGameState{
    Random rn = new Random();
public static boolean spawn=false;
int random = rn.nextInt(7) + 1;
public static Image blank,blue,green,yellow,red,orange,purple,cyan,play;
public String mouse="";
public static boolean placed=true,releaseleft=true,releaseright=true,releasedown=true,releaseup=true;
public static Image[][] array=new Image[10][20];
public static int[][] board=new int[10][20];
public static int[][] numberarray=new int[10][20];
public static int [][] piececoord=new int[4][2];
public static int[] available=new int[10];
public static boolean canMove=true;
int[]check=new int[10];
public static int nextpiece;
boolean run1=true;
boolean movedown;
int dropcounter=0;
int linecount=0;
boolean releaseenter=true;
int arraycount=0;
int[] maxdrop=new int[4];
int drop=0;
public static boolean gameover=false;
private static StateBasedGame game;
public TrueTypeFont font;
public TrueTypeFont font2;
    Play(int i) throws SlickException {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        play=new Image("Images\\play.png");
        blue=new Image ("Images\\blue.png");
        green=new Image ("Images\\green.png");
        yellow=new Image ("Images\\yellow.png");
        red=new Image ("Images\\red.png");
        orange=new Image ("Images\\orange.png");
        purple=new Image ("Images\\purple.png");
        cyan=new Image ("Images\\cyan.png");
        blank=new Image("Images\\blank.png");
        
        //setting original colors
        
        
        
        for(int z=0;z<20;z++){
            for(int a=0;a<10;a++){
                array[a][z]=new Image("Images\\blank.png");
                numberarray[a][z]=0;
                
            }
        }
    }

    @Override
    public int getID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        nextpiece=rn.nextInt(7)+1;
        //nextpiece=1;
        game=sbg;
  Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
         
    // load font from a .ttf file
    try {
        InputStream inputStream = ResourceLoader.getResourceAsStream("Images\\Ghostmea.ttf");
         
        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        awtFont2 = awtFont2.deriveFont(24f); // set font size
        font2 = new TrueTypeFont(awtFont2, false);
             
    } catch (Exception e) {
        e.printStackTrace();
    }   

        
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*UnicodeFont ufont = null;
String cad = "test";

   Font font = new Font("Serif", Font.BOLD, 15);
   ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
   ufont.addAsciiGlyphs();
   ufont.addGlyphs(400, 600);
   ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
   ufont.loadGlyphs();

g.setFont(ufont);
*/
        
        
    
   play.draw(0,0);

                g.setColor(Color.white);
            

    g.drawString(mouse, 10, 50);
    
       blank.draw(390,20,102,102);
       if(nextpiece==1){
           purple.draw(415,71,17,17);
           purple.draw(432,71,17,17);
           purple.draw(432,54,17,17);
           purple.draw(449,71,17,17);
       }
       if(nextpiece==2){
           cyan.draw(407,59,17,17);
           cyan.draw(424,59,17,17);
           cyan.draw(441,59,17,17);
           cyan.draw(458,59,17,17);
           
       }
       if(nextpiece==3){
           yellow.draw(424,54,17,17);
           yellow.draw(441,54,17,17);
           yellow.draw(424,71,17,17);
           yellow.draw(441,71,17,17);
       }
       if(nextpiece==4){//green s
           green.draw(415,71,17,17);
           green.draw(432,71,17,17);
           green.draw(432,54,17,17);
           green.draw(449,54,17,17);
       }
       if(nextpiece==5){//red z
           red.draw(415,54,17,17);
           red.draw(432,54,17,17);
           red.draw(432,71,17,17);
           red.draw(449,71,17,17);
       }
       if(nextpiece==6){
           blue.draw(415,54,17,17);
           blue.draw(415,71,17,17);
           blue.draw(432,71,17,17);
           blue.draw(449,71,17,17);
       }
       if(nextpiece==7){
           orange.draw(449,54,17,17);
           orange.draw(415,71,17,17);
           orange.draw(432,71,17,17);
           orange.draw(449,71,17,17);
       }


        //UPDATE BOARD
 
        for(int i=0;i<20;i++){
            for(int a=0;a<10;a++){
                if(board[a][i]==0){
                    blank.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==1){
                    purple.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==2){
                    cyan.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==3){
                    yellow.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==4){
                    green.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==5){
                    red.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==6){
                    blue.draw(220+a*17,20+i*17,17,17);
                }
                if(board[a][i]==7){
                    orange.draw(220+a*17,20+i*17,17,17);
                }
                
                
            }
        }
        
    }
    long pastTime = 0;
public boolean delay(long delta) {
    if(pastTime < 1000-((linecount/5)*200)) { 
        pastTime += delta;
        return false;
    }else{
        pastTime = 0;
        return true;
    }
}
    
long previousTime=0;
    @Override
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(gameover){
            
            game.enterState(4,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
        
        arraycount=0;
        drop=21;
    long tmp = System.currentTimeMillis();
    long customDelta = tmp - previousTime;
    previousTime = tmp;
        //System.out.println(500-((linecount)*300));
        movedown=false;
         Input input = gc.getInput();
 if(delay(customDelta)){
     System.out.println("hello\n\n");
     movedown=true;
 }

  
int xpos = input.getMouseX();
int ypos = input.getMouseY();
int maxX=0;
int maxY=0;
int minX=0;
int minY=20;
int holdMin=0;
int holdMax=0;
int hold=0;


if(input.isKeyDown(Input.KEY_ENTER)&&releaseenter&&!placed){
               for(int y=0;y<20;y++){       
                 minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 //System.out.println("the plus one y coord is "+(piececoord[hold][1]+1));
                 if(piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0&&!placed){
                     placed=true;
                     break;
                     
                 }
                 
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                   
                   if(!placed){
                   System.out.print("Moved down");
                   
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releaseenter=false;
                   }
                   if(placed){
                       break;
                   }
               }
                     for(int counter=0;counter<4;counter++){
             board[piececoord[counter][0]][piececoord[counter][1]]=random;
               
                }
}
//for(int counter=0;counter<4;counter++){
            //    board[piececoord[counter][0]][piececoord[counter][1]]=random;
               
              //  }
  if(!input.isKeyDown(Input.KEY_ENTER)){

        releaseenter=true;
    }


mouse = "x: " + xpos + " y: " + ypos; 



    if(placed&&!gameover){
        //start checking for complete line
        boolean line=true;

         for(int a=19;a>=0;a--){
             for(int x=0;x<10;x++){
                 if(board[x][a]==0){
                     //System.out.println(x+", "+a+" is false.");
                     line=false;
                     //System.out.println(line);
                     //break;
                     
                 }
             }
             //System.out.println(line);
             if(line){
                 linecount++;
                    System.out.println("line "+a+" is true");
             for(int shift=a;shift>=0;shift--){
                 for(int x=0;x<10;x++){
                     if(shift>=1){
                         //System.out.println(board[x][shift]+", "+board[x][shift-1]);
                         
                         board[x][shift]=board[x][shift-1];
                     }
                 }                 
             }
                 
                 a++;
             }
             
             line=true;
             
             
         }
        
        //end of checking for complete line
        
        
        
        spawn=false;
        random=nextpiece;//retrieveing data from "next piece" area
        nextpiece=rn.nextInt(7)+1;  //spawning random piece in "next piece" area
        //nextpiece=1;

        placed=false;
        System.out.print("spawn new");
        

    }
    
        
        //spawn=false;
        

        
        
        
        

        //PURPLE CROSS 
        if(random==1){
            if(!spawn){
            piececoord[0][0]=4;
            piececoord[0][1]=0;
            piececoord[1][0]=3;
            piececoord[1][1]=1;
            piececoord[2][0]=4;
            piececoord[2][1]=1;
            piececoord[3][0]=5;
            piececoord[3][1]=1;
            for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=1;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=1;
               
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
               
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED       fuckin niggers        
//=======================================================================================================================                
               
               
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                   maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                 minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
               
                 
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
               
               if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }
               
               
               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   
                  
                 minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 System.out.println("the plus one y coord is "+(piececoord[hold][1]+1));
                 if(board[minX][piececoord[hold][1]+1]!=0||(piececoord[hold][1]+1)>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                   
                   if(!placed){
                   System.out.print("Moved down");
                   
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
                   }
                   }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
        boolean rotate=true;
        int diffX=piececoord[2][0];
        int diffY=piececoord[2][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
            if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }   
        }
        if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
        }
        
        
        
        //CYAN STICK
        //
        //
        //
        //
        //
        //
        //
        if(random==2){
            if(!spawn){
            piececoord[0][0]=3;
            piececoord[0][1]=0;
            piececoord[1][0]=4;
            piececoord[1][1]=0;
            piececoord[2][0]=5;
            piececoord[2][1]=0;
            piececoord[3][0]=6;
            piececoord[3][1]=0;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
               board[piececoord[counter][0]][piececoord[counter][1]]=2;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=2;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
           
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                   maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
  

 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }
               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                   if(!placed){
                   System.out.print("Moved down");
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
                boolean rotate=true;
        int diffX=piececoord[2][0];
        int diffY=piececoord[2][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
           if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }
            
            
        }
         if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
        }
        //YELLOW SQAURE
        //
        //
        //
        //
        //
        //
        //
        //
        if(random==3){
            
            if(!spawn){
            piececoord[0][0]=4;
            piececoord[0][1]=0;
            piececoord[1][0]=5;
            piececoord[1][1]=0;
            piececoord[2][0]=4;
            piececoord[2][1]=1;
            piececoord[3][0]=5;
            piececoord[3][1]=1;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=3;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=3;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                  maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
                 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }



               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                 if(!placed){
                   System.out.print("Moved down");  
                   
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               

             
            
            }
        }
        //GREEN S
        //
        //
        //
        //
        //
        //
        //
        if(random==4){
            
            if(!spawn){
            piececoord[0][0]=4;
            piececoord[0][1]=0;
            piececoord[1][0]=5;
            piececoord[1][1]=0;
            piececoord[2][0]=3;
            piececoord[2][1]=1;
            piececoord[3][0]=4;
            piececoord[3][1]=1;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=4;
               
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=4;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                   maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
                 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }




               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                   if(!placed){
                   System.out.print("Moved down");
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
                boolean rotate=true;
        int diffX=piececoord[3][0];
        int diffY=piececoord[3][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
           if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }
            
            
        }
         if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
            
        }
        //RED Z
        //
        //
        //
        //
        //
        //
        //
        //
        if(random==5){
            if(!spawn){
            piececoord[0][0]=3;
            piececoord[0][1]=0;
            piececoord[1][0]=4;
            piececoord[1][1]=0;
            piececoord[2][0]=4;
            piececoord[2][1]=1;
            piececoord[3][0]=5;
            piececoord[3][1]=1;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=5;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=5;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                   maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
                 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }





               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                 if(!placed){
                   System.out.print("Moved down");
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
               boolean rotate=true; 
        int diffX=piececoord[2][0];
        int diffY=piececoord[2][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
           if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }
            
            
        }
         if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
            
            
        }
        //BLUE backwards L
        //
        //
        //
        //
        //
        //
        //
        //
        if(random==6){
            if(!spawn){
            piececoord[0][0]=3;
            piececoord[0][1]=0;
            piececoord[1][0]=3;
            piececoord[1][1]=1;
            piececoord[2][0]=4;
            piececoord[2][1]=1;
            piececoord[3][0]=5;
            piececoord[3][1]=1;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=6;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=6;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                 
                 maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                 
                     maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
                 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }




               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                 if(!placed){
                   System.out.print("Moved down");
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
                boolean rotate=true;
        int diffX=piececoord[2][0];
        int diffY=piececoord[2][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
           if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }
        }
         if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
        }
        //ORANGE L
        //
        //
        //
        //
        //
        //
        //

        
        if(random==7){
            if(!spawn){
            piececoord[0][0]=5;
            piececoord[0][1]=0;
            piececoord[1][0]=3;
            piececoord[1][1]=1;
            piececoord[2][0]=4;
            piececoord[2][1]=1;
            piececoord[3][0]=5;
            piececoord[3][1]=1;
             for(int counter=0;counter<4;counter++){
                if(board[piececoord[counter][0]][piececoord[counter][1]]!=0){
                gameover=true;
                break;
            }
            
            }
            
            
            for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=7;
                
            }
            spawn=true;
            }
            else{
                for(int counter=0;counter<4;counter++){
                board[piececoord[counter][0]][piececoord[counter][1]]=7;
                
                }
                
//                                                 WHEN LEFT KEY IS PRESSED              
//=======================================================================================================================  
                
                
             if (input.isKeyDown(Input.KEY_LEFT)&&piececoord[1][0]>0&&piececoord[0][0]>0&&piececoord[2][0]>0&&piececoord[3][0]>0&&releaseleft){
                maxY=0;
                 maxX=20;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]-1][minY]!=0||piececoord[hold][0]-1<0){
                     canMove=false;
                     System.out.println("Cannot move left");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=20;
                 minY++;
                 }
                 if(canMove){
                 
                 maxY=0;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=0;
                 minY++;
                 }
                 
                 
                 
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]--;
                     }
                     
                 releaseleft=false;
            }
             }
               if(!input.isKeyDown(Input.KEY_LEFT)){
        releaseleft=true;
    }
               
               
 //                                                 WHEN RIGHT KEY IS PRESSED              
//=======================================================================================================================                
               
               if (input.isKeyDown(Input.KEY_RIGHT)&&piececoord[3][0]<9&&piececoord[2][0]<9&&piececoord[1][0]<9&&piececoord[0][0]<9&&releaseright){
                   maxY=0;
                 maxX=0;
                 minY=20;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];
                }
                 }
                 
                 System.out.println("min y is:"+minY);
                 System.out.println("maxY is: "+maxY);
                 
                 while(minY<=maxY){
                     
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]>maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 
                 System.out.println((piececoord[hold][0]-1)+","+minY);
                 if(board[piececoord[hold][0]+1][minY]!=0||piececoord[hold][0]+1>9){
                     canMove=false;
                     System.out.println("Cannot move right");
                     break;
                     
                     
                 }
                 else{
                     canMove=true;
                 }
                 
                 maxX=0;
                 minY++;
                 }
                 if(canMove){
                   
                   
                   minY=20;
                 maxY=0;
                 maxX=11;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][1]>maxY){
                    maxY=piececoord[counter][1];
                    

                }
                if(piececoord[counter][1]<minY){
                    minY=piececoord[counter][1];

                }
                 }
                 System.out.print(maxY+", "+minY);
                 while(minY<=maxY){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][1]==minY){
                         if(piececoord[counter][0]<maxX){
                             hold=counter;
                             maxX=piececoord[counter][0];
                         }
                         
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 maxX=11;
                 minY++;
                 }
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][0]++;
                     }
                     
                 releaseright=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_RIGHT)){
        releaseright=true;
    }
//                                                 WHEN DOWN KEY IS PRESSED              
//=======================================================================================================================             
                 if (input.isKeyDown(Input.KEY_DOWN)&&releasedown||movedown){
                   for(int blocks=0;blocks<4;blocks++){
                       System.out.println("y block coord plus one is "+(piececoord[blocks][1]+1));
                       if((piececoord[blocks][1]+1)>19){
                           System.out.println("placed is true");
                           placed=true;
                       }
                   }
               }



               if (input.isKeyDown(Input.KEY_DOWN)&&!placed&&releasedown||(!placed&&movedown)){
                   minY=20;
                 maxY=0;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]>maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 if(board[minX][piececoord[hold][1]+1]!=0||piececoord[hold][1]+1>19){
                     placed=true;
                     break;
                     
                 }
                 //maxY=0;
                 maxY=0;
                 minX++;
                 }
                 if(!placed){
                   System.out.print("Moved down");
                minY=20;
                 maxY=20;
                 minX=10;
                 maxX=0;
                 for(int counter=0;counter<4;counter++){
                if(piececoord[counter][0]>maxX){
                    maxX=piececoord[counter][0];
                    
                }
                if(piececoord[counter][0]<minX){
                    minX=piececoord[counter][0];
                   
                }
                 }
                 while(minX<=maxX){
                 for(int counter=0;counter<4;counter++){
                     if(piececoord[counter][0]==minX){
                         if(piececoord[counter][1]<maxY){
                             hold=counter;
                             maxY=piececoord[counter][1];
                         }
                     }
                 }
                 board[piececoord[hold][0]][piececoord[hold][1]]=0;
                 
                 //maxY=0;
                 maxY=20;
                 minX++;
                 }
                 
                   
                   
                   
                   
                 
                 for(int counter=0;counter<4;counter++){
                    //System.out.print("hi");
                     piececoord[counter][1]++;
                     }
                     
                 releasedown=false;
            }
               }
               if(!input.isKeyDown(Input.KEY_DOWN)){
        releasedown=true;
    }
               
  //                                                 WHEN UP KEY IS PRESSED (ROTATING)             
//=======================================================================================================================               
    if (input.isKeyDown(Input.KEY_UP)&&piececoord[2][1]<19&&releaseup){
               boolean rotate=true; 
        int diffX=piececoord[2][0];
        int diffY=piececoord[2][1];
        int[][]tempcoord=new int[4][2];
        for(int counter=0;counter<4;counter++){
            tempcoord[counter][0]=piececoord[counter][0];
            tempcoord[counter][1]=piececoord[counter][1];
        }
        
        

        for(int counter=0;counter<4;counter++){
            piececoord[counter][0]-=diffX;
            piececoord[counter][1]-=diffY;
            
            hold=piececoord[counter][0];
            if(((piececoord[counter][1]*-1)+diffX)>9||((piececoord[counter][1]*-1)+diffX)<0||(hold+diffY)>19||(hold+diffY)<0){
               rotate=false;
                System.out.println("Stopped rotation");
            }
            else{
                 piececoord[counter][0]=((piececoord[counter][1]*-1)+diffX);
                 piececoord[counter][1]=hold+diffY;
            }
            
            
        }
         if(!rotate){
    for(int counter=0;counter<4;counter++){
        piececoord[counter][0]=tempcoord[counter][0];
        piececoord[counter][1]=tempcoord[counter][1];
    }
}
        //ERASE UNCOMMON COORDINATE
        //====================
        boolean same=false;
        for(int tempcounter=0;tempcounter<4;tempcounter++){
            for(int counter=0;counter<4;counter++){
                if(tempcoord[tempcounter][0]==piececoord[counter][0]&&tempcoord[tempcounter][1]==piececoord[counter][1]){
                    same=true;
                }
            }
            if(!same){
                board[tempcoord[tempcounter][0]][tempcoord[tempcounter][1]]=0;
                
            }
            same=false;
        }
        //====================
        //====================
        releaseup=false;
            }
               if(!input.isKeyDown(Input.KEY_UP)){
        releaseup=true;
    }
             
            
            }
            
        }
      
    

    

    
    
    
    
    
    }
    
}
