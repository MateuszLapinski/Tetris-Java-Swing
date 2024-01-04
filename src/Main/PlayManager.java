package Main;

import mino.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManager {
    final int WIDTH = 360;
    final int HEIGHT=600;
    //Board Range
    public static int leftX;
    public static int rightX;
    public static int topY;
    public static int bottomY;
    private Mino currentMino, nextMino;
    //MINO
    final int MINO_START_X;
    final int MINO_START_Y;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks= new ArrayList<>();

    public static int dropInterval=60;




    public PlayManager() {
        leftX= (GamePanel.WIDTH/2)-(WIDTH/2); //460
        rightX=leftX+WIDTH; //720;
        topY=50;
        bottomY= topY+ HEIGHT; //650
        MINO_START_X=leftX+(WIDTH/2)- Block.SIZE;
        MINO_START_Y= topY + Block.SIZE;

        NEXTMINO_X=rightX+180;
        NEXTMINO_Y=topY+500;

        //Starting mino.Mino
        currentMino= randomMino();
        currentMino.setPosition(MINO_START_X,MINO_START_Y);
        nextMino=randomMino();
        nextMino.setPosition(NEXTMINO_X,NEXTMINO_Y);
    }
    private Mino randomMino(){
        Mino mino=null;
        int variableMino= new Random().nextInt(7);
        System.out.println(variableMino);
        switch(variableMino){
            case 0-> mino=new Mino_L1();
            case 1-> mino=new Mino_L2();
            case 2-> mino=new Mino_Bar();
            case 3-> mino=new Mino_T();
            case 4-> mino=new Mino_Z1();
            case 5-> mino=new Mino_Z2();
            case 6-> mino=new Mino_Squere();
        }
        return mino;
    }
    public void update() {
        if (currentMino.active == false) {
            staticBlocks.add(currentMino.blockTable[0]);
            staticBlocks.add(currentMino.blockTable[1]);
            staticBlocks.add(currentMino.blockTable[2]);
            staticBlocks.add(currentMino.blockTable[3]);

            currentMino=nextMino;
            currentMino.setPosition(MINO_START_X,MINO_START_Y);
            nextMino= randomMino();
            nextMino.setPosition(NEXTMINO_X,NEXTMINO_Y);
        } else {
            currentMino.update();
        }
    }

    public void draw(Graphics2D g2){
        //Board Area
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(leftX-4, topY-4, WIDTH+8, HEIGHT+8); // 'i' for frame boundary


        // draw next mino.Mino Frame
        int x = rightX+100;
        int y= bottomY-200;
        g2.drawRect(x,y,200, 200);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x+60, y+60);

        //Draw currentMino
        if(currentMino !=null){
            currentMino.draw(g2);
        }

        //Draw nextMino
        nextMino.draw(g2);

        for(Block block: staticBlocks){
            block.draw(g2);
        }
        //draw info about pause
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if(KeyHandler.pausePressed){
            x=leftX+70;
            y=topY+320;
            g2.drawString("Paused", x,y);
        }
    }
}
