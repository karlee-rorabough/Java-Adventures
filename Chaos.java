package org.frc.teams.team1759.Chaos;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Random;
public class Chaos {
	
	public static class RGB {
		
		private double r_ = 0, g_ =0,  b_ = 0;
		
		public RGB() {
		
			
		}
		public RGB(double red, double green, double blue) {
			r_ = red;
			b_ = blue;
			g_ = green;
		}	
		public double r(){ return this.r_; }
		public double b(){ return this.b_; }
		public double g(){ return this.g_; }
	}
	public static class Point {
		public int x, y;
		public Point() {
			x = 0;
			y = 0;
			
		}
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
			
		}
	}
	public static void main(String[] args) throws IOException {
		int height = 500;
		int width = 500;
		Collection <RGB> obama = Collections.nCopies(width * height, new RGB(128, 0, 255));
		List<RGB> pixels = new ArrayList<RGB>(obama);
		chaosGame(pixels, width, height, 50000);
		createImage(pixels, width, height, "foo.ppm"); 

	}
	public static void chaosGame(List <RGB> pixels, int width, int height, int iterations) {
		Random generator = new Random();
		List<Point> points = new ArrayList <Point>();
		// for(int i = 0; i < 3; ++i) {
			// points.add(new Point(generator.nextInt(width), generator.nextInt(height)));
			// System.out.printf("vertex = %d, %d\n", points.get(i).x, points.get(i).y);
		// }
		points.add(new Point(width / 2, height - 1));
		points.add(new Point(0, 0));
		points.add(new Point(width -1, 0));
		Point initialPosition = points.get(generator.nextInt(points.size()));
		Point position = new Point(initialPosition.x, initialPosition.y);
		for(int i = 0; i < iterations; ++i) {
			// Set the pixel at the current position //
			int offset = width * position.y + position.x;
			pixels.set(offset, new RGB(255, 255, 255));
			
			// Choose the next positition //
			Point next = points.get(generator.nextInt(points.size()));
			position.x = (position.x + next.x) / 2;
			position.y = (position.y + next.y) / 2;
			
		} 
	}
	public static void createImage(List<RGB> pixels, int width, int height, String fileName) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		writer.write("P3\n#" + fileName + "\n");
		writer.write(Integer.valueOf(width).toString() + " ");
		writer.write(Integer.valueOf(height).toString() + "\n");
		writer.write("255\n");
		
		
		for (int column = 0; column < width; ++column) {
			for (int row = 0; row < height; ++row) {
				int offset = width * row + column;
				RGB color = pixels.get(offset);
				writer.write(String.valueOf((int)color.r()) + " ");
				writer.write(String.valueOf((int)color.g()) + " ");
				writer.write(String.valueOf((int)color.b()) + "\n");
			}	
		}
		writer.close();
	}	
}
