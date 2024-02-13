import java.util.ArrayList;
import java.util.List;

public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int num_of_planets = in.readInt();
        double uni_radius = in.readDouble();
        return uni_radius;
    }

    public static Planet[] readPlanets(String file) {
        List<Planet> universe = new ArrayList<>();
        In in = new In(file);
        int num_of_planets = in.readInt();
        double uni_radius = in.readDouble();


        while (!in.isEmpty()) {
            if (in.isEmpty() == true) {
                continue;
            }
            try {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            
            Planet p = new Planet(xP, yP, xV, yV, m, img);
            universe.add(p);
            } catch (Exception e){
                break;
            }
            
        }
        Planet[] universeArray = new Planet[universe.size()];
        universeArray = universe.toArray(universeArray);
        return universeArray;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide at least two arguments.");
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);

        // int width = 800;
        // int height = 600;
        // StdDraw.setCanvasSize(width, height);

        // // Set the scale of the coordinate system
        // StdDraw.setXscale(0, width);
        // StdDraw.setYscale(0, height);


        // String path;
        // for (Planet p : planets) {
        //     path = "images/" + p.imgFileName;
        //     StdDraw.picture(0, 0, path);
        // }
        
        StdDraw.enableDoubleBuffering();


        double time = 0;
        while (time < T){
            List<Double> xForces = new ArrayList<>();
            List<Double> yForces = new ArrayList<>();
            

            for (Planet p : planets) {
                xForces.add(p.calcNetForceExertedByX(planets));
                yForces.add(p.calcNetForceExertedByY(planets));
            }

            for (int i = 0; i<planets.length; i++) {
                planets[i].update(dt, xForces.get(i), yForces.get(i));
            }

            // StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        // StdOut.printf("%d\n", planets.length);
        // StdOut.printf("%.2e\n", radius);
        // for (int i = 0; i < planets.length; i++) {
        //     StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        //           planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
        //           planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        // }
    }
}
