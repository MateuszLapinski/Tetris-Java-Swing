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

    //Effects
    boolean effectCounterOn;
    int effectCounter;

    //Score
    private int level=1;
    private int lines, score;
    private Font general= new Font("Arial", Font.PLAIN,30);



    private boolean gameOver;
    ArrayList<Integer>effectY= new ArrayList<>();


    public boolean isGameOver() {
        return gameOver;
    }

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
        if (!currentMino.active) {
            staticBlocks.add(currentMino.blockTable[0]);
            staticBlocks.add(currentMino.blockTable[1]);
            staticBlocks.add(currentMino.blockTable[2]);
            staticBlocks.add(currentMino.blockTable[3]);

            if(currentMino.blockTable[0].x==MINO_START_X && currentMino.blockTable[0].y==MINO_START_Y ){
                gameOver=true;
                GamePanel.music.stop();
                GamePanel.soundEffect.play(2,false);
            }
            currentMino.deactivating=false;

            currentMino=nextMino;
            currentMino.setPosition(MINO_START_X,MINO_START_Y);
            nextMino= randomMino();
            nextMino.setPosition(NEXTMINO_X,NEXTMINO_Y);
            checkDelete();
        } else {
            currentMino.update();
        }
    }

    private void checkDelete(){
        int x=leftX;
        int y=topY;
        int blockcount=0;
        int lineCount=0;

        while(x<rightX && y <bottomY){

            for (Block staticBlock : staticBlocks) {
                if (staticBlock.x == x && staticBlock.y == y) {
                    blockcount++;
                }
            }
            x+=Block.SIZE;

            if(x == rightX){
                if(blockcount==12){
                    effectCounterOn=true;
                    effectY.add(y);
                    for(int i =staticBlocks.size()-1; i>-1; i--){
                        //remove all the blocks in the current line
                        if(staticBlocks.get(i).y == y){
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++;
                    lines++;
                    //DROP SPEED

                    if(lines%10==0 && dropInterval>1){
                        level++;
                        if(dropInterval>10){
                            dropInterval-=10;
                        }else{
                            dropInterval-=1;
                        }
                    }
                    for(Block block: staticBlocks){
                        //if a block is above the current y, move it down by the block size
                        if(block.y<y){
                            block.y+=Block.SIZE;
                        }
                    }
                }
                blockcount=0;
                x=leftX;
                y+=Block.SIZE;
            }
        }
        if(lineCount>0){
            GamePanel.soundEffect.play(1,false);
            int singleLineScore=10* level;
            score+=singleLineScore*lineCount;
        }
    }

        private void drawBoardElement (Graphics2D g2){
            int x = rightX + 100;
            int y=bottomY-200;
            g2.setFont(general);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(leftX - 4, topY - 4, WIDTH + 8, HEIGHT + 8); // 'i' for frame boundary
            //Left side
            g2.drawRect(x, topY, 250, 300);
            x += 40;
            y = topY + 90;
            g2.drawString("LEVEL: " + level, x, y);
            y += 70;
            g2.drawString("LINES: " + lines, x, y);
            y += 70;
            g2.drawString("SCORE: " + score, x, y);
            x = rightX + 100;
            y = bottomY - 200;
            //Next Mino Frame
            g2.drawRect(x, y, 200, 200);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("NEXT", x + 60, y + 60);
            //Right side
            x = 100;
            y = topY + 320;

            g2.setColor(Color.WHITE);
            g2.drawString("Tetris", x, y);
        }

        private void drawInfoAboutGame (Graphics2D g2){
            g2.setColor(Color.yellow);
            g2.setFont(g2.getFont().deriveFont(50f));
            int x = leftX + 70;
            int y = topY + 320;
            if (gameOver) {
                g2.drawString("GameOver", x, y);
            }
            if (KeyHandler.pausePressed) {
                g2.drawString("Paused", x, y);
            }
        }
        private void drawMino (Graphics2D g2){
            //Draw currentMino
            if (currentMino != null) {
                currentMino.draw(g2);
            }

            //Draw nextMino
            nextMino.draw(g2);

            for (Block block : staticBlocks) {
                block.draw(g2);
            }
        }

        private void drawEffects (Graphics2D g2){
            if (effectCounterOn) {
                effectCounter++;
                g2.setColor(Color.RED);
                for (int i = 0; i < effectY.size(); i++) {
                    g2.fillRect(leftX, effectY.get(i), WIDTH, Block.SIZE);
                }
                if (effectCounter == 10) {
                    effectCounterOn = false;
                    effectCounter = 0;
                    effectY.clear();
                }
            }
        }

        public void draw (Graphics2D g2){
            drawBoardElement(g2);
            drawMino(g2);
            drawEffects(g2);
            drawInfoAboutGame(g2);
        }
    }
