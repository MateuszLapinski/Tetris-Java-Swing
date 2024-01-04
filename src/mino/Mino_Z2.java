package mino;

import java.awt.*;

public class Mino_Z2 extends Mino {


    public Mino_Z2(){
        create(Color.green);
    }


    @Override
    public void setPosition(int x, int y) {
        blockTable[0].x=x;
        blockTable[0].y=y;

        blockTable[1].x=blockTable[0].x;
        blockTable[1].y=blockTable[0].y- Block.SIZE;

        blockTable[2].x=blockTable[0].x+Block.SIZE;
        blockTable[2].y=blockTable[0].y;

        blockTable[3].x=blockTable[0].x+ Block.SIZE;
        blockTable[3].y=blockTable[0].y+ Block.SIZE;
    }
    @Override
    public void getDirection1(){
        tempB[0].x= blockTable[0].x;
        tempB[0].y=blockTable[0].y;

        tempB[1].x=blockTable[0].x;
        tempB[1].y=blockTable[0].y- Block.SIZE;

        tempB[2].x=blockTable[0].x+ Block.SIZE;
        tempB[2].y=blockTable[0].y;

        tempB[3].x=blockTable[0].x+ Block.SIZE;
        tempB[3].y=blockTable[0].y+ Block.SIZE;

        updatePosition(1);
    }
    public void getDirection2(){
        tempB[0].x= blockTable[0].x;
        tempB[0].y=blockTable[0].y;

        tempB[1].x=blockTable[0].x- Block.SIZE;
        tempB[1].y=blockTable[0].y;

        tempB[2].x=blockTable[0].x;
        tempB[2].y=blockTable[0].y- Block.SIZE;

        tempB[3].x=blockTable[0].x+ Block.SIZE;
        tempB[3].y=blockTable[0].y- Block.SIZE;

        updatePosition(2);
    }
    public void getDirection3(){
        getDirection1();
    }
    public void getDirection4(){
        getDirection2();
    };
}
