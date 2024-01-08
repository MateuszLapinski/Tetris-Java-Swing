package Main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    Clip musicClip;
    URL url[]= new URL[10];

    public Sound(){
        url[0] = getClass().getResource("/white-labyrinth-active.wav");
        url[1] = getClass().getResource("/laser.wav");
        url[2] = getClass().getResource("/game-over.wav");
    }

    public void play(int i, boolean music){

        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(url[i]);
            Clip clip= AudioSystem.getClip();


            if(music){
                musicClip=clip;
            }
            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if(event.getType() == LineEvent.Type.STOP){
                        clip.close();
                    }
                }
            });
            ais.close();
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loop() {
        //if (musicClip != null) {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        //}
    }
    public void stop(){
        musicClip.stop();
        musicClip.close();
    }
}
