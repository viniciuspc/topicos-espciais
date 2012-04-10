package javacv;

import processadoresImagem.Visao;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;

import java.awt.image.BufferedImage;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.OpenCVFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Camera {
	
	public static void main(String args[]) throws Exception, java.lang.Exception{
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        
        

        IplImage frame = grabber.grab();
        //OpenCVFrameRecorder recorder = new OpenCVFrameRecorder("teste.avi", frame.width(), frame.height());
        //recorder.setFrameRate(CV_CAP_PROP_FPS);
       	//recorder.start();
       	
        
        IplImage imageTecnica1 = null;
        IplImage imageTecnica2 = grabber.grab();
        IplImage imageTecnica2Aux = null;
        IplImage prevImage = null;
        IplImage diff = null;
        Visao processadorImagem = new Visao();
        
        int[][] tecnica1Matriz;
        BufferedImage tecnica1Buffer;
        
        int[][] tecnica2Matriz;
        BufferedImage tecnica2Buffer;
        
        int[][] matrizAnterior = null;
        
        int[][] matrizDiff = null;
        
        int[][][] peleMatriz;
        BufferedImage peleBuffer;

        CanvasFrame canvasFrameTecnica1 = new CanvasFrame("Binariza??o - Limiar 200");
        canvasFrameTecnica1.setCanvasSize(frame.width(), frame.height());
        
        
        CanvasFrame canvasFrameTecnica2 = new CanvasFrame("Original");
        canvasFrameTecnica2.setCanvasSize(frame.width(), frame.height());

        CvMemStorage storage = CvMemStorage.create();
        while (canvasFrameTecnica1.isVisible() && (frame = grabber.grab()) != null) {
        	imageTecnica1 = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        	//imageTecnica2 = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        	imageTecnica2Aux = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        	
        	tecnica1Buffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	tecnica2Buffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	peleBuffer = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_BGR);
        	//cvCvtColor(frame, imageSobel, CV_RGB2GRAY);
        	
        	peleMatriz = processadorImagem.lerBufferRgb(frame.getBufferedImage());
        	tecnica1Matriz = processadorImagem.lerBuffer(frame.getBufferedImage());
        	tecnica2Matriz = processadorImagem.lerBuffer(imageTecnica2.getBufferedImage());
        	processadorImagem.binariza(tecnica1Matriz, 200);
        	
        	/*
        	if(matrizAnterior == null){
        		matrizAnterior = processadorImagem.copiar_Matriz(tecnica1Matriz);
        	} else {
        		matrizDiff = processadorImagem.subtrairMatriz(matrizAnterior, tecnica1Matriz);
        		matrizAnterior = processadorImagem.copiar_Matriz(tecnica1Matriz);
        	}
        	*/
        	
        	
        	
        	processadorImagem.lerMatriz(tecnica1Matriz, tecnica1Buffer);
        	processadorImagem.lerMatriz(tecnica2Matriz, tecnica2Buffer);
        	
        	
        	/*
        	if(matrizDiff != null){
	        	processadorImagem.lerMatriz(matrizDiff, tecnica2Buffer);
	        	
	        	frame = IplImage.createFrom(tecnica2Buffer);
	        	//recorder.record(frame);
	        	
	        	cvCvtColor(frame, imageTecnica1, CV_RGB2GRAY);
	        	canvasFrameTecnica1.showImage(frame);
        	}*/
        	
        	frame = IplImage.createFrom(tecnica1Buffer);
        	
        	cvCvtColor(frame, imageTecnica1, CV_RGB2GRAY);
        	canvasFrameTecnica1.showImage(imageTecnica1);
        	
        	//imageTecnica2Aux = IplImage.createFrom(tecnica2Buffer);
        	//cvCvtColor(imageTecnica2Aux, imageTecnica2, CV_RGB2GRAY);
        	canvasFrameTecnica2.showImage(imageTecnica2);
        }
        
        canvasFrameTecnica1.dispose();
        canvasFrameTecnica2.dispose();
        //recorder.stop();
        grabber.stop();
        	
	}

}
