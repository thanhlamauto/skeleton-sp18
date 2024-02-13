public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = Math.abs(p.xxPos - this.xxPos);
        double dy = Math.abs(p.yyPos - this.yyPos);
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p)*calcDistance(p);
        return Math.pow(10,-11)*6.67*p.mass*this.mass/distance;
    }

    public double calcForceExertedByX(Planet p) {
        double total_force = calcForceExertedBy(p);
        return total_force * (p.xxPos - this.xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double total_force = calcForceExertedBy(p);
        return total_force * (p.yyPos - this.yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double net_force_X = 0;
        for (Planet p : allPlanets){
            if (p.equals(this)){
                continue;
            }
            net_force_X += calcForceExertedByX(p);
        }
        return net_force_X;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double net_force_Y = 0;
        for (Planet p : allPlanets){
            if (p.equals(this)){
                continue;
            }
            net_force_Y += calcForceExertedByY(p);
        }
        return net_force_Y;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel += dt*fX/this.mass;
        this.yyVel += dt*fY/this.mass;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }

    public void draw() {
        String path = "images/" + this.imgFileName;
        StdDraw.picture(xxPos,yyPos,path);
    }

              
}