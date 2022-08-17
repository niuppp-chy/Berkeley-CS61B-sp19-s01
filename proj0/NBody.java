public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        double radius = 0.0;

        in.readInt();
        radius = in.readDouble();

        return radius; 
    } // when compile, warning for deprecated item is not annotated with @Deprecated ~

    public static Body[] readBodies(String file) {
        In in = new In(file);

        // skip first two lines
        int N = in.readInt();
        Body[] bodys = new Body[N];
        double radius = in.readDouble();

        // read every body
        // for(int i = 0; i < N; i++) { // why can not use this method to assign Body object ?
        //     bodys[i].xxPos = in.readDouble();
        //     bodys[i].yyPos = in.readDouble();
        //     bodys[i].xxVel = in.readDouble();
        //     bodys[i].yyVel = in.readDouble();
        //     bodys[i].mass = in.readDouble();
        //     bodys[i].imgFileName = in.readString();
        // }

        for(int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            bodys[i] = new Body(xP, yP, xV, yV, mass, imgFileName);
        }

        return bodys;
    }

    public static void main(String[] args) {
        String imageToDraw = "images/starfield.jpg";

        double T = Double.parseDouble(args[0]); // convert string to double !
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] bodies = readBodies(filename);

        // get radius and draw 
        In in = new In(filename);
        int size = in.readInt();
        double radius = in.readDouble(); 

        // // draw background
        // StdDraw.enableDoubleBuffering();
        // StdDraw.circle(0, 0, radius); // draw cricle

        // StdDraw.clear();
        // StdDraw.picture(0, 0, imageToDraw);
        
		// StdDraw.show();
		// // StdDraw.pause(2000);

        // // draw every Body
        // for (Body body : bodies) {
        //     body.draw();
        // }

        double t = 0.0;
        // int size = bodies.size; // 这个应该有问题
        double xForces [] = new double[size];
        double yForces [] = new double[size];

        while(t < T) {
            for (int i = 0; i < size; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            // update
            for(int i = 0; i < size; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            // draw background
            StdDraw.enableDoubleBuffering();
            StdDraw.circle(0, 0, radius); // draw cricle
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            StdDraw.show();
    
            // draw every Body
            for (Body body : bodies) {
                body.draw();
            }

            StdDraw.pause(1000); // 10ms ?
            t += dt;
        }

        
        ...
        
        // print the final state of the universe
        StdOut.printf("%d\n", size);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < size; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                          bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }

    }
}
