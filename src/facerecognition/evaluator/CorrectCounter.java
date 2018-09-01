package facerecognition.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import facerecognition.utils.Utils;
import facerecognition.javafaces.FaceRec;
import facerecognition.javafaces.FaceRecError;
import facerecognition.javafaces.FaceRecFirst;
import facerecognition.javafaces.MatchResult;

	public class CorrectCounter {
	
	static FaceRec frec;
	static double totalruns=10;
	static double totalacc;
	static List<String>probefiles;//= new ArrayList<String>();
	static String numbersList="12334567890";
	/////THE IDEA OF THIS CLASS IS THAT IT RUNS A TYPE OF FACEREC (EITHER MINE OR THE ORIGINAL)
	/////FOR EACH FACE IN THE "PROBES" FOLDER A NUMBER OF TIMES EQUAL TO TOTALRUNS, INCREMENTING 
	/////THE NUMBER OF EIGENVECTORS EACH TIME AND PRINTS THE ACCURACY EACH TIME. HELPS WITH COMPARING.
	

	public static void main(String[] args){
		frec=new FaceRec();
		try {
			probefiles=frec.parseDirectory("/Users/rjbrockett/Documents/workspace/Javafaces/probes", "png");
		} catch (FaceRecError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getAvgDerivs();//get the amount it changes from one num of vectors to the next at each num. gives slope of learning curve.
		System.out.println(getOverallAccuracy()); //get the accuracy over all vector values
	}
	
	
	public static double getOverallAccuracy(){
		return totalacc/totalruns;
	}

	
	public static void getAvgDerivs(){
		double acc1=-1;
		double acc2=-1;
		
		for(int i=1; i<totalruns; i++){
			if (acc1==-1){
				acc1=getAvgAcc(i);
			}
			else{
				acc1=acc2;
			}
			acc2=getAvgAcc(i+1);
			double deriv=acc2-acc1;
			System.out.println("Vectors: "+ (i)+"-"+(i+1)+"; deriv: "+deriv);
		}
	}
	
	
	public static double getAvgAcc(int numvectors){ //should return the percentage of correct guesses for the num of eigenvecgtors
		int probeslen=probefiles.size();
		//System.out.println(probeslen);
		double numCorrect=0;
		
		for(int i=0; i<probeslen; i++){ //get length of dir from facerec
			File selectedFile= new File(probefiles.get(i));
			//System.out.println(selectedFile.getPath());
			File selectedFolder= new File("/Users/rjbrockett/Documents/workspace/Javafaces/gallery");
			MatchResult choice = frec.processSelections(selectedFile.getPath(),selectedFolder.getPath(), numvectors+"", ""+1+numvectors*0.1);
			String matchName= nameCleaner(choice.getMatchFileName());
			String name=nameCleaner(selectedFile.getPath());
			
			if(name.equals(matchName)){
				numCorrect++;
			}
		}
		
		double vecavg=(1.0*numCorrect)/(1.0*probeslen);
		totalacc+=vecavg;
		System.out.println("Vectors: "+numvectors+"; Accuracy: "+ vecavg);
		return vecavg;		
	}
	
	
	public static String nameCleaner(String name){
		for(int j=name.length()-1;j>=0; j--){
			if(/*(name.charAt(j)=='.')||*/(numbersList.indexOf(name.charAt(j))!=-1)){
				name=name.substring(0,j);
			}
			else if (name.charAt(j)=='/'){
				name=name.substring(j+1,name.length());
				j=-1;
			}
		}
		return name;
	}
	
}

