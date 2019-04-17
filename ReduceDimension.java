import java.util.*;
import java.io.*;
import processing.core.*;

public class ReduceDimension extends PApplet {

    private ArrayList<Point> data;
    private ArrayList<Point> projection;

    private PGraphics pg;

    public ReduceDimension() {
        data = new ArrayList<Point>();
    }
    public void settings() {
        size(700, 700, P2D);
    }
    public void setup() {
        frameRate(1000);
        //fourQuadrants();
        //threeDQuadrants();
        //iris();
        words(300);
        pg = createGraphics(500, 500, P3D);
        //random positions
        for(Point p : data) {
            for(int i = 0; i < p.ncoord.length; i++) {
                p.ncoord[i] = random(10);
            }
        }
        //noStroke();
    }

    public void draw() {
        background(255);
        /*pg.beginDraw();
        pg.background(0);
        pg.camera(pg.width/2.0f, pg.height/2.0f, (pg.height/2.0f) / tan(PI*10.f / 180.0f), pg.width/2.0f, pg.height/2.0f, 0, 0, 1, 0);
        pg.lights();

            pg.noStroke();
            pg.fill(p.c);
            pg.pushMatrix();
            pg.translate(250, 250, 250);
            pg.rotateX(frameCount / 30.0f);
            pg.rotateY((frameCount + 7) / 30.0f);
            pg.rotateZ((frameCount + 13) / 30.0f);
            pg.translate(-250, -250, -250);
            pg.translate(p.coord[0]*50, p.coord[1]*50, p.coord[2]*50);
            pg.sphere(8);
            pg.translate(-p.coord[0]*50, -p.coord[1]*50, -p.coord[2]*50);
            pg.popMatrix();
            //ellipse(p.coord[0]*50, p.coord[1]*50, 20, 20);
        */
        //pg.endDraw();
        //image(pg, 0, 0);

        for(int itr = 0; itr < 5; itr++) {
            for(int i = 0; i < data.size(); i++) {
                Point p = data.get(i);
                float[] grad = new float[p.ncoord.length];
                for(int j = 0; j < data.size(); j++) {
                    if(i == j) continue;
                    float[] t = p.gradient(data.get(j));
                    for(int g = 0; g < t.length; g++) {
                        grad[g] += t[g];
                    }
                }
                for(int s = 0; s < p.ncoord.length; s++) {
                    p.ncoord[s] -= 0.001 * grad[s];
                }
            }
        }

        //translate(500, 400);
        scale(0.5f);
        translate(450, 450);
        for(Point p : data) {
            if(p.text == null) {
                fill(p.c);
                ellipse(p.ncoord[0]*70, p.ncoord[1]*70, 100, 100);
            }
            else {
                fill(0);
                textSize(30);
                text(p.text, p.ncoord[0]*70, p.ncoord[1]*70);
            }
        }
    }

    public void fourQuadrants() {
        int one = color(255, 0, 0, 160);//color(random(255), random(255), random(255), 160);
        int two = color(0, 60, 180, 160);//color(random(255), random(255), random(255), 160);
        int three = color(0, 230, 50, 160);//color(random(255), random(255), random(255), 160);
        int four = color(200, 0, 140, 160);//color(random(255), random(255), random(255), 160);
        for(float x = 0.5f; x < 10; x++) {
            for(float y = 0.5f; y < 10; y++) {
                Point p = new Point(1, x, y);
                if(x < 5) {
                    if(y < 5) p.c = one;
                    else p.c = two;
                }
                else {
                    if(y < 5) p.c = three;
                    else p.c = four;
                }
                data.add(p);
            }
        }
    }
    public void threeDQuadrants() {
        for(float x = 0.5f; x < 10; x++) {
            for(float y = 0.5f; y < 10; y++) {
                for(float z = 0.5f; z < 10; z++) {
                    Point p = new Point(2, x, y, z);
                    p.c = color(255*x/10, 255*y/10, 255*z/10);
                    data.add(p);
                }
            }
        }
    }
    public void iris() {
        int one = color(255, 0, 0, 160);//color(random(255), random(255), random(255), 160);
        int two = color(0, 60, 180, 160);//color(random(255), random(255), random(255), 160);
        int three = color(0, 230, 50, 160);//color(random(255), random(255), random(255), 160);
        try {
            Scanner in = new Scanner(new File("iris.data"));
            while(in.hasNext()) {
                int species = in.nextInt();
                Point p = new Point(2, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
                switch(species) {
                    case 0:
                        p.c = one;
                        break;
                    case 1:
                        p.c = two;
                        break;
                    case 2:
                        p.c = three;
                        break;
                }
                data.add(p);
            }
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void words(int dim) {
        int one = color(255, 0, 0, 160);//color(random(255), random(255), random(255), 160);
        int two = color(0, 60, 180, 160);//color(random(255), random(255), random(255), 160);
        int three = color(0, 230, 50, 160);//color(random(255), random(255), random(255), 160);
        try {
            Scanner in = new Scanner(new File("glove.6B/glove.6B." + dim + "d.txt"));
            if(dim == 301) dim = 300;
            for(int skip = 0; skip < 0; skip++) in.nextLine();
            for(int i = 0; i < 100; i++) {
                String[] line = in.nextLine().split(" ");
                float[] vec = new float[dim];
                for(int c = 0; c < vec.length; c++) {
                    vec[c] = Float.parseFloat(line[c + 1]) + 1.0f;
                }
                Point p = new Point(2, vec);
                p.text = line[0];
                data.add(p);
            }
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //four quadrants
        ReduceDimension rd = new ReduceDimension();
        PApplet.runSketch(new String[] { "Reduce Dimension" }, rd);
    }

}
