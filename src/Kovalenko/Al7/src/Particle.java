
public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	public int count; // number of collisions

	public Particle() {
		rx = StdRandom.uniform(0.0, 1.0);
		ry = StdRandom.uniform(0.0, 1.0);
		vx = StdRandom.uniform(-0.005, 0.005);
		vy = StdRandom.uniform(-0.005, 0.005);
		radius = 0.01;
		mass = 0.5;
	}

	public void move(double dt) {
		rx += vx * dt;
		ry += vy * dt;
	}

	/**
	 * Draws this particle to standard draw.
	 */
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}

	public double timeToHit(Particle that) {
		if (this == that)
			return Double.POSITIVE_INFINITY;
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		if (dvdr > 0)
			return Double.POSITIVE_INFINITY;
		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		if (d < 0)
			return Double.POSITIVE_INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	public double timeToHitVerticalWall() {
		if (vx > 0)
			return (1.0 - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return Double.POSITIVE_INFINITY;
	}

	public double timeToHitHorizontalWall() {
		if (vy > 0)
			return (1.0 - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return Double.POSITIVE_INFINITY;
	}

	public void bounceOff(Particle that) {
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		double dist = this.radius + that.radius;
		double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
		double Jx = J * dx / dist;
		double Jy = J * dy / dist;
		this.vx += Jx / this.mass;
		this.vy += Jy / this.mass;
		that.vx -= Jx / that.mass;
		that.vy -= Jy / that.mass;
		this.count++;
		that.count++;
	}

	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}

	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;
	}
}
