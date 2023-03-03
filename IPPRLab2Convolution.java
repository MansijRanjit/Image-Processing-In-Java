
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IPPRLab2Convolution 
{

	public static void main(String[] args) {
		BufferedImage image,image1,image2,image3;
		try
		{
			image=ImageIO.read(new File("E:\\7th Semester\\IPPR\\stop.png"));
			System.out.println("reading completed");
		}
		catch(IOException e)
		{
			System.out.println("Error:"+e);
			return;
		}
		int m=image.getWidth();
		int n=image.getHeight();
		int[][]arrayimage=new int[m][n];
		arrayimage=getpixel(image);
		image1=arrayToImage(arrayimage);
		display(image1);
		
		int[][]rescale=new int[m][n];
	
		double[][]kernel=new double[3][3];
	    kernel[0][0]=0;
		kernel[0][1]=1;
		kernel[0][2]=0;
		kernel[1][0]=1;
		kernel[1][1]=-4;
		kernel[1][2]=1;
		kernel[2][0]=0;
		kernel[2][1]=1;
		kernel[2][2]=0;
		
		
		int a=(kernel.length-1)/2;
		int b=(kernel[0].length-1)/2;
		int[][] f_padded=new int[2*a+arrayimage.length][2*b+arrayimage[0].length];
		for(int x=0;x<arrayimage.length;x++)
		{
			for(int y=0;y<arrayimage[0].length;y++)
			{
				f_padded[a+x][b+y]=arrayimage[x][y];
			}
		}
		
		for(int x=0;x<arrayimage.length;x++)
		{
			for(int y=0;y<arrayimage[0].length;y++)
			{
				for(int s=-a;s<=a;s++)
				{
					for(int t=-b;t<=b;t++)
					{
						double v=kernel[s+a][t+b];
						arrayimage[x][y]=(int)(arrayimage[x][y]+v*f_padded[(a+x)-s][(b+y)-t]);
					}
				}
			}
		}
		int ma=maxintensity(arrayimage);
		int mi=minintensity(arrayimage);
		System.out.println("maximum intenity:"+ma);
		System.out.println("minimum intenity:"+mi);
		for(int x=0;x<arrayimage.length;x++)
		{
			for(int y=0;y<arrayimage[0].length;y++)
			{
				rescale[x][y]=(int)(Math.round(arrayimage[x][y]-mi)*((double)255/(double)(ma-mi)));
			
			}
			
		}
		
	
		image2=arrayToImage(rescale);
		display(image2);
		
		
		
	}
	
	
	 static int[][] getpixel(BufferedImage image)
	  {
		 int[][] f= new int[image.getWidth()][image.getHeight()];
		     for(int y=0;y<image.getHeight();y++)
		     {
			    for(int x=0;x<image.getWidth();x++)
			     {
				    Color c =new Color(image.getRGB(x, y));
				    int red=(c.getRed());
				    int blue=(c.getBlue());
				    int green=(c.getGreen());
				
				    f[x][y]=(red+blue+green)/3;
				    }
		}
		return f;
	}
	 static BufferedImage arrayToImage(int[][] f)
		{
			BufferedImage image =new BufferedImage(f.length,f[0].length,BufferedImage.TYPE_BYTE_GRAY);
			
			for(int x=0;x<f.length;x++)
			{
				for(int y=0;y<f[0].length;y++)
				{
					Color newCol =new Color(f[x][y],f[x][y],f[x][y]);
					image.setRGB(x,y,newCol.getRGB());
					
				}
			}
			return image;
				
		}
	 static void display(BufferedImage bi)
		{
			ImageIcon icon =new ImageIcon(bi);
			JFrame frame =new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(300,250);
			JLabel lbl =new JLabel();
			lbl.setIcon(icon);
			frame.add(lbl);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	 
	 static int maxintensity(int[][]f )
	 {
	 	 int max=0;
	 	for(int x=0;x<f.length;x++)
	 	{
	 		for(int y=0;y<f[0].length;y++)
	 		{
	 			if(f[x][y]>max)
	 			{
	 				max=f[x][y];
	 			}
	 		}
	 		}
	 	return max;
	 }
	 static int minintensity(int[][]f)
	 {
	 	int min=255;
	 	for(int x=0;x<f.length;x++)
	 		{
		 		for(int y=0;y<f[0].length;y++)
		 		{
		 			if(f[x][y]<min)
		 			{
		 				min=f[x][y];
		 			}
		 		}
	 		}
	 	return min;
	 } 
}
