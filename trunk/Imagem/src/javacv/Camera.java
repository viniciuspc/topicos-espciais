package javacv;

import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;

import java.awt.image.BufferedImage;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Camera {
	
	public static void main(String args[]) throws Exception{
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();

        IplImage frame = grabber.grab();
        IplImage imageSobel = null;
        IplImage imagePrewitt = null;
        IplImage prevImage = null;
        IplImage diff = null;
        Visao processadorImagem = new Visao();
        
        int[][] sobelMatriz;
        
        BufferedImage sobelBuffer;
        
        int[][][] prewittMatriz;
        BufferedImage prewittBuffer;
        
        int[][][] peleMatriz;
        BufferedImage peleBuffer;

        CanvasFrame canvasFrameSobel = new CanvasFrame("Sobel");
        canvasFrameSobel.setCanvasSize(frame.width(), frame.height());
        
        
        //CanvasFrame canvasFramePrewitt = new CanvasFrame("Prewitt");
        //canvasFramePrewitt.setCanvasSize(frame.width(), frame.height());

        CvMemStorage storage = CvMemStorage.create();
        while (canvasFrameSobel.isVisible() && (frame = grabber.grab()) != null) {
        	imageSobel = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        	imagePrewitt = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        	
        	sobelBuffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	prewittBuffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	peleBuffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	//cvCvtColor(frame, imageSobel, CV_RGB2GRAY);
        	//cvCvtColor(frame, imagePrewitt, CV_RGB2GRAY);
        	peleMatriz = processadorImagem.lerBufferRgb(frame.getBufferedImage());
        	sobelMatriz = processadorImagem.lerBuffer(frame.getBufferedImage());
        	prewittMatriz = processadorImagem.lerBufferRgb(imagePrewitt.getBufferedImage());
        	int[][] resultado  = processadorImagem.fpa_Sobel(sobelMatriz);
        	int[][][] resultadoPele = processadorImagem.pele(peleMatriz);
        	
        	processadorImagem.lerMatriz(resultado, sobelBuffer);
        	processadorImagem.lerMatrizRgb(prewittMatriz, prewittBuffer);
        	processadorImagem.lerMatrizRgb(resultadoPele, peleBuffer);
        	
        	frame = IplImage.createFrom(peleBuffer);
        	
        	cvCvtColor(frame, imageSobel, CV_RGB2GRAY);
        	canvasFrameSobel.showImage(imageSobel);
        	//canvasFramePrewitt.showImage(imagePrewitt);
        }
        
        grabber.stop();
        canvasFrameSobel.dispose();
        //canvasFramePrewitt.dispose();

	
	}

}
