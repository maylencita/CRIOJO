package fr.emn.criojo.integration.physics;

import fr.emn.criojo.core.Atom;
import fr.emn.criojo.lang.Cham;
import processing.core.PApplet;
import fr.emn.criojo.integration.physics.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class PhysicsSketch extends PApplet {
    /*
    PROCESSINGJS.COM HEADER ANIMATION
    MIT License - F1lT3R/Hyper-Metrix
    Native Processing Compatible
    */

    // Set number of circles
    int count = 20;
    int speed = 4;
    // Set maximum and minimum circle size
    int maxSize = 100;
    int minSize = 20;
    // Build float array to store circle properties
    public ConcurrentLinkedQueue<PhysicAtom> e = new ConcurrentLinkedQueue<PhysicAtom>();

    public HashMap<Integer, PhysicAtom> mem = new HashMap<Integer, PhysicAtom>();

    // Set size of dot in circle center
    float ds=2;
    // Selected mode switch
    int sel = 0;
    // Set drag switch to false
    boolean dragging=false;
    // If use drags mouse...
    public void mouseDragged(){
        // Set drag switch to true
        dragging=true;
    }
    // If user releases mouse...
    public void mouseReleased(){
        // ..user is no-longer dragging
        dragging=false;
    }

    public void addAtom(PhysicAtom a) {
        e.add(a);
    }

    public void removeAtom(PhysicAtom a) {

        e.remove(a);
    }

    public PhysicAtom getPhysicAtom(PhysicCriojoAtom a) {
        return mem.get(a.getId());
    }
    
    PhysicCham cham = new PhysicCham(this);
    
    // Set up canvas
    public void setup(){
        // Frame rate
        frameRate(30);
        // Size of canvas (width,height)
        size(1024,768);

        // Stroke/line/border thickness
        strokeWeight(1);
        // Initiate array with random values for circles
        
        
        for(int j=0;j< count*2;j++){
            
            PhysicAtom a = new PhysicAtom();

            a.setX(random(width));
            a.setY(random(height));
            a.setRadius(80);
            a.setXSpeed(speed * random(-.5f, .5f));
            a.setYSpeed(speed * random(-.5f, .5f));
            a.setView(this);

            a.setAtom(cham.giveNewAtom(cham.H()));
            a.atom().setParentViewObject(a);

            e.add(a);
        }

        for(int j=0;j< count;j++){

            PhysicAtom a = new PhysicAtom();

            a.setX(random(width));
            a.setY(random(height));
            a.setRadius(80);
            a.setXSpeed(speed * random(-.5f, .5f));
            a.setYSpeed(speed * random(-.5f, .5f));
            a.setView(this);

            a.setAtom(cham.giveNewAtom(cham.O()));
            ((PhysicCriojoAtom) a.atom()).setParentViewObject(a);

            e.add(a);
        }

        for(int j=0;j< count/3;j++){

            PhysicAtom a = new PhysicAtom();

            a.setX(random(width));
            a.setY(random(height));
            a.setRadius(80);
            a.setXSpeed(speed * random(-.5f, .5f));
            a.setYSpeed(speed * random(-.5f, .5f));
            a.setView(this);

            a.setAtom(cham.giveNewAtom(cham.C()));
            a.atom().setParentViewObject(a);

            e.add(a);
        }

        ThreadCheckRelation tcheck = new ThreadCheckRelation(this);
        tcheck.start();
    }

    // Begin main draw loop (called 25 times per second)
    public void draw(){
        // Fill background black
        background(0);
        // Begin looping through circle array

        Iterator<PhysicAtom> it1 = e.iterator();
        
        while(it1.hasNext()) {

            PhysicAtom a = it1.next();

            // Disable shape stroke/border
            noStroke();

            // Cache diameter and radius of current circle
            float radi=a.radius();
            float diam=radi/2;
            // If the cursor is within 2x the radius of current circle...
            if( dist(a.x(),a.y(),mouseX,mouseY) < radi ){
                // Change fill color to green.
                fill(64,187,128,100);
                // Remember user has circle "selected"
                sel=1;
                // If user has mouse down and is moving...
                if (dragging){
                    // Move circle to circle position
                    a.setX(mouseX);
                    a.setY(mouseY);
                }
            } else {
                // Keep fill color blue
                fill(64,128,187,100);
                // User has nothing "selected"
                sel=0;
            }
            // Draw circle
            ellipse(a.x(),a.y(),radi,radi);
            // Move circle
            a.setX(a.x() + a.xSpeed());
            a.setY(a.y() + a.ySpeed());


            /* Wrap edges of canvas so circles leave the top
       and re-enter the bottom, etc... */
            if( a.x() < -diam      ){ a.setX(width + diam);  }
            if( a.x() > width+diam ){ a.setX(-diam);       }
            if( a.y() < 0-diam     ){ a.setY(height + diam); }
            if( a.y() > height+diam){ a.setY(-diam);       }

            // If current circle is selected...
            if (sel==1) {
                // Set fill color of center dot to white..
                fill(255,255,255,255);
                // ..and set stroke color of line to green.
                stroke(128,255,0,100);
            } else {
                // otherwise set center dot color to black..
                fill(0,0,0,255);
                // and set line color to turquoise.
                stroke(64,128,128,255);
            }

            fill(255,255,255);
            text(a.atom().relName(),a.x(),a.y());  // STEP 6 Display Text

            // Loop through all circles
            Iterator<PhysicAtom> it2 = e.iterator();

            while(it2.hasNext()) {

                PhysicAtom b = it2.next();
                // If the circles are close...
                float distance = dist(a.x(),a.y(),b.x(),b.y());
                radi = Math.max(a.radius(), b.radius());

                if( distance < radi && distance > 0){

                    line(a.x(),a.y(),b.x(),b.y());

                    if(distance<radi/2) {
                        
                        int signeX = random(-1f,1f)<0?1:-1;
                        int signeY = random(-1f,1f)<0?1:-1;

                        a.setX(a.x()+radi/4*signeX);
                        a.setY(a.y()+radi/4*signeY);


                        a.setXSpeed(speed * random(-1f,1f));
                        a.setYSpeed(speed * random(-1f,1f));
                    }
                }
            }

            noStroke();
            // Draw dot in center of circle
            rect(a.x()-ds,a.y()-ds,ds*2,ds*2);
        }
    }

    public class ThreadCheckRelation extends Thread {

        private PhysicsSketch view;

        public ThreadCheckRelation(PhysicsSketch view) {
            this.view = view;
        }

        public void run() {

            while(true) {

                Iterator<PhysicAtom> it1 = e.iterator();

                ConcurrentLinkedQueue<PhysicAtom> usedAtom = new ConcurrentLinkedQueue<PhysicAtom>();

                while(it1.hasNext()) {

                    PhysicAtom a = it1.next();

                    // Loop through all circles
                    Iterator<PhysicAtom> it2 = e.iterator();

                    ConcurrentLinkedQueue<PhysicAtom> linkedTo = new ConcurrentLinkedQueue<PhysicAtom>();
                    linkedTo.add(a);

                    while(it2.hasNext()) {

                        PhysicAtom b = it2.next();

                        if(a != b) {

                            // If the circles are close...
                            float distance = dist(a.x(),a.y(),b.x(),b.y());
                            float radi = Math.max(a.radius(), b.radius());

                            if( distance < radi && distance > 0){
                                linkedTo.add(b);
                            }
                        }
                    }

                    linkedTo.removeAll(usedAtom);

                    //System.out.println(view.e.size());
                    
                    ThreadReaction t = new ThreadReaction(linkedTo, view);
                    t.start();

                    usedAtom.addAll(linkedTo);
                }
                
                try{
                    Thread.sleep(200);
                }
                catch(Exception e) {
                    System.out.println("Erreur dans le thread de verification des reactions");
                }
            }
        }
    }

    public class ThreadReaction extends Thread {
        private ConcurrentLinkedQueue<PhysicAtom> queue;
        private PhysicsSketch view;

        public ThreadReaction(ConcurrentLinkedQueue<PhysicAtom> queue, PhysicsSketch view) {
            this.queue = queue;
            this.view = view;
        }
        public void run() {
            PhysicCham cham2 = new PhysicCham(view);

            for(PhysicAtom atom : queue) {

                cham2.introduceAtom(atom.atom());
            }

            cham2.executeRules();
        }
    }
}
