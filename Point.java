
public class Point {
    public float[] coord;
    public float[] ncoord;
    public int c;
    public String text;
    public Point(int dim, float... coord) {
        this.coord = new float[coord.length];
        for(int i = 0; i < coord.length; i++) {
            this.coord[i] = coord[i];
        }
        ncoord = new float[dim];
    }

    private static float distSquared(float[] p1, float[] p2) {
        double sum = 0;
        for(int i = 0; i < p1.length; i++) {
            double val = p1[i] - p2[i];
            sum += val*val;
        }
        return (float) Math.sqrt(sum);
    }
    private static float dist(float[] p1, float[] p2) {
        return (float) Math.sqrt(distSquared(p1, p2));
    }
    public float[] gradient(Point j) {
        //2d: return (float) ((Math.abs(nx - j.nx) - Math.hypot(x - j.x, y - j.y)) * Math.signum(nx - j.nx));
        float low = dist(ncoord, j.ncoord); //separation
        float high = dist(coord, j.coord); //length
        float[] grad = new float[ncoord.length];
        for(int i = 0; i < grad.length; i++) {
            float dJdI = (ncoord[i] - j.ncoord[i]) / low;
            grad[i] = (low - high) * dJdI;
        }
        return grad;
    }
}
