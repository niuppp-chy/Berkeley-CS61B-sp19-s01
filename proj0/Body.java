/** 没有main函数，因为不进�?? “java Body 命令�? */
/** 所有函数都是non-static */
/** -----���� */
public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                    double yV, double m, String img) {
        /** */
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        /**  */
        // Body a = b; // 这个�?什么意思？
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** 计算天体的距�? */
    public double calcDistance(Body r) {
        double distance = 0.0;
        double gapx;
        double gapy;

        gapx = xxPos - r.xxPos;
        gapy = yyPos - r.yyPos;
        distance = Math.sqrt(Math.pow(gapx, 2) + Math.pow(gapy, 2));
        
        return distance; 
    }


    static final double G = 6.67e-11; // 重力常数

    /** 计算两天体间的引�? */
    public double calcForceExertedBy(Body t) {
        double ForceExert = 0.0;
        double distance = calcDistance(t);

        ForceExert =  G * mass * t.mass / Math.pow(distance, 2); // F = G * m1 * m2 / r^2

        return ForceExert;
    }

    /** 计算天体间的X分量 
     *  计算受到天体Z的X分量
    */
    public double calcForceExertedByX(Body z) {
        double FEbyX = 0.0;
        double ForceExert = calcForceExertedBy(z);
        double Dx = z.xxPos - xxPos;
        double dis = calcDistance(z);
        
        // 表示受到Z天体的X分量的力
        // 当Dx为�?�时，则+
        // 当Dx为负时，�?-
        FEbyX = Dx * ForceExert / dis;

        return FEbyX;
    }

    /** 计算天体间的Y分量 
     *  计算受到天体Z的Y分量的力
    */
    public double calcForceExertedByY(Body z) {
        double FEbyY = 0.0;
        double ForceExert = calcForceExertedBy(z);
        double Dy = z.yyPos - yyPos;
        double dis = calcDistance(z);
        
        // 表示受到Z天体的X分量的力
        // 当Dx为�?�时，则+
        // 当Dx为负时，�?-
        FEbyY = Dy * ForceExert / dis;

        return FEbyY; // double�?无�?�号数，负数都是以补码的方式表示
    }

    /** 计算该天体受到的X方向上的合力 */
    public double calcNetForceExertedByX(Body[] allBodies) {
        double NetForceX = 0.0;
        for (Body body : allBodies) {
            //  To compare two bodies, use the .equals method instead of ==: samh.equals(samh) 
            // (which would return true)
            // equals �? == 的区�?�?
            if (body.equals(this)) // 遇到�?�?，直接跳�?
                continue;
            NetForceX += calcForceExertedByX(body);
        }

        return NetForceX;
    }

    /** 计算该天体受到的Y方向上的合力 */
    public double calcNetForceExertedByY(Body[] allBodies) {
        double NetForceY = 0.0;
        for (Body body : allBodies) {
            if (body.equals(this)) // 遇到�?�?，直接跳�?
                continue;
            NetForceY += calcForceExertedByY(body);
        }

        return NetForceY;
    }

    /** 更新天体的位�?信息 */
    public void update(double dt, double fx, double fy) {
        double anetx = fx / mass;
        double anety = fy / mass;
        
        /** 更新x，y速度 */
        xxVel = xxVel + dt * anetx;
        yyVel = yyVel + dt * anety;
        /** 更新x，y位置 */
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        // StdDraw.point(xxPos, yyPos);
        String s = "images/" + imgFileName;

        // StdDraw.enableDoubleBuffering();
        StdDraw.picture(xxPos, yyPos, s);

        // StdDraw.clear();

		// StdDraw.show();
		// StdDraw.pause(2000);
    }
    


}