package mino;

import Main.KeyHandler;
import Main.PlayManager;

import java.awt.*;
public class Mino {
    public Block blockTable[]= new Block[4];
    public Block tempB[]= new Block[4];
    int autoDropCounter=0;
    private int direction=1;
    private boolean leftCollision, rightCollision, bottomCollision;
    public boolean active=true;

    public void create(Color c){
        blockTable[0]=new Block(c);
        blockTable[1]=new Block(c);
        blockTable[2]=new Block(c);
        blockTable[3]=new Block(c);
        tempB[0]=new Block(c);
        tempB[1]=new Block(c);
        tempB[2]=new Block(c);
        tempB[3]=new Block(c);
    }

    public void setPosition(int x, int y){}
    private void checkStaticBlockCollision(){
        for(Block block: PlayManager.staticBlocks){
            int targetX= block.x;
            int targetY =block.y;
            for(Block block1: blockTable){
                if(block1.y+Block.SIZE== targetY && block1.x==targetX){
                    bottomCollision=true;
                }
            }
        }

    }
    public void checkMovementCollision(){

        leftCollision=false;
        rightCollision=false;
        bottomCollision=false;
        checkStaticBlockCollision();

        //leftwall
        for(int i=0; i<blockTable.length;i++){
            if(blockTable[i].x==PlayManager.leftX){
                leftCollision=true;
            }
        }

        //rightwall
        for(int i=0; i<blockTable.length;i++){
            if(blockTable[i].x+Block.SIZE==PlayManager.rightX){
                rightCollision=true;
            }
        }
        //bottomCollision
        for(int i=0; i<blockTable.length;i++){
            if(blockTable[i].y+Block.SIZE==PlayManager.bottomY){
                bottomCollision=true;
            }
        }
    }
    public void checkRotationCollision(){
        checkStaticBlockCollision();

        for(int i=0; i<blockTable.length;i++){
            if(tempB[i].x<PlayManager.leftX){
                leftCollision=true;
            }
        }

        //rightwall
        for(int i=0; i<blockTable.length;i++){
            if(tempB[i].x+Block.SIZE>PlayManager.rightX){
                rightCollision=true;
            }
        }
        //bottomCollision
        for(int i=0; i<blockTable.length;i++){
            if(tempB[i].y+Block.SIZE>PlayManager.bottomY){
                bottomCollision=true;
            }
        }
    }
    public void getDirection1(){};
    public void getDirection2(){};
    public void getDirection3(){};
    public void getDirection4(){};
    public void updatePosition(int direction) {
        checkRotationCollision();

        if (!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction;
            blockTable[0].x = tempB[0].x;
            blockTable[0].y = tempB[0].y;
            blockTable[1].x = tempB[1].x;
            blockTable[1].y = tempB[1].y;
            blockTable[2].x = tempB[2].x;
            blockTable[2].y = tempB[2].y;
            blockTable[3].x = tempB[3].x;
            blockTable[3].y = tempB[3].y;
        }
    }
    public void move(){
        if(KeyHandler.upPressed){
            System.out.println(direction);
            switch (direction){
                case 1 -> getDirection2();
                case 2 -> getDirection3();
                case 3 -> getDirection4();
                case 4 -> getDirection1();
            }
            KeyHandler.upPressed=false;
        }
        checkMovementCollision();
        if(KeyHandler.downPressed && !bottomCollision){
            for(int i= 0; i<blockTable.length;i++){
                blockTable[i].y+=Block.SIZE;
            }
            autoDropCounter++;
            KeyHandler.downPressed=false;
        }
        if(KeyHandler.leftPressed && !leftCollision){
            for(int i= 0; i< blockTable.length;i++){
                blockTable[i].x-=Block.SIZE;
            }
            KeyHandler.leftPressed=false;
        }

        if(KeyHandler.rightPressed && !rightCollision){
            for(int i= 0; i<blockTable.length;i++){
                blockTable[i].x+=Block.SIZE;
            }
            KeyHandler.rightPressed=false;
        }
    }

    public void update(){
        if(bottomCollision){
            active=false;
        }else {
            move();
            autoDropCounter++;
            if(autoDropCounter == PlayManager.dropInterval){
                blockTable[0].y+=Block.SIZE;
                blockTable[1].y+=Block.SIZE;
                blockTable[2].y+=Block.SIZE;
                blockTable[3].y+=Block.SIZE;
                autoDropCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2){

        int margin=2;
        g2.setColor(blockTable[0].blockcolor);
        g2.fillRect(blockTable[0].x+margin, blockTable[0].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(blockTable[1].x+margin, blockTable[1].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(blockTable[2].x+margin, blockTable[2].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(blockTable[3].x+margin, blockTable[3].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
    }


}
